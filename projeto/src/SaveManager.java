import java.io.*;

// Classe responsável por salvar e carregar o progresso do jogador
public class SaveManager {

    // ====== SALVAR O JOGO ======
    public static void salvar(Jogador jogador, Mapa mapa, String caminho) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            writer.println("=== SAVE JAVAMON ===");
            writer.println("Nome=" + jogador.getNome());
            writer.println("Dinheiro=" + jogador.getDinheiro());
            writer.println("PosX=" + mapa.getJogadorX());
            writer.println("PosY=" + mapa.getJogadorY());

            // Equipe
            writer.println("Equipe=" + jogador.getEquipe().size());
            for (Javamon j : jogador.getEquipe()) {
                writer.println(j.getNome() + "," + j.getNivel());
            }

            // Itens
            writer.println("Itens=" + jogador.getBolsa().size());
            // aqui assumimos que Itens tem getNome() e getQuantidade()
            for (Itens i : jogador.getBolsa()) {
                writer.println(i.getNome() + "," + i.getQuantidade());
            }

            System.out.println("💾 Jogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar: " + e.getMessage());
        }
    }

    // ====== CARREGAR O JOGO ======
    public static Jogador carregar(String caminho, Mapa mapa) {
        File f = new File(caminho);
        if (!f.exists()) {
            System.out.println("⚠️ Nenhum jogo salvo encontrado.");
            return null; // ou retornar novo Jogador padrão
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String line;

            // título
            line = reader.readLine();
            if (line == null) throw new IOException("Arquivo de save inválido.");

            String nome = nextValue(reader.readLine());
            int dinheiroSalvo = parseIntSafe(nextValue(reader.readLine()), 100);
            int posX = parseIntSafe(nextValue(reader.readLine()), 0);
            int posY = parseIntSafe(nextValue(reader.readLine()), 0);

            Jogador jogador = new Jogador(nome);
            // ajustar dinheiro de forma segura
            int atual = jogador.getDinheiro();
            if (dinheiroSalvo > atual) jogador.ganharDinheiro(dinheiroSalvo - atual);
            else if (dinheiroSalvo < atual) jogador.gastarDinheiro(atual - dinheiroSalvo);

            // posiciona jogador no mapa (valida internamente no mapa)
            mapa.setPosicaoJogador(posX, posY);

            // lê equipe
            int qtdEquipe = parseIntSafe(nextValue(reader.readLine()), 0);
            for (int i = 0; i < qtdEquipe; i++) {
                line = reader.readLine();
                if (line == null) break;
                String[] dados = line.split(",", 2);
                if (dados.length < 2) continue;
                String especie = dados[0].trim();
                int nivel = parseIntSafe(dados[1].trim(), 1);

                Javamon j = criarJavamonPorNome(especie);
                if (j != null) {
                    j.setLvl(nivel);
                    jogador.adicionarJavamon(j);
                } else {
                    System.out.println("⚠️ Não foi possível recriar Javamon: " + especie);
                }
            }

            // lê itens
            int qtdItens = parseIntSafe(nextValue(reader.readLine()), 0);
            for (int i = 0; i < qtdItens; i++) {
                line = reader.readLine();
                if (line == null) break;
                String[] dados = line.split(",", 2);
                if (dados.length < 2) continue;
                String itemNome = dados[0].trim();
                int quantidade = parseIntSafe(dados[1].trim(), 1);

                // aqui assumimos que Itens tem um construtor (nome, tipo, preco)
                // e que jogador.adicionarItem adiciona uma unidade por chamada.
                for (int q = 0; q < quantidade; q++) {
                    jogador.adicionarItem(new Itens(itemNome, "", 0));
                }
            }

            System.out.println("✅ Jogo carregado com sucesso!");
            return jogador;

        } catch (IOException e) {
            System.out.println("❌ Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    // Helper: obtém parte após '=' com checagem
    private static String nextValue(String line) throws IOException {
        if (line == null) throw new IOException("Formato de save inesperado.");
        String[] parts = line.split("=", 2);
        return parts.length > 1 ? parts[1].trim() : "";
    }

    private static int parseIntSafe(String s, int defaultVal) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return defaultVal;
        }
    }

    // Fábrica simples: estenda com todas as espécies do seu jogo
    private static Javamon criarJavamonPorNome(String nome) {
        if (nome == null) return null;
        switch (nome.toLowerCase()) {
            case "feuermon":
            case "feuer": // exemplo de alias
                return new Feuermon(nome, 70, 70, 25, 15, 20, 1, 0);
            // case "outramons": return new OutraClasse(...);
            default:
                return null;
        }
    }
}