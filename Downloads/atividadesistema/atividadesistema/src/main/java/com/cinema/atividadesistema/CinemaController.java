package com.cinema.atividadesistema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // OBRIGATÓRIO: Use apenas @Controller para Thymeleaf funcionar
@RequestMapping("/api/filmes")
public class CinemaController {

    @Autowired
    private CinemaService service;

    @Autowired
    private FilmeRepository repository;

    // Rota: http://localhost:8080/api/filmes/listagem
    @GetMapping("/listagem")
    public String abrirListagem(Model model) {
        // Envia a lista do banco para o HTML
        model.addAttribute("filmes", service.listarFilmes());
        return "listagem"; // Procura o arquivo listagem.html
    }

    // Rota: http://localhost:8080/api/filmes/cadastro
    @GetMapping("/cadastro")
public String exibirFormulario(Model model) {
    model.addAttribute("filme", new Filme()); // Isso evita o erro de objeto nulo no HTML
    return "cadastro-filme"; 
}

// Rota que o seu HTML está tentando acessar: /api/filmes/detalhes/{id}
@GetMapping("/detalhes/{id}")
public String verDetalhes(@PathVariable int id, Model model) {
    // Busca o filme no repositório pelo ID
    Filme filme = repository.findById(id).orElse(null);
    
    if (filme != null) {
        model.addAttribute("filme", filme);
        return "detalhes"; // Procura o arquivo detalhes.html na pasta templates
    }
    
    return "redirect:/api/filmes/listagem"; // Se não achar o filme, volta para a lista
}

    // Rota que recebe os dados do botão "Salvar"
    @PostMapping("/cadastrar")
    public String adicionar(@ModelAttribute Filme filme) { 
        // @ModelAttribute é essencial para formulários HTML
        repository.save(filme); // Salva no MySQL
        return "redirect:/api/filmes/listagem";
    }
}