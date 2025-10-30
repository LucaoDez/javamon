public class Revive extends Itens {
    public Revive(int quantidade) {
        super("Revive", "Ressuscita um Javamon nocauteado com metade do HP.", quantidade);
    }

    @Override
    public void usar(Javamon alvo) {
        if (getQuantidade() <= 0) {
            System.out.println("Você não tem mais Revives!");
            return;
        }

        if (alvo.isVivo()) {
            System.out.println(alvo.getNome() + " já está vivo!");
            return;
        }

        alvo.reviver();
        removerQuantidade(1);
        System.out.println(alvo.getNome() + " foi revivido com metade do HP!");
    }
}
