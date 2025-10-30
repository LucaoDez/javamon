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

    private void mostrar() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == y && j == x) System.out.print("@");
                else System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    private void mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        if (d == 's') ny++;
        if (d == 'a') nx--;
        if (d == 'd') nx++;

        if (mapa[ny][nx] == '#') return; // parede

        char destino = mapa[ny][nx];

        // 🌪️ vento empurra o jogador
        if (destino == '~' && ventoAtivo) {
            System.out.println("🌪️ O vento te empurra!");
            ny--;
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

    // ✅ batalha igual ao estilo dos outros ginásios
    private void iniciarBatalha() {
        Javamon[] time = {
            new Javamon("Pidgeotto", "Ar", 65),
            new Javamon("Noivern",   "Ar", 80),
            new Javamon("Rayquaza",  "Ar", 120) // chefe forte
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
