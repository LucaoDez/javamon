import java.util.*;

public class Batalha {
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
                turnoJogador = usarItem(jogador, sc);
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

            // Turno do inimigo (apenas se o jogador usou turno)
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
                    System.out.println(inimigo.getNome() + " não tem ataques disponíveis e usou um golpe fraco!");
                    ativo.levarDano(5);
                    System.out.println(ativo.getNome() + " recebeu 5 de dano!\n");
                }
            }

            // Verifica se o Javamon ativo desmaiou e troca automaticamente
            if (!ativo.estaVivo()) {
                System.out.println("💀 " + ativo.getNome() + " desmaiou!");
                
                // Remove o Javamon desmaiado da primeira posição
                jogador.getEquipe().remove(0);
                
                // Verifica se ainda tem Javamon disponível
                if (jogador.getEquipe().isEmpty()) {
                    System.out.println("\n❌ Você não tem mais Javamon para lutar!");
                    System.out.println("💀 Você foi derrotado!");
                    return;
                }
                
                // Mostra Javamon disponíveis para troca forçada
                System.out.println("\nEscolha um Javamon para continuar:");
                if (trocarJavamon(jogador, sc)) {
                    ativo = jogador.getEquipe().get(0);
                    System.out.println("➡️ " + ativo.getNome() + " entrou em campo!");
                } else {
                    // Se não conseguiu trocar, pega o primeiro disponível
                    ativo = jogador.getEquipe().get(0);
                    System.out.println("➡️ " + ativo.getNome() + " foi enviado automaticamente!");
                }
            }
        }

        if (!inimigo.estaVivo()) {
            System.out.println("\n🎉 " + inimigo.getNome() + " foi derrotado!");
            ativo.ganharExperiencia(20);
        }
    }

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
        
        // Escolher em qual Javamon usar o item
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
        
        // Aplicar efeito do item usando o método da própria classe Itens
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
}