public class Ventrix extends Javamon{
    public Ventrix(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "ar", 70, 70, 25, 15, 20, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Rajada de vento", 17, "ar", 15));
        ataques.add(new Ataque("Lâmina de ar", 25, "ar", 10));
        ataques.add(new Ataque("Vendaval", 35, "ar", 5));
        ataques.add(new Ataque("Furacão", 40, "ar", 3));
    }
}

