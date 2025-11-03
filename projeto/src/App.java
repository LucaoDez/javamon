// ...existing code...
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Cria mapa primeiro (necessário para o carregamento posicionar o jogador)
        Mapa mapa = new Mapa();

        // Scanner único para o programa (não fechar)
        Scanner sc = new Scanner(System.in);

        // Tenta carregar save (SaveManager pode retornar null)
        Jogador jogador = null;
        try {
            jogador = SaveManager.carregar("save.txt", mapa);
        } catch (Throwable t) {
            System.out.println("Erro ao carregar save: " + t.getMessage());
            jogador = null;
        }

        if (jogador == null) {
            jogador = new Jogador("Treinador");
        }

        // Garante equipe inicial usando a API do Jogador
        if (jogador.getEquipe() == null || jogador.getEquipe().isEmpty()) {
            jogador.adicionarJavamon(new Feuermon("Feuermon", 70, 70, 25, 15, 20, 1, 0));
        }

        Menu menu = new Menu(jogador);

        // Loop principal de jogo
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
                // Mantém a chamada existente; se Menu precisar do Scanner, adapte Menu para receber o Scanner
                menu.abrirMenu(mapa);
            } else if (comando == 'b') {
                Javamon selvagem = new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0);
                Batalha.lutar(jogador, selvagem);
            } else if ("wasd".indexOf(comando) >= 0) {
                // usa mover que aceita jogador para permitir batalhas selvagens/entradas de ginásios
                mapa.mover(comando, jogador);
            } else {
                System.out.println("Comando inválido.");
            }
        }

        // NÃO feche o Scanner(System.in) — outras classes também leem a entrada
        // sc.close();
    }
}
// ...existing code...