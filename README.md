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

  <img width="625" height="230" alt="image" src="https://github.com/user-attachments/assets/9b9ba7c0-dfa1-4b50-8d1c-723846069996" />



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
<img width="625" height="230" alt="image" src="https://github.com/user-attachments/assets/9f1ee555-a074-465e-bfee-1861a94b511a" />



Após usar W, A, S ou D, o jogador "@" irá se movimentar pelo mapa:
<img width="619" height="226" alt="image" src="https://github.com/user-attachments/assets/0e4871e1-cfab-4ac2-b257-6098caca9274" />

<img width="612" height="222" alt="image" src="https://github.com/user-attachments/assets/c5b0e492-b344-43cc-b112-845601efd738" />

<img width="628" height="221" alt="image" src="https://github.com/user-attachments/assets/aef7b7a7-24e0-470f-bc66-1f8d4af369ff" />

<img width="617" height="227" alt="image" src="https://github.com/user-attachments/assets/39314d6a-5350-4926-aac9-1700e49e4ffc" />



Apertando M, o menu do jogo aparecerá, mostrando as opções disponíveis ao jogador:
<img width="609" height="224" alt="image" src="https://github.com/user-attachments/assets/715a8c92-6b3a-4111-96b0-8af45fa9e8ad" />


Apertando 1 no menu, o terminal mostrará os javamons presentes na equipe do jogador:  
<img width="186" height="219" alt="image" src="https://github.com/user-attachments/assets/2e82f749-71fb-455d-8327-e49d2ddfcfba" />


Apertando 2 no menu, o terminal mostrará os javamons que o jogador possui, mas que não estão em sua equipe:
<img width="260" height="225" alt="image" src="https://github.com/user-attachments/assets/98bb548a-0ede-4b23-88d1-6fcd36ba3092" />


Apertando 3 no menu, o terminal mostrará o inventario do jogador, cintendo os itens que ele possui:  
<img width="255" height="205" alt="image" src="https://github.com/user-attachments/assets/78636a51-845d-41cd-8df9-2cfe2614cb75" />


Apertando 4 no menu, o terminal mostrará a loja:  
<img width="163" height="123" alt="image" src="https://github.com/user-attachments/assets/01fe13b1-aa21-4f1f-8df8-89c696e918b1" />



Apertando 5, o jogo será salvo:  
<img width="219" height="211" alt="image" src="https://github.com/user-attachments/assets/ab86f990-39c0-44a5-8494-9d347ab493ff" />


Apertando 6, a equipe do jogador será curada.


Apertando 7, o código será encerrado:  
<img width="405" height="244" alt="image" src="https://github.com/user-attachments/assets/e16b2f4f-ce61-48b9-80a4-9bba7dad19da" />


Apertando 8, o jogador voltará ao mapa:  
<img width="608" height="432" alt="image" src="https://github.com/user-attachments/assets/9118e20c-b071-429d-972b-a6adb1c6089e" />



No mapa, ao apertar B(função temporária), o jogador começará uma batalha com um javamon selvagem:
<img width="629" height="164" alt="image" src="https://github.com/user-attachments/assets/6aeabd2a-91e1-478f-83de-7895404349c2" />


Apertando 1 na batalha, o jogador terá a opção de escolhar 1 dos ataques de seu javamon:
<img width="428" height="358" alt="image" src="https://github.com/user-attachments/assets/2c9dcc36-36d8-4796-9ea7-f4a71dd8babe" />


Apertando 2, o jogador poderá usar um de seus itens em seu inventário, e apertando 3, o jogador poderá trocar o javamon ativo com algum javamon de sua equipe(sistemas ainda não implementados);


Apertando 4, o jogador terá uma chance de fugir da batalha:  
<img width="414" height="197" alt="image" src="https://github.com/user-attachments/assets/16c34891-b96d-45b7-9f55-c874cbeaed4e" />


Após o jogador entrar no mapa da liga(letra "L" no mapa), o jogador terá que derrotar quatro líderes de ginásio para poder enfrentar o campeao  
<img width="273" height="141" alt="image" src="https://github.com/user-attachments/assets/962e6a70-b1c8-4f92-8eb5-9862949c94c8" />


<img width="352" height="242" alt="image" src="https://github.com/user-attachments/assets/f0b19532-d463-4320-8b81-116c569470eb" />  


<img width="278" height="233" alt="image" src="https://github.com/user-attachments/assets/4b29b274-e115-4667-a6f1-23a03ff11531" />


<img width="384" height="228" alt="image" src="https://github.com/user-attachments/assets/e2f0968b-8b65-4c38-b7e7-87878405c4cf" />


<img width="340" height="215" alt="image" src="https://github.com/user-attachments/assets/6476bee4-f730-40b5-b659-4cd050e0df7f" />



