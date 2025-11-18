# Projeto: Javamon
(Versão Beta 1.0)

## 👥 Integrantes do grupo

- Lucas Paraíso Benning de Oliveira
- Rafael Cavalcanti Montenegro
- Paulo Barbosa Apolinario Neto

---

## ⚙️ Instruções de Execução

### Clonando o Repositório
```bash
git clone https://github.com/LucaoDez/javamon
```

### Compilação e Execução
Abra o projeto em uma IDE Java e execute:

```bash
javac src/App.java
java src/App
```

O jogo será iniciado no terminal (modo CLI).

---

## 🎮 Características Principais

### ✨ Novo Sistema de Menu Inicial
- **Menu Principal Completo**: Novo jogo, Carregar jogo, Créditos e Controles
- **Escolha de Starter Aprimorada**: 4 opções balanceadas com estatísticas visuais
  - 🔥 **Feuermon** - Tank de Ataque (HP: 100 | ATK: 40 | DEF: 20 | SPD: 10)
  - 💧 **Aquaril** - Balanceado (HP: 70 | ATK: 30 | DEF: 30 | SPD: 30)
  - 🌿 **Terravox** - Tank Defensivo (HP: 120 | ATK: 20 | DEF: 50 | SPD: 5)
  - 🌪️ **Ventrix** - Speedster (HP: 50 | ATK: 35 | DEF: 10 | SPD: 50)

### ⚔️ Sistema de Batalha Aprimorado
- **Sistema de Velocidade**: Javamon mais rápido ataca primeiro
- **Batalhas contra Treinadores**: Sistema completo de batalhas sequenciais
- **Recompensas Balanceadas**: EXP e dinheiro baseados em nível
- **Itens em Batalha**: Uso de poções e Javacubes durante combate
- **Proteção de Captura**: Javacubes bloqueadas em batalhas de treinadores

### 🏛️ Liga Javamon Completa
- **4 Ginásios Temáticos**:
  - 🔥 Ginásio do Fogo (Líder Pyros)
  - 💧 Ginásio da Água (Líder Aqua)
  - 🌿 Ginásio da Terra (Líder Gaia)
  - 🌪️ Ginásio do Ar (Líder Aeris)
- **Desafio Final**: Batalha épica contra o Campeão Eclipse
- **Sistema Anti-Repetição**: Ginásios derrotados não podem ser desafiados novamente
- **Progressão Obrigatória**: Necessário derrotar os 4 líderes antes do Campeão

### 💾 Sistema de Save/Load Robusto
- **Salvamento Completo**: Equipe, Box, Inventário, Posição e Progresso
- **Persistência de Progresso**: Ginásios derrotados são salvos
- **Carregar do Menu**: Opção de continuar jogo anterior

### 🎬 Tela Final Épica
- **Animação de Vitória**: Celebração visual ao se tornar Campeão
- **Estatísticas Finais**: Resumo completo da jornada
- **Créditos Completos**: Reconhecimento da equipe e tecnologias
- **Opções Pós-Jogo**: Continuar jogando ou sair

---

## 🧱 Estrutura de Pacotes e Classes

### 📦 model (Modelos de Dados)
- `Javamon.java` - Classe abstrata base de todos os monstros
- `Feuermon.java` - Javamon tipo Fogo (Tank de Ataque)
- `Aquaril.java` - Javamon tipo Água (Balanceado)
- `Hydreon.java` - Javamon tipo Água (Evolução)
- `Terravox.java` - Javamon tipo Terra (Tank Defensivo)
- `Mudrill.java` - Javamon tipo Terra
- `Ventrix.java` - Javamon tipo Ar (Speedster)
- `Borealix.java` - Javamon tipo Ar
- `Cindrax.java` - Javamon tipo Fogo (Evolução)
- `Ataque.java` - Representa os ataques com PP
- `Itens.java` - Classe base para itens
- `Pocao.java` - Item de cura
- `Revive.java` - Item de reviver
- `Javacube.java` - Item de captura com sistema de chance

### 🎮 controller (Controladores)
- `Batalha.java` - Sistema de combate completo
  - Turnos baseados em velocidade
  - Batalhas contra treinadores múltiplos
  - Sistema de captura integrado
- `Captura.java` - Sistema legado de captura
- `Menu.java` - Menu do jogador com todas as opções
- `MenuInicial.java` - Sistema de menu inicial e escolha de starter

### 🗺️ view (Mapas e Interface)
- `Mapa.java` - Mapa principal da cidade
- `MapaLiga.java` - Hall central da Liga
- `MapaGinasioFogo.java` - Ginásio do Fogo com obstáculos
- `MapaGinasioAgua.java` - Ginásio da Água com correnteza
- `MapaGinasioTerra.java` - Ginásio da Terra com buracos
- `MapaGinasioAr.java` - Ginásio do Ar com ventos
- `MapaCampeao.java` - Salão do Campeão
- `TelaFinal.java` - Tela de vitória e créditos

### 🔧 service (Serviços)
- `SaveManager.java` - Sistema completo de save/load
  - Salva progresso de ginásios
  - Restaura stats e HP corretamente
  - Suporte a múltiplos tipos de itens

### 👤 Entidades
- `Jogador.java` - Jogador com equipe, box, bolsa e progresso
  - Sistema de ginásios derrotados
  - Status de campeão

### 🎯 App.java
Classe principal que:
- Integra menu inicial
- Gerencia loop do jogo
- Controla transições entre mapas
- Detecta entrada/saída de ginásios

---

## 📸 Principais Funcionalidades

### 🎮 Menu Inicial
```
                    🎮 MENU PRINCIPAL 🎮

                1️⃣  Novo Jogo
                2️⃣  Carregar Jogo
                3️⃣  Créditos
                4️⃣  Controles
                5️⃣  Sair
```

### 🔥 Escolha de Starter
Sistema visual completo com:
- Estatísticas detalhadas (HP, ATK, DEF, SPD)
- Estilo de jogo recomendado
- Vantagens e desvantagens de tipo
- Confirmação de escolha

### ⚔️ Sistema de Batalha
**Durante batalhas você pode:**
1. **Atacar** - Escolher entre 4 ataques (com PP)
2. **Usar Item** - Poções, Revives ou Javacubes
3. **Trocar Javamon** - Mudar durante a batalha
4. **Fugir/Desistir** - Escapar (não funciona contra treinadores)

**Mecânicas especiais:**
- Javamon mais rápido ataca primeiro
- Sistema de vantagem de tipos (2x/0.5x dano)
- Recompensas triplicadas em batalhas de treinadores
- Troca automática quando Javamon é nocauteado

### 🏛️ Liga Javamon
**Progressão:**
1. Entre no mapa da Liga (letra "L" no mapa principal)
2. Derrote os 4 líderes de ginásio em qualquer ordem
3. Desafie o Campeão Eclipse (requer 4 vitórias)
4. Torne-se o Campeão!

**Recursos:**
- Ginásios com puzzles temáticos
- Líderes com times balanceados
- Sistema anti-farm (sem re-desafios)
- Saída automática após vitória

### 💾 Sistema de Save
**O que é salvo:**
- Nome e dinheiro do jogador
- Posição no mapa
- Equipe completa (6 Javamons)
- Box de armazenamento
- Inventário com quantidades
- Ginásios derrotados
- Status de campeão

**Como salvar:**
- Opção 5 no Menu (M)
- Automático ao sair (opção 7)

### 🎬 Tela Final
Ao se tornar Campeão:
1. Animação de vitória com brilhos
2. Mensagem de parabéns personalizada
3. Estatísticas da sua jornada
4. Créditos completos da equipe
5. Opções: Continuar, Ver créditos, Salvar ou Sair

---

## 🎯 Controles

### No Mapa
- **W** - Mover para cima ⬆️
- **S** - Mover para baixo ⬇️
- **A** - Mover para esquerda ⬅️
- **D** - Mover para direita ➡️
- **M** - Abrir menu
- **Q** - Salvar e sair

### No Menu
- **1** - Ver Equipe
- **2** - Ver Box
- **3** - Inventário
- **4** - Loja
- **5** - Salvar Jogo
- **6** - Curar Equipe (grátis)
- **7** - Sair do Jogo
- **8** - Voltar ao mapa

### Durante Batalha
- **1** - Atacar (escolher ataque)
- **2** - Usar Item (Poções/Javacubes)
- **3** - Trocar Javamon
- **4** - Fugir (apenas selvagens)

---

## 🔄 Melhorias Implementadas

### Sistema de Batalha
✅ Ordem de ataque baseada em velocidade  
✅ Batalhas sequenciais contra treinadores  
✅ Sistema de troca durante batalha  
✅ Uso de itens em batalha  
✅ Proteção contra captura de Javamons de treinadores  
✅ Recompensas balanceadas (EXP e dinheiro)  

### Liga Javamon
✅ 4 ginásios com puzzles únicos  
✅ Desafio final contra o Campeão  
✅ Sistema anti-repetição de ginásios  
✅ Progressão obrigatória (4 líderes → Campeão)  
✅ Navegação fluida entre mapas  

### Interface e UX
✅ Menu inicial profissional  
✅ Escolha de starter visual e informativa  
✅ Tela final épica com créditos  
✅ Mensagens claras de feedback  
✅ Animações de texto  

### Persistência
✅ Save/Load robusto  
✅ Progresso de ginásios persistente  
✅ Restauração completa de stats  
✅ Suporte a múltiplos tipos de itens  

---

## 🐛 Correções de Bugs

✅ **Save/Load**: Stats e HP agora são restaurados corretamente  
✅ **Ginásios**: Não é mais possível re-desafiar líderes derrotados  
✅ **Javacubes**: Bloqueadas em batalhas de treinadores  
✅ **Velocidade**: Sistema de prioridade funciona corretamente  
✅ **Compilação**: Erro `MapaLiga.entrar()` corrigido  

---

## 🎓 Tecnologias e Conceitos

### Linguagem
- Java (JDK 8+)

### Paradigma
- Programação Orientada a Objetos (POO)

### Padrões
- MVC (Model-View-Controller)
- Herança e Polimorfismo
- Encapsulamento

### Recursos
- Reflection (para save/load flexível)
- Collections (List, Set)
- File I/O (BufferedReader/PrintWriter)
- Exception Handling

---

## 📝 Observações de Desenvolvimento

### Em Produção
- ✅ Sistema de batalha completo
- ✅ Liga Javamon funcional
- ✅ Save/Load robusto
- ✅ Menu inicial e tela final

### Futuras Melhorias Possíveis
- 🔮 Sistema de evolução de Javamons
- 🔮 Mais tipos de Javamons (Elétrico, Gelo, etc)
- 🔮 Missões secundárias
- 🔮 Sistema de trocas entre jogadores
- 🔮 Interface gráfica (GUI)

---

## 🏆 Como Vencer o Jogo

1. **Escolha seu starter** sabiamente no início
2. **Treine sua equipe** batalhando em grama alta (*)
3. **Compre itens** na loja (Poções, Revives, Javacubes)
4. **Capture Javamons** selvagens para diversificar seu time
5. **Entre na Liga** (L no mapa) quando estiver pronto
6. **Derrote os 4 líderes** em qualquer ordem
7. **Desafie o Campeão** Eclipse (requer 4 vitórias)
8. **Torne-se o Campeão** da Liga Javamon!

---

## 💡 Dicas Estratégicas

### Composição de Time
- ✅ Tenha Javamons de tipos variados
- ✅ Balance ataque, defesa e velocidade
- ✅ Mantenha seus Javamons em níveis similares

### Batalhas
- 💪 Use vantagem de tipos (2x de dano!)
- ⚡ Javamons rápidos atacam primeiro
- 🛡️ Troque para absorver golpes super efetivos
- 💊 Use poções antes que seu Javamon desmaie

### Recursos
- 💰 Economize dinheiro para Javacubes
- 📦 Use o Box para armazenar capturas extras
- 🏥 Cure sua equipe no Menu antes de ginásios
- 💾 Salve frequentemente!

---

## 📞 Suporte e Contribuições

Para reportar bugs ou sugerir melhorias, abra uma issue no repositório:
[https://github.com/LucaoDez/javamon](https://github.com/LucaoDez/javamon)

---

## 📜 Licença

Este é um projeto acadêmico desenvolvido para fins educacionais.  
Inspirado em Pokémon © Nintendo/Game Freak.

---

**Versão:** Beta 1.0  
**Data:** 2025.2  
**Status:** ✅ Jogável e Completo

🎮 **Boa jornada, futuro Campeão!** 🏆
