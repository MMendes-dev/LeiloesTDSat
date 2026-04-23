package com.cinema.atividadesistema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    // Métodos para Filmes
    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(int id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public void excluirFilme(int id) {
        filmeRepository.deleteById(id);
    }

    // Métodos para Análises
    public Analise salvarAnalise(Analise analise) {
        return analiseRepository.save(analise);
    }

    public List<Analise> listarAnalisesPorFilme(int filmeId) {
        // O Spring Data JPA já busca as análises vinculadas ao ID do filme
        return analiseRepository.findAll().stream()
                .filter(a -> a.getFilme().getId() == filmeId)
                .toList();
    }
}
