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
    private static final String NOME_GINASIO = "CAMPEAO"; // Identificador único

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
            if (mover(comando)) {
                break; // Saiu do mapa
            }
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

    public boolean mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return false;

        if (!dentro(nx, ny)) return false;
        char destino = mapa[ny][nx];

        if (mapa[ny][nx] == '#') return false;

        if (destino == 'O') {
            System.out.println("🗿 As estátuas bloqueiam o caminho!");
            return false;
        }

        if (destino == 'C') {
            // VERIFICAÇÃO: Já derrotou o campeão?
            if (jogador.jaDerrotoGinasio(NOME_GINASIO)) {
                System.out.println("\n👑 Campeão Eclipse: Parabéns, você é o novo campeão!");
                System.out.println("💬 \"Não há necessidade de lutarmos novamente.\"");
                System.out.println("💬 \"Continue defendendo seu título com honra!\"");
                return false;
            }
            
            System.out.println("\n👑 CAMPEÃO FINAL");
            System.out.println("⚔️ Campeão Eclipse: \"Seu caminho termina aqui!\"");
            enfrentarCampeao();
            return false;
        }

        if (destino == 'E') {
            System.out.println("➡️ Você voltou ao mapa da liga!");
            return true; // Retorna true para sair do mapa
        }

        x = nx;
        y = ny;
        return false;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void enfrentarCampeao() {
        Javamon[] timeEclipse = {
            new Feuermon("Titanflare", 110, 1, 70, 60, 5, 10, 0),
            new Aquaril("AquaTempest", 120, 1, 75, 70, 4, 10, 0),
            new Ventrix("StormValkyrie", 150, 1, 70, 65, 6, 10, 0),
            new Terravox("TerraGolem", 140, 1, 60, 60, 6, 10, 0),
            new Cindrax("ElectroRift", 150, 1, 75, 55, 5, 10, 0),
            new Borealix("VoidSeraph", 110, 1, 70, 65, 5, 10, 0)
        };

        boolean venceu = Batalha.lutarContraTreinador(jogador, "Campeão Eclipse", timeEclipse);
    
        if (venceu) {
            System.out.println("\n🎉 VOCÊ É O NOVO CAMPEÃO!");
            System.out.println("🏅 Você entrou para o Hall da Fama!");
            jogador.setSeTornouCampeao();
            // ========== VITÓRIA! ==========
            System.out.println("\n🎉 VOCÊ DERROTOU O CAMPEÃO ECLIPSE!");
            System.out.println("🏅 Você é o CAMPEÃO SUPREMO!");
    
            // Marca o jogador como campeão
            jogador.setSeTornouCampeao();
    
            // Aguarda antes da tela final
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // Ignora
            }
    
            // ========== EXIBE TELA FINAL ==========
            TelaFinal.exibir(jogador);
        } else {
            System.out.println("\n💀 Você foi derrotado pelo Campeão.");
            x = spawnX;
            y = spawnY;
        }
    }   
}