import java.util.ArrayList;
import java.util.Scanner;

enum Perfil {
    COLABORADOR {
        @Override
        public void exibirPerfil(Usuario usuario) {
            System.out.println("Colaborador: " + usuario.getNome() + " | Email: " + usuario.getEmail());
        }
    },
    GERENTE {
        @Override
        public void exibirPerfil(Usuario usuario) {
            System.out.println("Gerente: " + usuario.getNome() + " | CPF: " + usuario.getCpf());
        }
    },
    ADMINISTRADOR {
        @Override
        public void exibirPerfil(Usuario usuario) {
            System.out.println("Administrador: " + usuario.getNome() + " | Login: " + usuario.getLogin());
        }
    };

    public abstract void exibirPerfil(Usuario usuario);
}

class Usuario {
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario(String nomeCompleto, String cpf, String email, String login, String senha, Perfil perfil) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome() { return nomeCompleto; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public Perfil getPerfil() { return perfil; }

    public void exibirPerfil() {
        perfil.exibirPerfil(this);
    }
}

public class SistemaCadastro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        boolean continuar = true;

        // Cadastro de usuários
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

            System.out.println("Selecione o perfil: ");
            System.out.println("1 - Colaborador");
            System.out.println("2 - Gerente");
            System.out.println("3 - Administrador");
            int opcao = sc.nextInt();
            sc.nextLine();

            Perfil perfilEscolhido;
            switch (opcao) {
                case 1: perfilEscolhido = Perfil.COLABORADOR; break;
                case 2: perfilEscolhido = Perfil.GERENTE; break;
                case 3: perfilEscolhido = Perfil.ADMINISTRADOR; break;
                default:
                    System.out.println("Opção inválida, será cadastrado como COLABORADOR.");
                    perfilEscolhido = Perfil.COLABORADOR;
            }

            Usuario usuario = new Usuario(nome, cpf, email, login, senha, perfilEscolhido);
            usuarios.add(usuario);

            System.out.println("\nUsuário cadastrado com sucesso!");
            usuario.exibirPerfil();

            System.out.print("\nDeseja cadastrar outro usuário? (s/n): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        // Simulação de login
        System.out.println("\n=== Tela de Login ===");
        System.out.print("Digite o login: ");
        String login = sc.nextLine();
        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();

        boolean autenticado = false;
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                System.out.println("\nLogin bem-sucedido!");
                u.exibirPerfil();
                autenticado = true;
                break;
            }
        }

        if (!autenticado) {
            System.out.println("\nLogin ou senha incorretos!");
        }

        sc.close();
    }
}
