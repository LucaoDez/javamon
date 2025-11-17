public class Hydreon extends Javamon{
    public Hydreon(String nome, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, "água", 65, 65, 35, 35, 40, 1, 0);
        inicializarAtaques();
    }

    @Override
    public void inicializarAtaques() {
        ataques.add(new Ataque("Jato d'água", 17, "água", 15, 15));
        ataques.add(new Ataque("Esfera d'água", 25, "água", 10, 10));
        ataques.add(new Ataque("Hidrobomba", 35, "água", 5, 5));
        ataques.add(new Ataque("Tsunami", 40, "água", 3, 3));
    }
}

