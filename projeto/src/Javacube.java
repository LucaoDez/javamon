import java.util.Random;

public class Javacube extends Itens {
    private Random random = new Random();

    public Javacube(int quantidade) {
        super("Javacube", "Usado para capturar Javamons. Só pode ser usado em batalha!", quantidade);
    }

    @Override
    public void usar(Javamon alvo) {
        // Javacube NÃO pode ser usado fora de batalha
        System.out.println("❌ Javacubes só podem ser usadas durante batalhas!");
        System.out.println("⚠️ Use a opção 'Usar Item' durante uma batalha para capturar!");
    }

    // Método especial para uso em batalha (chamado pela classe Batalha)
    public boolean tentarCapturar(Javamon alvo, Jogador jogador) {
        if (getQuantidade() <= 0) {
            System.out.println("❌ Você não tem mais Javacubes!");
            return false;
        }

        if (alvo == null) {
            System.out.println("❌ Nenhum alvo válido!");
            return false;
        }

        // Consome a Javacube
        removerQuantidade(1);
        System.out.println("🎯 Você lançou uma Javacube!");

        // Javamon já desmaiado = captura garantida
        if (!alvo.estaVivo()) {
            System.out.println("✅ Captura bem-sucedida! " + alvo.getNome() + " foi capturado!");
            if (jogador != null) {
                // Cura o Javamon capturado para metade do HP
                alvo.setHpATUAL(alvo.getHpMAX() / 2);
                jogador.adicionarJavamon(alvo);
            }
            return true;
        }

        // Chance de captura baseada no HP restante
        // Quanto menor o HP, maior a chance
        double porcentagemHP = (double) alvo.getHpATUAL() / alvo.getHpMAX();
        double chanceBase = (1.0 - porcentagemHP) * 100.0;
        
        // Garante uma chance mínima de 5%
        double chanceReal = Math.max(5.0, chanceBase);
        
        double roll = random.nextDouble() * 100.0;

        System.out.printf("💫 Chance de captura: %.1f%%\n", chanceReal);
        
        // Animação de captura
        try {
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.println();
        } catch (InterruptedException e) {
            // Ignora erro de sleep
        }

        if (roll <= chanceReal) {
            System.out.println("🎉 Sucesso! " + alvo.getNome() + " foi capturado!");
            if (jogador != null) {
                jogador.adicionarJavamon(alvo);
            }
            return true;
        } else {
            System.out.println("❌ Oh não! " + alvo.getNome() + " escapou da Javacube!");
            return false;
        }
    }
}