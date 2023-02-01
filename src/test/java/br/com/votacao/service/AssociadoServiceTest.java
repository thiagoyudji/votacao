package br.com.votacao.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import br.com.votacao.application.service.AssociadoService;
import br.com.votacao.application.service.CpfAPIService;
import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceTest {

    @Mock
    private AssociadoRepositoryService associadoRepositoryService;

    @Mock
    private CpfAPIService cpfAPIService;

    @InjectMocks
    private AssociadoService associadoService;

    @Test
    public void validaNovoAssociado(){
        doNothing().when(cpfAPIService).validaCpf(anyString());
        when(associadoRepositoryService.salva(any(Associado.class))).thenAnswer(answer -> {
            return answer.getArgument(0);
        });
        Associado associado = associadoService.novo("123456");
        assertNotNull(associado);
        assertEquals("123456", associado.getCpf());
    }
}
