public class Feuermon extends Javamon{
    public Feuermon(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "Fogo", 70, 70, 25, 15, 20, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Brasa", 15, "Fogo", 15));
        ataques.add(new Ataque("Labareda", 25, "Fogo", 10));
        ataques.add(new Ataque("Chamas", 35, "Fogo", 5));
        ataques.add(new Ataque("Incendio", 45, "Fogo", 3));
    }
}



