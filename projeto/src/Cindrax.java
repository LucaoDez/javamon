public class Cindrax extends Javamon{
    public Cindrax(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "fogo", 90, 90, 30, 15, 10, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Brasa", 15, "fogo", 15));
        ataques.add(new Ataque("Labareda", 25, "fogo", 10));
        ataques.add(new Ataque("Chamas", 35, "fogo", 5));
        ataques.add(new Ataque("Incendio", 45, "fogo", 3));
    }
}