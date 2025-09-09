import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Javamon> equipe; // javamons que estao na equipe do jogador limite de 6
    private List<Javamon> box; // javamons que nao estao na equipe, mas o jogador possui
    private List<Item> bolsa; // itens que o jogador possui

    public Jogador(String nome) {
        this.nome = nome;
        this.equipe = new ArrayList<>();
        this.box = new ArrayList<>();
        this.bolsa = new ArrayList<>();
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

    // getters
    public List<Javamon> getEquipe() { return equipe; }
    public List<Javamon> getBox() { return box; }
    public List<Item> getBolsa() { return bolsa; }
    public String getNome() { return nome; }

    // adiciona um item na bolsa do jogador
    public void adicionarItem(Item item) { bolsa.add(item); }

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