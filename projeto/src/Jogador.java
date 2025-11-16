import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private final String nome;
    private int dinheiro;

    private static final int MAX_EQUIPE = 6;
    private List<Javamon> equipe;
    private List<Javamon> box;
    private List<Itens> bolsa;
    private int vitoriasGym = 0;
    private boolean seTornouCampeao = false;

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

    // dinheiro
    public void ganharDinheiro(int valor){
        this.dinheiro += valor;
    }

    public void gastarDinheiro(int valor){
        if (valor < 0) return;
        if (dinheiro >= valor) {
            dinheiro -= valor;
        } else {
            System.out.println("Dinheiro insuficiente.");
        }
    }

    // adicionar Javamon / item
    public void adicionarJavamon(Javamon j) {
        if (j == null) return;
        if (equipe.size() < MAX_EQUIPE) {
            equipe.add(j);
            System.out.println(j.getNome() + " foi adicionado à sua equipe!");
        } else {
            box.add(j);
            System.out.println("Equipe cheia. " + j.getNome() + " foi enviado para o box.");
        }
    }

    public void adicionarItem(Itens item) {
        if (item == null) return;
    
        // Verifica se já existe um item do mesmo tipo na bolsa
        boolean itemEncontrado = false;
    
        for (Itens itemExistente : bolsa) {
            // Compara pelo nome do item
            if (itemExistente.getNome().equals(item.getNome())) {
                // Se encontrou, adiciona a quantidade ao item existente
                itemExistente.adicionarQuantidade(item.getQuantidade());
                itemEncontrado = true;
                System.out.println("📦 " + item.getNome() + " empilhado! Total: " + itemExistente.getQuantidade());
                break;
            }
        }
    
        // Se não encontrou, adiciona como novo item
        if (!itemEncontrado) {
            bolsa.add(item);
            System.out.println("🆕 " + item.getNome() + " adicionado à bolsa!");
        }
    }

    // Mostrar bolsa com índices
    public void mostrarBolsa() {
        if (bolsa.isEmpty()) {
            System.out.println("A bolsa de " + nome + " está vazia.");
        } else {
            System.out.println("\n=== BOLSA ===");
            for (int i = 0; i < bolsa.size(); i++) {
                Itens item = bolsa.get(i);
                System.out.println(i + ": " + item.getNome() + " (x" + item.getQuantidade() + ") - " + item.getDescricao());
            }
        }
    }

    // Mostrar equipe com índices
    public void mostrarEquipe() {
        System.out.println("\n=== EQUIPE ===");
        if (equipe.isEmpty()) {
            System.out.println(nome + " não tem javamons na equipe.");
        } else {
            for (int i = 0; i < equipe.size(); i++) {
                Javamon javamon = equipe.get(i);
                System.out.println(i + ": " + javamon.getNome() + " (Nível " + javamon.getNivel() + ")");
            }
        }
    }

    // Mostrar box
    public void mostrarBox() {
        System.out.println("\n=== BOX ===");
        if (box.isEmpty()) {
            System.out.println(nome + " não tem javamons na box.");
        } else {
            for (int i = 0; i < box.size(); i++) {
                Javamon javamon = box.get(i);
                System.out.println(i + ": " + javamon.getNome() + " (Nível " + javamon.getNivel() + ")");
            }
        }
    }

    // trocar javamon equipe <-> box
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

    // vitórias / campeão
    public int getVitoriasGym() { return vitoriasGym; }
    public void addVitoriaGym() {
        vitoriasGym++;
        System.out.println("🏅 Vitória registrada! Total de ginásios vencidos: " + vitoriasGym);
    }

    public void resetVitoriasGym() { vitoriasGym = 0; }
    public boolean getSeTornouCampeao() { return seTornouCampeao; }
    public void setSeTornouCampeao() {
        seTornouCampeao = true;
        System.out.println("🏆 Você agora é o CAMPEÃO da Liga Javamon!!!");
    }

    /* ============================
       MÉTODOS DE USAR ITENS
       ============================ */

    // Usa item no javamon da equipe
    public void usarItem(int indiceBolsa, int indiceEquipe) {
        if (indiceBolsa < 0 || indiceBolsa >= bolsa.size()) {
            System.out.println("Índice do item inválido.");
            return;
        }
        if (indiceEquipe < 0 || indiceEquipe >= equipe.size()) {
            System.out.println("Índice do javamon inválido.");
            return;
        }

        Itens item = bolsa.get(indiceBolsa);
        Javamon alvo = equipe.get(indiceEquipe);

        try {
            item.usar(alvo);
            reduzirOuRemoverItem(indiceBolsa, item);
            System.out.println("Usou " + item.getNome() + " em " + alvo.getNome() + ".");
        } catch (Exception e) {
            System.out.println("Erro ao usar o item: " + e.getMessage());
        }
    }

    // Usa item sem alvo (ex: buff no jogador)
    public void usarItem(int indiceBolsa) {
        if (indiceBolsa < 0 || indiceBolsa >= bolsa.size()) {
            System.out.println("Índice do item inválido.");
            return;
        }

        Itens item = bolsa.get(indiceBolsa);

        try {
            item.usar(null);
            reduzirOuRemoverItem(indiceBolsa, item);
            System.out.println("Usou " + item.getNome() + ".");
        } catch (Exception e) {
            System.out.println("Erro ao usar o item: " + e.getMessage());
        }
    }

    // Conveniência: usa por nome no primeiro javamon
    public boolean usarItemPorNomeNoPrimeiroJavamon(String nomeItem) {
        if (equipe.isEmpty()) {
            System.out.println("Nenhum Javamon na equipe para usar o item.");
            return false;
        }
        for (int i = 0; i < bolsa.size(); i++) {
            Itens item = bolsa.get(i);
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                usarItem(i, 0);
                return true;
            }
        }
        System.out.println("Item '" + nomeItem + "' não encontrado na bolsa.");
        return false;
    }

    // decrementa quantidade e remove item se necessário
    private void reduzirOuRemoverItem(int indiceBolsa, Itens item) {
        try {
            int qtd = item.getQuantidade();
            item.setQuantidade(Math.max(0, qtd - 1));
            if (item.getQuantidade() == 0) {
                bolsa.remove(indiceBolsa);
                System.out.println(item.getNome() + " acabou e foi removido da bolsa.");
            }
        } catch (Exception e) {
            // caso Itens não tenha get/set quantidade, ignorar
        }
    }

    /* ============================
       MÉTODOS INTERATIVOS (console)
       ============================ */

    /**
     * Cria Scanner(System.in) e chama a versão que usa Scanner.
     * Não feche esse Scanner aqui (para não fechar System.in).
     */
    public void usarItemInterativo() {
        usarItemInterativo(new Scanner(System.in));
    }

    /**
     * Versão que recebe um Scanner (recomendada se você já tiver um Scanner no main).
     * Mostra bolsa → escolhe item → mostra equipe → escolhe javamon ou usa no jogador.
     */
    public void usarItemInterativo(Scanner sc) {
        if (bolsa.isEmpty()) {
            System.out.println("A bolsa está vazia.");
            return;
        }

        mostrarBolsa();
        int indiceItem = lerInt(sc, "Digite o índice do item (ou -1 para cancelar):", -1, bolsa.size() - 1);
        if (indiceItem == -1) {
            System.out.println("Operação cancelada.");
            return;
        }
        if (indiceItem < 0 || indiceItem >= bolsa.size()) {
            System.out.println("Índice do item inválido.");
            return;
        }

        Itens item = bolsa.get(indiceItem);

        if (!equipe.isEmpty()) {
            System.out.println("Deseja usar '" + item.getNome() + "' em um Javamon (1) ou no jogador (0)?");
            int escolha = lerInt(sc, "Digite 1 para Javamon, 0 para jogador ou -1 para cancelar:", -1, 1);
            if (escolha == -1) {
                System.out.println("Operação cancelada.");
                return;
            }
            if (escolha == 1) {
                mostrarEquipe();
                int indiceJav = lerInt(sc, "Escolha o índice do Javamon para aplicar o item (ou -1 para cancelar):", -1, equipe.size() - 1);
                if (indiceJav == -1) {
                    System.out.println("Operação cancelada.");
                    return;
                }
                usarItem(indiceItem, indiceJav);
            } else {
                usarItem(indiceItem);
            }
        } else {
            System.out.println("Não há Javamons na equipe. Deseja usar o item no jogador? (1 = sim, 0 = não)");
            int escolha = lerInt(sc, "Digite 1 para usar no jogador ou 0 para cancelar:", 0, 1);
            if (escolha == 1) {
                usarItem(indiceItem);
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }

    /**
     * Helper para ler inteiro com limites. Continua pedindo até ser válido.
     * Aceita min > max caso queira tratar valores especiais (mas normalmente min <= max).
     */
    private int lerInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " ");
            String linha = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(linha);
                if (min <= max) {
                    if (v < min || v > max) {
                        System.out.println("Por favor, digite um número entre " + min + " e " + max + ".");
                        continue;
                    }
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }
}
