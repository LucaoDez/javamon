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

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
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

    // Retorna um novo mapa se entrar em ginásio, ou null se continuar aqui
    public Object mover(char d) {
        int nx = x, ny = y;

        switch (d) {
            case 'w' -> ny--;
            case 's' -> ny++;
            case 'a' -> nx--;
            case 'd' -> nx++;
            default -> { return null; }
        }

        if (!dentro(nx, ny)) return null;
        if (mapa[ny][nx] == '#') return null;

        char destino = mapa[ny][nx];

        // Ginásios - retorna o novo mapa
        if (destino == 'F') {
            System.out.println("\n🔥 Entrando no Ginásio do Fogo...");
            return new MapaGinasioFogo(jogador);
        }
        if (destino == 'A') {
            System.out.println("\n🌊 Entrando no Ginásio da Água...");
            return new MapaGinasioAgua(jogador);
        }
        if (destino == 'T') {
            System.out.println("\n⛰️ Entrando no Ginásio da Terra...");
            return new MapaGinasioTerra(jogador);
        }
        if (destino == 'R') {
            System.out.println("\n🌬️ Entrando no Ginásio do Ar...");
            return new MapaGinasioAr(jogador);
        }

        // Campeão
        if (destino == 'C') {
            if (jogador.getVitoriasGym() == 4) {
                System.out.println("\n👑 Entrando no Salão do Campeão...");
                return new MapaCampeao(jogador);
            } else {
                System.out.println("\n❌ Você precisa derrotar os 4 líderes primeiro!");
                return null;
            }
        }

        // Sair da Liga
        if (destino == 'S') {
            System.out.println("\n↩️ Você saiu da Liga!");
            saiu = true;
            return null;
        }

        x = nx;
        y = ny;
        return null;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    public boolean saiuDaLiga() { return saiu; }
    public void resetSair() { saiu = false; }
}