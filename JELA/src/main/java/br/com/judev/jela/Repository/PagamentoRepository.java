package br.com.judev.jela.Repository;


import br.com.judev.jela.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
