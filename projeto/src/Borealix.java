public class Borealix extends Javamon{
    public Borealix(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "ar", 80, 80, 20, 25, 15, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Rajada de vento", 15, "ar", 15));
        ataques.add(new Ataque("Lâmina de ar", 25, "ar", 10));
        ataques.add(new Ataque("Vendaval", 35, "ar", 5));
        ataques.add(new Ataque("Furacão", 45, "ar", 3));
    }
}
