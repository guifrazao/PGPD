package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Teste de unidade para validar a configuração do ambiente do Jantar dos Filósofos.
 * O teste verifica se, após a execução do método iniciar(),
 * os filósofos e garfos foram corretamente instanciados e associados.
 */
public class JantarTest {

    private Jantar jantar;

    @BeforeEach
    void setUp() {
        jantar = new Jantar();
    }

    @Test
    void testConfiguracaoInicialDoJantar() throws Exception {
        jantar.iniciar();

        // Verifica se a lista de filósofos foi criada
        Field filosofosField = Jantar.class.getDeclaredField("filosofos");
        filosofosField.setAccessible(true);
        List<?> filosofos = (List<?>) filosofosField.get(jantar);

        // Verifica se a lista de garfos foi criada
        Field garfosField = Jantar.class.getDeclaredField("garfos");
        garfosField.setAccessible(true);
        List<?> garfos = (List<?>) garfosField.get(jantar);

        // Validações básicas
        assertNotNull(filosofos, "A lista de filósofos não deve ser nula após iniciar()");
        assertNotNull(garfos, "A lista de garfos não deve ser nula após iniciar()");
        assertEquals(filosofos.size(), garfos.size(), "O número de filósofos deve ser igual ao número de garfos");

        // Verifica se cada filósofo tem seus dois garfos corretamente associados
        for (Object f : filosofos) {
            Field garfoEsquerdoField = f.getClass().getDeclaredField("garfoEsquerdo");
            garfoEsquerdoField.setAccessible(true);
            Object garfoEsquerdo = garfoEsquerdoField.get(f);

            Field garfoDireitoField = f.getClass().getDeclaredField("garfoDireito");
            garfoDireitoField.setAccessible(true);
            Object garfoDireito = garfoDireitoField.get(f);

            assertNotNull(garfoEsquerdo, "O filósofo deve ter um garfo esquerdo atribuído");
            assertNotNull(garfoDireito, "O filósofo deve ter um garfo direito atribuído");
            assertTrue(garfos.contains(garfoEsquerdo), "Garfo esquerdo deve estar na lista de garfos do jantar");
            assertTrue(garfos.contains(garfoDireito), "Garfo direito deve estar na lista de garfos do jantar");
        }
    }
}

