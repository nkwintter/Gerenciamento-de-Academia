package gerenciamento.academia.menus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import gerenciamento.academia.modelos.Aluno;
import gerenciamento.academia.modelos.Avaliacao;
import gerenciamento.academia.modelos.Cargo;
import gerenciamento.academia.modelos.Funcionario;
import gerenciamento.academia.modelos.Periodo;
import gerenciamento.academia.modelos.Personal;
import gerenciamento.academia.modelos.Pessoa;
import gerenciamento.academia.modelos.Plano;
import gerenciamento.academia.modelos.PlanoAtivo;


public class Cadastro {

	public static List<Aluno> alunos = new ArrayList<>(); // Lista de alunos
	public static List<Personal> personals = new ArrayList<>(); // Lista de personal trainers
	public static List<Funcionario> funcionarios = new ArrayList<>(); // Lista de funcionarios
	public static List<Avaliacao> avaliacoes = new ArrayList<>(); // Lista de avaliações
	public static List<Plano> planos = new ArrayList<>();
	
	public static HashSet<String> cpfs = new HashSet<>();

	public static void main(String[] args) {
		
		Path test = Paths.get("./src/gerenciamento/academia/arquivos/cadastros.csv"); 
		cadastrar(test);

		System.out.println("\nAlunos: ");
		for(Pessoa p : alunos) {
			System.out.println(p.toString());
		}

		System.out.println("\nPersonal Trainers: ");
		for(Pessoa p : personals) {
			System.out.println(p.toString());
		}

		System.out.println("\nFuncionários: ");
		for(Pessoa p : funcionarios) {
			System.out.println(p.toString());
		}
		
		System.out.println("\nPlanos: ");
		for(Plano p : planos) {
			System.out.println(p.toString());
		}

	}

	public static void cadastrar(Path arquivoEntrada) {

		try(BufferedReader br = Files.newBufferedReader(arquivoEntrada)){
			String linha;

			while((linha = br.readLine()) != null) {
				if (linha.isBlank()) continue;

				String partes[] = linha.split(",");

				String tipo = partes[0].trim().toLowerCase(); 

				if(tipo.equals("plano")) {
					String nome = partes[1];
					
					int ctr = 0;
					for(Plano p : planos) {
						if(p.getNome().equals(nome)) {
							System.out.printf("O plano: %s, já foi cadastrado!", nome);
							ctr++;
						}
					}
					
					if(ctr == 0) {
						String descricao = partes[2];
						Double precoM = Double.parseDouble(partes[3]);
						Double precoT = Double.parseDouble(partes[4]);
						Double precoA = Double.parseDouble(partes[5]);
						
						planos.add(new Plano(nome, descricao, precoM, precoT, precoA));
					}
					else {
						continue;
					}
					
				}
				else {
					String cpf = partes[2].trim();

					if(cpfs.contains(cpf)) {
						System.out.printf("O CPf: %s já foi cadastrado! Ignorado\n", cpf);
						continue;
					}
					else {
						cpfs.add(cpf);
					}

					String nome = partes[1];
					String senha = partes[3].trim();

					switch(tipo) {
					case "aluno" -> {
						String dataMatricula= partes[4].trim().toLowerCase();
						String nomePlano = partes[5];
						String periodo = partes[6].trim().toUpperCase();
						
						Plano plano = null;
						for (Plano p : planos) {
							if(p.getNome().equals(nomePlano)) {
								plano = p;
							}
						}
						//rever
						alunos.add(new Aluno(nome, cpf, senha, LocalDate.parse(dataMatricula), new PlanoAtivo(plano, Periodo.valueOf(periodo))));
					}
					case "personal" -> {
						String especialidade = partes[4].trim().toLowerCase();
						String cref = partes[5].trim().toLowerCase();

						personals.add(new Personal(nome, cpf, senha, especialidade, cref));
					}	
					default -> { // será funcionário por default
						String cargo = partes[4].trim().toUpperCase();

						funcionarios.add(new Funcionario(nome, cpf, senha, Cargo.valueOf(cargo)));	
					}

					}
				}

			}
			System.out.println("Arquivo lido com sucesso!\n");

		}catch(IOException e) {
			e.printStackTrace();

		}

	}

}


