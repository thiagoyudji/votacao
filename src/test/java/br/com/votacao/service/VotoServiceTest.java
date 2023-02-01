package br.com.votacao.service;

import br.com.votacao.application.service.PautaService;
import br.com.votacao.application.service.VotoService;
import br.com.votacao.application.service.enumerated.VotoTipo;
import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceTest {

    @Mock
    private AssociadoRepositoryService associadoRepository;

    @Mock
    private PautaRepositoryService pautaRepository;

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private VotoService votoService;

    @Test
    public void novo(){
        Pauta pauta = Pauta.builder().nome("Pauta xxxx").build();
        when(pautaService.contabilizaVoto(any(Pauta.class), any(Voto.class))).thenReturn(pauta);
        when(associadoRepository.buscaPorId(anyString())).thenReturn(Associado.builder().cpf("12345").build());
        when(pautaRepository.buscaPorId(anyString())).thenReturn(pauta);

        Voto voto = votoService.novo("123456", VotoTipo.SIM, "654321");
        assertNotNull(voto);
        assertEquals(VotoTipo.SIM, voto.getVotoTipo());
        assertEquals("12345", voto.getAssociado().getCpf());
        assertEquals("Pauta xxxx", voto.getPauta().getNome());
    }
}
