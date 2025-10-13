import java.util.*

// gerencia uma batalha contra um javamon selvagem(ataque, item, capturar, trocar, fugir)
public class Batalha{
    public static void lutar(Jogador jogador, Javamon inimigo){
        Scanner sc == new Scanner(System.in);
        Random rand = new Random();

        if(jogador.getEquipe().isEmpty()){

        }
        Javamon ativo = jogador.getEquipe().get(0);

        Sytem.out.println("\n um " + inimigo.getNome() +"selvagem apareceu");
        while (ativo.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu: " + ativo.getNome() + " HP " + ativo.getStatus().getHpAtual() + "/" + ativo.getStatus().getHpMax());
            System.out.println("Inimigo: " + inimigo.getNome() + " HP " + inimigo.getStatus().getHpAtual() + "/" + inimigo.getStatus().getHpMax());

            System.out.println("1 - Atacar | 2 - Usar Item | 3 - Mudar Javamon | 4 - Fugir");
            
        }   
    }
}