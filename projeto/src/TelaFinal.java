import java.util.Scanner;

/**
 * Tela Final do Javamon
 * Exibida quando o jogador se torna Campeão
 */
public class TelaFinal {
    
    /**
     * Exibe a tela final completa com créditos
     * @param jogador - O jogador campeão
     */
    public static void exibir(Jogador jogador) {
        Scanner sc = new Scanner(System.in);
        
        // Animação de vitória
        exibirAnimacaoVitoria();
        aguardar(2000);
        
        // Mensagem de parabéns
        exibirParabens(jogador);
        aguardar(3000);
        
        // Estatísticas do jogador
        exibirEstatisticas(jogador);
        aguardar(3000);
        
        // Créditos completos
        exibirCreditos();
        
        // Opções finais
        exibirOpcoesFinal(sc);
    }
    
    // ========== ANIMAÇÃO DE VITÓRIA ==========
    
    private static void exibirAnimacaoVitoria() {
        limparTela();
        
        System.out.println("\n\n\n");
        System.out.println("                    ████████████████████████");
        System.out.println("                  ██                        ██");
        System.out.println("                ██    🏆  VOCÊ VENCEU! 🏆    ██");
        System.out.println("                  ██                        ██");
        System.out.println("                    ████████████████████████");
        System.out.println("\n");
        System.out.println("            ⭐ PARABÉNS, CAMPEÃO DA LIGA JAVAMON! ⭐");
        System.out.println("\n\n");
        
        // Efeito de brilho
        for (int i = 0; i < 3; i++) {
            System.out.print("                        ✨ ✨ ✨ ✨ ✨");
            aguardar(500);
            System.out.print("\r                                                  \r");
            aguardar(500);
        }
        System.out.println("                        ✨ ✨ ✨ ✨ ✨");
    }
    
    // ========== MENSAGEM DE PARABÉNS ==========
    
    private static void exibirParabens(Jogador jogador) {
        limparTela();
        
        System.out.println("\n\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                                                                  ");
        System.out.println("                  ⚡ MENSAGEM DO CAMPEÃO ⚡                       ");
        System.out.println("                                                                  ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("   Parabéns, " + jogador.getNome() + "!");
        System.out.println();
        System.out.println("   Você superou todos os desafios da Liga Javamon e derrotou");
        System.out.println("   o temível Campeão Eclipse. Sua jornada foi marcada por");
        System.out.println("   coragem, estratégia e determinação.");
        System.out.println();
        System.out.println("   Você e seus Javamons provaram que a verdadeira força vem");
        System.out.println("   da conexão entre treinador e seus companheiros.");
        System.out.println();
        System.out.println("   Seu nome entrará para a história como um dos maiores");
        System.out.println("   treinadores de todos os tempos!");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n              Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    // ========== ESTATÍSTICAS DO JOGADOR ==========
    
    private static void exibirEstatisticas(Jogador jogador) {
        limparTela();
        
        System.out.println("\n\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                                                                  ");
        System.out.println("                     📊 SUAS ESTATÍSTICAS 📊                     ");
        System.out.println("                                                                  ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("   👤 TREINADOR: " + jogador.getNome());
        System.out.println("   💰 DINHEIRO FINAL: " + jogador.getDinheiro() + "$");
        System.out.println("   🏅 GINÁSIOS VENCIDOS: " + jogador.getVitoriasGym() + "/4");
        System.out.println();
        System.out.println("   ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("   🌟 SUA EQUIPE CAMPEÃ:");
        System.out.println();
        
        // Mostra a equipe do jogador
        if (jogador.getEquipe() != null && !jogador.getEquipe().isEmpty()) {
            for (int i = 0; i < jogador.getEquipe().size(); i++) {
                Javamon j = jogador.getEquipe().get(i);
                String emoji = obterEmoji(j.getTipagem());
                System.out.println("   " + (i + 1) + ". " + emoji + " " + j.getNome() + 
                                 " (Lv." + j.getLvl() + ") - " + j.getTipagem().toUpperCase());
            }
        }
        
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n              Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    private static String obterEmoji(String tipo) {
        switch (tipo.toLowerCase()) {
            case "fogo": return "🔥";
            case "água": case "agua": return "💧";
            case "terra": return "🌿";
            case "ar": return "🌪️";
            default: return "⚡";
        }
    }
    
    // ========== CRÉDITOS COMPLETOS ==========
    
    private static void exibirCreditos() {
        limparTela();
        
        System.out.println("\n\n\n");
        System.out.println("     ██╗ █████╗ ██╗   ██╗ █████╗ ███╗   ███╗ ██████╗ ███╗   ██╗");
        System.out.println("     ██║██╔══██╗██║   ██║██╔══██╗████╗ ████║██╔═══██╗████╗  ██║");
        System.out.println("     ██║███████║██║   ██║███████║██╔████╔██║██║   ██║██╔██╗ ██║");
        System.out.println("██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██║╚██╔╝██║██║   ██║██║╚██╗██║");
        System.out.println("╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║██║ ╚═╝ ██║╚██████╔╝██║ ╚████║");
        System.out.println(" ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("                                                                  ");
        System.out.println("                         🎬 CRÉDITOS 🎬                          ");
        System.out.println("                                                                  ");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("                     👥 DESENVOLVIDO POR:");
        System.out.println();
        System.out.println("              • Lucas Paraíso Benning de Oliveira");
        System.out.println("              • Rafael Cavalcanti Montenegro");
        System.out.println("              • Paulo Barbosa Apolinario Neto");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("                       🎓 PROJETO ACADÊMICO");
        System.out.println("                     Programação Orientada a Objetos");
        System.out.println("                              Java - 2025.2");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("                      ✨ AGRADECIMENTOS ESPECIAIS:");
        System.out.println();
        System.out.println("                 • Professores e orientadores");
        System.out.println("                 • Inspiração: Pokémon (Nintendo/Game Freak)");
        System.out.println("                 • Todos que jogaram e testaram");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("                    🎮 TECNOLOGIAS UTILIZADAS:");
        System.out.println();
        System.out.println("                         • Java");
        System.out.println("                         • Padrão MVC");
        System.out.println("                         • POO (Herança, Polimorfismo)");
        System.out.println("                         • Sistema de Save/Load");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("            🌟 OBRIGADO POR JOGAR JAVAMON! 🌟");
        System.out.println();
        System.out.println("              Sua jornada foi épica, Campeão!");
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n              Pressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    // ========== OPÇÕES FINAIS ==========
    
    private static void exibirOpcoesFinal(Scanner sc) {
        while (true) {
            limparTela();
            
            System.out.println("\n\n\n");
            System.out.println("══════════════════════════════════════════════════════════════════");
            System.out.println("                                                                  ");
            System.out.println("                      🎮 O QUE FAZER AGORA? 🎮                   ");
            System.out.println("                                                                  ");
            System.out.println("══════════════════════════════════════════════════════════════════");
            System.out.println();
            System.out.println("                    1️⃣  Continuar Jogando");
            System.out.println("                    2️⃣  Ver Créditos Novamente");
            System.out.println("                    3️⃣  Salvar e Sair");
            System.out.println("                    4️⃣  Sair sem Salvar");
            System.out.println();
            System.out.println("══════════════════════════════════════════════════════════════════");
            System.out.print("\n                    > Escolha: ");
            
            String escolha = sc.nextLine().trim();
            
            switch (escolha) {
                case "1":
                    System.out.println("\n✅ Continuando sua jornada...");
                    aguardar(1500);
                    return; // Volta ao jogo
                    
                case "2":
                    exibirCreditos(); // Mostra créditos novamente
                    break;
                    
                case "3":
                    System.out.println("\n💾 Salvando seu progresso...");
                    aguardar(1000);
                    System.out.println("✅ Jogo salvo com sucesso!");
                    System.out.println("\n👋 Até a próxima, Campeão!");
                    aguardar(2000);
                    System.exit(0);
                    break;
                    
                case "4":
                    System.out.println("\n⚠️  Tem certeza? Seu progresso não será salvo!");
                    System.out.print("Digite 'sim' para confirmar: ");
                    String confirmacao = sc.nextLine().trim().toLowerCase();
                    if (confirmacao.equals("sim")) {
                        System.out.println("\n👋 Até a próxima, Campeão!");
                        aguardar(2000);
                        System.exit(0);
                    }
                    break;
                    
                default:
                    System.out.println("\n❌ Opção inválida!");
                    aguardar(1500);
            }
        }
    }
    
    // ========== UTILIDADES ==========
    
    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private static void aguardar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // Ignora
        }
    }
}