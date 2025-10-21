// Classe que representa um item no jogo
public class Itens {
    private final String nome;
    private final String descricao;
    private int quantidade;
    private int preco;

    public Itens(String nome, String descricao, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    
    public String getNome() {
        return nome;
    }

    
    public String getDescricao() {
        return descricao;
    }

    
    public int getQuantidade() {
        return quantidade;
    }

    public int getPreco() {
        return preco;
    }

    
    public void adicionarQuantidade(int valor) {
        this.quantidade += valor;
    }

    
    public void removerQuantidade(int valor) {
        this.quantidade -= valor;
        if (this.quantidade < 0) this.quantidade = 0;
    }

    public void setQuantidade(int novaQuantidade) {
        this.quantidade = Math.max (0, novaQuantidade);
    }


    @Override
    public String toString() {
        return nome + " (x" + quantidade + ")";
    }
}
