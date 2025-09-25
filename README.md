Sistema de Gerenciamento Simples 
(Java) 
Este é um sistema em Java, desenvolvido para operar via linha de comando (console), 
que simula o cadastro e gerenciamento de Usuários, Projetos e Equipes. Ele utiliza 
conceitos de Programação Orientada a Objetos (POO), como herança e 
polimorfismo, para estruturar o cadastro de diferentes perfis de usuários. 
Funcionalidades 
O sistema oferece as seguintes opções no menu principal: 
1. Cadastrar Usuário: Permite registrar um novo usuário com perfil de 
Colaborador, Gerente ou Administrador. 
2. Cadastrar Projeto: Permite criar um novo projeto, especificando seu status 
(Planejado, Em Andamento, etc.) e associando um Gerente responsável e, 
opcionalmente, uma ou mais Equipes cadastradas. 
3. Cadastrar Equipe: Permite criar uma nova equipe e vincular múltiplos Usuários 
cadastrados como membros. 
4. Listar Usuários: Exibe o perfil detalhado de todos os usuários cadastrados. 
5. Listar Projetos: Exibe os detalhes de todos os projetos, incluindo o gerente e as 
equipes vinculadas. 
6. Listar Equipes: Exibe os detalhes de todas as equipes, incluindo seus membros 
e projetos associados. 
7. Sair: Encerra o programa. 
️ Estrutura do Código 
O código está organizado em classes principais que representam as entidades do 
sistema e a lógica de execução: 
Classes de Entidade 
• Usuario (Classe Base): Define as propriedades comuns de um usuário (nome, 
CPF, email, login, senha, cargo). Contém um método exibirPerfil() 
polimórfico. 
o Subclasses (Colaborador, Gerente, Administrador): Herdam de 
Usuario e sobrescrevem o método exibirPerfil() para exibir 
informações específicas do cargo. 
• StatusProjeto (Enum): Define os possíveis estados de um projeto 
(PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO). 
• Projeto: Representa um projeto com nome, descrição, datas, status, um 
Gerente responsável e uma lista de Equipes vinculadas. 
• Equipe: Representa uma equipe com nome, descrição, uma lista de Usuarios 
como membros e uma lista de Projetos associados. 
Classe Principal 
• SistemaCadastro (Classe Principal com main): 
o Responsável pela interação com o usuário via Scanner e pelo menu 
principal em um loop. 
o Utiliza ArrayLists para armazenar as coleções de usuarios, projetos 
e equipes. 
o Contém métodos estáticos auxiliares (cadastrarUsuario, 
cadastrarProjeto, cadastrarEquipe) que implementam a lógica de 
criação e associação das entidades. 
o Inclui um tratamento básico de exceção (InputMismatchException) 
para entradas inválidas no menu. 
�
� Como Executar 
1. Salve o código em um arquivo chamado SistemaCadastro.java. 
2. Compile o código usando o compilador Java: 
Bash 
javac SistemaCadastro.java 
3. Execute o programa a partir do terminal: 
Bash 
java SistemaCadastro 
4. Siga as instruções do menu para interagir com o sistema. 
Observações 
• O sistema não possui persistência de dados, ou seja, todos os dados 
cadastrados são perdidos ao sair do programa. 
• As associações entre projetos, equipes e usuários são feitas em memória 
durante a execução. 
• Sequência para cadastramento deve seguir a ordem, Usuário > Equipe > 
Projeto. 
• Há uma verificação de dependência: para cadastrar um projeto, é necessário 
que haja pelo menos um Gerente cadastrado.
