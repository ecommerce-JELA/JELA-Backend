package br.com.judev.jela.service;

import br.com.judev.jela.dto.Pedido.ItemResponse;
import br.com.judev.jela.dto.Pedido.PedidoRequest;
import br.com.judev.jela.dto.Pedido.PedidoResponse;
import br.com.judev.jela.entity.*;
import br.com.judev.jela.entity.enums.StatusPedido;
import br.com.judev.jela.Repository.ClienteRepository;
import br.com.judev.jela.Repository.PedidoRepository;
import br.com.judev.jela.Repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoService {

   private final PedidoRepository pedidoRepository;
   private final ClienteRepository clienteRepository;
   private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoResponse criarPedido(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setcliente(cliente);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);

        Set<ItemPedido> itens = request.itens().stream()
                .map(itemRequest -> {
            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto com ID " + itemRequest.produtoId() + " não encontrado"));

            Estoque estoque = produto.getEstoque();
            if (estoque.getQtdAtual() < itemRequest.quantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            estoque.setQtdAtual(estoque.getQtdAtual() - itemRequest.quantidade());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemRequest.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            return itemPedido;
        }).collect(Collectors.toSet());

        pedido.setItens(itens);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return new PedidoResponse(
                pedidoSalvo.getId(),
                pedidoSalvo.getStatus(),
                pedidoSalvo.getData(),
                pedidoSalvo.getCliente().getId(),
                pedidoSalvo.getItens()
                );
    }
    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    private PedidoResponse converterParaResponse(Pedido pedido) {
        List<ItemResponse> itens = pedido.getItens()
                .stream()
                .map(this::converterItemParaResponse)
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getData(),
                pedido.getCliente().getId(),
                pedido.getItens()
        );
    }

    private ItemResponse converterItemParaResponse(ItemPedido item) {
        return new ItemResponse(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
        );
    }
}
