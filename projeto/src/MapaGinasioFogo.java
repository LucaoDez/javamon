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

        // ✅ Áreas bloqueadas
        if (mapa[ny][nx] == '#' || mapa[ny][nx] == '^' || mapa[ny][nx] == '*') return;

        char destino = mapa[ny][nx];

        if (destino == 'L') {
            System.out.println("\n🔥 Pyros: Mostre seu poder, treinador!");
            iniciarBatalha();
            return;
        }

        if (destino == 'E') {
            System.out.println("\n↩️ Você deixou o Ginásio do Fogo");
            new MapaLiga(jogador).entrar();
            return;
        }

        x = nx;
        y = ny;
    }

    private void iniciarBatalha() {
        boolean venceu = jogador.batalhaContra("Pyros", "Fogo", 30);

        if (venceu) {
            System.out.println("\n🏆 Você derrotou o Líder Pyros!");
            jogador.adicionarVitoriaGym("Fogo");
            new MapaLiga(jogador).entrar();
        } else {
            System.out.println("\n💀 Você foi derrotado!");
            new MapaLiga(jogador).entrar();
        }
    }
}
