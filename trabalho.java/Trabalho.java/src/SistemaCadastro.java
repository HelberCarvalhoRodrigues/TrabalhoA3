import java.util.ArrayList;
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

    // Polimorfismo: cada perfil vai exibir detalhes diferentes
    public void exibirPerfil() {
        System.out.println("Usuário comum: " + nomeCompleto);
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

// Classe principal para o sistema de cadastro
public class SistemaCadastro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        boolean continuar = true;

        while (continuar) {
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
            sc.nextLine(); // consumir quebra de linha

            Usuario usuario;
            switch (opcao) {
                case 1:
                    usuario = new Colaborador(nome, cpf, email, login, senha);
                    break;
                case 2:
                    usuario = new Gerente(nome, cpf, email, login, senha);
                    break;
                case 3:
                    usuario = new Administrador(nome, cpf, email, login, senha);
                    break;
                default:
                    System.out.println("Opção inválida, será cadastrado como Colaborador.");
                    usuario = new Colaborador(nome, cpf, email, login, senha);
            }

            usuarios.add(usuario);
            System.out.println("\nUsuário cadastrado com sucesso!");
            usuario.exibirPerfil();

            System.out.print("\nDeseja cadastrar outro usuário? (s/n): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        System.out.println("\n=== Lista de Usuários Cadastrados ===");
        for (Usuario u : usuarios) {
            u.exibirPerfil(); // polimorfismo em ação
        }

        sc.close();
    }
}