package ludovico.matheus.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ludovico.matheus.LiterAlura.Service.LivroService;
import ludovico.matheus.LiterAlura.Service.AutorService;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("=== Menu ===");
			System.out.println("1. Buscar livro pelo título");
			System.out.println("2. Listar livros registrados");
			System.out.println("3. Listar nossos autores");
			System.out.println("4. Listar autores em determinado ano");
			System.out.println("5. Listar livros em determinado idioma");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir o \n
			switch (opcao) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					livroService.buscarLivroPeloTitulo(titulo);
					break;
				case 2:
					livroService.listarLivros();
					break;
				case 3:
					autorService.listarAutores();
					break;
				case 4:
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					autorService.listarAutoresPorAno(ano);
					break;
				case 5:
					System.out.print("Escolha o idioma (ES, EN, FR, PT): ");
					String idioma = scanner.nextLine().toUpperCase();
					livroService.listarLivrosPorIdioma(idioma);
					break;
				case 6:
					System.out.println("Encerrando o programa...");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (opcao != 6);

		scanner.close();
	}
}