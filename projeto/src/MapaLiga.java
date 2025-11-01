import java.util.Scanner;

public class MapaLiga {

    // 🗺️ Mapa da Ilha da Liga Javamon
    // '#': parede
    // 'F', 'T', 'A', 'R': portas dos ginásios
    // 'C': Campeão (só acessa após 4 vitórias)
    // 'S': Saída do Hall (volta para a cidade)
    private char[][] mapa = {
        "#########################".toCharArray(),
        "#       F       T       #".toCharArray(),
        "#########       #########".toCharArray(),
        "#                      C#".toCharArray(),
        "#########       #########".toCharArray(),
        "#       A   S   R       #".toCharArray(), // ✅ SAÍDA adicionada no centro
        "#########################".toCharArray(),
    };

    // 🧍 Posição inicial do jogador (sobre o 'S')
    private int x = 12, y = 5;

    private Jogador jogador;

    public MapaLiga(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🔥 Bem-vindo à Ilha da Liga Javamon!");

        Scanner in = new Scanner(System.in);

        while (true) {
            mostrar();
            System.out.print("Movimente-se (WASD): ");
            String line = in.nextLine();
            if (line == null || line.trim().isEmpty()) continue;
            char m = Character.toLowerCase(line.trim().charAt(0));
            mover(m);
        }
    }

    // 👀 Exibir o mapa com o jogador como '@'
    private void mostrar() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == y && j == x) System.out.print("@");
                else System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    // ➡️ Movimentação e lógica das portas
    private void mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return;

        if (!dentro(nx, ny)) return;

        // Se for parede, não anda
        if (mapa[ny][nx] == '#') return;

        char destino = mapa[ny][nx];

        // 🌋 Ginásio do Fogo
        if (destino == 'F') {
            new MapaGinasioFogo(jogador).entrar();
            return;
        }

        // 🌊 Ginásio da Água
        if (destino == 'A') {
            new MapaGinasioAgua(jogador).entrar();
            return;
        }

        // 🌱 Ginásio da Terra
        if (destino == 'T') {
            new MapaGinasioTerra(jogador).entrar();
            return;
        }

        // 🌪️ Ginásio do Ar
        if (destino == 'R') {
            new MapaGinasioAr(jogador).entrar();
            return;
        }

        // 🏆 Campeão — só após 4 vitórias
        if (destino == 'C') {
            if (jogador.getVitoriasGym() == 4) {
                new MapaCampeao(jogador).entrar();
            } else {
                System.out.println("\n❌ Você precisa derrotar os 4 líderes primeiro!");
            }
            return;
        }

        // 🚪 SAÍDA do Hall
        if (destino == 'S') {
            System.out.println("\n↩️ Você saiu do Hall!");
            new Mapa ().entrar(jogador); // ✅ volta para a cidade
            return;
        }

        // ✅ Se não for porta, apenas anda
        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }
}