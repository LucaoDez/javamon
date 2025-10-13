

public class Captura {
    private Itens javacube;
    private Javamon javamon;
    private int hpAtual;
    private int hpMax;

    public Captura(Itens javacube, Javamon javamon, int hpAtual, int hpMax) {
        this.javacube = javacube;
        this.javamon = javamon;
        this.hpAtual = hpAtual;
        this.hpMax = hpMax;

        if (hpAtual > hpMax) {
            throw new IllegalArgumentException("HP atual não pode ser maior que o HP máximo.");
        }
    }

    public Itens getJavacube() {
        return javacube;
    }

    public Javamon getJavamon() {
        return javamon;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public int getHpMax() {
        return hpMax;
    }
}
