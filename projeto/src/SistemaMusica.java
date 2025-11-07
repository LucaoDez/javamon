import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Sistema de música do Javamon
 * Gerencia todas as músicas do jogo
 */
public class SistemaMusica {
    private static Clip musicaAtual;
    private static Clip musicaMenu;
    private static Clip musicaMapa;
    private static Clip musicaBatalha;
    private static boolean musicaHabilitada = true;
    
    /**
     * Inicializa o sistema de música
     * Carrega todos os arquivos de áudio
     */
    public static void inicializar() {
        try {
            System.out.println("🎵 Carregando sistema de áudio...");
            
            musicaMenu = carregarAudio("assets/music/menu.wav");
            musicaMapa = carregarAudio("assets/music/mapa.wav");
            musicaBatalha = carregarAudio("assets/music/batalha.wav");
            
            if (musicaMenu != null || musicaMapa != null || musicaBatalha != null) {
                System.out.println("✅ Sistema de áudio carregado!");
            } else {
                System.out.println("⚠️ Nenhuma música encontrada. Jogo continuará sem som.");
                exibirInstrucoes();
                musicaHabilitada = false;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Erro ao carregar músicas: " + e.getMessage());
            musicaHabilitada = false;
        }
    }
    
    /**
     * Exibe instruções de como adicionar músicas
     */
    private static void exibirInstrucoes() {
        System.out.println("\n💡 COMO ADICIONAR MÚSICA AO JOGO:");
        System.out.println("   1. Crie a pasta: projeto/assets/music/");
        System.out.println("   2. Adicione arquivos .wav com os nomes:");
        System.out.println("      - menu.wav (música do menu inicial)");
        System.out.println("      - mapa.wav (música durante exploração)");
        System.out.println("      - batalha.wav (música durante batalhas)");
        System.out.println("   3. Reinicie o jogo\n");
    }
    
    /**
     * Carrega um arquivo de áudio
     */
    private static Clip carregarAudio(String caminho) {
        try {
            File audioFile = new File(caminho);
            if (!audioFile.exists()) {
                return null;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("⚠️ Não foi possível carregar: " + caminho);
            return null;
        }
    }
    
    /**
     * Toca uma música específica
     */
    private static void tocar(Clip musica) {
        if (!musicaHabilitada || musica == null) return;
        
        try {
            musica.setFramePosition(0);
            musica.start();
            musica.loop(Clip.LOOP_CONTINUOUSLY);
            musicaAtual = musica;
        } catch (Exception e) {
            // Ignora erros de áudio
        }
    }
    
    /**
     * Para a música atual
     */
    public static void parar() {
        if (musicaAtual != null && musicaAtual.isRunning()) {
            musicaAtual.stop();
        }
    }
    
    /**
     * Troca para uma nova música
     */
    private static void trocar(Clip novaMusica) {
        parar();
        tocar(novaMusica);
    }
    
    // ========== MÉTODOS PÚBLICOS ==========
    
    /**
     * Toca a música do menu
     */
    public static void tocarMenu() {
        trocar(musicaMenu);
    }
    
    /**
     * Toca a música do mapa
     */
    public static void tocarMapa() {
        trocar(musicaMapa);
    }
    
    /**
     * Toca a música de batalha
     */
    public static void tocarBatalha() {
        trocar(musicaBatalha);
    }
    
    /**
     * Habilita ou desabilita a música
     */
    public static void setHabilitada(boolean habilitada) {
        musicaHabilitada = habilitada;
        if (!habilitada) {
            parar();
        }
    }
    
    /**
     * Verifica se a música está habilitada
     */
    public static boolean isHabilitada() {
        return musicaHabilitada;
    }
    
    /**
     * Libera recursos de áudio
     */
    public static void encerrar() {
        parar();
        if (musicaMenu != null) musicaMenu.close();
        if (musicaMapa != null) musicaMapa.close();
        if (musicaBatalha != null) musicaBatalha.close();
    }
}