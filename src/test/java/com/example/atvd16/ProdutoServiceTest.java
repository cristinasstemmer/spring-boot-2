package com.example.atvd16;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.atvd16.dto.ProdutoDTO;
import com.example.atvd16.model.Produto;
import com.example.atvd16.repository.ProdutoRepository;
import com.example.atvd16.service.ProdutoService;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Produto A", "Descrição do Produto A", BigDecimal.valueOf(50.75), 100);
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ResponseEntity<Produto> response = produtoService.createProduto(produtoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Produto A", response.getBody().getNome());
    }

    @Test
    public void testReadProduto() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setDescricao("Descrição do Produto A");
        produto.setPreco(BigDecimal.valueOf(50.75));
        produto.setQuantidadeEmEstoque(100);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ResponseEntity<Produto> response = produtoService.readProduto(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto A", response.getBody().getNome());
    }

    @Test
    public void testUpdateProduto() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Produto A Atualizado", "Descrição Atualizada", BigDecimal.valueOf(55.00), 150);
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ResponseEntity<Produto> response = produtoService.updateProduto(1L, produtoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto A Atualizado", response.getBody().getNome());
    }

    @Test
    public void testDeleteProduto() {
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).deleteById(1L);

        ResponseEntity<Void> response = produtoService.deleteProduto(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
