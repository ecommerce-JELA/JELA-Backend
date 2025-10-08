package br.com.judev.jela.Repository;

import br.com.judev.jela.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
   Optional<Estoque> findById(int id);

}
