import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Perfil {
    COLABORADOR, ADMINISTRADOR, GERENTE
}

// CLASSE DO USUÁRIO QUE RECEBERÁ AS INFORMAÇÕES DO REGISTRO
class Cadastrante {
    private String nomeCompleto;
    private String cpf;
    private String cargo;
    private String email;
    private String senha;
    private Perfil perfil;

    public Cadastrante (String nomeCompleto, String cpf, String cargo, String email, String senha, Perfil perfil) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cargo = cargo;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }
    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public class Usuario extends Cadastrante {
        private Perfil perfil;
        
        public Usuario(String nomeCompleto, String cpf, String cargo, String email, String senha, Perfil perfil) {
            super(nomeCompleto, cpf, cargo, email, senha, perfil);
            this.perfil = perfil;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cadastrante> usuarios = new ArrayList<>();

        System.out.print("Quantos usuários deseja cadastrar? ");
        int quantidadeUsuarios = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < quantidadeUsuarios; i++) {
            System.out.println("Cadastro do usuário " + (i + 1) + ":");

            System.out.print("Nome Completo: ");
            String nomeCompleto = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            System.out.println("Escolha o perfil (1 - ADMINISTRADOR, 2 - GERENTE, 3 - COLABORADOR): ");
            int opcao = scanner.nextInt();

            Perfil perfil;
            switch (opcao) {
                case 1:
                    perfil = Perfil.ADMINISTRADOR;
                    break;
                case 2:
                    perfil = Perfil.GERENTE;
                    break;
                case 3:
                    perfil = Perfil.COLABORADOR;
                    break;
                default:
                    System.out.println("Opção inválida. Usuário será cadastrado como COLABORADOR.");
                    perfil = Perfil.COLABORADOR;
            }

            Cadastrante usuario = new Cadastrante(nomeCompleto, cpf, cargo, email, senha, perfil);
            usuarios.add(usuario);
        }

        System.out.println("\nUsuários cadastrados:");
        for (Cadastrante usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNomeCompleto());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Cargo: " + usuario.getCargo());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Perfil: " + usuario.getPerfil());
            System.out.println("---------------------------");
        }

        scanner.close();
    }
}