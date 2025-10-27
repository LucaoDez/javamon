// ...existing code...
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
                new MapaLiga().entrar();
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
                    Javamon selvagem = new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0);
                    Batalha.lutar(jogador, selvagem);
                } else {
                    System.out.println("Passe um Jogador para iniciar a batalha programaticamente.");
                }
            }
        }
    }
}
// ...existing code...