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
            // mostrar mapa conforme o tipo
            if (mapaAtual instanceof Mapa) ((Mapa) mapaAtual).mostrarMapa();
            else if (mapaAtual instanceof MapaLiga) ((MapaLiga) mapaAtual).mostrar();

            System.out.println("\nUse W/A/S/D para mover | M para Menu | B para batalha de teste | Q para salvar e sair");
            String entrada = sc.nextLine().trim().toLowerCase();
            if (entrada.isEmpty()) continue;
            char comando = entrada.charAt(0);

            if (comando == 'q') {
                SaveManager.salvar(jogador, (Mapa) mapaCidade, "save.txt");
                System.out.println("Jogo salvo. Saindo...");
                break;
            } else if (comando == 'm') {
                menu.abrirMenu((mapaAtual instanceof Mapa) ? (Mapa)mapaAtual : (Mapa)mapaCidade);
            } else if (comando == 'b') {
                Batalha.lutar(jogador, new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0));
            } else if ("wasd".indexOf(comando) >= 0) {
                // delega movimento ao mapa atual
                if (mapaAtual instanceof Mapa) {
                    ((Mapa) mapaAtual).mover(comando, jogador);
                } else if (mapaAtual instanceof MapaLiga) {
                    ((MapaLiga) mapaAtual).mover(comando);
                    // se saiu da liga, troca para mapa da cidade e chama entrar para spawn perto do 'L'
                    MapaLiga ml = (MapaLiga) mapaAtual;
                    if (ml.saiuDaLiga()) {
                        ml.resetSair();
                        mapaAtual = mapaCidade;
                        mapaCidade.entrar(jogador); // spawna ao lado esquerdo do 'L'
                    }
                }
            } else {
                System.out.println("Comando inválido.");
            }
        }
    }
}
// ...existing code...