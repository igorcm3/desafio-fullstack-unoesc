package br.edu.unoesc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioFullstackUnoescApplication {

	// @Autowired
	// private static PerfilRepository perfilRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioFullstackUnoescApplication.class, args);

		// Cria os perfis de usuario padrão.

		// Perfil professor = new Perfil();
		// professor.setId(Long.parseLong("1"));
		// professor.setTipo("Professor");

		// Perfil aluno = new Perfil();
		// aluno.setId(Long.parseLong("2"));
		// aluno.setTipo("Aluno");

		// Perfil admin = new Perfil();
		// aluno.setId(Long.parseLong("3"));
		// aluno.setTipo("Admin");	
		
		// List<Perfil> perfis = new ArrayList<>();
		// perfis.add(professor);
		// perfis.add(aluno);
		// perfis.add(admin);

		// perfilRepository.saveAll(perfis);

	}

}
