import java.io.*;
import java.util.List;

public class SaveManager {

    // ====== SALVAR O JOGO ======
    public static void salvar(Jogador jogador, Mapa mapa, String caminho) {
        if (jogador == null || mapa == null || caminho == null) {
            System.out.println("⚠️ Dados inválidos para salvar.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            writer.println("=== SAVE JAVAMON ===");
            
            // 1. DADOS DO JOGADOR
            writer.println("Nome=" + safe(jogador.getNome()));
            writer.println("Dinheiro=" + jogador.getDinheiro());
            writer.println("VitoriasGym=" + jogador.getVitoriasGym());
            
            // 2. POSIÇÃO NO MAPA
            writer.println("PosX=" + mapa.getJogadorX());
            writer.println("PosY=" + mapa.getJogadorY());

            // 3. EQUIPE (salva todos os detalhes)
            List<Javamon> equipe = jogador.getEquipe();
            writer.println("Equipe=" + (equipe == null ? 0 : equipe.size()));
            if (equipe != null) {
                for (Javamon j : equipe) {
                    // Formato: Especie|Nome|Nivel|Exp|HpAtual|HpMax|Atk|Def|Spd
                    writer.println(String.format("%s|%s|%d|%d|%d|%d|%d|%d|%d",
                        safe(j.getClass().getSimpleName()),  // classe concreta (Feuermon, Aquaril...)
                        safe(j.getNome()),
                        j.getLvl(),
                        j.getExp(),
                        j.getHpATUAL(),
                        j.getHpMAX(),
                        j.getAtk(),
                        j.getDef(),
                        j.getSpd()
                    ));
                }
            }

            // 4. BOX (mesma estrutura da equipe)
            List<Javamon> box = jogador.getBox();
            writer.println("Box=" + (box == null ? 0 : box.size()));
            if (box != null) {
                for (Javamon j : box) {
                    writer.println(String.format("%s|%s|%d|%d|%d|%d|%d|%d|%d",
                        safe(j.getClass().getSimpleName()),
                        safe(j.getNome()),
                        j.getLvl(),
                        j.getExp(),
                        j.getHpATUAL(),
                        j.getHpMAX(),
                        j.getAtk(),
                        j.getDef(),
                        j.getSpd()
                    ));
                }
            }

            // 5. INVENTÁRIO / BOLSA
            List<Itens> bolsa = jogador.getBolsa();
            writer.println("Itens=" + (bolsa == null ? 0 : bolsa.size()));
            if (bolsa != null) {
                for (Itens i : bolsa) {
                    String tipo = i.getClass().getSimpleName();
                    
                    // Para Pocao, salva também o valor de cura
                    if (i instanceof Pocao) {
                        // tenta pegar o valor de cura via reflexão
                        try {
                            java.lang.reflect.Field curaField = Pocao.class.getDeclaredField("cura");
                            curaField.setAccessible(true);
                            int cura = curaField.getInt(i);
                            writer.println(tipo + "|" + i.getQuantidade() + "|" + cura);
                        } catch (Exception e) {
                            // fallback: salva sem cura
                            writer.println(tipo + "|" + i.getQuantidade());
                        }
                    } else {
                        writer.println(tipo + "|" + i.getQuantidade());
                    }
                }
            }

            System.out.println("💾 Jogo salvo com sucesso em " + caminho + "!");

        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
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
            if (line == null || !line.contains("SAVE JAVAMON")) {
                System.out.println("⚠️ Arquivo de save inválido.");
                return null;
            }

            // 1. DADOS DO JOGADOR
            String nome = nextValue(reader.readLine());
            int dinheiroSalvo = parseIntSafe(nextValue(reader.readLine()), 100);
            int vitoriasGym = parseIntSafe(nextValue(reader.readLine()), 0);
            
            // 2. POSIÇÃO NO MAPA
            int posX = parseIntSafe(nextValue(reader.readLine()), 2);
            int posY = parseIntSafe(nextValue(reader.readLine()), 2);

            Jogador jogador = new Jogador(nome);

            // ajustar dinheiro
            int atual = jogador.getDinheiro();
            if (dinheiroSalvo > atual) jogador.ganharDinheiro(dinheiroSalvo - atual);
            else if (dinheiroSalvo < atual) jogador.gastarDinheiro(atual - dinheiroSalvo);

            // ajustar vitórias de ginásio
            try {
                java.lang.reflect.Field vField = jogador.getClass().getDeclaredField("vitoriasGym");
                vField.setAccessible(true);
                vField.setInt(jogador, vitoriasGym);
            } catch (Exception e) {
                System.out.println("⚠️ Não foi possível restaurar vitórias de ginásio.");
            }

            // posiciona jogador no mapa
            if (mapa != null) mapa.setPosicaoJogador(posX, posY);

            // 3. LER EQUIPE
            int qtdEquipe = parseIntSafe(nextValue(reader.readLine()), 0);
            for (int i = 0; i < qtdEquipe; i++) {
                line = reader.readLine();
                if (line == null) break;
                Javamon j = parsearJavamon(line);
                if (j != null) {
                    jogador.adicionarJavamon(j);
                }
            }

            // 4. LER BOX
            int qtdBox = parseIntSafe(nextValue(reader.readLine()), 0);
            for (int i = 0; i < qtdBox; i++) {
                line = reader.readLine();
                if (line == null) break;
                Javamon j = parsearJavamon(line);
                if (j != null && jogador.getBox() != null) {
                    jogador.getBox().add(j);
                }
            }

            // 5. LER INVENTÁRIO
            int qtdItens = parseIntSafe(nextValue(reader.readLine()), 0);
            for (int i = 0; i < qtdItens; i++) {
                line = reader.readLine();
                if (line == null) break;
                String[] dados = line.split("\\|");
                if (dados.length < 2) continue;
                
                String tipoItem = dados[0].trim();
                int quantidade = parseIntSafe(dados[1].trim(), 1);
                
                // Para Pocao, pode ter um terceiro parâmetro (cura)
                int cura = 50; // valor padrão
                if (dados.length >= 3) {
                    cura = parseIntSafe(dados[2].trim(), 50);
                }

                try {
                    Itens item = criarItem(tipoItem, quantidade, cura);
                    if (item != null && jogador.getBolsa() != null) {
                        jogador.getBolsa().add(item);
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Não foi possível carregar item: " + tipoItem);
                }
            }

            System.out.println("✅ Jogo carregado com sucesso!");
            System.out.println("📊 Equipe: " + jogador.getEquipe().size() + " | Box: " + (jogador.getBox() == null ? 0 : jogador.getBox().size()) + " | Dinheiro: " + jogador.getDinheiro());
            return jogador;

        } catch (IOException e) {
            System.out.println("❌ Erro ao carregar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // ====== HELPERS PRIVADOS ======

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
        return s == null ? "" : s.replace("\n", "").replace("\r", "").replace("|", "");
    }

    // Parseia linha do formato: Especie|Nome|Nivel|Exp|HpAtual|HpMax|Atk|Def|Spd
    private static Javamon parsearJavamon(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        
        String[] dados = line.split("\\|");
        if (dados.length < 9) {
            System.out.println("⚠️ Linha de Javamon inválida: " + line);
            return null;
        }

        try {
            String especie = dados[0].trim();
            String nome = dados[1].trim();
            int nivel = parseIntSafe(dados[2], 1);
            int exp = parseIntSafe(dados[3], 0);
            int hpAtual = parseIntSafe(dados[4], 70);
            int hpMax = parseIntSafe(dados[5], 70);
            int atk = parseIntSafe(dados[6], 25);
            int def = parseIntSafe(dados[7], 15);
            int spd = parseIntSafe(dados[8], 20);

            Javamon j = criarJavamonPorEspecie(especie, nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
            return j;

        } catch (Exception e) {
            System.out.println("⚠️ Erro ao parsear Javamon: " + e.getMessage());
            return null;
        }
    }

    // Fábrica de Javamon por nome de classe
    private static Javamon criarJavamonPorEspecie(String especie, String nome, int hpMax, int hpAtual, int atk, int def, int spd, int nivel, int exp) {
        Javamon j = null;
        
        switch (especie) {
            case "Feuermon":
                j = new Feuermon(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Aquaril":
                j = new Aquaril(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Hydreon":
                j = new Hydreon(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Ventrix":
                j = new Ventrix(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Terravox":
                j = new Terravox(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Mudrill":
                j = new Mudrill(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Cindrax":
                j = new Cindrax(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            case "Borealix":
                j = new Borealix(nome, hpMax, hpAtual, atk, def, spd, nivel, exp);
                break;
            default:
                System.out.println("⚠️ Espécie desconhecida: " + especie);
                return null;
        }

        return j;
    }

    // Fábrica de Itens por nome de classe
    private static Itens criarItem(String tipoItem, int quantidade, int cura) {
        try {
            switch (tipoItem) {
                case "Pocao":
                    return new Pocao(quantidade, cura);
                case "Revive":
                    return new Revive(quantidade);
                case "Javacube":
                    return new Javacube(quantidade);
                default:
                    System.out.println("⚠️ Tipo de item desconhecido: " + tipoItem);
                    return new Itens(tipoItem, "Item carregado do save", quantidade) {
                        @Override
                        public void usar(Javamon alvo) {
                            System.out.println("Este item não tem efeito.");
                        }
                    };
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar item " + tipoItem + ": " + e.getMessage());
            return null;
        }
    }
}