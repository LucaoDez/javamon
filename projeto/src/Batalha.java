import java.util.*;

public class Batalha {
    public static void lutar(Jogador jogador, Javamon inimigo) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        if (jogador.getEquipe().isEmpty()) {
            System.out.println("Você não tem Javamon para batalhar!");
            return;
        }

        Javamon ativo = jogador.getEquipe().get(0);

        System.out.println("\nUm " + inimigo.getNome() + " selvagem apareceu!");
        while (ativo.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu: " + ativo.getNome() + " HP " + ativo.getHpATUAL() + "/" + ativo.getHpMAX());
            System.out.println("Inimigo: " + inimigo.getNome() + " HP " + inimigo.getHpATUAL() + "/" + inimigo.getHpMAX());

            System.out.println("1 - Atacar | 2 - Usar Item | 3 - Mudar Javamon | 4 - Fugir");
            System.out.print("Escolha: ");

            int escolha;
            try {
                escolha = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            if (escolha == 1) {
                // usa o método da classe Javamon para escolher ataque via Scanner
                int indiceAtaque = ativo.escolherAtaque(sc);
                if (indiceAtaque >= 0) {
                    ativo.atacar(inimigo, indiceAtaque);
                }
            } else if (escolha == 2) {
                System.out.println("Sistema de itens não implementado.");
            } else if (escolha == 3) {
                System.out.println("Troca de Javamon não implementada.");
            } else if (escolha == 4) {
                if (rand.nextInt(100) < 60) {
                    System.out.println("Você fugiu da batalha!");
                    return;
                } else {
                    System.out.println("Não conseguiu fugir!");
                }
            } else {
                System.out.println("Opção inválida.");
            }

            // turno do inimigo (IA simples)
            if (inimigo.estaVivo()) {
                // tenta escolher um ataque com PP > 0
                int atkIndex = -1;
                List<Ataque> listaAtks = inimigo.ataques; // acesso protegido — mesmo pacote
                if (listaAtks != null && !listaAtks.isEmpty()) {
                    for (int i = 0; i < listaAtks.size(); i++) {
                        if (listaAtks.get(i).getPp() > 0) {
                            atkIndex = i;
                            break;
                        }
                    }
                }

                if (atkIndex >= 0) {
                    inimigo.atacar(ativo, atkIndex);
                } else {
                    // sem ataques com PP: golpe fraco padrão
                    System.out.println(inimigo.getNome() + " não tem ataques disponíveis e usou um golpe fraco!");
                    ativo.levarDano(5);
                    System.out.println(ativo.getNome() + " recebeu 5 de dano!\n");
                }
            }
        }

        if (!inimigo.estaVivo()) {
            System.out.println("🎉 " + inimigo.getNome() + " foi derrotado!");
            ativo.ganharExperiencia(20); // usa método existente em Javamon
            // jogador.ganharDinheiro(15); // mantém se método existir em Jogador
        } else {
            System.out.println("💀 " + ativo.getNome() + " desmaiou!");
        }
    }
}