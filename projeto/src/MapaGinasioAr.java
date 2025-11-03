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

    private int x = 1, y = 8; // posição inicial próxima do E
    private Jogador jogador;
    private boolean ventoAtivo = true; // evita empurrar infinito

    public MapaGinasioAr(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("🌬️ Bem-vindo ao Ginásio do AR!");
        System.out.println("⚠️ Correntes de vento podem te mover!");
        System.out.println("Use W A S D para andar.");

        java.util.Scanner in = new java.util.Scanner(System.in);

        while (true) {
            mostrar();
            System.out.print("> ");
            char m = in.next().charAt(0);
            mover(m);
        }
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

    public void mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;

        if (mapa[ny][nx] == '#') return; // parede

        char destino = mapa[ny][nx];

        // 🌪️ vento empurra o jogador
        if (destino == '~' && ventoAtivo) {
            System.out.println("🌪️ O vento te empurra!");
            ny--; // empurra para cima (exemplo)
            // revalida após empurrão
            if (!dentro(nx, ny)) { resetPosicao(); return; }
            destino = mapa[ny][nx];
            ventoAtivo = false;
        }

        // reseta vento ao sair
        if (destino != '~') ventoAtivo = true;

        // 🏆 Líder Aeris
        if (destino == 'L') {
            System.out.println("🕊️ Aeris: Só quem domina os céus pode vencer aqui!");
            iniciarBatalha();
            return;
        }

        // 🚪 Saída do ginásio
        if (destino == 'E') {
            System.out.println("➡️ Você saiu do Ginásio do Ar!");
            new MapaLiga(jogador).entrar();
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void resetPosicao() {
        // voltar ao spawn ou posição segura
        x = 1; y = 8;
    }

    // ✅ batalha igual ao estilo dos outros ginásios
    private void iniciarBatalha() {
        Javamon[] time = {
            new Ventrix("Pidgeotto", 65, 65, 18, 12, 16, 5, 0),
            new Ventrix("Noivern",   80, 80, 22, 14, 20, 7, 0),
            new Ventrix("Rayquaza", 120,120, 30, 20, 24,10, 0) // chefe forte
        };

        for (Javamon inimigo : time) {
            System.out.println("\n🕊️ Aeris enviou " + inimigo.getNome() + "!");

            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("\n💀 Você foi arremessado pelos ventos!");
                new MapaLiga(jogador).entrar();
                return;
            }
        }

        System.out.println("\n🏆 Você conquistou a Insígnia dos Ventos!");
        jogador.addVitoriaGym();
        new MapaLiga(jogador).entrar();
    }
}
