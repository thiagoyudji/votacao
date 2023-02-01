package br.com.votacao.domain.repository.service;

import org.springframework.stereotype.Service;
import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.repository.AssociadoRepository;
import br.com.votacao.entrypoint.handler.AssociadoException;

@Service
public class AssociadoRepositoryService {

    private final AssociadoRepository repository;

    public AssociadoRepositoryService(AssociadoRepository repository) {
        this.repository = repository;
    }

    public Associado salva(Associado associado){
        return repository.save(associado);
    }

    public Associado buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new AssociadoException("Não existe um associado com o ID informado."));
    }
}
