import java.util.Scanner;

public class MapaLiga {

    // 🗺️ Mapa da Ilha da Liga Javamon
    private char[][] mapa = {
        "#########################".toCharArray(),
        "#       F       T       #".toCharArray(),
        "#########       #########".toCharArray(),
        "#                      C#".toCharArray(),
        "#########       #########".toCharArray(),
        "#       A   S   R       #".toCharArray(),
        "#########################".toCharArray(),
    };

    private int x = 12, y = 5; // posição inicial no 'S'
    private Jogador jogador;
    private Scanner in = new Scanner(System.in);

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🔥 Bem-vindo à Ilha da Liga Javamon!");

        while (true) {
            mostrar();
            System.out.print("Movimente-se (WASD): ");
            String line = in.nextLine();

            if (line.isEmpty()) continue;
            mover(Character.toLowerCase(line.charAt(0)));
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

        switch (d) {
            case 'w' -> ny--;
            case 's' -> ny++;
            case 'a' -> nx--;
            case 'd' -> nx++;
            default -> { return; }
        }

        if (!dentro(nx, ny)) return;
        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        // Ginásios
        if (destino == 'F') { new MapaGinasioFogo(jogador).entrar(); return; }
        if (destino == 'A') { new MapaGinasioAgua(jogador).entrar(); return; }
        if (destino == 'T') { new MapaGinasioTerra(jogador).entrar(); return; }
        if (destino == 'R') { new MapaGinasioAr(jogador).entrar(); return; }

        // Campeão
        if (destino == 'C') {
            if (jogador.getVitoriasGym() == 4) {
                new MapaCampeao(jogador).entrar();
            } else {
                System.out.println("\n❌ Você precisa derrotar os 4 líderes primeiro!");
            }
            return;
        }

        // ✅ SAIR DA LIGA CORRETAMENTE
        if (destino == 'S') {
            System.out.println("\n↩️ Você saiu do Hall!");

            Mapa mapaCidade = new Mapa();
            mapaCidade.entrar(jogador); // teleporta pertinho do 'L'

            // volta ao loop de andar na cidade
            while (true) {
                mapaCidade.mostrarMapa();
                System.out.print("Movimente-se (WASD): ");
                char mov = in.nextLine().toLowerCase().charAt(0);
                mapaCidade.mover(mov, jogador);
            }
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }
}
