public class Ataque {
    private final String nome;
    private int poder;
    private final String tipo;
    private int pp;

    public Ataque(String nome, int poder, String tipo, int pp) {
        if (nome == null) throw new IllegalArgumentException("nome não pode ser nulo");
        this.nome = nome;
        this.poder = Math.max(0, poder);
        this.tipo = (tipo == null) ? "" : tipo;
        this.pp = Math.max(0, pp);
    }

    public String getNome() { return nome; }
    public int getPoder() { return poder; }
    public String getTipo() { return tipo; }
    public int getPp() { return pp; }

    public boolean podeUsar() { return pp > 0; }

    public void reduzirPp() {
        if (pp > 0) pp--;
    }

    public void restaurarPp(int valor) {
        if (valor <= 0) return;
        pp += valor;
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ") poder=" + poder + " pp=" + pp;
    }
}