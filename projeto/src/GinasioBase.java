public abstract class GinasioBase {

    protected Jogador jogador;

    public GinasioBase(Jogador jogador) {
        this.jogador = jogador;
    }

    public abstract String getNomeLider();
    public abstract Javamon[] getTimeLider();

    public void entrarGinasio() {
        System.out.println("\n🏛️ Bem-vindo ao Ginásio de " + getNomeLider() + "!");
        System.out.println("⚔️  Derrote o líder para ganhar a insígnia!");

        for (Javamon inimigo : getTimeLider()) {
            System.out.println("\nLíder " + getNomeLider() + " enviou: " + inimigo.getNome());
            Batalha.lutar(jogador, inimigo);

            if (inimigo.estaVivo()) {
                System.out.println("\n❌ Você perdeu! Treine mais e volte!");
                return;
            }
        }

        System.out.println("\n🏅 Você derrotou o líder " + getNomeLider() + "!");
        jogador.addVitoriaGym();
    }
}
