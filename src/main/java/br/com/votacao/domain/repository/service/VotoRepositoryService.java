package br.com.votacao.domain.repository.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.VotoRepository;

@Service
public class VotoRepositoryService {

    private final VotoRepository repository;

    public VotoRepositoryService(VotoRepository repository) {
        this.repository = repository;
    }

    public Voto salva(Voto voto){
        return repository.save(voto);
    }

    public Voto buscaPorId(final String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe uma voto com o ID informado!"));
    }

}
