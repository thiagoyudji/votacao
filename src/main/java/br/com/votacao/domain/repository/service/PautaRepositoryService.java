package br.com.votacao.domain.repository.service;

import org.springframework.stereotype.Service;

import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.repository.PautaRepository;
import br.com.votacao.entrypoint.handler.PautaException;

@Service
public class PautaRepositoryService {

    private final PautaRepository pautaRepository;

    public PautaRepositoryService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta salva(final Pauta pauta){
        return pautaRepository.save(pauta);
    }

    public Pauta buscaPorId(final String id) {
        return pautaRepository.findById(id).orElseThrow(() -> new PautaException("Não existe uma pauta com o ID informado."));
    }
    public Pauta buscaPorNome(final String nome) {
        return pautaRepository.findByNome(nome).orElseThrow(() -> new PautaException("Não existe uma pauta com o nome informado."));
    }
}
