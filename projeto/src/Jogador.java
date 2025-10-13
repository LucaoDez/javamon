import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome; 
    private int dinheiro;
    private List<Javamon> equipe; // javamons que estao na equipe do jogador limite de 6
    private List<Javamon> box; // javamons que nao estao na equipe, mas o jogador possui
    private List<Itens> bolsa; // itens que o jogador possui

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

    //ganha dinheiro
    public void ganharDinheiro(int valor){
        this.dinheiro += valor;
    }

    //gasta dinheiro(nao deixa negativo)
    public void gastarDinheiro(int valor){
        this.dinheiro -= valor;
        if(dinheiro < 0) dinheiro = 0;
    }

    //adiciona um javamon na equipe ou na box, caso a equipe ja tenha 6
    public void AdicionarJavamon(Javamon javamon) {
        if (equipe.size() < 6) {
            equipe.add(javamon);
            System.out.println(javamon.getNome() + " adicionado(a) na equipe de " + nome);
        } else {
            box.add(javamon);
            System.out.println(javamon.getNome() + " adicionado(a) na box de " + nome);
        }
    }

    // adiciona um item na bolsa do jogador
    public void adicionarItem(Itens itens){
        bolsa.add(itens);
    }

    //mostra itens na bolsa
    public void mostrarBolsa() {
        if (bolsa.isEmpty()) {
            System.out.println("A bolsa de " + nome + " esta vazia.");
        } else {
            System.out.println("Itens na bolsa de " + nome + ":");
            for (Item item : bolsa) {
                System.out.println("- " + item.getNome() + ": " + item.getDescricao());
            }
        }
    }

    //mostra javamons na equipe
    public void mostrarEquipe() {
        System.out.println("\n=== EQUIPE ===");
        if (equipe.isEmpty()) {
            System.out.println(nome + " nao tem javamons na equipe.");
        } else {
            System.out.println("Equipe de " + nome + ":");
            for (Javamon javamon : equipe) {
                System.out.println("- " + javamon.getNome() + " (Nivel " + javamon.getNivel() + ")");
            }
        }
    }

    //mostra javamons na box
    public void mostrarBox() {
        System.out.println("\n=== BOX ===");
        if (box.isEmpty()) {
            System.out.println(nome + " nao tem javamons na box.");
        } else {
            System.out.println("Box de " + nome + ":");
            for (Javamon javamon : box) {
                System.out.println("- " + javamon.getNome() + " (Nivel " + javamon.getNivel() + ")");
            }
        }
    }

    // troca um javamon da equipe com um da box 
    public void trocarJavamon(int indiceEquipe, int indiceBox) {
        if (indiceEquipe < 0 || indiceEquipe >= equipe.size()) {
            System.out.println("Indice da equipe invalido.");
            return;
        }
        if (indiceBox < 0 || indiceBox >= box.size()) {
            System.out.println("Indice da box invalido.");
            return;
        }
        Javamon javamonEquipe = equipe.get(indiceEquipe);
        Javamon javamonBox = box.get(indiceBox);
        equipe.set(indiceEquipe, javamonBox);
        box.set(indiceBox, javamonEquipe);
        System.out.println("Trocado " + javamonEquipe.getNome() + " da equipe com " + javamonBox.getNome() + " da box.");
    }
}