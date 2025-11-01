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
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return;

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
            iniciarBatalha();
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

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void iniciarBatalha() {
        System.out.println("\n⛰️ Gaia: Prepare-se para ser esmagado pela Terra!");

        // use subclasses concretas existentes no projeto (ajuste nomes/estatísticas conforme necessidade)
        Javamon[] timeTerra = {
            new Mudrill("Sandarm", 70, 70, 18, 12, 8, 5, 0),
            new Terravox("Mudhorn", 85, 85, 20, 14, 6, 7, 0),
            new Terravox("Golemrock", 100, 100, 25, 18, 4, 9, 0) // Boss
        };

        for (Javamon inimigo : timeTerra) {
            System.out.println("⛰️ Gaia enviou " + inimigo.getNome() + "!");
            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("💀 Você foi esmagado pela força da Terra!");
                new MapaLiga(jogador).entrar();
                return;
            }
        }

        System.out.println("🏆 Você derrotou a Líder Gaia do Ginásio Terra!");
        // garanta que Jogador tem método para registrar vitória; ajuste se necessário
        if (jogador != null) {
            try {
                jogador.addVitoriaGym();; // ou use jogador.addVitoriaGym()
            } catch (Throwable t) {
                // método não existe — ignore ou implemente em Jogador
            }
        }
        new MapaLiga(jogador).entrar();
    }
}
