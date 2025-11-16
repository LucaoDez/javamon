import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Mapa mapaCidade = new Mapa();
        Jogador jogador = SaveManager.carregar("save.txt", mapaCidade);
        if (jogador == null){ 
            jogador = new Jogador("Treinador");
            jogador.adicionarJavamon(new Feuermon("Ignis", 100, 100, 30, 20, 25, 5, 0));
        }

        // mapa atual começa na cidade
        Object mapaAtual = mapaCidade;

        Menu menu = new Menu(jogador);

        while (true) {
            // Desenha o mapa atual após qualquer ação
            if (mapaAtual instanceof Mapa) {
                ((Mapa) mapaAtual).mostrarMapa();
            } else if (mapaAtual instanceof MapaLiga) {
                ((MapaLiga) mapaAtual).mostrar();
            } else if (mapaAtual instanceof MapaGinasioFogo) {
                ((MapaGinasioFogo) mapaAtual).mostrar();
            } else if (mapaAtual instanceof MapaGinasioAgua) {
                ((MapaGinasioAgua) mapaAtual).mostrar();
            } else if (mapaAtual instanceof MapaGinasioTerra) {
                ((MapaGinasioTerra) mapaAtual).mostrar();
            } else if (mapaAtual instanceof MapaGinasioAr) {
                ((MapaGinasioAr) mapaAtual).mostrar();
            } else if (mapaAtual instanceof MapaCampeao) {
                ((MapaCampeao) mapaAtual).mostrar();
            }

            System.out.println("\nUse W/A/S/D para mover | M para Menu | B para batalha de teste | Q para salvar e sair");
            String entrada = sc.nextLine().trim().toLowerCase();
            if (entrada.isEmpty()) continue;
            char comando = entrada.charAt(0);

            // --- movimentação consolidada ---
            // ...existing code...
            if ("wasd".indexOf(comando) >= 0) {
                if (mapaAtual instanceof Mapa) {
                    Mapa m = (Mapa) mapaAtual;
                    m.mover(comando, jogador);

                    if (m.entrouNaLiga()) {
                        mapaAtual = new MapaLiga(jogador);
                        m.resetEntrouNaLiga();
                        continue;
                    }
                } else if (mapaAtual instanceof MapaLiga) {
                    MapaLiga ml = (MapaLiga) mapaAtual;
                    ml.mover(comando);
                    
                    // Detectar entrada em ginásio
                    String ginasio = ml.getGinasioEntrado();
                    if (ginasio != null) {
                        ml.resetGinasio();
                        
                        switch (ginasio) {
                            case "FOGO":
                                mapaAtual = new MapaGinasioFogo(jogador);
                                ((MapaGinasioFogo) mapaAtual).entrar();
                                break;
                            case "AGUA":
                                mapaAtual = new MapaGinasioAgua(jogador);
                                ((MapaGinasioAgua) mapaAtual).entrar();
                                break;
                            case "TERRA":
                                mapaAtual = new MapaGinasioTerra(jogador);
                                ((MapaGinasioTerra) mapaAtual).entrar();
                                break;
                            case "AR":b
                            
                               mapaAtual = new MapaGinasioAr(jogador);
                                ((MapaGinasioAr) mapaAtual).entrar();
                                break;
                            case "CAMPEAO":
                                mapaAtual = new MapaCampeao(jogador);
                                ((MapaCampeao) mapaAtual).entrar();
                                break;
                        }
                        continue;
                    }
                    
                    // Detectar saída da liga
                    if (ml.saiuDaLiga()) {
                        ml.resetSair();
                        mapaAtual = mapaCidade;
                        mapaCidade.entrar(jogador);
                        continue;
                    }
                } else if (mapaAtual instanceof MapaGinasioFogo) {
                    MapaGinasioFogo mg = (MapaGinasioFogo) mapaAtual;
                    if (mg.mover(comando)) {
                        // Retornou true = saiu do ginásio
                        mapaAtual = new MapaLiga(jogador);
                        continue;
                    }
                } else if (mapaAtual instanceof MapaGinasioAgua) {
                    MapaGinasioAgua mg = (MapaGinasioAgua) mapaAtual;
                    if (mg.mover(comando)) {
                        mapaAtual = new MapaLiga(jogador);
                        continue;
                    }
                } else if (mapaAtual instanceof MapaGinasioTerra) {
                    MapaGinasioTerra mg = (MapaGinasioTerra) mapaAtual;
                    if (mg.mover(comando)) {
                        mapaAtual = new MapaLiga(jogador);
                        continue;
                    }
                } else if (mapaAtual instanceof MapaGinasioAr) {
                    MapaGinasioAr mg = (MapaGinasioAr) mapaAtual;
                    if (mg.mover(comando)) {
                        mapaAtual = new MapaLiga(jogador);
                        continue;
                    }
                } else if (mapaAtual instanceof MapaCampeao) {
                    MapaCampeao mc = (MapaCampeao) mapaAtual;
                    if (mc.mover(comando)) {
                        mapaAtual = new MapaLiga(jogador);
                        continue;
                    }
                }
            }

            // comandos não relacionados a movimento
            if (comando == 'q') {
                SaveManager.salvar(jogador, mapaCidade, "save.txt");
                System.out.println("Jogo salvo. Saindo...");
                break;
            } else if (comando == 'm') {
                if (mapaAtual instanceof Mapa) {
                    menu.abrirMenu((Mapa) mapaAtual);
                } else {
                    menu.abrirMenu(mapaCidade);
                }
            } else if (comando == 'b') {
                Batalha.lutar(jogador, new Feuermon("Selvagem", 70, 70, 25, 15, 20, 1, 0));
                // O mapa será redesenhado no próximo loop
            } else if (!"wasd".contains(String.valueOf(comando))) {
                System.out.println("Comando inválido.");
            }
        }
    }
}