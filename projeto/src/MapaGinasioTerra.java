public class MapaGinasioTerra {
    private char[][] mapa = {
        "########################".toCharArray(),
        "#   *   O   .    *    #".toCharArray(),
        "# O O   ***   O    O  #".toCharArray(),
        "#   .     *      .   #".toCharArray(),
        "#****   OOOO   ****  #".toCharArray(),
        "#   O   . L .    O   #".toCharArray(),
        "#  ***    .     ***  #".toCharArray(),
        "#  O   O      O    E #".toCharArray(),
        "########################".toCharArray()
    };

    private int x = 1, y = 7;
    private Jogador jogador;
    private static final String NOME_GINASIO = "TERRA"; // Identificador único

    public MapaGinasioTerra(Jogador jogador) {
        this.jogador = jogador;
        System.out.println("⛰️ Bem-vindo ao Ginásio de Terra!");
        System.out.println("⚠️ Rochas bloqueiam e o chão pode desmoronar!");
    }

    public void entrar() {
        System.out.println("\n🌱 Você entrou no Ginásio da Terra!");
        mostrar();
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

    // Retorna true se saiu do ginásio
    public boolean mover(char d) {
        int nx = x, ny = y;

        if (d == 'w') ny--;
        else if (d == 's') ny++;
        else if (d == 'a') nx--;
        else if (d == 'd') nx++;
        else return false;

        if (!dentro(nx, ny)) return false;
        if (mapa[ny][nx] == '#') return false;

        char destino = mapa[ny][nx];

        // Rocha grande bloqueia
        if (destino == 'O') {
            System.out.println("🪨 Uma rocha enorme bloqueia o caminho!");
            return false;
        }

        // Buraco no chão
        if (destino == '.') {
            System.out.println("💥 O chão cedeu! Você caiu e voltou ao início!");
            x = 1;
            y = 7;
            return false;
        }

        // Líder do ginásio
        if (destino == 'L') {
            // VERIFICAÇÃO: Já derrotou este ginásio?
            if (jogador.jaDerrotoGinasio(NOME_GINASIO)) {
                System.out.println("\n⛰️ Líder Gaia: Sua força já foi provada!");
                System.out.println("💬 \"A terra reconhece sua vitória. Siga em frente!\"");
                return false;
            }
            
            System.out.println("⛰️ Líder Gaia: Mostre que sua força é inabalável!");
            iniciarBatalha();
            return false;
        }

        // Saída
        if (destino == 'E') {
            System.out.println("➡️ Você saiu do Ginásio de Terra!");
            return true;
        }

        x = nx;
        y = ny;
        return false;
    }

    private boolean dentro(int nx, int ny) {
        return ny >= 0 && ny < mapa.length && nx >= 0 && nx < mapa[ny].length;
    }

    private void iniciarBatalha() {
        System.out.println("\n⛰️ Gaia: Prepare-se para ser esmagado pela Terra!");

        Javamon[] timeTerra = {
            new Mudrill("Sandarm", 70, 70, 18, 12, 8, 5, 0),
            new Terravox("Mudhorn", 85, 85, 20, 14, 6, 7, 0),
            new Terravox("Golemrock", 100, 100, 25, 18, 4, 9, 0)
        };

        boolean venceu = Batalha.lutarContraTreinador(jogador, "Líder Gaia", timeTerra);
    
        if (venceu) {
            System.out.println("\n🏆 Você conquistou a Insígnia da Terra!");
            jogador.addVitoriaGym();
            jogador.marcarGinasioDerrotado(NOME_GINASIO); // MARCA COMO DERROTADO
        } else {
            System.out.println("\n💀 Você foi soterrado pela força da terra!");
        }
    }
}