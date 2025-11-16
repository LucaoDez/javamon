import java.util.*;

public class Batalha {

    /**
     * Inicia uma batalha padrão (contra Javamon selvagem - pode capturar)
     */
    public static void lutar(Jogador jogador, Javamon inimigo) {
        lutar(jogador, inimigo, false);
    }

    /**
     * Inicia uma batalha com controle de captura
     * @param jogador O jogador
     * @param inimigo O Javamon inimigo
     * @param ehBatalhaTreinador true = não pode capturar (líder/campeão), false = pode capturar (selvagem)
     */
    public static void lutar(Jogador jogador, Javamon inimigo, boolean ehBatalhaTreinador) {
        Scanner sc = new Scanner(System.in);
        
        if (ehBatalhaTreinador) {
            System.out.println("\n⚔️ Batalha contra treinador!");
            System.out.println("🎯 " + inimigo.getNome() + " (Nv." + inimigo.getLvl() + ")");
        } else {
            System.out.println("\n⚔️ Um " + inimigo.getNome() + " selvagem apareceu! (Nv." + inimigo.getLvl() + ")");
        }
        
        // Escolhe primeiro Javamon vivo da equipe
        Javamon aliado = null;
        for (Javamon j : jogador.getEquipe()) {
            if (j.estaVivo()) {
                aliado = j;
                break;
            }
        }
        
        if (aliado == null) {
            System.out.println("💀 Sua equipe está toda nocauteada!");
            return;
        }
        
        while (aliado.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n🔥 " + aliado.getNome() + " HP:" + aliado.getHpATUAL() + "/" + aliado.getHpMAX());
            System.out.println("🐾 " + inimigo.getNome() + " HP:" + inimigo.getHpATUAL() + "/" + inimigo.getHpMAX());
            
            // Monta menu de opções baseado no tipo de batalha
            if (ehBatalhaTreinador) {
                System.out.println("\n[1] Atacar | [2] Trocar Javamon | [3] Usar Item (cura) | [4] Desistir");
            } else {
                System.out.println("\n[1] Atacar | [2] Trocar Javamon | [3] Usar Item | [4] Fugir");
            }
            
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) continue;
            
            int escolha = -1;
            try {
                escolha = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Opção inválida!");
                continue;
            }
            
            if (escolha == 1) {
                // Atacar
                List<Ataque> ataques = aliado.getAtaques();
                if (ataques == null || ataques.isEmpty()) {
                    System.out.println("❌ " + aliado.getNome() + " não possui ataques!");
                    continue;
                }
                
                System.out.println("\n🎯 Escolha um ataque:");
                for (int i = 0; i < ataques.size(); i++) {
                    Ataque atk = ataques.get(i);
                    System.out.printf("[%d] %s (PP: %d/%d, Poder: %d)\n", 
                        i + 1, atk.getNome(), atk.getPpATUAL(), atk.getPpMAX(), atk.getPoder());
                }
                
                entrada = sc.nextLine().trim();
                int indiceAtaque = -1;
                try {
                    indiceAtaque = Integer.parseInt(entrada) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Ataque inválido!");
                    continue;
                }
                
                if (indiceAtaque < 0 || indiceAtaque >= ataques.size()) {
                    System.out.println("❌ Ataque inválido!");
                    continue;
                }
                
                Ataque ataqueEscolhido = ataques.get(indiceAtaque);
                if (!ataqueEscolhido.podeUsar()) {
                    System.out.println("❌ " + ataqueEscolhido.getNome() + " está sem PP!");
                    continue;
                }
                
                // Aliado ataca
                aliado.atacar(inimigo, indiceAtaque);
                
                // Verifica se inimigo foi derrotado
                if (!inimigo.estaVivo()) {
                    System.out.println("\n🎉 VITÓRIA!");
                    System.out.println("✨ " + inimigo.getNome() + " foi derrotado!");
                    
                    // Recompensas (apenas em batalhas selvagens ou ajustar multiplicador para treinadores)
                    int multiplicador = ehBatalhaTreinador ? 3 : 1;
                    int expGanha = (50 + (inimigo.getLvl() * 10)) * multiplicador;
                    int dinheiroGanho = (30 + (inimigo.getLvl() * 15)) * multiplicador;
                    
                    System.out.println("\n💰 Recompensas:");
                    System.out.println("   💵 Dinheiro: +" + dinheiroGanho);
                    jogador.ganharDinheiro(dinheiroGanho);
                    
                    System.out.println("   ⭐ " + aliado.getNome() + " ganhou " + expGanha + " de EXP!");
                    
                    int nivelAntes = aliado.getLvl();
                    aliado.ganharExperiencia(expGanha);
                    int nivelDepois = aliado.getLvl();
                    
                    if (nivelDepois > nivelAntes) {
                        System.out.println("   🎊 " + aliado.getNome() + " subiu para o nível " + nivelDepois + "!");
                    }
                    
                    System.out.println("\n💼 Dinheiro total: " + jogador.getDinheiro());
                    return;
                }
                
                // Inimigo ataca (se ainda estiver vivo)
                if (inimigo.estaVivo()) {
                    List<Ataque> ataquesInimigo = inimigo.getAtaques();
                    if (ataquesInimigo != null && !ataquesInimigo.isEmpty()) {
                        Random rand = new Random();
                        int indiceAleatorio = rand.nextInt(ataquesInimigo.size());
                        inimigo.atacar(aliado, indiceAleatorio);
                        
                        if (!aliado.estaVivo()) {
                            System.out.println("\n💀 " + aliado.getNome() + " foi nocauteado!");
                            
                            // Tenta trocar para outro Javamon vivo
                            Javamon novoAliado = null;
                            for (Javamon j : jogador.getEquipe()) {
                                if (j.estaVivo() && j != aliado) {
                                    novoAliado = j;
                                    break;
                                }
                            }
                            
                            if (novoAliado == null) {
                                System.out.println("\n💀 Todos os seus Javamon foram derrotados!");
                                if (ehBatalhaTreinador) {
                                    System.out.println("😓 Você perdeu a batalha...");
                                } else {
                                    System.out.println("🏥 Você foi levado ao Centro Javamon mais próximo...");
                                }
                                return;
                            }
                            
                            aliado = novoAliado;
                            System.out.println("🔄 " + aliado.getNome() + " entrou em campo!");
                        }
                    }
                }
                
            } else if (escolha == 2) {
                // Trocar Javamon
                System.out.println("\n🔄 Escolha um Javamon:");
                List<Javamon> equipe = jogador.getEquipe();
                for (int i = 0; i < equipe.size(); i++) {
                    Javamon j = equipe.get(i);
                    String status = j.estaVivo() ? "✅" : "💀";
                    System.out.printf("[%d] %s %s (HP: %d/%d, Nv.%d)\n", 
                        i + 1, status, j.getNome(), j.getHpATUAL(), j.getHpMAX(), j.getLvl());
                }
                
                entrada = sc.nextLine().trim();
                int indiceJavamon = -1;
                try {
                    indiceJavamon = Integer.parseInt(entrada) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Escolha inválida!");
                    continue;
                }
                
                if (indiceJavamon < 0 || indiceJavamon >= equipe.size()) {
                    System.out.println("❌ Escolha inválida!");
                    continue;
                }
                
                Javamon novoAliado = equipe.get(indiceJavamon);
                if (!novoAliado.estaVivo()) {
                    System.out.println("❌ Este Javamon está nocauteado!");
                    continue;
                }
                
                if (novoAliado == aliado) {
                    System.out.println("❌ Este Javamon já está em campo!");
                    continue;
                }
                
                aliado = novoAliado;
                System.out.println("🔄 " + aliado.getNome() + " entrou em campo!");
                
                // Inimigo ataca durante a troca
                if (inimigo.estaVivo()) {
                    List<Ataque> ataquesInimigo = inimigo.getAtaques();
                    if (ataquesInimigo != null && !ataquesInimigo.isEmpty()) {
                        Random rand = new Random();
                        int indiceAleatorio = rand.nextInt(ataquesInimigo.size());
                        inimigo.atacar(aliado, indiceAleatorio);
                    }
                }
                
            } else if (escolha == 3) {
                // Usar Item (com restrição de captura em batalhas contra treinadores)
                int resultado = usarItemEmBatalha(jogador, inimigo, sc, ehBatalhaTreinador);
                if (resultado == 1) {
                    System.out.println("\n🎉 Você capturou " + inimigo.getNome() + "!");
                    return; // Batalha termina com captura
                }
                // Se usou item de cura, inimigo ainda ataca
                if (resultado == 0 && inimigo.estaVivo()) {
                    List<Ataque> ataquesInimigo = inimigo.getAtaques();
                    if (ataquesInimigo != null && !ataquesInimigo.isEmpty()) {
                        Random rand = new Random();
                        int indiceAleatorio = rand.nextInt(ataquesInimigo.size());
                        inimigo.atacar(aliado, indiceAleatorio);
                    }
                }
                
            } else if (escolha == 4) {
                // Fugir / Desistir
                if (ehBatalhaTreinador) {
                    System.out.println("❌ Você não pode desistir de uma batalha contra treinador!");
                    continue;
                } else {
                    System.out.println("🏃 Você fugiu da batalha!");
                    return;
                }
            } else {
                System.out.println("❌ Opção inválida!");
            }
        }
    }

    /**
     * Sistema de uso de itens EM BATALHA
     * @param ehBatalhaTreinador true = bloqueia uso de Javacube
     * @return 1 = capturou (encerra batalha), 0 = usou item normal, -1 = cancelou
     */
    private static int usarItemEmBatalha(Jogador jogador, Javamon inimigo, Scanner sc, boolean ehBatalhaTreinador) {
        List<Itens> bolsa = jogador.getBolsa();
        if (bolsa == null || bolsa.isEmpty()) {
            System.out.println("❌ Sua bolsa está vazia!");
            return -1;
        }

        System.out.println("\n🎒 Itens disponíveis:");
        for (int i = 0; i < bolsa.size(); i++) {
            Itens item = bolsa.get(i);
            
            // Se for batalha contra treinador, não mostra Javacube
            if (ehBatalhaTreinador && item instanceof Javacube) {
                continue;
            }
            
            System.out.printf("[%d] %s (x%d) - %s\n", 
                i + 1, item.getNome(), item.getQuantidade(), item.getDescricao());
        }
        System.out.println("[0] Cancelar");

        String entrada = sc.nextLine().trim();
        int escolha = -1;
        try {
            escolha = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("❌ Escolha inválida!");
            return -1;
        }

        if (escolha == 0) return -1;

        if (escolha < 1 || escolha > bolsa.size()) {
            System.out.println("❌ Item inválido!");
            return -1;
        }

        Itens itemEscolhido = bolsa.get(escolha - 1);

        // Bloqueia uso de Javacube em batalhas contra treinadores
        if (itemEscolhido instanceof Javacube) {
            if (ehBatalhaTreinador) {
                System.out.println("❌ Você não pode capturar Javamon de outros treinadores!");
                return -1;
            }
            
            Javacube cube = (Javacube) itemEscolhido;
            boolean capturou = cube.tentarCapturar(inimigo, jogador);
            return capturou ? 1 : 0;
        }

        // Senão, precisa escolher alvo (Javamon da equipe)
        System.out.println("\n🎯 Usar em qual Javamon?");
        List<Javamon> equipe = jogador.getEquipe();
        for (int i = 0; i < equipe.size(); i++) {
            Javamon j = equipe.get(i);
            String status = j.estaVivo() ? "✅" : "💀";
            System.out.printf("[%d] %s %s (HP: %d/%d)\n", 
                i + 1, status, j.getNome(), j.getHpATUAL(), j.getHpMAX());
        }

        entrada = sc.nextLine().trim();
        int indiceAlvo = -1;
        try {
            indiceAlvo = Integer.parseInt(entrada) - 1;
        } catch (NumberFormatException e) {
            System.out.println("❌ Escolha inválida!");
            return -1;
        }

        if (indiceAlvo < 0 || indiceAlvo >= equipe.size()) {
            System.out.println("❌ Javamon inválido!");
            return -1;
        }

        Javamon alvo = equipe.get(indiceAlvo);
        itemEscolhido.usar(alvo);

        return 0;
    }
}