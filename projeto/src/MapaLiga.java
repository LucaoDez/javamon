public class MapaLiga {

    // 🗺️ Mapa da Ilha da Liga Javamon
    // '#': parede
    // 'F', 'T', 'A', 'R': portas dos ginásios
    // 'C': Campeão (só acessa após 4 vitórias)
    // 'S': Saída do Hall (volta para a cidade)
    private char[][] mapa = {
        "#########################".toCharArray(),
        "#       F       T       #".toCharArray(),
        "#########       #########".toCharArray(),
        "#                      C#".toCharArray(),
        "#########       #########".toCharArray(),
        "#       A   S   R       #".toCharArray(), // ✅ SAÍDA adicionada no centro
        "#########################".toCharArray(),
    };

    // 🧍 Posição inicial do jogador (sobre o 'S')
    private int x = 12, y = 5;

    private Jogador jogador;

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🔥 Bem-vindo à Ilha da Liga Javamon!");

        java.util.Scanner in = new java.util.Scanner(System.in);

        while (true) {
            mostrar();
            System.out.print("Movimente-se (WASD): ");
            char m = in.next().charAt(0);
            mover(m);
        }
    }

    // 👀 Exibir o mapa com o jogador como '@'
    private void mostrar() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == y && j == x) System.out.print("@");
                else System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    // ➡️ Movimentação e lógica das portas
    private void mover(char d) {
        int nx = x, ny = y;
        if (d == 'w') ny--;
        if (d == 's') ny++;
        if (d == 'a') nx--;
        if (d == 'd') nx++;

        // Se for parede, não anda
        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        // 🌋 Ginásio do Fogo
        if (destino == 'F') {
            new MapaGinasioFogo(jogador).entrar();
            return;
        }

        // 🌊 Ginásio da Água
        if (destino == 'A') {
            new MapaGinasioAgua(jogador).entrar();
            return;
        }

        // 🌱 Ginásio da Terra
        if (destino == 'T') {
            new MapaGinasioTerra(jogador).entrar();
            return;
        }

        // 🌪️ Ginásio do Ar
        if (destino == 'R') {
            new MapaGinasioAr(jogador).entrar();
            return;
        }

        // 🏆 Campeão — só após 4 vitórias
        if (destino == 'C') {
            if (jogador.getVitoriasGym() == 4) {
                new MapaCampeao(jogador).entrar();
            } else {
                System.out.println("\n❌ Você precisa derrotar os 4 líderes primeiro!");
            }
            return;
        }

        // 🚪 SAÍDA do Hall
        if (destino == 'S') {
            System.out.println("\n↩️ Você saiu do Hall!");
            new Mapa (jogador).entrar(); // ✅ volta para a cidade
            return;
        }

        // ✅ Se não for porta, apenas anda
        x = nx;
        y = ny;
    }
}