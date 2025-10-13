import java.util.*;

// Sistema de batalha completo, agora com verificação de PP
public class Batalha {
    public static void lutar(Jogador jogador, Javamon inimigo) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        if (jogador.getEquipe().isEmpty()) {
            System.out.println("Você não tem Javamon para batalhar!");
            return;
        }

        Javamon ativo = jogador.getEquipe().get(0);

        System.out.println("\n Um " + inimigo.getNome() + " selvagem apareceu!");
        while (ativo.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu: " + ativo.getNome() + " HP " + ativo.getStatus().getHpAtual() + "/" + ativo.getStatus().getHpMax());
            System.out.println("Inimigo: " + inimigo.getNome() + " HP " + inimigo.getStatus().getHpAtual() + "/" + inimigo.getStatus().getHpMax());

            System.out.println("1 - Atacar | 2 - Usar Item | 3 - Mudar Pokémon | 4 - Fugir");
            System.out.print("Escolha: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            if (escolha == 1) {
                List<Ataque> atks = ativo.getAtaques();
                if (atks.isEmpty()) {
                    System.out.println(ativo.getNome() + " não tem ataques!");
                } else {
                    System.out.println("\nEscolha um ataque:");
                    for (int i = 0; i < atks.size(); i++) {
                        Ataque atk = atks.get(i);
                        System.out.println((i + 1) + " - " + atk.getNome() + " (Dano " + atk.getDano() +
                                           " | PP " + atk.getPpAtual() + "/" + atk.getPpMax() + ")");
                    }

                    System.out.print("Escolha: ");
                    int ai = sc.nextInt() - 1;
                    sc.nextLine();

                    if (ai >= 0 && ai < atks.size()) {
                        Ataque escolhido = atks.get(ai);
                        if (!escolhido.usarAtaque()) {
                            System.out.println("❌ Esse ataque não tem mais PP!");
                        } else {
                            inimigo.receberDano(escolhido.getDano());
                            System.out.println(ativo.getNome() + " usou " + escolhido.getNome() + "!");
                        }
                    }
                }
            }
            else if (escolha == 4) {
                if (rand.nextInt(100) < 60) {
                    System.out.println("🏃 Você fugiu da batalha!");
                    return;
                } else {
                    System.out.println("❌ Não conseguiu fugir!");
                }
            }

            // turno do inimigo
            if (inimigo.estaVivo()) {
                inimigo.receberDano(0);
                ativo.receberDano(5);
                System.out.println(inimigo.getNome() + " atacou!");
            }
        }

        if (!inimigo.estaVivo()) {
            System.out.println("🎉 " + inimigo.getNome() + " foi derrotado!");
            ativo.ganharXP(20);
            jogador.ganharDinheiro(15);
        } else {
            System.out.println("💀 " + ativo.getNome() + " desmaiou!");
        }
    }
}