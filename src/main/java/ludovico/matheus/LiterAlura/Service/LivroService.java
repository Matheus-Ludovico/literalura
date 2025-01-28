package ludovico.matheus.LiterAlura.Service;

import com.fasterxml.jackson.databind.JsonNode;
import ludovico.matheus.LiterAlura.Model.Autor;
import ludovico.matheus.LiterAlura.Model.Livro;
import ludovico.matheus.LiterAlura.Repository.AutorRepository;
import ludovico.matheus.LiterAlura.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private HttpRequestService httpRequestService;

    public void buscarLivroPeloTitulo(String titulo) {
        try {
            JsonNode rootNode = httpRequestService.realizarRequisicaoAPI(titulo);

            System.out.println("=== Livros encontrados ===");
            for (JsonNode bookNode : rootNode.get("results")) {
                String tituloLivro = bookNode.get("title").asText();
                String nomeAutor = bookNode.get("authors").get(0).get("name").asText();
                System.out.println("Título: " + tituloLivro + " | Autor: " + nomeAutor);

                Livro livro = new Livro();
                livro.setTitulo(tituloLivro);
                livro.setIdioma(bookNode.get("languages").get(0).asText());
                livro.setNumeroDownloads(bookNode.get("download_count").asInt());

                Autor autor = autorService.processarAutor(bookNode.get("authors").get(0));
                livro.setAutor(autor);
                livroRepository.save(livro);
            }

            System.out.println("Livro(s) salvo(s) com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor().getNome()));
    }

    public void listarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findAll();
        livros.stream()
                .filter(livro -> livro.getIdioma().equalsIgnoreCase(idioma))
                .forEach(livro -> System.out.println("Título: " + livro.getTitulo()));
    }
}
