package br.com.judev.jela.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaRepository, Integer> {
    Optional<CategoriaRepository> findByNome(String nome);

}
