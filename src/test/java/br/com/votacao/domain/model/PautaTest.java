package br.com.votacao.domain.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import br.com.votacao.application.service.enumerated.VotoTipo;


@RunWith(MockitoJUnitRunner.class)
public class PautaTest {

    private Pauta pauta;

    @Before
    public void init(){
        pauta = Pauta.builder().nome("Pauta xxxx").build();
    }

    @Test
    public void validaAberturaSessao(){
        LocalDateTime data = LocalDateTime.now().plusDays(10);
        pauta.abreSessao(data);
        assertEquals(data, pauta.getDataExpiracaoSessao());
        assertTrue(pauta.isSessaoDisponivel());
    }

    @Test
    public void validaFinalizaSessao(){
        LocalDateTime data = LocalDateTime.now().plusDays(10);
        pauta.abreSessao(data);
        pauta.finalizaSessao();
        assertFalse(pauta.isSessaoDisponivel());
    }

    @Test
    public void validaContabilizaResultado(){
        pauta.adicionaVoto(Voto.builder()
                .associado(
                        Associado.builder()
                                .id("uuidaleatorio123")
                                .build()
                )
                .votoTipo(VotoTipo.SIM)
                .build());
        pauta.contabilizaResultado();
        assertNotNull(pauta.getResultado());
        assertEquals(1, pauta.getResultado().getTotalVotos());
        assertEquals(0, pauta.getResultado().getTotalVotosNao());
        assertEquals(1, pauta.getResultado().getTotalVotosSim());
    }

    @Test
    public void validaAdiconaVoto(){
        pauta.adicionaVoto(Voto.builder()
                .associado(
                        Associado.builder()
                                .id("uuidaleatorio123")
                                .build()
                )
                .votoTipo(VotoTipo.SIM)
                .build());
        assertFalse(pauta.getVotos().isEmpty());
    }

}
