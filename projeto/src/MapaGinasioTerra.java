public class MapaGinasioTerra {
    private char[][] mapa = {
        "########################".toCharArray(),
        "#   *   O   .    *    #".toCharArray(),
        "# O O   ***   O    O  #".toCharArray(),
        "#   .     *      .   #".toCharArray(),
        "#****   OOOO   ****  #".toCharArray(),
        "#   O   . L .    O   #".toCharArray(),
        "#  ***    .     ***  #".toCharArray(),
        "#  O   O      O    E #".toCharArray(),
        "########################".toCharArray()
    };

    private int x = 1, y = 7; // começa perto do E
    private Jogador jogador;

    public MapaGinasioTerra(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("⛰️ Bem-vindo ao Ginásio de Terra!");
        System.out.println("⚠️ Rochas bloqueiam e o chão pode desmoronar!");

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

        // 🪨 Rocha grande bloqueia
        if (destino == 'O') {
            System.out.println("🪨 Uma rocha enorme bloqueia o caminho!");
            return;
        }

        // 🕳️ Buraco no chão (teleporta para entrada)
        if (destino == '.') {
            System.out.println("💥 O chão cedeu! Você caiu e voltou ao início!");
            x = 1;
            y = 7;
            return;
        }

        // 🏆 Líder do ginásio
        if (destino == 'L') {
            System.out.println("⛰️ Líder Gaia: Mostre que sua força é inabalável!");

            // ✅ Time da líder Gaia (Tipo Terra)
            Javamon[] timeTerra = {
                new Javamon("Sandarm", "Terra", 70, 18, 12),
                new Javamon("Mudhorn", "Terra", 85, 20, 14),
                new Javamon("Golemrock", "Terra", 100, 25, 18) // Boss
            };

            for (Javamon inimigo : timeTerra) {
                System.out.println("⛰️ Gaia enviou " + inimigo.getNome() + "!");
                Batalha.lutar(jogador, inimigo); // seu sistema de batalha

                if (inimigo.estaVivo()) {
                    System.out.println("💀 Você foi esmagado pela força da Terra!");
                    new MapaLiga(jogador).entrar();
                    return;
                }
            }

            System.out.println("🏆 Você derrotou a Líder Gaia do Ginásio Terra!");
            jogador.adicionarVitoriaGym("Terra");
            new MapaLiga(jogador).entrar();
            return;
        }

        // 🚪 Saída para mapa principal
        if (destino == 'E') {
            System.out.println("➡️ Você saiu do Ginásio de Terra!");
            new MapaLiga(jogador).entrar();
            return;
        }

        x = nx;
        y = ny;
    }
}
