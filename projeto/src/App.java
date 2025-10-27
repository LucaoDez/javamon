// ...existing code...
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        Jogador jogador = SaveManager.carregar("save.txt", mapa);
        if (jogador == null) {
            jogador = new Jogador("Treinador");
        }
        
        if (jogador.getEquipe().isEmpty()) {
            jogador.adicionarJavamon(new Feuermon("Feuermon", 70, 70, 25, 15, 20, 1, 0));
        }

        Menu menu = new Menu(jogador);
        Scanner sc = new Scanner(System.in);

        while (true) {
            mapa.mostrarMapa();
            System.out.println("\nUse W/A/S/D para mover | M para Menu | B para batalha de teste | Q para salvar e sair");
            String entrada = sc.nextLine().trim().toLowerCase();
            if (entrada.isEmpty()) continue;
            char comando = entrada.charAt(0);

            if (comando == 'q') {
                SaveManager.salvar(jogador, mapa, "save.txt");
                System.out.println("Jogo salvo. Saindo...");
                break;
            } else if (comando == 'm') {
                menu.abrirMenu(mapa);
            } else if (comando == 'b') {
                Javamon selvagem = new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0);
                Batalha.lutar(jogador, selvagem);
            } else if ("wasd".indexOf(comando) >= 0) {
                mapa.mover(comando, jogador);
            } else {
                System.out.println("Comando inválido.");
            }
        }

        sc.close();
    }
}
