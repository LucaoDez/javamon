public class Captura {
    private int javacubes;   // número de javacubes disponíveis
    private Javamon javamon; // o Javamon que está sendo capturado
    private int hpAtual;
    private int hpMax;

    public Captura(int javacubes, Javamon javamon, int hpAtual, int hpMax) {
        if (javacubes < 1) {
            throw new IllegalArgumentException("Você precisa ter pelo menos 1 javacube para capturar!");
        }

        this.javacubes = javacubes;
        this.javamon = javamon;
        this.hpAtual = hpAtual;
        this.hpMax = hpMax;
    }

    public int getJavacubes() {
        return javacubes;
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

    public boolean tentarCaptura() {
        // chance de captura baseada na vida atual
        double chance = (1.0 - ((double) hpAtual / hpMax)) * 100;
        double rolar = Math.random() * 100;

        System.out.println("Chance de captura: " + chance + "%");

        if (rolar <= chance) {
            System.out.println(javamon.getNome() + " foi capturado!");
            return true;
        } else {
            System.out.println(javamon.getNome() + " escapou!");
            return false;
        }
    }
}
