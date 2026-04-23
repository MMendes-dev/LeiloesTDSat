package com.cinema.atividadesistema;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeRepository repository;

    // GET - Listar todos
    @GetMapping
    public List<Filme> listar() {
        return repository.findAll();
    }

    // POST - Cadastrar novo
    @PostMapping
    public Filme salvar(@RequestBody Filme filme) {
        return repository.save(filme);
    }

    // PUT - Atualizar (Diferencial da Atividade 2)
    @PutMapping("/{id}")
    public Filme atualizar(@PathVariable int id, @RequestBody Filme filmeAlterado) {
        return repository.findById(id).map(filme -> {
            filme.setTitulo(filmeAlterado.getTitulo());
            filme.setGenero(filmeAlterado.getGenero());
            return repository.save(filme);
        }).orElse(null);
    }

    // DELETE - Remover (Exigência da Atividade 2)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        repository.deleteById(id);
    }
}