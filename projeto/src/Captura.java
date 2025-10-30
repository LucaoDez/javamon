public class Captura {
    private int javacubes;   // número de javacubes disponíveis nesta tentativa/contexto
    private final Javamon javamon; // o Javamon que está sendo capturado
    private final int hpAtual;
    private final int hpMax;

    public Captura(int javacubes, Javamon javamon, int hpAtual, int hpMax) {
        if (javacubes < 0) javacubes = 0;
        this.javacubes = javacubes;
        this.javamon = javamon;
        this.hpAtual = Math.max(0, hpAtual);
        this.hpMax = Math.max(1, hpMax);
    }

    public int getJavacubes() { return javacubes; }
    public Javamon getJavamon() { return javamon; }
    public int getHpAtual() { return hpAtual; }
    public int getHpMax() { return hpMax; }

    // retorna true se capturou
    public boolean tentarCaptura() {
        if (javacubes <= 0) {
            System.out.println("Sem javacubes para tentar a captura.");
            return false;
        }
        javacubes--;

        // chance básica: mais provável quanto menor o hp atual
        double chance = Math.max(5.0, (1.0 - ((double) hpAtual / hpMax)) * 100.0);
        double roll = Math.random() * 100.0;

        System.out.printf("Chance de captura: %.1f%% (rolou %.1f)%n", chance, roll);
        boolean sucesso = roll <= chance;
        System.out.println(sucesso ? javamon.getNome() + " foi capturado!" : javamon.getNome() + " escapou!");
        return sucesso;
    }

    // tenta capturar e adiciona automaticamente ao jogador em caso de sucesso
    public boolean tentarCaptura(Jogador jogador) {
        boolean ok = tentarCaptura();
        if (ok && jogador != null) {
            jogador.capturarJavamon(javamon);
        }
        return ok;
    }
}
