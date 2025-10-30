public class Pocao extends Itens {
    private int cura;

    public Pocao(int quantidade, int cura) {
        super("Poção", "Recupera HP de um Javamon.", quantidade);
        this.cura = cura;
    }

    @Override
    public void usar(Javamon alvo) {
        if (getQuantidade() <= 0) {
            System.out.println("Você não tem mais poções!");
            return;
        }

        if (!alvo.isVivo()) {
            System.out.println(alvo.getNome() + " está nocauteado e não pode ser curado!");
            return;
        }

        alvo.curar(cura);
        removerQuantidade(1);
        System.out.println(alvo.getNome() + " recuperou " + cura + " de HP!");
    }
}
    private int cura;

    public Pocao(int quantidade, int cura) {
        super("Poção", "Recupera HP de um Javamon.", quantidade);
        this.cura = cura;
    }

    @Override
    public void usar(Javamon alvo) {
        if (getQuantidade() <= 0) {
            System.out.println("Você não tem mais poções!");
            return;
        }

        if (!alvo.isVivo()) {
            System.out.println(alvo.getNome() + " está nocauteado e não pode ser curado!");
            return;
        }

        alvo.curar(cura);
        removerQuantidade(1);
        System.out.println(alvo.getNome() + " recuperou " + cura + " de HP!");
    }
}
