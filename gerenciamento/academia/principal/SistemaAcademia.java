package gerenciamento.academia.principal;

import java.util.Scanner;

import gerenciamento.academia.menus.*;

public class SistemaAcademia {

	public static void main(String[] args) {

		// base de dados incial, contendo todos os tipos de pessoa e planos
		Cadastro.cadastrar("D:/Downloads/cadastros.csv");

		iniciar();
	}	

	public static void iniciar() {
		Scanner sc = new Scanner(System.in);

		String login = """
				==== Serviço de Gerenciamento de Academia ====
				__________________________________________________
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
		case "Aluno" -> MenuAluno.iniciarMenuAluno(cpf); 
		case "Personal" -> MenuPersonal.iniciarMenuPersonal(cpf);
		default -> MenuFuncionario.iniciarMenuFuncionario(); 
		}

		sc.close();
	}
}
