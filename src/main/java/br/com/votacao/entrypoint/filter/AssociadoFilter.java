package br.com.votacao.entrypoint.filter;

import org.springframework.stereotype.Component;

import br.com.votacao.domain.repository.AssociadoRepository;
import br.com.votacao.entrypoint.handler.AssociadoException;

@Component
public class AssociadoFilter {

    private AssociadoRepository associadoRepository;

    public AssociadoFilter(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public void validaNovoAssociado(final String cpf){
        if(associadoRepository.findByCpf(cpf).isPresent())
            throw new AssociadoException("Já existe um associado cadastrado com esse CPF.");
    }
}
