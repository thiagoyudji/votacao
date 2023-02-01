package br.com.votacao.entrypoint.filter;

import org.springframework.stereotype.Component;

import br.com.votacao.domain.repository.VotoRepository;
import br.com.votacao.domain.repository.service.PautaRepositoryService;
import br.com.votacao.entrypoint.handler.AssociadoException;
import br.com.votacao.entrypoint.handler.PautaException;

@Component
public class VotoFilter {

    private VotoRepository votoRepository;

    private PautaRepositoryService pautaRepository;

    public VotoFilter(VotoRepository votoRepository, PautaRepositoryService pautaRepository) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
    }

    public void validaNovoVoto(final String pautaId, final String associadoId){
        if (!pautaRepository.buscaPorId(pautaId).isSessaoDisponivel())
            throw new PautaException("Sessão de votação para esta pauta não está disponivel.");
        if (votoRepository.findByAssociadoAndPauta(associadoId, pautaId).isPresent())
            throw new AssociadoException("O Associado já votou nessa pauta.");
    }
}
