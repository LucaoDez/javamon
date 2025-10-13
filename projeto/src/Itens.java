public class Itens {
    // Variáveis de instância (atributos)
    public int javacubes;
    public int revive;
    public int potion;

    // Construtor
    public Itens(int javacubes, int revive, int potion) {
        this.javacubes = javacubes;
        this.revive = revive;
        this.potion = potion;
    }

    // Método exemplo: mostrar os itens
    public void mostrarItens() {
        System.out.println("JavaCubes: " + javacubes);
        System.out.println("Revive: " + revive);
        System.out.println("Potion: " + potion);
    }
}
