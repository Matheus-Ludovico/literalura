package ludovico.matheus.LiterAlura.Repository;

import ludovico.matheus.LiterAlura.Model.Autor;
import ludovico.matheus.LiterAlura.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}