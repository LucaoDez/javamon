import java.util.Scanner;

public class MapaGinasioAgua {

    private char[][] mapa = {
        "###########################".toCharArray(),
        "#....○..>>>...=○....>....#".toCharArray(),
        "#.##.#..###..###..#.###..#".toCharArray(),
        "#.#.....#○...#...#..#...L#".toCharArray(),
        "#.####.=#####..###..#.#..#".toCharArray(),
        "#.#..#........○..#....#..#".toCharArray(),
        "#.##.###<<<########<<<#.##".toCharArray(),
        "#....○....##.....○....#.E#".toCharArray(),
        "###########################".toCharArray()
    };

    // posição inicial do jogador
    private int x = 1, y = 7;
    private int spawnX = 1, spawnY = 7;

    private Jogador jogador;

    public MapaGinasioAgua(Jogador jogador) {
        this.jogador = jogador;
    }

    public void entrar() {
        System.out.println("\n🌊 Você entrou no Ginásio da Água!");
        System.out.println("💧 A umidade no ar é intensa... mova-se com cuidado!");
        System.out.println("Use W A S D para andar\n");

        Scanner in = new Scanner(System.in);

        while (true) {
            mostrar();
            System.out.print("> ");
            char m = in.next().charAt(0);
            mover(m);
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

    public void mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return;

        char obst = mapa[ny][nx];

        // paredes
        if (obst == '#') return;

        // água profunda
        if (obst == '○') {
            System.out.println("💧 Você caiu na água profunda e voltou ao início!");
            x = spawnX;
            y = spawnY;
            return;
        }

        // correnteza direita
        if (obst == '>') {
            System.out.println("🌊 A correnteza te empurrou!");
            nx++;
            if (!dentro(nx, ny)) { x = spawnX; y = spawnY; return; }
            obst = mapa[ny][nx];
            if (obst == '#') return;
        }

        // correnteza esquerda
        if (obst == '<') {
            System.out.println("🌊 A correnteza te puxou!");
            nx--;
            if (!dentro(nx, ny)) { x = spawnX; y = spawnY; return; }
            obst = mapa[ny][nx];
            if (obst == '#') return;
        }

        // chão escorregadio
        if (obst == '=') {
            if (Math.random() < 0.35) {
                System.out.println("⚠️ Você escorregou e voltou ao início!");
                x = spawnX;
                y = spawnY;
                return;
            }
        }

        // líder
        if (obst == 'L') {
            System.out.println("\n💦 AQUA: Prepare-se para enfrentar o poder das marés!");
            iniciarBatalha();
            return;
        }

        // saída
        if (obst == 'E') {
            System.out.println("\n↩️ Você deixou o Ginásio da Água");
            new MapaLiga(jogador).entrar();
            return;
        }

        x = nx;
        y = ny;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void iniciarBatalha() {
        System.out.println("\n🌊 AQUA: As ondas vão te engolir!");

        Javamon[] time = {
            new Aquaril("Wartortle", 60, 60, 18, 14, 12, 5, 0),
            new Hydreon("Starmie",   75, 75, 22, 15, 18, 7, 0),
            new Aquaril("Gyarados",  90, 90, 30, 20, 15, 9, 0)
        };

        for (Javamon inimigo : time) {
            System.out.println("\n🌊 AQUA enviou " + inimigo.getNome());
            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("\n💀 Você foi derrotado!");
                new MapaLiga(jogador).entrar();
                return;
            }
        }

        System.out.println("\n🏆 Você derrotou o Líder AQUA!");
        jogador.addVitoriaGym();
        new MapaLiga(jogador).entrar();
    }
}
