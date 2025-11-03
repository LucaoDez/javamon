public class MapaGinasioFogo {

    private char[][] mapa = {
        "###############".toCharArray(),
        "#^^  *L*  ^^###".toCharArray(),
        "#^. .^. .^. .##".toCharArray(),
        "# .^. .^. .^. #".toCharArray(),
        "# ^. .^. .^. ^#".toCharArray(),
        "# .^. .^. .^. #".toCharArray(),
        "#^^^. .^. .^^^#".toCharArray(),
        "#      E      #".toCharArray(),
        "###############".toCharArray()
    };

    // posição inicial do jogador
    private int x = 7, y = 7;

    private Jogador jogador;

    public MapaGinasioFogo(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🔥 Você entrou no Ginásio do Fogo!");
        System.out.println("🔥 O calor é insuportável... avance com cuidado!");

        java.util.Scanner in = new java.util.Scanner(System.in);
        while (true) {
            mostrar();
            System.out.print("> ");
            mover(in.next().charAt(0));
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
        else return;

        if (!dentro(nx, ny)) return;

        char destino = mapa[ny][nx];

        // ✅ paredes e obstáculos (lava '^' e fogo '*')
        if (mapa[ny][nx] == '#' || mapa[ny][nx] == '^' || mapa[ny][nx] == '*') return;

        // Líder Pyros
        if (destino == 'L') {
            System.out.println("\n🔥 Pyros: Mostre seu poder, treinador!");
            iniciarBatalha();
            return;
        }

        // Saída
        if (destino == 'E') {
            System.out.println("\n↩️ Você deixou o Ginásio do Fogo");
            new MapaLiga(jogador).entrar();
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    // ✅ sistema novo de batalha integrado com seu mapa
    private void iniciarBatalha() {
        System.out.println("\n🔥 Pyros: Prepare-se para queimar!");

        // time do líder Pyros
        Javamon[] time = {
            new Feuermon("Flareon", 60, 60, 20, 12, 14, 5, 0),
            new Feuermon("Magmar",  70, 70, 22, 13, 12, 7, 0),
            new Feuermon("Arcanine",85, 85, 28, 18, 16, 9, 0)
        };

        for (Javamon inimigo : time) {
            System.out.println("\n🔥 Pyros enviou " + inimigo.getNome());

            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("\n💀 Você foi derrotado!");
                new MapaLiga(jogador).entrar();
                return;
            }
        }

        System.out.println("\n🏆 Você derrotou o Líder Pyros!");
        jogador.addVitoriaGym(); // ✅ registra vitória
        new MapaLiga(jogador).entrar();
    }
}
