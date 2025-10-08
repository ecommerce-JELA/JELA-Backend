package br.com.judev.jela.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Produto extends JpaRepository<Pagamento, Integer> {
    Optional<Pagamento> findById(int id);
}
