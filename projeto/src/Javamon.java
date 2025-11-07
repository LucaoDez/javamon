import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Javamon{
    protected String nome;
    protected String tipagem;
    protected int hpMAX, hpATUAL, atk, def, spd, lvl, exp;
    protected List<Ataque> ataques = new ArrayList<>();

    public Javamon(String nome, String tipagem, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        this.nome = nome;
        this.tipagem = tipagem;
        this.hpMAX = hpMAX;
        this.hpATUAL = hpATUAL;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.lvl = lvl;
        this.exp = exp;
    }

    public void mostrarStatus() {
        System.out.println("=== " + nome + " ===");
        System.out.println("Tipo: " + tipagem);
        System.out.println("Nível: " + lvl);
        System.out.println("HP: " + hpATUAL + "/" + hpMAX);
        System.out.println("Ataque: " + atk);
        System.out.println("Defesa: " + def);
        System.out.println("Velocidade: " + spd);
        System.out.println();
    }

    public void ganharExperiencia(int xp) {
        exp += xp;
        if (exp >= lvl * 10) { 
            lvl++;
            exp = 0;
            hpMAX += 10;
            atk += 2;
            def += 2;
            spd += 1;
            hpATUAL = hpMAX;
            System.out.println(nome + " subiu para o nível " + lvl + "!");
        }
    }

    public void mostrarAtaques() {
        if (ataques == null || ataques.isEmpty()) {
            System.out.println(nome + " não tem ataques cadastrados.");
            return;
        }

        System.out.println("Ataques de " + nome + ":");
        for (int i = 0; i < ataques.size(); i++) {
            Ataque a = ataques.get(i);
            System.out.printf("%d) %s - Poder: %d - Tipo: %s - PP: %d/%d%n",
                          i + 1, a.getNome(), a.getPoder(), a.getTipo(), a.getPpATUAL(), a.getPpMAX());
        }   
    }

    public int escolherAtaque(Scanner sc) {
        if (ataques == null || ataques.isEmpty()) {
            System.out.println(nome + " não tem ataques.");
            return -1;
        }

        while (true) {
            mostrarAtaques();
            System.out.print("Escolha o número do ataque: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine(); // descarta entrada inválida
                continue;
            }

            int escolha = sc.nextInt();
            sc.nextLine();

            if (escolha < 1 || escolha > ataques.size()) {
                System.out.println("Escolha inválida. Informe um número entre 1 e " + ataques.size());
                continue;
            }

            Ataque ataqueEscolhido = ataques.get(escolha - 1);
            if (ataqueEscolhido.getPpATUAL() <= 0) {
                System.out.println("Esse ataque não tem PP restante. Escolha outro.");
                continue;
            }
        return escolha - 1; 
        }
    }

    public static double calcularMultiplicador(String tipoAtacante, String tipoDefensor) {
        tipoAtacante = tipoAtacante.toLowerCase();
        tipoDefensor = tipoDefensor.toLowerCase();

        // Super eficaz
        if ((tipoAtacante.equals("água") && tipoDefensor.equals("fogo")) ||
            (tipoAtacante.equals("fogo") && tipoDefensor.equals("ar")) ||
            (tipoAtacante.equals("terra") && tipoDefensor.equals("água")) ||
            (tipoAtacante.equals("ar") && tipoDefensor.equals("terra")))
            return 2.0;

        // Pouco eficaz
        if ((tipoAtacante.equals("fogo") && tipoDefensor.equals("água")) ||
            (tipoAtacante.equals("ar") && tipoDefensor.equals("fogo")) ||
            (tipoAtacante.equals("água") && tipoDefensor.equals("terra")) ||
            (tipoAtacante.equals("terra") && tipoDefensor.equals("ar")))
            return 0.5;

        // Neutro
        return 1.0;
    }


    public void atacar(Javamon defensor, int indiceAtaque) {
        if (indiceAtaque < 0 || indiceAtaque >= ataques.size()) {
            System.out.println("Ataque inválido!");
            return;
        }

        Ataque ataque = ataques.get(indiceAtaque);

        if (ataque.getPpATUAL() <= 0) {
            System.out.println(ataque.getNome() + " está sem PP!");
            return;
        }

        ataque.reduzirPp(); // reduz PP em 1

        double multiplicador = calcularMultiplicador(ataque.getTipo(), defensor.getTipagem());

        int danoBase = (ataque.getPoder() + this.atk) - defensor.getDef();
        int danoFinal = (int) (danoBase * multiplicador);
        if (danoFinal < 0) danoFinal = 0;

        defensor.levarDano(danoFinal);

        System.out.println(this.nome + " usou " + ataque.getNome() + "!");
        if (multiplicador == 2.0) System.out.println("Foi super eficaz!");
        else if (multiplicador == 0.5) System.out.println("Foi pouco eficaz...");
        System.out.println(defensor.getNome() + " recebeu " + danoFinal + " de dano!\n");
    }

    public void levarDano(int dano) {
        this.hpATUAL -= dano;
        if (this.hpATUAL < 0) this.hpATUAL = 0;
    }

    public void curar(int valor) {
        this.hpATUAL += valor;
        if (this.hpATUAL > hpMAX) this.hpATUAL = hpMAX;
    }

    public boolean estaVivo() {
    // retorna true se tiver HP > 0
        return this.getHpATUAL() > 0;
    }

    public void reviver() {
        // se já estiver vivo, não faz nada
        try {
            // tenta delegar para um método existente (por ex. estaVivo/getHpATUAL/getHpMAX)
            java.lang.reflect.Method estaVivoM = this.getClass().getMethod("estaVivo");
            Object alive = estaVivoM.invoke(this);
            if (alive instanceof Boolean && (Boolean) alive) return;
        } catch (NoSuchMethodException ignore) {
            // continua — pode não existir estaVivo()
        } catch (Throwable ignore) {}

        // obtém hpMax através de possíveis getters
        int hpMax = 0;
        String[] gettersMax = {"getHpMAX", "getHpMax", "getHpMaximo", "getHpMAXIMO"};
        for (String g : gettersMax) {
            try {
                java.lang.reflect.Method m = this.getClass().getMethod(g);
                Object r = m.invoke(this);
                if (r instanceof Number) { hpMax = ((Number) r).intValue(); break; }
            } catch (Throwable ignored) {}
        }

        // se não conseguiu via método, tenta campo direto
        if (hpMax <= 0) {
            try {
                java.lang.reflect.Field f = this.getClass().getDeclaredField("hpMAX");
                f.setAccessible(true);
                Object v = f.get(this);
                if (v instanceof Number) hpMax = ((Number) v).intValue();
            } catch (Throwable ignored) {}
        }

        if (hpMax <= 0) hpMax = 1; // fallback seguro

        int novoHp = Math.max(1, hpMax / 2);

        // tenta setar via setters conhecidos
        String[] settersAtual = {"setHpATUAL", "setHpAtual", "setHp"};
        boolean aplicado = false;
        for (String s : settersAtual) {
            try {
                java.lang.reflect.Method ms = this.getClass().getMethod(s, int.class);
                ms.invoke(this, novoHp);
                aplicado = true;
                break;
            } catch (Throwable ignored) {}
        }

        // limpa flag de nocaute/desmaiado se existir (ex.: "nocauteado", "desmaiado", "fainted")
        String[] flags = {"nocauteado", "desmaiado", "fainted", "isFainted"};
        for (String fName : flags) {
            try {
                java.lang.reflect.Field f = this.getClass().getDeclaredField(fName);
                f.setAccessible(true);
                if (f.getType() == boolean.class) { f.setBoolean(this, false); break; }
                if (f.getType() == Boolean.class) { f.set(this, Boolean.FALSE); break; }
            } catch (Throwable ignored) {}
        }
    }

    public abstract void inicializarAtaques();

    public String getNome() { return nome; }
    public String getTipagem() { return tipagem; }
    public int getHpMAX() { return hpMAX; }
    public int getHpATUAL() { return hpATUAL; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpd() { return spd; }
    public int getNivel() { return lvl; }
    public List<Ataque> getAtaques() { return ataques; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }

    public void setHpATUAL(int hpATUAL) { this.hpATUAL = hpATUAL; }
    public void setLvl(int lvl) { this.lvl = lvl; }
}