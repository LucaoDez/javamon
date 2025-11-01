// ...existing code...

import java.util.Random;

public class Mapa {

    private final char[][] grid;
    private int jogadorX, jogadorY;

    public Mapa() {

        String[] linhas = {
            "############################################################",
            "#   ******* #  **** * # * ****  # ***    ****** #   *   L ##",
            "# **##    ######   # **##    #   ##  ######     # #### *  ##",
            "#   ## **     #   # ** ** ##   ##   **   #  # *  #  **    ##",
            "#   #########  #   #######  ##   #########  # *  #   #######",
            "#  **   ##***#  **    ##   ***  ##   ***   #  *** #   **  ##",
            "#######  ***   ######   #######  ##   ######   ####### ** ##",
            "#    ##  **   # ** #   **   ##   **   #    #  **   #   ** ##",
            "#    ##   ######    ######   ######   ######   ##### **  ##",
            "# **       ***        ***      ***   ** #   *    **       ##",
            "############################################################"
        };

        int altura = linhas.length;
        int largura = 0;
        for (String l : linhas) if (l != null) largura = Math.max(largura, l.length());

        grid = new char[altura][largura];

        for (int y = 0; y < altura; y++) {
            String linha = linhas[y] == null ? "" : linhas[y];
            for (int x = 0; x < largura; x++) {
                grid[y][x] = (x < linha.length()) ? linha.charAt(x) : ' ';
            }
        }

        jogadorX = 2;
        jogadorY = 2;
    }


    public void mostrarMapa() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (x == jogadorX && y == jogadorY) {
                    System.out.print("@");
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.println();
        }
    }

    // getters / setter combinando X e Y
    public int getJogadorX() { return jogadorX; }
    public int getJogadorY() { return jogadorY; }

    public boolean setPosicaoJogador(int x, int y) {
        if (!validaPosicao(x, y)) return false;
        jogadorX = x;
        jogadorY = y;
        return true;
    }

    private boolean validaPosicao(int x, int y) {
        return y >= 0 && y < grid.length && x >= 0 && x < grid[y].length;
    }

     private Javamon gerarSelvagem(Jogador jogador) {
        Random rand = new Random();

        // lista de espécies disponíveis no jogo (adicione/remova conforme seu projeto)
        String[] especies = { "Feuermon", "Aquaril", "Hydreon", "Borealix", "Cindrax", "Terravox", "Ventrix", "Mudrill" };

        // escolha aleatória (pode trocar por pesos se quiser)
        String especie = especies[rand.nextInt(especies.length)];

        // determina nível baseado no javamon líder do jogador (se houver)
        int nivel = 1;
        if (jogador != null && !jogador.getEquipe().isEmpty()) {
            try {
                int lvlJogador = jogador.getEquipe().get(0).getLvl();
                // gera nível entre (lvlJogador-1) e (lvlJogador+2)
                nivel = Math.max(1, lvlJogador - 1 + rand.nextInt(4));
            } catch (Exception e) {
                nivel = 1 + rand.nextInt(3);
            }
        } else {
            nivel = 1 + rand.nextInt(3);
        }

        // criar instância concreta por espécie (ajuste stats se desejar)
        switch (especie.toLowerCase()) {
            case "feuermon":
                return new Feuermon("Selvagem Feuermon", 70, 70, 25, 15, 20, nivel, 0);
            case "aquaril":
                return new Aquaril("Selvagem Aquaril", 70, 70, 25, 15, 20, nivel, 0);
            case "hydreon":
                return new Hydreon("Selvagem Hydreon", 70, 70, 25, 15, 20, nivel, 0);
            case "borealix":
                return new Borealix("Selvagem Borealix", 70, 70, 25, 15, 20, nivel, 0);
            case "cindrax":
                return new Cindrax("Selvagem Cindrax", 70, 70, 25, 15, 20, nivel, 0);
            case "terravox":
                return new Terravox("Selvagem Terravox", 70, 70, 25, 15, 20, nivel, 0);
            case "ventrix":
                return new Ventrix("Selvagem Ventrix", 70, 70, 25, 15, 20, nivel, 0);
            case "mudrill":
                return new Mudrill("Selvagem Mudrill", 70, 70, 25, 15, 20, nivel, 0);
            default:
                return new Feuermon("Selvagem Feuermon", 70, 70, 25, 15, 20, nivel, 0);
        }
    }

    // mover sem jogador (mantém compatibilidade)
    public void mover(char d) {
        mover(d, null);
    }

    // mover com opção de passar o jogador (para iniciar batalhas/ligas)
    public void mover(char d, Jogador jogador) {
        int novoX = jogadorX;
        int novoY = jogadorY;

        if (d == 'w') novoY--;
        else if (d == 's') novoY++;
        else if (d == 'a') novoX--;
        else if (d == 'd') novoX++;
        else return;

        if (!validaPosicao(novoX, novoY)) return; // evita acessar fora dos limites

        char destino = grid[novoY][novoX];
        if (destino == '#') return;

        if (destino == 'L') {
            if (jogador != null) {
                new MapaLiga(jogador).entrar();
            } else {
                System.out.println("Portão da Liga (necessário jogador).");
            }
            return;
        }

        jogadorX = novoX;
        jogadorY = novoY;

        if (destino == '*') {
            double chance = Math.random();
            if (chance < 0.20) {
                System.out.println("\n⚠ Javamon selvagem apareceu!");
                if (jogador != null) {
                    // cria um inimigo de teste e inicia a batalha usando a API existente
                    Javamon selvagem = gerarSelvagem(jogador);
                    Batalha.lutar(jogador, selvagem);
                } else {
                    System.out.println("Passe um Jogador para iniciar a batalha programaticamente.");
                }
            }
        }
    }

    public void entrar(Jogador jogador) {
        // encontra 'L' no grid (posição fixa no mapa da classe)
        int lx = -1, ly = -1;
        for (int y = 0; y < grid.length && lx == -1; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'L') { lx = x; ly = y; break; }
            }
        }

        if (lx != -1) {
            int leftX = lx - 1;
            // tenta spawnar exatamente à esquerda do 'L'
            if (validaPosicao(leftX, ly) && grid[ly][leftX] != '#') {
                jogadorX = leftX;
                jogadorY = ly;
            } else {
                // fallback: se a célula esquerda estiver bloqueada, tenta colocar ao lado (direita/cima/baixo) rapidamente
                int[][] candidatos = { {lx + 1, ly}, {lx, ly - 1}, {lx, ly + 1} };
                boolean colocado = false;
                for (int[] c : candidatos) {
                    int cx = c[0], cy = c[1];
                    if (validaPosicao(cx, cy) && grid[cy][cx] != '#') {
                        jogadorX = cx;
                        jogadorY = cy;
                        colocado = true;
                        break;
                    }
                }
                if (!colocado) {
                    // último recurso: coloca ao lado esquerdo mesmo que seja parede (garante retorno à cidade)
                    jogadorX = Math.max(0, Math.min(grid[0].length - 1, leftX));
                    jogadorY = Math.max(0, Math.min(grid.length - 1, ly));
                }
            }
        } else {
            // se 'L' não existir por algum motivo, spawn padrão
            jogadorX = 2;
            jogadorY = 2;
        }

        mostrarMapa();
        System.out.println("Você apareceu próximo à entrada da Liga.");
    }
}
// ...existing code...