public class MapaLiga {

    private char[][] mapa = {
        "#########################".toCharArray(),
        "#       F       T       #".toCharArray(),
        "#########       #########".toCharArray(),
        "#                      C#".toCharArray(),
        "#########       #########".toCharArray(),
        "#       A   S   R       #".toCharArray(),
        "#########################".toCharArray(),
    };

    private int x = 12, y = 5;
    private Jogador jogador;
    private boolean saiu = false;
    private String ginasioEntrado = null; // "FOGO", "AGUA", "TERRA", "AR", "CAMPEAO"

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🔥 Bem-vindo à Ilha da Liga Javamon!");
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

    public void mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return;

        if (!dentro(nx, ny)) return;
        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        // 🌋 Ginásio do Fogo
        if (destino == 'F') {
            ginasioEntrado = "FOGO";
            return;
        }

        // 🌊 Ginásio da Água
        if (destino == 'A') {
            ginasioEntrado = "AGUA";
            return;
        }

        // 🌱 Ginásio da Terra
        if (destino == 'T') {
            ginasioEntrado = "TERRA";
            return;
        }

        // 🌪️ Ginásio do Ar
        if (destino == 'R') {
            ginasioEntrado = "AR";
            return;
        }

        // 🏆 Campeão
        if (destino == 'C') {
            if (jogador != null && jogador.getVitoriasGym() == 4) {
                ginasioEntrado = "CAMPEAO";
            } else {
                System.out.println("\n❌ Você precisa derrotar os 4 líderes primeiro!");
            }
            return;
        }

        // 🚪 SAÍDA do Hall
        if (destino == 'S') {
            System.out.println("\n↩️ Você saiu do Hall!");
            saiu = true;
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    public boolean saiuDaLiga() { return saiu; }
    public void resetSair() { saiu = false; }
    
    public String getGinasioEntrado() { return ginasioEntrado; }
    public void resetGinasio() { ginasioEntrado = null; }
}