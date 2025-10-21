import java.util.*;

// Menu interno do jogo, chamado quando o jogador pressiona 'm'
public class Menu {
    private Jogador jogador;
    private Scanner sc = new Scanner(System.in);

    public Menu(Jogador jogador) {
        this.jogador = jogador;
    }

    // abrir menu; requer referência ao mapa para salvar posição
    public boolean abrirMenu(Mapa mapa) {
        int opc;
        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1 - Ver Equipe");
            System.out.println("2 - Ver Box");
            System.out.println("3 - Inventário");
            System.out.println("4 - Loja");
            System.out.println("5 - Salvar Jogo");
            System.out.println("6 - Curar Equipe (grátis)");
            System.out.println("7 - Sair do Jogo");
            System.out.println("8 - Voltar ao mapa");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> jogador.mostrarEquipe();
                case 2 -> jogador.mostrarBox();
                case 3 -> jogador.mostrarBolsa();
                case 4 -> abrirLoja();
                case 5 -> SaveManager.salvar(jogador, mapa, "save.txt");
                case 6 -> curarEquipe();
                case 7 -> {
                    // nova opção: sair do jogo com confirmação
                    System.out.print("Deseja realmente sair do jogo? (s/n): ");
                    char confirma = sc.nextLine().toLowerCase().charAt(0);
                    if (confirma == 's') {
                        SaveManager.salvar(jogador, mapa, "save.txt");
                        System.out.println("💾 Jogo salvo. Até logo!");
                        System.exit(0); // encerra completamente o programa
                    } else {
                        System.out.println("Cancelado, voltando ao menu.");
                    }
                }
                case 8 -> {
                    System.out.println("Voltando ao jogo...");
                    return true; // sai do menu e volta pro mapa
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void abrirLoja(){
        System.out.println("\n=== LOJA ===");
        System.out.println("1 - pocao (20$)");
        System.out.println("2 - revive (50$)");
        System.out.println("3 - javaball (10$)");
        System.out.println("4 - voltar");
        System.out.println("escola:");
        int e = sc.nextInt();
        sc.nextLine();

        if (e == 1) jogador.comprarItens(new Itens("Poção", "cura", 20));
        else if (e == 2) jogador.comprarItens(new Itens("Revive", "revive", 50));
        else if (e == 3) jogador.comprarItens(new Itens("Pokébola", "javaball", 10));
        else System.out.println("Saindo da loja...");
    }
     private void curarEquipe(){
        // restaura HP de cada Javamon para o máximo (ajuste se preferir usar curar(valor))
        for (Javamon j : jogador.getEquipe()) {
            j.setHpATUAL(j.getHpMAX());
        }
        System.out.println("Equipe curada.");
    }
}