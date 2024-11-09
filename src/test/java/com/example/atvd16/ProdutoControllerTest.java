package com.example.atvd16;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.atvd16.controller.ProdutoController;
import com.example.atvd16.dto.ProdutoDTO;
import com.example.atvd16.model.Produto;
import com.example.atvd16.service.ProdutoService;

public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    public void testCreateProduto() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO("Produto A", "Descrição do Produto A", BigDecimal.valueOf(50.75), 100);
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());

        when(produtoService.createProduto(any(ProdutoDTO.class))).thenReturn(ResponseEntity.status(201).body(produto));

        mockMvc.perform(post("/produtos")
                .contentType("application/json")
                .content("{\"nome\":\"Produto A\",\"descricao\":\"Descrição do Produto A\",\"preco\":50.75,\"quantidadeEmEstoque\":100}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Produto A"))
                .andExpect(jsonPath("$.descricao").value("Descrição do Produto A"))
                .andExpect(jsonPath("$.preco").value(50.75))
                .andExpect(jsonPath("$.quantidadeEmEstoque").value(100));
    }

    @Test
    public void testReadProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");
        produto.setDescricao("Descrição do Produto A");
        produto.setPreco(BigDecimal.valueOf(50.75));
        produto.setQuantidadeEmEstoque(100);

        when(produtoService.readProduto(1L)).thenReturn(ResponseEntity.ok(produto));

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto A"))
                .andExpect(jsonPath("$.descricao").value("Descrição do Produto A"))
                .andExpect(jsonPath("$.preco").value(50.75))
                .andExpect(jsonPath("$.quantidadeEmEstoque").value(100));
    }

    @Test
    public void testReadAllProdutos() throws Exception {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");
        produto1.setDescricao("Descrição do Produto A");
        produto1.setPreco(BigDecimal.valueOf(50.75));
        produto1.setQuantidadeEmEstoque(100);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");
        produto2.setDescricao("Descrição do Produto B");
        produto2.setPreco(BigDecimal.valueOf(30.50));
        produto2.setQuantidadeEmEstoque(200);

        when(produtoService.readAllProdutos()).thenReturn(ResponseEntity.ok(Arrays.asList(produto1, produto2)));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto A"))
                .andExpect(jsonPath("$[1].nome").value("Produto B"));
    }

    @Test
    public void testUpdateProduto() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO("Produto A Atualizado", "Descrição Atualizada", BigDecimal.valueOf(55.00), 150);
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());

        when(produtoService.updateProduto(eq(1L), any(ProdutoDTO.class))).thenReturn(ResponseEntity.ok(produto));

        mockMvc.perform(put("/produtos/1")
                .contentType("application/json")
                .content("{\"nome\":\"Produto A Atualizado\",\"descricao\":\"Descrição Atualizada\",\"preco\":55.00,\"quantidadeEmEstoque\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto A Atualizado"))
                .andExpect(jsonPath("$.descricao").value("Descrição Atualizada"))
                .andExpect(jsonPath("$.preco").value(55.00))
                .andExpect(jsonPath("$.quantidadeEmEstoque").value(150));
    }

    @Test
    public void testDeleteProduto() throws Exception {
        doNothing().when(produtoService).deleteProduto(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }
}
