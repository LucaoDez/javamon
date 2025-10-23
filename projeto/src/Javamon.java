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
            System.out.printf("%d) %s - Poder: %d - Tipo: %s - PP: %d%n",
                          i + 1, a.getNome(), a.getPoder(), a.getTipo(), a.getPp());
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
            if (ataqueEscolhido.getPp() <= 0) {
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

        if (ataque.getPp() <= 0) {
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
        return hpATUAL > 0;
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

    public void setHpATUAL(int hpATUAL) { this.hpATUAL = hpATUAL; }
    public void setLvl(int lvl) { this.lvl = lvl; }
}