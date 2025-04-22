package gerenciamento.academia.menus;

import gerenciamento.academia.modelos.Pessoa;

public class Login {
	
	static String validarLogin(String cpf, String senha) {
		
		for (Pessoa p : Cadastro.alunos) {
			if (p.getCpf().equals(cpf) && p.autenticarSenha(senha)) {
				return "Aluno";
			}
		}
		
	    for (Pessoa p : Cadastro.personals) {
	    	if (p.getCpf().equals(cpf) && p.autenticarSenha(senha)) {
	    		return "Personal";
	    	}
	    }
	    
	    for (Pessoa p : Cadastro.funcionarios) {
	    	if (p.getCpf().equals(cpf) && p.autenticarSenha(senha)) {
	    		return "Funcionario";
	    	}
	    }
	    
		return "Invalido";	
	}
	
}
