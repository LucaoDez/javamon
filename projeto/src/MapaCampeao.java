public class MapaCampeao {

    private char[][] mapa = {
        "#############################".toCharArray(),
        "#************OO*************#".toCharArray(),
        "#************OO*************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#***********====************#".toCharArray(),
        "#************OO*************#".toCharArray(),
        "#************OO*************#".toCharArray(),
        "#*************C*************#".toCharArray(),
        "#**************************E#".toCharArray(),
        "#############################".toCharArray()
    };

    private int x = 25, y = 15;
    private Jogador jogador;
    private final int spawnX = 25, spawnY = 15;

    public MapaCampeao(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🏛️ Você entrou no Salão do Campeão!");
        System.out.println("👑 O destino do mundo Javamon está em suas mãos.");
        System.out.println("Avance até o Campeão!");

        java.util.Scanner in = new java.util.Scanner(System.in);

        while (true) {
            mostrar();
            System.out.print("> ");
            String line = in.nextLine();
            if (line == null || line.trim().isEmpty()) continue;
            char comando = line.trim().toLowerCase().charAt(0);
            mover(comando);
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

        if (!dentro(nx, ny)) return;
        char destino = mapa[ny][nx];

        if (mapa[ny][nx] == '#') return;


        if (destino == 'O') {
            System.out.println("🗿 As estátuas bloqueiam o caminho!");
            return;
        }

        if (destino == 'C') {
            System.out.println("\n👑 CAMPEÃO FINAL");
            System.out.println("⚔️ Campeão Eclipse: \"Seu caminho termina aqui!\"");
            enfrentarCampeao();
            return;
        }

        if (destino == 'E') {
            System.out.println("➡️ Você voltou ao mapa da liga!");
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }
    private void enfrentarCampeao() {
        // crie instâncias concretas (ajuste stats conforme suas classes)
        Javamon[] timeEclipse = {
            new Feuermon("Titanflare", 320, 320, 140, 120, 100, 50, 0),
            new Aquaril("AquaTempest", 340, 340, 125, 130, 90, 50, 0),
            new Ventrix("StormValkyrie", 290, 290, 130, 105, 120, 50, 0),
            new Terravox("TerraGolem", 380, 380, 120, 160, 60, 50, 0),
            new Cindrax("ElectroRift", 300, 300, 135, 110, 95, 50, 0),
            new Borealix("VoidSeraph", 310, 310, 150, 115, 85, 50, 0)
        };

        for (Javamon inimigo : timeEclipse) {
            System.out.println("\nO Campeão enviou " + inimigo.getNome() + "!");
            Batalha.lutar(jogador, inimigo);

            // verifica se jogador ainda tem algum Javamon vivo
            boolean jogadorTemVivo = false;
            if (jogador != null && jogador.getEquipe() != null) {
                for (Javamon j : jogador.getEquipe()) {
                    if (j != null && j.estaVivo()) { jogadorTemVivo = true; break; }
                }
            }

            if (!jogadorTemVivo) {
                System.out.println("\n💀 Você foi derrotado pelo Campeão.");
                // opcional: teleportar para spawn
                x = spawnX;
                y = spawnY;
                return;
            }
        }

        System.out.println("\n🎉 VOCÊ DERROTOU O CAMPEÃO ECLIPSE!");
        System.out.println("🏅 Você é o CAMPEÃO SUPREMO!");
        // registra vitória/seTornouCampeao no Jogador aqui se esse método existir
        // ex: jogador.seTornouCampeao();
    }
}
