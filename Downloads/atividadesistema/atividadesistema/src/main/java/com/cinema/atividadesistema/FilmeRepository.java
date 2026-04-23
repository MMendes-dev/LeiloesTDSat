package com.cinema.atividadesistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    // Aqui o Spring já cria o salvar, listar, deletar e atualizar automaticamente
}
