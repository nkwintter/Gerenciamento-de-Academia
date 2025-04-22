package gerenciamento.academia.menus;

import java.util.Scanner;

public class Aplicacao {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String login = """
				==== Serviço de Gerenciamento de Academia ====
				=== Faça seu Login ===
				Informme seus dados para realizar seu login:
				"""; 
		
		String validate = null, cpf; 
		
		do {
			if("Invalido".equals(validate)) {
				System.out.println("Credenciais incorretas! Tente Novamente\n");
			}
			
			System.out.println(login);
			System.out.println("Digite o CPF cadastrado: ");
			cpf = sc.nextLine();
			
			System.out.println("Digite a senha correspondente: ");
			String senha = sc.nextLine();
			
			validate = Login.validarLogin(cpf, senha);
			
		}while("Invalido".equals(validate));
		
		//fazer um switch aq
		switch(validate){
			case "Aluno" -> MenuAluno.iniciarMenuAluno(cpf); 
			case "Personal" -> MenuPersonal.iniciarMenuPersonal(cpf);
			//default -> MenuFuncionario.iniciarMenuFuncionario(); A FAZER
		}
		
	sc.close();
	}
}		
	
	
