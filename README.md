# Projeto: Javamon

👥 Integrantes do grupo

 - Lucas Paraíso Benning de Oliveira
 - Rafael Cavalcanti Montenegro
 - Paulo Barbosa Apolinario Neto
 - José Diego Monteiro Navarro


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
