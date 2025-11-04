# Projeto: Javamon
(Versão Beta)

👥 Integrantes do grupo

 - Lucas Paraíso Benning de Oliveira
 - Rafael Cavalcanti Montenegro
 - Paulo Barbosa Apolinario Neto


⚙️ Instruções de Execução:

- Clone o repositório GitHub com o seguinte comando:

git clone https://github.com/LucaoDez/javamon


- Abra o projeto em uma IDE Java.

- Compile e execute o projeto com os seguintes comandos:

javac src/Main.java
java src/Main  


- O jogo será iniciado no terminal (modo CLI).

  <img width="633" height="150" alt="image" src="https://github.com/user-attachments/assets/f2542b3c-8301-438e-b3fc-cd0c8e18fc61" />  


🧱 Estrutura de Pacotes e Classes:
A organização do projeto segue o padrão MVC (Model-View-Controller), garantindo modularidade e separação de responsabilidades.  
Alguns pacotes ainda estão em desenvolvimento e serão implementados nas próximas etapas.

- model:  
-Javamon.java: Classe base de todos os monstros;  
-Feuermon.java: Javamon de tipo Fogo;  
-Ataque.java: Representa os ataques dos Javamons;
-Itens.java: Representa os itens que podem ser usados no jogo.  

- controller:  
-Batalha.java: Controla os combates em turnos;  
-Captura.java: Gerencia o sistema de captura;  
-Menu.java: Exibe o menu do jogo.

- view:  
No momento, a interface está sendo testada diretamente no terminal (CLI).

- service:
-SaveManager.java: Salva e carrega o progresso do jogador.  

- util:  
(em desenvolvimento) Destinado a armazenar classes utilitárias e funções auxiliares,
como geradores aleatórios (RandomGenerator) e manipuladores de texto ou arquivos.

- App.java:  
Classe principal que inicia o jogo


📸 Prints:
Ao iniciar o jogo pela primeira vez, aparecerá a seguinte tela:
<img width="633" height="150" alt="image" src="https://github.com/user-attachments/assets/efce81f3-e699-44ca-92f2-8ab2c83564d7" />


Após usar W, A, S ou D, o jogador "@" irá se movimentar pelo mapa:
<img width="603" height="105" alt="image" src="https://github.com/user-attachments/assets/443d579b-4d85-40a2-b7cf-c26b7c750f10" />
<img width="624" height="111" alt="image" src="https://github.com/user-attachments/assets/da46a478-b0ae-4651-b0ba-888a9dd47a1c" />
<img width="633" height="111" alt="image" src="https://github.com/user-attachments/assets/1ccede2e-e904-4741-b729-9a0a49501e56" />
<img width="604" height="107" alt="image" src="https://github.com/user-attachments/assets/f9446ce9-a5f0-4586-8234-c56d33e0df1e" />


Apertando M, o menu do jogo aparecerá, mostrando as opções disponíveis ao jogador:
<img width="609" height="224" alt="image" src="https://github.com/user-attachments/assets/715a8c92-6b3a-4111-96b0-8af45fa9e8ad" />


Apertando 1 no menu, o terminal mostrará os javamons presentes na equipe do jogador:  
<img width="186" height="219" alt="image" src="https://github.com/user-attachments/assets/2e82f749-71fb-455d-8327-e49d2ddfcfba" />


Apertando 2 no menu, o terminal mostrará os javamons que o jogador possui, mas que não estão em sua equipe:
<img width="260" height="225" alt="image" src="https://github.com/user-attachments/assets/98bb548a-0ede-4b23-88d1-6fcd36ba3092" />


Apertando 3 no menu, o terminal mostrará o inventario do jogador, cintendo os itens que ele possui:  
<img width="255" height="205" alt="image" src="https://github.com/user-attachments/assets/78636a51-845d-41cd-8df9-2cfe2614cb75" />


Apertando 4 no menu, o terminal mostrará a loja:  
<img width="190" height="121" alt="image" src="https://github.com/user-attachments/assets/e41e17d5-de11-438e-aed1-048684e97206" />


Apertando 5, o jogo será salvo:  
<img width="219" height="211" alt="image" src="https://github.com/user-attachments/assets/ab86f990-39c0-44a5-8494-9d347ab493ff" />


Apertando 6, a equipe do jogador será curada.


Apertando 7, o código será encerrado:  
<img width="405" height="244" alt="image" src="https://github.com/user-attachments/assets/e16b2f4f-ce61-48b9-80a4-9bba7dad19da" />


Apertando 8, o jogador voltará ao mapa:  
<img width="610" height="311" alt="image" src="https://github.com/user-attachments/assets/3ea657b0-f715-4c89-9648-beff00a17dca" />


No mapa, ao apertar B(função temporária), o jogador começará uma batalha com um javamon selvagem:
<img width="629" height="164" alt="image" src="https://github.com/user-attachments/assets/6aeabd2a-91e1-478f-83de-7895404349c2" />


Apertando 1 na batalha, o jogador terá a opção de escolhar 1 dos ataques de seu javamon:
<img width="428" height="358" alt="image" src="https://github.com/user-attachments/assets/2c9dcc36-36d8-4796-9ea7-f4a71dd8babe" />


Apertando 2, o jogador poderá usar um de seus itens em seu inventário, e apertando 3, o jogador poderá trocar o javamon ativo com algum javamon de sua equipe(sistemas ainda não implementados);


Apertando 4, o jogador terá uma chance de fugir da batalha:  
<img width="414" height="197" alt="image" src="https://github.com/user-attachments/assets/16c34891-b96d-45b7-9f55-c874cbeaed4e" />
