public class MapaLiga {
    private char[][] mapa = {
        "#########################".toCharArray(),
        "#       F       T       #".toCharArray(),
        "#########       #########".toCharArray(),
        "#                      C#".toCharArray(),
        "#########       ##########".toCharArray(),
        "#       A       R       #".toCharArray(),
        "#########################".toCharArray(),
    };

    private int x = 1, y = 2;
    private Jogador jogador;

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("🔥 Bem-vindo à Ilha da Liga Javamon!");

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

        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        if (destino == 'F') new MapaGinasioFogo(jogador).entrar();
        if (destino == 'A') new MapaGinasioAgua(jogador).entrar();
        if (destino == 'T') new MapaGinasioTerra(jogador).entrar();
        if (destino == 'R') new MapaGinasioAr(jogador).entrar();

        // ⚠️ Só entra no C se já venceu todos!
        if (destino == 'C') {
            if (jogador.getVitoriasGym() == 4) {
                new MapaCampeao(jogador).entrar();
            } else {
                System.out.println("❌ Você precisa derrotar os 4 líderes primeiro!");
            }
            return;
        }

        x = nx;
        y = ny;
    }
}
