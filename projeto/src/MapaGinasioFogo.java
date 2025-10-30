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

        // ✅ paredes e obstáculos (lava '^' e fogo '*')
        if (mapa[ny][nx] == '#' || mapa[ny][nx] == '^' || mapa[ny][nx] == '*') return;

        char destino = mapa[ny][nx];

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

    // ✅ sistema novo de batalha integrado com seu mapa
    private void iniciarBatalha() {
        System.out.println("\n🔥 Pyros: Prepare-se para queimar!");

        // time do líder Pyros
        Javamon[] time = {
            new Javamon("Flareon", "Fogo", 60),
            new Javamon("Magmar",  "Fogo", 70),
            new Javamon("Arcanine","Fogo", 85)
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
