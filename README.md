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

