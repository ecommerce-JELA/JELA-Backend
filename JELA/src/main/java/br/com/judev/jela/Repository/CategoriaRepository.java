package br.com.judev.jela.Repository;

import br.com.judev.jela.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<CategoriaRepository> findByNome(String nome);
}
