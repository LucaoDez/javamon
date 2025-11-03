public class MapaGinasioAr {

    private char[][] mapa = {
        "#######################".toCharArray(),
        "#         o   o      #".toCharArray(),
        "#  I   ~~~~~~~   I   #".toCharArray(),
        "#   o     o      o   #".toCharArray(),
        "#~~~~~~       ~~~~~~ #".toCharArray(),
        "#   I   o   o   I    #".toCharArray(),
        "#     o   L    o     #".toCharArray(),
        "#~~~~~~       ~~~~~~ #".toCharArray(),
        "#   o    o     o   E #".toCharArray(),
        "#######################".toCharArray()
    };

    private int x = 1, y = 8;
    private Jogador jogador;
    private boolean ventoAtivo = true;

    public MapaGinasioAr(Jogador jogador) {
        this.jogador = jogador;
        System.out.println("🌬️ Bem-vindo ao Ginásio do AR!");
        System.out.println("⚠️ Correntes de vento podem te mover!");
    }

    public void mostrar() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == y && j == x) System.out.print("@");
                else System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    // Retorna true se saiu do ginásio
    public boolean mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return false;

        if (!dentro(nx, ny)) return false;
        if (mapa[ny][nx] == '#') return false;

        char destino = mapa[ny][nx];

        // vento empurra o jogador
        if (destino == '~' && ventoAtivo) {
            System.out.println("🌪️ O vento te empurra!");
            ny--;
            if (!dentro(nx, ny)) { resetPosicao(); return false; }
            destino = mapa[ny][nx];
            ventoAtivo = false;
        }

        // reseta vento ao sair
        if (destino != '~') ventoAtivo = true;

        // Líder Aeris
        if (destino == 'L') {
            System.out.println("🕊️ Aeris: Só quem domina os céus pode vencer aqui!");
            iniciarBatalha();
            return false;
        }

        // Saída do ginásio
        if (destino == 'E') {
            System.out.println("➡️ Você saiu do Ginásio do Ar!");
            return true;
        }

        x = nx;
        y = ny;
        return false;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void resetPosicao() {
        x = 1;
        y = 8;
    }

    private void iniciarBatalha() {
        Javamon[] time = {
            new Ventrix("Pidgeotto", 65, 65, 18, 12, 16, 5, 0),
            new Ventrix("Noivern",   80, 80, 22, 14, 20, 7, 0),
            new Ventrix("Rayquaza", 120,120, 30, 20, 24,10, 0)
        };

        for (Javamon inimigo : time) {
            System.out.println("\n🕊️ Aeris enviou " + inimigo.getNome() + "!");
            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("\n💀 Você foi arremessado pelos ventos!");
                return;
            }
        }

        System.out.println("\n🏆 Você conquistou a Insígnia dos Ventos!");
        jogador.addVitoriaGym();
    }
}