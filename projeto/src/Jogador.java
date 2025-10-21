import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private final String nome; 
    private int dinheiro;

    // coleções marcadas como final porque não são reatribuídas
    public final List<Javamon> equipe; // javamons que estão na equipe (máx. 6)
    public final List<Javamon> box;    // javamons extras do jogador
    public final List<Itens> bolsa;    // itens que o jogador possui

    public Jogador(String nome) {
        this.nome = nome;
        this.dinheiro = 100;
        this.equipe = new ArrayList<>();
        this.box = new ArrayList<>();
        this.bolsa = new ArrayList<>();
    }

    // getters
    public List<Javamon> getEquipe() { return equipe; }
    public int getDinheiro() { return dinheiro; }
    public List<Javamon> getBox() { return box; }
    public List<Itens> getBolsa() { return bolsa; }
    public String getNome() { return nome; }

    // ganha dinheiro
    public void ganharDinheiro(int valor){
        this.dinheiro += valor;
    }

    // gasta dinheiro (não deixa negativo)
    public void gastarDinheiro(int valor){
        if (valor < 0) return;
        if (dinheiro >= valor) {
            dinheiro -= valor;
        } else {
            System.out.println("Dinheiro insuficiente.");
        }
    }

    // adiciona um javamon na equipe ou box se estiver cheia
    public void adicionarJavamon(Javamon javamon) {
        if (equipe.size() < 6) {
            equipe.add(javamon);
            System.out.println(javamon.getNome() + " adicionado(a) na equipe de " + nome);
        } else {
            box.add(javamon);
            System.out.println(javamon.getNome() + " adicionado(a) na box de " + nome);
        }
    }

    // adiciona um item na bolsa
    public void adicionarItem(Itens item){
        bolsa.add(item);
    }

    // mostra itens reais na bolsa
    public void mostrarBolsa() {
        if (bolsa.isEmpty()) {
            System.out.println("A bolsa de " + nome + " está vazia.");
        } else {
            System.out.println("Itens na bolsa de " + nome + ":");
            for (Itens item : bolsa) {
                System.out.println("- " + item.getNome() + ": " + item.getDescricao());
            }
        }
    }

    // mostra equipe
    public void mostrarEquipe() {
        System.out.println("\n=== EQUIPE ===");
        if (equipe.isEmpty()) {
            System.out.println(nome + " não tem javamons na equipe.");
        } else {
            for (Javamon javamon : equipe) {
                System.out.println("- " + javamon.getNome() + " (Nível " + javamon.getNivel() + ")");
            }
        }
    }

    // mostra box
    public void mostrarBox() {
        System.out.println("\n=== BOX ===");
        if (box.isEmpty()) {
            System.out.println(nome + " não tem javamons na box.");
        } else {
            for (Javamon javamon : box) {
                System.out.println("- " + javamon.getNome() + " (Nível " + javamon.getNivel() + ")");
            }
        }
    }

    // troca javamon da equipe com um da box 
    public void trocarJavamon(int indiceEquipe, int indiceBox) {
        if (indiceEquipe < 0 || indiceEquipe >= equipe.size()) {
            System.out.println("Índice da equipe inválido.");
            return;
        }
        if (indiceBox < 0 || indiceBox >= box.size()) {
            System.out.println("Índice da box inválido.");
            return;
        }

        Javamon daEquipe = equipe.get(indiceEquipe);
        Javamon daBox = box.get(indiceBox);
        equipe.set(indiceEquipe, daBox);
        box.set(indiceBox, daEquipe);
        System.out.println("Trocado " + daEquipe.getNome() + " da equipe com " + daBox.getNome() + " da box.");
    }

    public void comprarItens(Itens item) {
        if (item == null) {
            System.out.println("Item inválido.");
            return;
        }
        int preco = item.getPreco();
        if (dinheiro >= preco) {
            gastarDinheiro(preco);
            adicionarItem(item);
            System.out.println("Você comprou: " + item.getNome() + " por " + preco + "$.");
        } else {
            System.out.println("Dinheiro insuficiente. Você tem " + dinheiro + "$, o item custa " + preco + "$.");
        }
    }
}
