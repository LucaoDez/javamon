public class App {
    public static void main(String[] args) throws Exception {
        Mapa mapa = new Mapa();

        // Loop principal do jogo
        while (true) {
            mapa.mostrarMapa();
            System.out.println("\nUse W/A/S/D para mover | Q para sair");
            char comando = sc.next().toLowerCase().charAt(0);
            if (comando == 'q') break;

            mapa.mover(comando, Jogador);
        }
    }
}
