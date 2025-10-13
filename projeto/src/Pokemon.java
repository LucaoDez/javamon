import java.util.ArrayList;

public class Pokemon extends Javamon {
    public Pokemon(String nome, String tipo, int hpMAX, int hpATUAL, int atk, int def, int spd, int lvl, int exp) {
        super(nome, tipo, hpMAX, hpATUAL, atk, def, spd, lvl, exp);
        ataques = new ArrayList<>();
    }

    public void adicionarAtaque(String nome, int poder, String tipo, int pp) {
        ataques.add(new Ataque(nome, poder, tipo, pp));
    }
}

Javamon feuermon = new Pokemon("Feuermon", "Fogo", 100, 100, 40, 30, 20, 5, 0);
Feuermon.adicionarAtaque("Chama", 40, "Fogo", 15);
feuermon.adicionarAtaque("Brasa", 50, "Fogo", 10);
feuermon.adicionarAtaque("Labareda", 60, "Fogo", 5);
feuermon.adicionarAtaque("Inferno", 80, "Fogo", 3);

Pokemon aquamon = new Pokemon("Aquamon", "Água", 110, 110, 35, 25, 22, 5, 0);
aquamon.adicionarAtaque("Jato d'Água", 40, "Água", 15);
aquamon.adicionarAtaque("Bolha", 30, "Água", 20);
aquamon.adicionarAtaque("Onda", 60, "Água", 5);
