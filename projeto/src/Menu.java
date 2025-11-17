import java.util.*;

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
                case 3 -> mostrarInventario();
                case 4 -> abrirLoja();
                case 5 -> SaveManager.salvar(jogador, mapa, "save.txt");
                case 6 -> curarEquipe();
                case 7 -> {
                    System.out.print("Deseja realmente sair do jogo? (s/n): ");
                    char confirma = sc.nextLine().toLowerCase().charAt(0);
                    if (confirma == 's') {
                        SaveManager.salvar(jogador, mapa, "save.txt");
                        System.out.println("💾 Jogo salvo. Até logo!");
                        System.exit(0);
                    } else {
                        System.out.println("Cancelado, voltando ao menu.");
                    }
                }
                case 8 -> {
                    System.out.println("Voltando ao jogo...");
                    return true;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void mostrarInventario() {
        List<Itens> bolsa = jogador.getBolsa();
        
        if (bolsa.isEmpty()) {
            System.out.println("\n❌ Sua mochila está vazia!");
            return;
        }

        System.out.println("\n=== INVENTÁRIO ===");
        for (int i = 0; i < bolsa.size(); i++) {
            Itens item = bolsa.get(i);
            System.out.println((i + 1) + " - " + item.getNome() + " (x" + item.getQuantidade() + ") - " + item.getDescricao());
        }
        
        System.out.println("\n1 - Usar Item | 0 - Voltar");
        System.out.print("Escolha: ");
        
        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (escolha == 1) {
            usarItemDoInventario();
        }
    }

    private void usarItemDoInventario() {
        List<Itens> bolsa = jogador.getBolsa();
        
        System.out.println("\n=== USAR ITEM ===");
        for (int i = 0; i < bolsa.size(); i++) {
            Itens item = bolsa.get(i);
            System.out.println((i + 1) + " - " + item.getNome() + " (x" + item.getQuantidade() + ")");
        }
        System.out.println("0 - Cancelar");
        
        System.out.print("Escolha um item: ");
        int indiceItem;
        try {
            indiceItem = Integer.parseInt(sc.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (indiceItem == -1) {
            System.out.println("❌ Cancelado.");
            return;
        }

        if (indiceItem < 0 || indiceItem >= bolsa.size()) {
            System.out.println("❌ Item inválido.");
            return;
        }

        Itens itemEscolhido = bolsa.get(indiceItem);
        
        // Verifica se é Javacube/Pokébola (não pode usar fora de batalha)
        if (itemEscolhido instanceof Javacube || 
            itemEscolhido.getNome().toLowerCase().contains("ball") ||
            itemEscolhido.getNome().toLowerCase().contains("cube")) {
            System.out.println("❌ " + itemEscolhido.getNome() + " só pode ser usado em batalha!");
            return;
        }

        // Mostra equipe
        List<Javamon> equipe = jogador.getEquipe();
        if (equipe.isEmpty()) {
            System.out.println("❌ Você não tem Javamon na equipe!");
            return;
        }

        System.out.println("\n=== SUA EQUIPE ===");
        for (int i = 0; i < equipe.size(); i++) {
            Javamon j = equipe.get(i);
            String status = j.estaVivo() ? "HP: " + j.getHpATUAL() + "/" + j.getHpMAX() : "DESMAIADO";
            System.out.println((i + 1) + " - " + j.getNome() + " (" + status + ")");
        }
        System.out.println("0 - Cancelar");
        
        System.out.print("Usar em qual Javamon? ");
        int indiceJavamon;
        try {
            indiceJavamon = Integer.parseInt(sc.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (indiceJavamon == -1) {
            System.out.println("❌ Cancelado.");
            return;
        }

        if (indiceJavamon < 0 || indiceJavamon >= equipe.size()) {
            System.out.println("❌ Javamon inválido.");
            return;
        }

        Javamon alvo = equipe.get(indiceJavamon);
        
        // Usa o item
        try {
            itemEscolhido.usar(alvo);
    
            // Verifica e remove se a quantidade chegou a 0 ou menos
            if (itemEscolhido.getQuantidade() <= 0) {
                bolsa.remove(indiceItem);
                System.out.println("✅ " + itemEscolhido.getNome() + " foi usado e removido da bolsa!");
            } else {
                System.out.println("✅ Item usado! Restam: " + itemEscolhido.getQuantidade());
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao usar item: " + e.getMessage());
        }
    }

    private void abrirLoja() {
        System.out.println("\n=== LOJA JAVAMON ===");
        System.out.println("Seu dinheiro: " + jogador.getDinheiro() + "$");
        System.out.println("\n1 - Poção (20$) - Recupera 50 HP");
        System.out.println("2 - Super Poção (50$) - Recupera 100 HP");
        System.out.println("3 - Revive (100$) - Revive um Javamon desmaiado");
        System.out.println("4 - Javacube (50$) - Captura Javamons selvagens");
        System.out.println("5 - Voltar");
        System.out.print("Escolha: ");
        
        int e;
        try {
            e = sc.nextInt();
            sc.nextLine();
        } catch (Exception ex) {
            sc.nextLine();
            System.out.println("Entrada inválida.");
            return;
        }

        Itens itemComprado = null;
        int preco = 0;

        switch (e) {
            case 1:
                preco = 20;
                if (jogador.getDinheiro() >= preco) {
                    itemComprado = new Pocao(1, 50);
                }
                break;
            case 2:
                preco = 50;
                if (jogador.getDinheiro() >= preco) {
                    itemComprado = new Pocao(1, 100);
                    itemComprado = new Itens("Super Poção", "Recupera 100 HP", 1) {
                        @Override
                        public void usar(Javamon alvo) {
                            if (!alvo.estaVivo()) {
                                System.out.println(alvo.getNome() + " está desmaiado e não pode ser curado!");
                                return;
                            }
                            alvo.curar(100);
                            System.out.println(alvo.getNome() + " recuperou 100 HP!");
                        }
                    };
                }
                break;
            case 3:
                preco = 100;
                if (jogador.getDinheiro() >= preco) {
                    itemComprado = new Revive(1);
                }
                break;
            case 4:
                preco = 50;
                if (jogador.getDinheiro() >= preco) {
                    itemComprado = new Javacube(1);
                }
                break;
            case 5:
                System.out.println("Saindo da loja...");
                return;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (itemComprado != null) {
            jogador.gastarDinheiro(preco);
            jogador.adicionarItem(itemComprado);
            System.out.println("✅ Você comprou " + itemComprado.getNome() + " por " + preco + "$!");
            System.out.println("Dinheiro restante: " + jogador.getDinheiro() + "$");
        } else if (jogador.getDinheiro() < preco) {
            System.out.println("❌ Dinheiro insuficiente! Você tem " + jogador.getDinheiro() + "$, mas precisa de " + preco + "$");
        }
    }

    private void curarEquipe() {
        for (Javamon j : jogador.getEquipe()) {
            j.setHpATUAL(j.getHpMAX());
            if (j.getAtaques() != null) {
                for (Ataque a : j.getAtaques()) {
                    a.restaurarPP();
                }
            }
        }
        System.out.println("✅ Toda a equipe foi curada!");
    }
}