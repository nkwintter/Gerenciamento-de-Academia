package gerenciamento.academia.principal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import gerenciamento.academia.extras.Limpar;
import gerenciamento.academia.menus.*;

public class SistemaAcademia {

	public static void main(String[] args) {

		// base de dados incial, contendo todos os tipos de pessoa e planos
		Path pathCadastro = Paths.get("./src/gerenciamento/academia/arquivos/cadastros.csv");
		Cadastro.cadastrar(pathCadastro);

		iniciar();
	}	

	public static void iniciar() {
		Scanner sc = new Scanner(System.in);

		String login = """
				==== Serviço de Gerenciamento de Academia ====
				
				||Página de Login ||
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
			Limpar.limparConsole(8);
			MenuAluno.iniciarMenuAluno(cpf); 
		}
		case "Personal" -> {
			Limpar.limparConsole(8);
			MenuPersonal.iniciarMenuPersonal(cpf);
		}
		default -> {
			Limpar.limparConsole(8);
			MenuFuncionario.iniciarMenuFuncionario(); 
		}
		}

		sc.close();
	}
}
