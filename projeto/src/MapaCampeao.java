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

        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        if (destino == 'O') {
            System.out.println("🗿 As estátuas bloqueiam o caminho!");
            return;
        }

        if (destino == 'C') {
            System.out.println("\n👑 CAMPEÃO FINAL");
            System.out.println("⚔️ Campeão Eclipse: \"Seu caminho termina aqui!\"");

            // JAVAMONS DO CAMPEÃO
            Javamon[] timeEclipse = {
                new Javamon("Titanflare", "Fogo/Lutador", 50, 320, 140, 120,
                    new String[]{"Chama Final", "Soco Meteoro", "Explosão Vulcânica", "Chute Dragão"}),
                new Javamon("AquaTempest", "Água/Dragão", 50, 340, 125, 130,
                    new String[]{"Tsunami", "Hidro Impacto", "Rugido Dracônico", "Corrente Abissal"}),
                new Javamon("ElectroRift", "Elétrico/Fantasma", 50, 300, 135, 110,
                    new String[]{"Raio Espectral", "Choque Dimensional", "Tempestade Plasma", "Surto Fantasma"}),
                new Javamon("TerraGolem", "Terra/Aço", 50, 380, 120, 160,
                    new String[]{"Terremoto", "Punho de Ferro", "Avalanche Rochosa", "Murro Sísmico"}),
                new Javamon("StormValkyrie", "Voador/Fada", 50, 290, 130, 105,
                    new String[]{"Vendaval Divino", "Asa Tempestuosa", "Luz Sagrada", "Dança Celestial"}),
                new Javamon("VoidSeraph", "Sombrio/Psíquico", 50, 310, 150, 115,
                    new String[]{"Colapso Mental", "Lâmina Sombria", "Pulso Astral", "Ritual do Vazio"})
            };

            boolean venceu = jogador.batalharContraEquipe("Eclipse", timeEclipse);

            if (venceu) {
                System.out.println("🎉 VOCÊ DERROTOU O CAMPEÃO ECLIPSE!");
                System.out.println("🏅 Você é o CAMPEÃO SUPREMO!");
                jogador.seTornouCampeao();
            } else {
                System.out.println("💀 Você foi derrotado… treine mais!");
            }
            return;
        }

        if (destino == 'E') {
            System.out.println("➡️ Você voltou ao mapa da liga!");
            return;
        }

        x = nx;
        y = ny;
    }
}
