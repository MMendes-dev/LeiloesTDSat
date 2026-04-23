package com.cinema.atividadesistema;

import org.junit.jupiter.api.Test; // Note que seu projeto está usando JUnit 5 (Jupiter)
import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {

    @Test
    public void testObterCategoriaPorAno() {
        // Cenário 1: Filme de 2026 deve ser "Lançamento"
        Filme filmeLancamento = new Filme();
        filmeLancamento.setAnoLancamento(2026);
        assertEquals("Lançamento", filmeLancamento.obterCategoriaPorAno());

        // Cenário 2: Filme de 1990 deve ser "Clássico"
        Filme filmeClassico = new Filme();
        filmeClassico.setAnoLancamento(1990);
        assertEquals("Clássico", filmeClassico.obterCategoriaPorAno());
    }
}