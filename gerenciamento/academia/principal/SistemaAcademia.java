package gerenciamento.academia.principal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import gerenciamento.academia.extras.Creditos;
import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.menus.*;

public class SistemaAcademia {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// base de dados incial, contendo todos os tipos de pessoa e planos
		Path pathCadastro = Paths.get("./src/gerenciamento/academia/arquivos/cadastros.csv");
		Cadastro.cadastrar(pathCadastro);
		
		int choice = 0;
		String menu = """
				\n==== Serviço de Gerenciamento de Academia ====
				1. Login
				2. Créditos
				Escolha uma das opções acima
				""";
		
		do{
			System.out.println(menu);
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1 -> iniciar();
			case 2 -> Creditos.agradecimentos();
		}
			
		}while(choice != 1);

	}	

	public static void iniciar() {
		

		String login = """
				\n||Página de Login ||
				__________________________________________________
				Informe seus dados para realizar seu login:
				"""; 

		String validate = null, cpf; 

		do {
			if("Invalido".equals(validate)) {
				System.out.println("Credenciais incorretas! Tente Novamente\n");
			}

			System.out.println(login);
			System.out.println("Digite o CPF cadastrado: ");
			cpf = sc.nextLine().trim();

			System.out.println("Digite a senha correspondente: ");
			String senha = sc.nextLine().trim();

			validate = Login.validarLogin(cpf, senha);

		}while("Invalido".equals(validate));
		
		switch(validate){
		case "Aluno" -> {
			Limpar.limparConsole(5);
			MenuAluno.iniciarMenuAluno(cpf); 
		}
		case "Personal" -> {
			Limpar.limparConsole(5);
			MenuPersonal.iniciarMenuPersonal(cpf);
		}
		default -> {
			Limpar.limparConsole(5);
			MenuFuncionario.iniciarMenuFuncionario(); 
		}
		}

		sc.close();
	}
}
