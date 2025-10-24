//classe que representa o mapa ASCII
public class Mapa{
    private char [][] grid;
    private int jogadorX, jogadorY;

    public Mapa(){
    // exemplo: constrói grid a partir de linhas definidas (ajuste conforme sua lógica atual)
    String[] linhas = {
        "########################",
        "#    #      #     #   #",
        "#    #  ##  #  ## #   #",
        "########################"
    };

    int altura = linhas.length;
    int largura = 0;
    for (String l : linhas) if (l != null) largura = Math.max(largura, l.length());

    grid = new char[altura][largura];
    for (int i = 0; i < altura; i++) {
        String linha = (linhas[i] == null) ? "" : linhas[i];
        // preenche e evita charAt fora do intervalo
        for (int j = 0; j < largura; j++) {
            if (j < linha.length()) {
                grid[i][j] = linha.charAt(j);
            } else {
                grid[i][j] = ' '; // preencher com espaço se a linha for curta
            }
        }
    }

    // inicializa posição do jogador de forma segura
    jogadorX = 1;
    jogadorY = 1;
}

    // Mostra o mapa no terminal
    public void mostrarMapa() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (y == jogadorY && x == jogadorX) {
                    System.out.print("@"); // Jogador
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.println();
        }
    }

    // Movimento do jogador
    public void mover(char direcao, Jogador jogador) {
        int novoX = jogadorX, novoY = jogadorY;
        if (direcao == 'w') novoY--;
        else if (direcao == 's') novoY++;
        else if (direcao == 'a') novoX--;
        else if (direcao == 'd') novoX++;

        if (novoX >= 0 && novoX < grid[0].length && novoY >= 0 && novoY < grid.length) {
            char destino = grid[novoY][novoX];
            if (destino != '-') { // não atravessa árvores
                jogadorX = novoX;
                jogadorY = novoY;
            }
        }
    }

    public int getJogadorX() {
        return jogadorX;
    }

    public int getJogadorY() {
        return jogadorY;
    }

    public void setPosicaoJogador(int x, int y) {
        // valida limites do mapa antes de atribuir
        if (grid == null) return;
        if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
            this.jogadorX = x;
            this.jogadorY = y;
        } else {
            System.out.println("Posição inválida para o jogador no mapa.");
        }
    }
}