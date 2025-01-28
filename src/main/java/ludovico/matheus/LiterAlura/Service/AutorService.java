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
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor processarAutor(JsonNode authorNode) {
        Autor autor = new Autor();
        autor.setNome(authorNode.get("name").asText());
        autor.setAnoNascimento(authorNode.has("birth_year") ? authorNode.get("birth_year").asInt() : null);
        autor.setAnoFalecimento(authorNode.has("death_year") ? authorNode.get("death_year").asInt() : null);
        autorRepository.save(autor);
        return autor;
    }

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAllWithLivros();
        autores.forEach(autor -> {
            System.out.println("Autor: " + autor.getNome());
            autor.getLivros().forEach(livro -> System.out.println("  - Livro: " + livro.getTitulo()));
        });
    }

    public void listarAutoresPorAno(int ano) {
        List<Autor> autores = autorRepository.findAllWithLivros();
        autores.stream()
                .filter(autor -> {
                    int nascimento = autor.getAnoNascimento() != null ? autor.getAnoNascimento() : Integer.MIN_VALUE;
                    int falecimento = autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : Integer.MAX_VALUE;
                    return ano >= nascimento && ano <= falecimento;
                })
                .forEach(autor -> System.out.println("Autor: " + autor.getNome()));
    }
}
