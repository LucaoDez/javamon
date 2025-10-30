import java.util.Random;

public class Javacube extends Itens {
    private Random random = new Random();

    public Javacube(int quantidade) {
        super("Javacube", "Usado para capturar Javamons.", quantidade);
    }

    @Override
    public void usar(Javamon alvo) {
        if (getQuantidade() <= 0) {
            System.out.println("Você não tem mais Javacubes!");
            return;
        }

        removerQuantidade(1);

        if (!alvo.isVivo()) {
            System.out.println("Você capturou facilmente " + alvo.getNome() + "!");
            return;
        }

        // Chance de captura baseada na porcentagem de HP
        double chance = 1.0 - ((double) alvo.getHpAtual() / alvo.getHpMax());
        double roll = random.nextDouble();

        if (roll < chance) {
            System.out.println("Você capturou " + alvo.getNome() + "!");
        } else {
            System.out.println(alvo.getNome() + " escapou!");
        }
    }
}
