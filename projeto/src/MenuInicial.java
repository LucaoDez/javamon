import java.io.File;
import java.util.Scanner;

/**
 * Classe MenuInicial - Sistema de menu e escolha de starters
 * Separada do App.java para melhor organização
 */
public class MenuInicial {
    
    /**
     * Exibe o menu principal e retorna as informações do jogador
     * @return array [nome, starter] ou null se cancelar
     */
    public static Object[] exibirMenu() {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            limparTela();
            exibirLogoJavamon();
            exibirMenuPrincipal();
            
            System.out.print("\n> Escolha uma opção: ");
            String opcao = sc.nextLine().trim();
            
            switch (opcao) {
                case "1":
                    return novoJogo(sc);
                case "2":
                    return carregarJogo(sc);
                case "3":
                    exibirCreditos(sc);
                    break;
                case "4":
                    exibirControles(sc);
                    break;
                case "5":
                    System.out.println("\n👋 Obrigado por jogar Javamon!");
                    System.exit(0);
                default:
                    System.out.println("\n❌ Opção inválida! Pressione ENTER para continuar...");
                    sc.nextLine();
            }
        }
    }
    
    // ========== LOGO E MENU ==========
    
    private static void exibirLogoJavamon() {
        System.out.println("\n" +
            "     ██╗ █████╗ ██╗   ██╗ █████╗ ███╗   ███╗ ██████╗ ███╗   ██╗\n" +
            "     ██║██╔══██╗██║   ██║██╔══██╗████╗ ████║██╔═══██╗████╗  ██║\n" +
            "     ██║███████║██║   ██║███████║██╔████╔██║██║   ██║██╔██╗ ██║\n" +
            "██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██║╚██╔╝██║██║   ██║██║╚██╗██║\n" +
            "╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║██║ ╚═╝ ██║╚██████╔╝██║ ╚████║\n" +
            " ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝\n");
        
        System.out.println("                    ⚡ AVENTURA COMEÇA AQUI ⚡");
        System.out.println("                         Versão Beta 1.0\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n                        🎮 MENU PRINCIPAL 🎮\n");
        System.out.println("                    1️⃣  Novo Jogo");
        System.out.println("                    2️⃣  Carregar Jogo");
        System.out.println("                    3️⃣  Créditos");
        System.out.println("                    4️⃣  Controles");
        System.out.println("                    5️⃣  Sair");
        System.out.println("\n══════════════════════════════════════════════════════════════════");
    }
    
    // ========== NOVO JOGO ==========
    
    private static Object[] novoJogo(Scanner sc) {
        limparTela();
        System.out.println("\n🌟 BEM-VINDO AO MUNDO JAVAMON! 🌟\n");
        System.out.println("Uma jornada épica está prestes a começar...\n");
        
        System.out.print("📝 Digite o nome do seu treinador: ");
        String nome = sc.nextLine().trim();
        
        if (nome.isEmpty()) {
            nome = "Treinador";
        }
        
        System.out.println("\n✨ Bem-vindo, " + nome + "!");
        aguardar(1000);
        
        // ESCOLHA DO STARTER
        Javamon starter = escolherStarter(sc);
        
        if (starter == null) {
            System.out.println("\n❌ Você precisa escolher um Javamon inicial!");
            System.out.println("Pressione ENTER para tentar novamente...");
            sc.nextLine();
            return novoJogo(sc);
        }
        
        System.out.println("\n⚡ Sua aventura começa agora...");
        aguardar(1500);
        
        return new Object[]{nome, starter};
    }
    
    // ========== ESCOLHA DE STARTER ==========
    
    private static Javamon escolherStarter(Scanner sc) {
        while (true) {
            limparTela();
            System.out.println("\n🔥💧🌿⚡ ESCOLHA SEU JAVAMON INICIAL 🔥💧🌿⚡\n");
            System.out.println("══════════════════════════════════════════════════════════════════");
            
            // FEUERMON - Tank de Ataque Alto
            System.out.println("\n1️⃣  🔥 FEUERMON - O Devorador de Chamas (FOGO)");
            System.out.println("    ╔══════════════════════════════════════╗");
            System.out.println("    ║  HP:  ████████░░ 100 ⭐ MUITO ALTO  ║");
            System.out.println("    ║  ATK: ████████░░ 40  ⚔️  ALTÍSSIMO  ║");
            System.out.println("    ║  DEF: ████░░░░░░ 20  🛡️  BAIXO      ║");
            System.out.println("    ║  SPD: ██░░░░░░░░ 10  ⚡ MUITO LENTO ║");
            System.out.println("    ╚══════════════════════════════════════╝");
            System.out.println("    💥 ESTILO: ATACANTE PURO - Causa muito dano mas é lento");
            System.out.println("    💪 Forte contra: AR | 💔 Fraco contra: ÁGUA");
            
            // AQUARIL - Balanceado
            System.out.println("\n2️⃣  💧 AQUARIL - O Guardião das Marés (ÁGUA)");
            System.out.println("    ╔══════════════════════════════════════╗");
            System.out.println("    ║  HP:  ██████░░░░ 70  💚 MÉDIO       ║");
            System.out.println("    ║  ATK: ██████░░░░ 30  ⚔️  MÉDIO      ║");
            System.out.println("    ║  DEF: ██████░░░░ 30  🛡️  MÉDIO      ║");
            System.out.println("    ║  SPD: ██████░░░░ 30  ⚡ MÉDIO       ║");
            System.out.println("    ╚══════════════════════════════════════╝");
            System.out.println("    ⚖️  ESTILO: BALANCEADO - Bom em tudo, mestre em nada");
            System.out.println("    💪 Forte contra: FOGO | 💔 Fraco contra: TERRA");
            
            // TERRAVOX - Tank Defensivo
            System.out.println("\n3️⃣  🌿 TERRAVOX - O Colosso de Pedra (TERRA)");
            System.out.println("    ╔══════════════════════════════════════╗");
            System.out.println("    ║  HP:  ██████████ 120 ⭐ ALTÍSSIMO   ║");
            System.out.println("    ║  ATK: ████░░░░░░ 20  ⚔️  MUITO BAIXO║");
            System.out.println("    ║  DEF: ██████████ 50  🛡️  ALTÍSSIMO  ║");
            System.out.println("    ║  SPD: █░░░░░░░░░ 5   ⚡ MUITO LENTO ║");
            System.out.println("    ╚══════════════════════════════════════╝");
            System.out.println("    🛡️  ESTILO: MURALHA - Quase indestrutível mas ataca pouco");
            System.out.println("    💪 Forte contra: ÁGUA | 💔 Fraco contra: AR");
            
            // VENTRIX - Speedster
            System.out.println("\n4️⃣  🌪️ VENTRIX - O Furacão Veloz (AR)");
            System.out.println("    ╔══════════════════════════════════════╗");
            System.out.println("    ║  HP:  ████░░░░░░ 50  💚 BAIXO       ║");
            System.out.println("    ║  ATK: ███████░░░ 35  ⚔️  ALTO       ║");
            System.out.println("    ║  DEF: ██░░░░░░░░ 10  🛡️  MUITO BAIXO║");
            System.out.println("    ║  SPD: ██████████ 50  ⚡ ALTÍSSIMO   ║");
            System.out.println("    ╚══════════════════════════════════════╝");
            System.out.println("    💨 ESTILO: ASSASSINO - Ataca primeiro sempre!");
            System.out.println("    💪 Forte contra: TERRA | 💔 Fraco contra: FOGO");
            
            System.out.println("\n══════════════════════════════════════════════════════════════════");
            System.out.println("💡 DICA: Escolha de acordo com seu estilo de jogo!");
            System.out.print("\n> Escolha seu starter (1-4) ou 0 para voltar: ");
            
            String escolha = sc.nextLine().trim();
            
            switch (escolha) {
                case "1":
                    if (confirmarStarter(sc, "FEUERMON", "🔥", "PODER BRUTO")) {
                        return new Feuermon("Ignis", 100, 100, 40, 20, 10, 5, 0);
                    }
                    break;
                    
                case "2":
                    if (confirmarStarter(sc, "AQUARIL", "💧", "EQUILÍBRIO")) {
                        return new Aquaril("Splash", 70, 70, 30, 30, 30, 5, 0);
                    }
                    break;
                    
                case "3":
                    if (confirmarStarter(sc, "TERRAVOX", "🌿", "RESISTÊNCIA")) {
                        return new Terravox("Rocky", 120, 120, 15, 50, 5, 5, 0);
                    }
                    break;
                    
                case "4":
                    if (confirmarStarter(sc, "VENTRIX", "🌪️", "VELOCIDADE")) {
                        return new Ventrix("Aero", 50, 50, 35, 10, 50, 5, 0);
                    }
                    break;
                    
                case "0":
                    return null;
                    
                default:
                    System.out.println("\n❌ Opção inválida! Pressione ENTER...");
                    sc.nextLine();
            }
        }
    }
    
    private static boolean confirmarStarter(Scanner sc, String nome, String emoji, String estilo) {
        limparTela();
        System.out.println("\n" + emoji + " Você escolheu " + nome + "!");
        
        System.out.print("\n✨ Confirmar escolha? (S/N): ");
        if (confirmarSim(sc)) {
            System.out.println("\n🎉 Parabéns! Seu " + nome + " foi adicionado à equipe!");
            System.out.println("⚔️  Você escolheu o caminho da " + estilo + "!");
            aguardar(2000);
            return true;
        }
        return false;
    }
    
    private static boolean confirmarSim(Scanner sc) {
        String resp = sc.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("sim");
    }
    
    // ========== CARREGAR JOGO ==========
    
    private static Object[] carregarJogo(Scanner sc) {
        limparTela();
        System.out.println("\n💾 CARREGAR JOGO\n");
        
        File saveFile = new File("save.txt");
        
        if (!saveFile.exists()) {
            System.out.println("❌ Nenhum save encontrado!");
            System.out.println("\n⚠️  Inicie um novo jogo primeiro.");
            System.out.println("\nPressione ENTER para voltar...");
            sc.nextLine();
            return null;
        }
        
        System.out.println("✅ Save encontrado!");
        System.out.println("\n📂 Carregando seu progresso...");
        aguardar(1000);
        System.out.println("✅ Carregamento completo!");
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
        
        // Retorna null, null para indicar que deve carregar do save
        return new Object[]{null, null};
    }
    
    // ========== CRÉDITOS ==========
    
    private static void exibirCreditos(Scanner sc) {
        limparTela();
        System.out.println("\n🎬 CRÉDITOS 🎬\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n                    👥 EQUIPE DE DESENVOLVIMENTO");
        System.out.println("\n              • Lucas Paraíso Benning de Oliveira");
        System.out.println("              • Rafael Cavalcanti Montenegro");
        System.out.println("              • Paulo Barbosa Apolinario Neto\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n                    🎮 PROJETO JAVAMON");
        System.out.println("                  Um jogo inspirado em Pokémon");
        System.out.println("                     Feito com ❤️ em Java\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n              Obrigado por jogar nosso projeto! 🌟");
        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }
    
    // ========== CONTROLES ==========
    
    private static void exibirControles(Scanner sc) {
        limparTela();
        System.out.println("\n🎮 CONTROLES 🎮\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n                    📍 MOVIMENTAÇÃO NO MAPA");
        System.out.println("\n                    W - Mover para cima    ⬆️");
        System.out.println("                    S - Mover para baixo   ⬇️");
        System.out.println("                    A - Mover para esquerda ⬅️");
        System.out.println("                    D - Mover para direita  ➡️\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n                    ⚙️ AÇÕES GERAIS");
        System.out.println("\n                    M - Abrir Menu");
        System.out.println("                    B - Iniciar Batalha (teste)");
        System.out.println("                    Q - Salvar e Sair\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n                    ⚔️ DURANTE BATALHAS");
        System.out.println("\n                    1 - Atacar");
        System.out.println("                    2 - Usar Item");
        System.out.println("                    3 - Trocar Javamon");
        System.out.println("                    4 - Fugir\n");
        System.out.println("══════════════════════════════════════════════════════════════════");
        System.out.println("\n💡 DICA: Explore a grama alta (*) para encontrar Javamons!");
        System.out.println("💡 DICA: Entre na Liga (L) após treinar sua equipe!");
        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
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