// ...existing code...
import java.io.*;
import java.util.List;

public class SaveManager {

    // ====== SALVAR O JOGO ======
    public static void salvar(Jogador jogador, Mapa mapa, String caminho) {
        if (jogador == null || mapa == null || caminho == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            writer.println("=== SAVE JAVAMON ===");
            writer.println("Nome=" + safe(jogador.getNome()));
            writer.println("Dinheiro=" + jogador.getDinheiro());
            writer.println("PosX=" + mapa.getJogadorX());
            writer.println("PosY=" + mapa.getJogadorY());

            // Equipe
            List<Javamon> equipe = jogador.getEquipe();
            writer.println("Equipe=" + (equipe == null ? 0 : equipe.size()));
            if (equipe != null) {
                for (Javamon j : equipe) {
                    // usa getNome() e getLvl() (ajuste se o método for diferente)
                    writer.println(safe(j.getNome()) + "," + j.getLvl());
                }
            }

            // Itens
            List<Itens> bolsa = jogador.getBolsa();
            writer.println("Itens=" + (bolsa == null ? 0 : bolsa.size()));
            if (bolsa != null) {
                for (Itens i : bolsa) {
                    writer.println(safe(i.getNome()) + "," + i.getQuantidade());
                }
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
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String line = reader.readLine(); // título
            if (line == null) throw new IOException("Arquivo de save inválido.");

            String nome = nextValue(reader.readLine());
            int dinheiroSalvo = parseIntSafe(nextValue(reader.readLine()), 100);
            int posX = parseIntSafe(nextValue(reader.readLine()), 0);
            int posY = parseIntSafe(nextValue(reader.readLine()), 0);

            Jogador jogador = new Jogador(nome);

            // ajustar dinheiro de forma segura (usa métodos existentes)
            int atual = jogador.getDinheiro();
            if (dinheiroSalvo > atual) jogador.ganharDinheiro(dinheiroSalvo - atual);
            else if (dinheiroSalvo < atual) jogador.gastarDinheiro(atual - dinheiroSalvo);

            // posiciona jogador no mapa
            if (mapa != null) mapa.setPosicaoJogador(posX, posY);

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
                    // usa adicionarJavamon para respeitar lógica de equipe/box
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

                // adiciona itens diretamente na bolsa (usa adicionarItem se existir)
                if (jogador.getBolsa() != null) {
                    for (int q = 0; q < quantidade; q++) {
                        jogador.getBolsa().add(new Itens(itemNome, "", 0));
                    }
                } else {
                    // fallback: tentar método adicionarItem
                    try { for (int q = 0; q < quantidade; q++) jogador.adicionarItem(new Itens(itemNome, "", 0)); } catch (Throwable ignored) {}
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

    private static String safe(String s) {
        return s == null ? "" : s.replace("\n", "").replace("\r", "");
    }

    // Fábrica simples: estenda com todas as espécies do seu jogo
    private static Javamon criarJavamonPorNome(String nome) {
        if (nome == null) return null;
        switch (nome.toLowerCase()) {
            case "feuermon":
            case "feuer":
                return new Feuermon(nome, 70, 70, 25, 15, 20, 1, 0);
            case "aquaril":
                return new Aquaril(nome, 70, 70, 25, 15, 20, 1, 0);
            case "hydreon":
                return new Hydreon(nome, 70, 70, 25, 15, 20, 1, 0);
            case "ventrix":
                return new Ventrix(nome, 70, 70, 25, 15, 20, 1, 0);
            case "terravox":
                return new Terravox(nome, 70, 70, 25, 15, 20, 1, 0);
            case "mudrill":
                return new Mudrill(nome, 70, 70, 25, 15, 20, 1, 0);
            case "cindrax":
                return new Cindrax(nome, 70, 70, 25, 15, 20, 1, 0);
            case "borealix":
                return new Borealix(nome, 70, 70, 25, 15, 20, 1, 0);
            default:
                return null;
        }
    }
}
// ...existing code...