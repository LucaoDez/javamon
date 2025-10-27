public class Mapa {

    private char[][] grid;
    private int jogadorX, jogadorY;

    public Mapa() {

        String[] linhas = {
            "############################################################",
            "#F  **TT*** #  **** * # * ****  # ***  # ***TT* #   *  L ##",
            "# **##    ######   # **##    #   ##  ######   #   #### * ##",
            "#   ## **     #   # ** ** ##   ##   **   #  #   #  **    ##",
            "#   #########  #   #######  ##   #########  #   #   #######",
            "#  **   ##   # **    ##   ***  ##   ***   #   ** #   **  ##",
            "#######   ##   ######   #######  ##   ######   ####### ** ##",
            "#    ##  **   # ** #   **   ##   **   #    #  **   #   ** ##",
            "#    ##   ######    ######   #######   ######   ##### **  ##",
            "# **    #  ***        ***      ***   ** #   *    **       ##",
            "############################################################"
        };

        grid = new char[linhas.length][linhas[0].length()];

        for (int y = 0; y < linhas.length; y++) {
            grid[y] = linhas[y].toCharArray();
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


    public void mover(char d) {
        int novoX = jogadorX;
        int novoY = jogadorY;

        if (d == 'w') novoY--;
        if (d == 's') novoY++;
        if (d == 'a') novoX--;
        if (d == 'd') novoX++;

        if (grid[novoY][novoX] == '#') return;

        if (grid[novoY][novoX] == 'L') {
            new LigaJavamon().entrarLiga();
            return;
        }

        jogadorX = novoX;
        jogadorY = novoY;

        if (grid[jogadorY][jogadorX] == '*') {
            double chance = Math.random();
            if (chance < 0.20) {
                System.out.println("\n⚠ Javamon selvagem apareceu!");
                Batalha.iniciarBatalhaSelvagem();
            }    
        }
    }
}
