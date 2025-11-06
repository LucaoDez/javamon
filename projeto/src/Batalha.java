import java.util.*;

public class Batalha {
    // Substitua o método lutar() e usarItem() na classe Batalha.java

public static void lutar(Jogador jogador, Javamon inimigo) {
    if (jogador == null) {
        System.out.println("Jogador inválido.");
        return;
    }
    if (inimigo == null) {
        System.out.println("Inimigo inválido.");
        return;
    }

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    if (jogador.getEquipe() == null || jogador.getEquipe().isEmpty()) {
        System.out.println("Você não tem Javamon para batalhar!");
        return;
    }

    Javamon ativo = jogador.getEquipe().get(0);

    System.out.println("\nUm " + inimigo.getNome() + " selvagem apareceu!");
    while (ativo.estaVivo() && inimigo.estaVivo()) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Seu: " + ativo.getNome() + " HP " + ativo.getHpATUAL() + "/" + ativo.getHpMAX());
        System.out.println("Inimigo: " + inimigo.getNome() + " HP " + inimigo.getHpATUAL() + "/" + inimigo.getHpMAX());
        System.out.println("=".repeat(50));

        System.out.println("1 - Atacar | 2 - Usar Item | 3 - Mudar Javamon | 4 - Fugir");
        System.out.print("Escolha: ");

        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            continue;
        }

        boolean turnoJogador = true;

        if (escolha == 1) {
            int indiceAtaque;
            try {
                indiceAtaque = ativo.escolherAtaque(sc);
            } catch (UnsupportedOperationException ex) {
                indiceAtaque = 0;
            }

            if (indiceAtaque >= 0) {
                ativo.atacar(inimigo, indiceAtaque);
            }
        } else if (escolha == 2) {
            // PASSA O INIMIGO PARA O MÉTODO usarItem
            int resultadoItem = usarItemEmBatalha(jogador, inimigo, sc);
            if (resultadoItem == 1) {
                // Capturou com sucesso
                System.out.println("✅ Batalha encerrada - Javamon capturado!");
                return;
            } else if (resultadoItem == 0) {
                // Usou item normalmente (consumiu turno)
                turnoJogador = true;
            } else {
                // Cancelou (-1)
                turnoJogador = false;
            }
        } else if (escolha == 3) {
            turnoJogador = trocarJavamon(jogador, sc);
            if (turnoJogador) {
                ativo = jogador.getEquipe().get(0);
            }
        } else if (escolha == 4) {
            if (rand.nextInt(100) < 60) {
                System.out.println("🏃 Você fugiu da batalha!");
                return;
            } else {
                System.out.println("❌ Não conseguiu fugir!");
            }
        } else {
            System.out.println("Opção inválida.");
            turnoJogador = false;
        }

        // Turno do inimigo
        if (turnoJogador && inimigo.estaVivo() && ativo.estaVivo()) {
            int atkIndex = -1;
            List<Ataque> listaAtks = inimigo.getAtaques();
            if (listaAtks != null && !listaAtks.isEmpty()) {
                for (int i = 0; i < listaAtks.size(); i++) {
                    if (listaAtks.get(i).getPpATUAL() > 0) {
                        atkIndex = i;
                        break;
                    }
                }
            }

            if (atkIndex >= 0) {
                inimigo.atacar(ativo, atkIndex);
            } else {
                System.out.println(inimigo.getNome() + " não tem ataques disponíveis!");
                ativo.levarDano(5);
                System.out.println(ativo.getNome() + " recebeu 5 de dano!\n");
            }
        }

        // Verifica se ativo desmaiou
        if (!ativo.estaVivo()) {
            System.out.println("💀 " + ativo.getNome() + " desmaiou!");
            
            boolean temVivo = false;
            for (Javamon j : jogador.getEquipe()) {
                if (j.estaVivo()) {
                    temVivo = true;
                    break;
                }
            }
            
            if (!temVivo) {
                System.out.println("\n❌ Todos os seus Javamon desmaiaram!");
                System.out.println("💀 Você foi derrotado!");
                return;
            }
            
            System.out.println("\nEscolha um Javamon para continuar:");
            if (trocarJavamonForcado(jogador, sc)) {
                ativo = jogador.getEquipe().get(0);
                System.out.println("➡️ " + ativo.getNome() + " entrou em campo!");
            } else {
                for (int i = 1; i < jogador.getEquipe().size(); i++) {
                    if (jogador.getEquipe().get(i).estaVivo()) {
                        Javamon desmaiado = jogador.getEquipe().get(0);
                        Javamon vivo = jogador.getEquipe().get(i);
                        jogador.getEquipe().set(0, vivo);
                        jogador.getEquipe().set(i, desmaiado);
                        ativo = vivo;
                        System.out.println("➡️ " + ativo.getNome() + " foi enviado!");
                        break;
                    }
                }
            }
        }
    }
}

/**
 * Sistema de uso de itens EM BATALHA
 * @return 1 = capturou (encerra batalha), 0 = usou item normal, -1 = cancelou
 */
private static int usarItemEmBatalha(Jogador jogador, Javamon inimigo, Scanner sc) {
    List<Itens> bolsa = jogador.getBolsa();
    
    if (bolsa == null || bolsa.isEmpty()) {
        System.out.println("\n❌ Sua mochila está vazia!");
        return -1;
    }

    System.out.println("\n=== ITENS DISPONÍVEIS ===");
    for (int i = 0; i < bolsa.size(); i++) {
        Itens item = bolsa.get(i);
        System.out.println((i + 1) + " - " + item.getNome() + " (x" + item.getQuantidade() + ")");
    }
    System.out.println("0 - Cancelar");
    
    System.out.print("Escolha um item: ");
    int escolha;
    try {
        escolha = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida.");
        return -1;
    }

    if (escolha == 0) {
        System.out.println("❌ Cancelado.");
        return -1;
    }

    if (escolha < 1 || escolha > bolsa.size()) {
        System.out.println("❌ Item inválido.");
        return -1;
    }

    Itens itemEscolhido = bolsa.get(escolha - 1);
    
    // ===== JAVACUBE: USA NO INIMIGO =====
    if (itemEscolhido instanceof Javacube) {
        Javacube cube = (Javacube) itemEscolhido;
        boolean capturou = cube.tentarCapturar(inimigo, jogador);
        
        // Remove da bolsa se acabou
        if (cube.getQuantidade() <= 0) {
            bolsa.remove(escolha - 1);
            System.out.println("❌ Suas Javacubes acabaram!");
        }
        
        return capturou ? 1 : 0; // 1 = capturou, 0 = falhou mas gastou turno
    }
    
    // ===== OUTROS ITENS: USA NA SUA EQUIPE =====
    System.out.println("\nUsar em qual Javamon?");
    List<Javamon> equipe = jogador.getEquipe();
    for (int i = 0; i < equipe.size(); i++) {
        Javamon j = equipe.get(i);
        String status = j.estaVivo() ? "HP: " + j.getHpATUAL() + "/" + j.getHpMAX() : "DESMAIADO";
        System.out.println((i + 1) + " - " + j.getNome() + " (" + status + ")");
    }
    System.out.println("0 - Cancelar");
    
    System.out.print("Escolha: ");
    int indiceJavamon;
    try {
        indiceJavamon = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida.");
        return -1;
    }

    if (indiceJavamon == 0) {
        System.out.println("❌ Cancelado.");
        return -1;
    }

    if (indiceJavamon < 1 || indiceJavamon > equipe.size()) {
        System.out.println("❌ Javamon inválido.");
        return -1;
    }

    Javamon alvo = equipe.get(indiceJavamon - 1);
    
    try {
        itemEscolhido.usar(alvo);
        itemEscolhido.removerQuantidade(1);
        
        if (itemEscolhido.getQuantidade() <= 0) {
            bolsa.remove(escolha - 1);
            System.out.println("❌ " + itemEscolhido.getNome() + " acabou!");
        }
        
        return 0; // Usou turno
    } catch (Exception e) {
        System.out.println("❌ Erro ao usar item: " + e.getMessage());
        return -1;
    }
}

    /**
     * Sistema de uso de itens
     * @return true se usou um turno, false se cancelou
     */
    // Substitua o método usarItem() na classe Batalha.java por este:

/**
 * Sistema de uso de itens
 * @return true se usou um turno, false se cancelou
 */
private static boolean usarItem(Jogador jogador, Scanner sc) {
    List<Itens> bolsa = jogador.getBolsa();
    
    if (bolsa == null || bolsa.isEmpty()) {
        System.out.println("\n❌ Sua mochila está vazia!");
        return false;
    }

    System.out.println("\n=== ITENS DISPONÍVEIS ===");
    
    for (int i = 0; i < bolsa.size(); i++) {
        Itens item = bolsa.get(i);
        System.out.println((i + 1) + " - " + item.getNome() + " (x" + item.getQuantidade() + ")");
    }
    System.out.println("0 - Cancelar");
    
    System.out.print("Escolha um item: ");
    int escolha;
    try {
        escolha = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida.");
        return false;
    }

    if (escolha == 0) {
        System.out.println("❌ Cancelado.");
        return false;
    }

    if (escolha < 1 || escolha > bolsa.size()) {
        System.out.println("❌ Item inválido.");
        return false;
    }

    Itens itemEscolhido = bolsa.get(escolha - 1);
    
    // ===== JAVACUBE: USA NO INIMIGO =====
    if (itemEscolhido instanceof Javacube) {
        Javacube cube = (Javacube) itemEscolhido;
        
        // Pega o inimigo atual (precisa ser passado como parâmetro)
        // NOTA: Você precisa modificar a assinatura do método para receber o inimigo
        // Por enquanto, vou mostrar a lógica correta:
        System.out.println("🎯 Tentando capturar o Javamon inimigo...");
        
        // Esta chamada precisa do inimigo - veja a modificação completa abaixo
        // boolean capturou = cube.tentarCapturar(inimigo, jogador);
        
        // Remove da bolsa se acabou
        if (cube.getQuantidade() <= 0) {
            bolsa.remove(escolha - 1);
        }
        
        // return capturou; // Se capturou, encerra a batalha
        System.out.println("⚠️ Funcionalidade de Javacube precisa de mais ajustes!");
        return true;
    }
    
    // ===== OUTROS ITENS: USA NA SUA EQUIPE =====
    System.out.println("\nUsar em qual Javamon?");
    List<Javamon> equipe = jogador.getEquipe();
    for (int i = 0; i < equipe.size(); i++) {
        Javamon j = equipe.get(i);
        String status = j.estaVivo() ? "HP: " + j.getHpATUAL() + "/" + j.getHpMAX() : "DESMAIADO";
        System.out.println((i + 1) + " - " + j.getNome() + " (" + status + ")");
    }
    System.out.println("0 - Cancelar");
    
    System.out.print("Escolha: ");
    int indiceJavamon;
    try {
        indiceJavamon = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida.");
        return false;
    }

    if (indiceJavamon == 0) {
        System.out.println("❌ Cancelado.");
        return false;
    }

    if (indiceJavamon < 1 || indiceJavamon > equipe.size()) {
        System.out.println("❌ Javamon inválido.");
        return false;
    }

    Javamon alvo = equipe.get(indiceJavamon - 1);
    
    // Aplicar efeito do item
    try {
        itemEscolhido.usar(alvo);
        
        // Diminui quantidade do item
        itemEscolhido.removerQuantidade(1);
        
        // Remove da bolsa se acabou
        if (itemEscolhido.getQuantidade() <= 0) {
            bolsa.remove(escolha - 1);
            System.out.println("❌ " + itemEscolhido.getNome() + " acabou!");
        }
        
        return true; // Usou um turno
    } catch (Exception e) {
        System.out.println("❌ Erro ao usar o item: " + e.getMessage());
        return false;
    }
}
    /**
     * Sistema de troca de Javamon
     * @return true se trocou com sucesso, false se cancelou
     */
    private static boolean trocarJavamon(Jogador jogador, Scanner sc) {
        List<Javamon> equipe = jogador.getEquipe();
        
        if (equipe.size() <= 1) {
            System.out.println("\n❌ Você só tem um Javamon na equipe!");
            return false;
        }

        System.out.println("\n=== SUA EQUIPE ===");
        Javamon atual = equipe.get(0);
        
        for (int i = 0; i < equipe.size(); i++) {
            Javamon j = equipe.get(i);
            String status = j.estaVivo() ? "HP: " + j.getHpATUAL() + "/" + j.getHpMAX() : "DESMAIADO";
            String marcador = (i == 0) ? " [EM BATALHA]" : "";
            System.out.println((i + 1) + " - " + j.getNome() + " (" + status + ")" + marcador);
        }
        System.out.println("0 - Cancelar");
        
        System.out.print("Trocar para qual Javamon? ");
        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return false;
        }

        if (escolha == 0) {
            System.out.println("❌ Cancelado.");
            return false;
        }

        if (escolha < 1 || escolha > equipe.size()) {
            System.out.println("❌ Escolha inválida.");
            return false;
        }

        int indice = escolha - 1;
        
        if (indice == 0) {
            System.out.println("❌ " + atual.getNome() + " já está em batalha!");
            return false;
        }

        Javamon novo = equipe.get(indice);
        
        if (!novo.estaVivo()) {
            System.out.println("❌ " + novo.getNome() + " está desmaiado!");
            return false;
        }

        // Realiza a troca
        equipe.set(indice, atual);
        equipe.set(0, novo);
        
        System.out.println("✅ Volte, " + atual.getNome() + "!");
        System.out.println("➡️ Vá, " + novo.getNome() + "!");
        
        return true;
    }

    private static boolean trocarJavamonForcado(Jogador jogador, Scanner sc) {
        List<Javamon> equipe = jogador.getEquipe();
        
        System.out.println("\n=== SUA EQUIPE ===");
        
        for (int i = 0; i < equipe.size(); i++) {
            Javamon j = equipe.get(i);
            String status = j.estaVivo() ? "HP: " + j.getHpATUAL() + "/" + j.getHpMAX() : "DESMAIADO";
            System.out.println((i + 1) + " - " + j.getNome() + " (" + status + ")");
        }
        System.out.println("0 - Escolher automaticamente");
        
        System.out.print("Trocar para qual Javamon? ");
        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return false;
        }

        if (escolha == 0) {
            return false; // Vai escolher automaticamente
        }

        if (escolha < 1 || escolha > equipe.size()) {
            System.out.println("❌ Escolha inválida.");
            return false;
        }

        int indice = escolha - 1;
        
        if (indice == 0) {
            System.out.println("❌ Esse Javamon está desmaiado!");
            return trocarJavamonForcado(jogador, sc); // Tenta de novo
        }

        Javamon novo = equipe.get(indice);
        
        if (!novo.estaVivo()) {
            System.out.println("❌ " + novo.getNome() + " está desmaiado!");
            return trocarJavamonForcado(jogador, sc); // Tenta de novo
        }

        // Realiza a troca - desmaiado vai para a posição escolhida
        Javamon desmaiado = equipe.get(0);
        equipe.set(indice, desmaiado);
        equipe.set(0, novo);
        
        return true;
    }
}