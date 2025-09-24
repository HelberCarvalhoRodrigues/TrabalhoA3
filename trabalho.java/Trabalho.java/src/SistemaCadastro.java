import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Classe base
class Usuario {
    protected String nomeCompleto;
    protected String cpf;
    protected String email;
    protected String login;
    protected String senha;
    protected String cargo;

    public Usuario(String nomeCompleto, String cpf, String email, String login, String senha, String cargo) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public void exibirPerfil() {
        System.out.println("Usuário comum: " + nomeCompleto);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }
}

// Subclasses herdando de Usuario
class Colaborador extends Usuario {
    public Colaborador(String nomeCompleto, String cpf, String email, String login, String senha) {
        super(nomeCompleto, cpf, email, login, senha, "Colaborador");
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Perfil de Colaborador: " + nomeCompleto + " | Email: " + email);
    }
}

class Gerente extends Usuario {
    public Gerente(String nomeCompleto, String cpf, String email, String login, String senha) {
        super(nomeCompleto, cpf, email, login, senha, "Gerente");
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Perfil de Gerente: " + nomeCompleto + " | CPF: " + cpf);
    }
}

class Administrador extends Usuario {
    public Administrador(String nomeCompleto, String cpf, String email, String login, String senha) {
        super(nomeCompleto, cpf, email, login, senha, "Administrador");
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Perfil de Administrador: " + nomeCompleto + " | Login: " + login);
    }
}

// Enum para status do projeto
enum StatusProjeto {
    PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
}

// Classe Projeto
class Projeto {
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataTerminoPrevista;
    private StatusProjeto status;
    private Gerente gerenteResponsavel;
    private ArrayList<Equipe> equipes; // equipes vinculadas

    public Projeto(String nome, String descricao, String dataInicio, String dataTerminoPrevista,
                   StatusProjeto status, Gerente gerenteResponsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = status;
        this.gerenteResponsavel = gerenteResponsavel;
        this.equipes = new ArrayList<>();
    }

    public void adicionarEquipe(Equipe equipe) {
        equipes.add(equipe);
    }

    public String getNome() {
        return nome;
    }

    public void exibirProjeto() {
        System.out.println("\n=== Projeto ===");
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Data de Término Prevista: " + dataTerminoPrevista);
        System.out.println("Status: " + status);
        System.out.println("Gerente Responsável: " + gerenteResponsavel.getNomeCompleto());
        if (equipes.isEmpty()) {
            System.out.println("Equipes: Nenhuma vinculada");
        } else {
            System.out.print("Equipes: ");
            for (Equipe e : equipes) {
                System.out.print(e.getNome() + " ");
            }
            System.out.println();
        }
    }
}

// Classe Equipe
class Equipe {
    private String nome;
    private String descricao;
    private ArrayList<Usuario> membros;
    private ArrayList<Projeto> projetos;

    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    public void adicionarMembro(Usuario usuario) {
        membros.add(usuario);
    }

    public void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public String getNome() {
        return nome;
    }

    public void exibirEquipe() {
        System.out.println("\n=== Equipe ===");
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);

        System.out.print("Membros: ");
        if (membros.isEmpty()) {
            System.out.println("Nenhum membro.");
        } else {
            for (Usuario u : membros) {
                System.out.print(u.getNomeCompleto() + " (" + u.getCargo() + "), ");
            }
            System.out.println();
        }

        System.out.print("Projetos: ");
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto associado.");
        } else {
            for (Projeto p : projetos) {
                System.out.print(p.getNome() + ", ");
            }
            System.out.println();
        }
    }
}

// Classe principal para o sistema
public class SistemaCadastro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Projeto> projetos = new ArrayList<>();
        ArrayList<Equipe> equipes = new ArrayList<>();

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Cadastrar Projeto");
            System.out.println("3 - Cadastrar Equipe");
            System.out.println("4 - Listar Usuários");
            System.out.println("5 - Listar Projetos");
            System.out.println("6 - Listar Equipes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcaoMenu = -1;
            try {
                opcaoMenu = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("⚠ Entrada inválida, digite apenas números.");
                sc.nextLine();
                continue;
            }

            switch (opcaoMenu) {
                case 1: cadastrarUsuario(sc, usuarios); break;
                case 2: cadastrarProjeto(sc, usuarios, projetos, equipes); break;
                case 3: cadastrarEquipe(sc, usuarios, equipes); break;
                case 4:
                    System.out.println("\n=== Usuários Cadastrados ===");
                    for (Usuario u : usuarios) u.exibirPerfil();
                    break;
                case 5:
                    System.out.println("\n=== Projetos Cadastrados ===");
                    for (Projeto p : projetos) p.exibirProjeto();
                    break;
                case 6:
                    System.out.println("\n=== Equipes Cadastradas ===");
                    for (Equipe e : equipes) e.exibirEquipe();
                    break;
                case 0: rodando = false; break;
                default: System.out.println("⚠ Opção inválida!");
            }
        }
        sc.close();
    }

    private static void cadastrarUsuario(Scanner sc, ArrayList<Usuario> usuarios) {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        System.out.println("Selecione o perfil (1 - Colaborador | 2 - Gerente | 3 - Administrador): ");
        int opcao = sc.nextInt();
        sc.nextLine();

        Usuario usuario;
        switch (opcao) {
            case 1: usuario = new Colaborador(nome, cpf, email, login, senha); break;
            case 2: usuario = new Gerente(nome, cpf, email, login, senha); break;
            case 3: usuario = new Administrador(nome, cpf, email, login, senha); break;
            default:
                System.out.println("Opção inválida, será cadastrado como Colaborador.");
                usuario = new Colaborador(nome, cpf, email, login, senha);
        }

        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarProjeto(Scanner sc, ArrayList<Usuario> usuarios,
                                         ArrayList<Projeto> projetos, ArrayList<Equipe> equipes) {
        if (usuarios.isEmpty()) {
            System.out.println("⚠ Não há usuários cadastrados. Cadastre um gerente primeiro.");
            return;
        }

        System.out.println("\n=== Cadastro de Projeto ===");
        System.out.print("Nome do projeto: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Data de início: ");
        String dataInicio = sc.nextLine();
        System.out.print("Data de término prevista: ");
        String dataTermino = sc.nextLine();

        System.out.println("Status (1-Planejado | 2-Em andamento | 3-Concluído | 4-Cancelado): ");
        int s = sc.nextInt();
        sc.nextLine();

        StatusProjeto status = switch (s) {
            case 2 -> StatusProjeto.EM_ANDAMENTO;
            case 3 -> StatusProjeto.CONCLUIDO;
            case 4 -> StatusProjeto.CANCELADO;
            default -> StatusProjeto.PLANEJADO;
        };

        // escolher gerente
        ArrayList<Gerente> gerentes = new ArrayList<>();
        for (Usuario u : usuarios) if (u instanceof Gerente) gerentes.add((Gerente) u);

        if (gerentes.isEmpty()) {
            System.out.println("⚠ Não há gerentes cadastrados. Cadastre um gerente primeiro.");
            return;
        }

        System.out.println("Escolha o gerente responsável: ");
        for (int i = 0; i < gerentes.size(); i++) {
            System.out.println((i + 1) + " - " + gerentes.get(i).getNomeCompleto());
        }
        int g = sc.nextInt();
        sc.nextLine();

        if (g < 1 || g > gerentes.size()) {
            System.out.println("⚠ Escolha inválida!");
            return;
        }

        Projeto projeto = new Projeto(nome, descricao, dataInicio, dataTermino, status, gerentes.get(g - 1));

        // vincular equipes
        if (!equipes.isEmpty()) {
            System.out.println("Deseja vincular equipes ao projeto? (s/n): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                for (int i = 0; i < equipes.size(); i++) {
                    System.out.println((i + 1) + " - " + equipes.get(i).getNome());
                }
                System.out.println("Digite os números das equipes separados por espaço (ou 0 para nenhuma): ");
                String[] escolhaEquipes = sc.nextLine().split(" ");
                for (String e : escolhaEquipes) {
                    try {
                        int idx = Integer.parseInt(e);
                        if (idx > 0 && idx <= equipes.size()) {
                            Equipe eq = equipes.get(idx - 1);
                            projeto.adicionarEquipe(eq);
                            eq.adicionarProjeto(projeto);
                        }
                    } catch (NumberFormatException ignore) {}
                }
            }
        }

        projetos.add(projeto);
        System.out.println("Projeto cadastrado com sucesso!");
    }

    private static void cadastrarEquipe(Scanner sc, ArrayList<Usuario> usuarios, ArrayList<Equipe> equipes) {
        System.out.println("\n=== Cadastro de Equipe ===");
        System.out.print("Nome da equipe: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Equipe equipe = new Equipe(nome, descricao);

        if (usuarios.isEmpty()) {
            System.out.println("⚠ Nenhum usuário disponível para adicionar como membro.");
        } else {
            System.out.println("Selecione membros para a equipe (digite números separados por espaço): ");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + " - " + usuarios.get(i).getNomeCompleto() +
                                   " (" + usuarios.get(i).getCargo() + ")");
            }
            String[] escolhaMembros = sc.nextLine().split(" ");
            for (String m : escolhaMembros) {
                try {
                    int idx = Integer.parseInt(m);
                    if (idx > 0 && idx <= usuarios.size()) {
                        equipe.adicionarMembro(usuarios.get(idx - 1));
                    }
                } catch (NumberFormatException ignore) {}
            }
        }

        equipes.add(equipe);
        System.out.println("Equipe cadastrada com sucesso!");
    }
}