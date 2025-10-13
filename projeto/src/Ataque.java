public class Ataque {
    private String nome;
    private int poder;
    private String tipo;
    private int pp;

    public Ataque(String nome, int poder, String tipo, int pp) {
        this.nome = nome;
        this.poder = poder;
        this.tipo = tipo;
        this.pp = pp;
    }

    public String getNome() { return nome; }
    public int getPoder() { return poder; }
    public String getTipo() { return tipo; }
    public int getPp() { return pp; }

    public void reduzirPp() {
        if (pp > 0) pp--;
    }

    public void restaurarPp(int valor) {
        pp += valor;
    }
}
