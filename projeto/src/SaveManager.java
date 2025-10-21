public class SaveManager {
    public static void salvar(Jogador jogador, Mapa mapa, String caminho) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))){
            writer.println("=== SAVE JAVAMON ===");
            writer.println("jogador: " + jogador.getNome());
            writer.println("dinheiro: " + jogador.getDinheiro());
            writer.println("mapa: " + mapa.getNome());
            writer.println("posicao: " + jogador.getPosicaoX() + "," + jogador.getPosicaoY());

            // equipe do jogador
            writer.println()
        }
    }
}