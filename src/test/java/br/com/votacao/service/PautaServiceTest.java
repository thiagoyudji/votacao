package br.com.votacao.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import br.com.votacao.application.service.PautaService;
import br.com.votacao.application.service.enumerated.VotoTipo;
import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

    private Pauta pauta;

    @Mock
    private PautaRepositoryService repository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private PautaService pautaService;

    @Before
    public void init() {
        this.pauta = Pauta.builder().nome("Pauta xxxx").id("uuidteste").dataExpiracaoSessao(LocalDateTime.now()).build();
    }

    @Test
    public void nova(){
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Pauta pauta = pautaService.nova("Pauta xxxx");
        assertNotNull(pauta);
        assertEquals("Pauta xxxx", pauta.getNome());
    }

    @Test
    public void abreSessao(){
        when(repository.buscaPorNome(anyString())).thenReturn(this.pauta);
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaService.abreSessao(this.pauta.getId(), LocalDateTime.now().plusDays(10));
        assertNotNull(this.pauta.getDataExpiracaoSessao());
        assertTrue(this.pauta.isSessaoDisponivel());
    }

    @Test
    public void contabilizaVoto(){
        when(repository.salva(any(Pauta.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        this.pauta = pautaService.contabilizaVoto(this.pauta, Voto.builder()
                .associado(
                        Associado.builder()
                                .cpf("")
                                .build()
                )
                .votoTipo(VotoTipo.SIM)
                .build());
        assertFalse(this.pauta.getVotos().isEmpty());
    }
}
