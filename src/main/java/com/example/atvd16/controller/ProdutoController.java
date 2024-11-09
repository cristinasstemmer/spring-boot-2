package com.example.atvd16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atvd16.dto.ProdutoDTO;
import com.example.atvd16.model.Produto;
import com.example.atvd16.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.createProduto(produtoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> readProduto(@PathVariable Long id) {
        return produtoService.readProduto(id);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> readAllProdutos() {
        return produtoService.readAllProdutos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.updateProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        return produtoService.deleteProduto(id);
    }
}
