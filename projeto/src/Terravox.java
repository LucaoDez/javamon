public class Terravox extends Javamon{
    public Terravox(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "terra", 120, 120, 15, 50, 5, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Pancada de Areia", 17, "terra", 15, 15));
        ataques.add(new Ataque("Lâmina de ar", 25, "terra", 10, 10));
        ataques.add(new Ataque("Terremoto", 35, "terra", 5, 5));
        ataques.add(new Ataque("Meteoro de terra", 40, "terra", 3, 3));
    }
}