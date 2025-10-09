package br.com.judev.jela.service;

import br.com.judev.jela.Repository.CategoriaRepository;
import br.com.judev.jela.dto.Categoria.CategoriaRequest;
import br.com.judev.jela.dto.Categoria.CategoriaResponse;
import br.com.judev.jela.entity.Categoria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponse cadastrarCategoria(CategoriaRequest request) {
        categoriaRepository.findByNome(request.nome()).ifPresent(c -> {
            throw new IllegalArgumentException("Já existe uma categoria com este nome.");
        });

        Categoria categoria = new Categoria(request.nome(), request.descricao());
        Categoria salva = categoriaRepository.save(categoria);

        return new CategoriaResponse(salva.getIdCategoria(), salva.getNome(), salva.getDescricao());
    }

    public CategoriaResponse atualizarCategoria(Integer id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

        if (request.nome() != null && !request.nome().isBlank()) {
            categoria.setNome(request.nome());
        }
        if (request.descricao() != null) {
            categoria.setDescricao(request.descricao());
        }

        Categoria atualizada = categoriaRepository.save(categoria);
        return new CategoriaResponse(atualizada.getIdCategoria(), atualizada.getNome(), atualizada.getDescricao());
        }

     public void removerCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada para exclusão.");
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaResponse consultarCategoria(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        return new CategoriaResponse(categoria.getIdCategoria(), categoria.getNome(), categoria.getDescricao());
        }

     public List<CategoriaResponse> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
         return categorias.stream()
                .map(categoria -> new CategoriaResponse(
                        categoria.getIdCategoria(),
                        categoria.getNome(),
                        categoria.getDescricao()))
                .toList();
        }

}
