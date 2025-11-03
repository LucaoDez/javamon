// ...existing code...
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Mapa mapaCidade = new Mapa();
        Jogador jogador = SaveManager.carregar("save.txt", mapaCidade);
        if (jogador == null) jogador = new Jogador("Treinador");

        // mapa atual começa na cidade
        Object mapaAtual = mapaCidade;
        mapaCidade.entrar(jogador);

        Menu menu = new Menu(jogador);

        while (true) {

            System.out.println("\nUse W/A/S/D para mover | M para Menu | B para batalha de teste | Q para salvar e sair");
            String entrada = sc.nextLine().trim().toLowerCase();
            if (entrada.isEmpty()) continue;
            char comando = entrada.charAt(0);

            // --- movimentação consolidada (aplica o comando apenas uma vez) ---
            if ("wasd".indexOf(comando) >= 0) {
                if (mapaAtual instanceof Mapa) {
                    Mapa m = (Mapa) mapaAtual;
                    m.mover(comando, jogador);

                    // detectar entrada na Liga e trocar o mapa aqui
                if (m.entrouNaLiga()) {
                    mapaAtual = new MapaLiga(); // cria um novo mapa da Liga
                    ((MapaLiga) mapaAtual).entrar(); // agora sim o objeto é MapaLiga
                    continue; // evita que o resto do código desse loop rode ainda com o mapa antigo
                }

                } else if (mapaAtual instanceof MapaLiga) {
                    MapaLiga ml = (MapaLiga) mapaAtual;
                    ml.mover(comando);

                    if (ml.saiuDaLiga()) {
                        ml.resetSair();
                        mapaAtual = mapaCidade;
                        mapaCidade.entrar(jogador); // spawn junto ao 'L'
                        continue;
                    }
                }
            }

            // comandos não relacionados a movimento
            if (comando == 'q') {
                SaveManager.salvar(jogador, (Mapa) mapaCidade, "save.txt");
                System.out.println("Jogo salvo. Saindo...");
                break;
            } else if (comando == 'm') {
                menu.abrirMenu((mapaAtual instanceof Mapa) ? (Mapa)mapaAtual : (Mapa)mapaCidade);
            } else if (comando == 'b') {
                Batalha.lutar(jogador, new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0));
            } else if (!"wasd".contains(String.valueOf(comando))) {
                System.out.println("Comando inválido.");
            }
        }
    }
}
// ...existing code...