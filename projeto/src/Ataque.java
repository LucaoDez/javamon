public class Ataque {
    private final String nome;
    private int poder;
    private final String tipo;
    private int ppATUAL;
    private final int ppMAX;

    public Ataque(String nome, int poder, String tipo, int ppATUAL,  int ppMAX) {
        if (nome == null) throw new IllegalArgumentException("nome não pode ser nulo");
        this.nome = nome;
        this.poder = Math.max(0, poder);
        this.tipo = (tipo == null) ? "" : tipo;
        this.ppMAX = ppMAX;
        this.ppATUAL = ppATUAL;
    }

    public String getNome() { return nome; }
    public int getPoder() { return poder; }
    public String getTipo() { return tipo; }
    public int getPpATUAL() { return ppATUAL; }
    public int getPpMAX() { return ppMAX; }

    public boolean podeUsar() { return ppATUAL > 0; }

    public void reduzirPp() {
        if (ppATUAL > 0) ppATUAL--;
    }

    public void restaurarPP() { this.ppATUAL = this.ppMAX; }

    @Override
    public String toString() {
        return nome + " (" + tipo + ") poder=" + poder + " pp=" + ppATUAL;
    }
}