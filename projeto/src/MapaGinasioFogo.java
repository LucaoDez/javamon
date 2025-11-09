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
        System.out.println("\n🔥 Você entrou no Ginásio do Fogo!");
        System.out.println("🔥 O calor é insuportável... avance com cuidado!");
    }

    public void entrar() {
        System.out.println("\n🔥 Você entrou no Ginásio do Fogo!");
        // mostra o mapa do ginásio imediatamente
        mostrar();
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

        char destino = mapa[ny][nx];

        // paredes e obstáculos
        if (mapa[ny][nx] == '#' || mapa[ny][nx] == '^' || mapa[ny][nx] == '*') return false;

        // Líder Pyros
        if (destino == 'L') {
            System.out.println("\n🔥 Pyros: Mostre seu poder, treinador!");
            iniciarBatalha();
            return false; // Continua no ginásio após batalha
        }

        // Saída
        if (destino == 'E') {
            System.out.println("\n↩️ Você deixou o Ginásio do Fogo");
            return true; // Sai do ginásio
        }

        x = nx;
        y = ny;
        return false;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }



    private void iniciarBatalha() {
        System.out.println("\n🔥 Pyros: Prepare-se para queimar!");

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
                return;
            }
        }

        System.out.println("\n🏆 Você derrotou o Líder Pyros!");
        jogador.addVitoriaGym();
    }
}