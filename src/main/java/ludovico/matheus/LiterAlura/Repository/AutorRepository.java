package ludovico.matheus.LiterAlura.Repository;

import ludovico.matheus.LiterAlura.Model.Autor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> findAllWithLivros();
}