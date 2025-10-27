public class Ventrix extends Javamon{
    public Ventrix(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "vento", 70, 70, 25, 15, 20, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Rajada de vento", 17, "água", 15));
        ataques.add(new Ataque("Pedregulho", 25, "água", 10));
        ataques.add(new Ataque("Vendaval", 35, "água", 5));
        ataques.add(new Ataque("Meteoro de terra", 40, "água", 3));
    }
}

