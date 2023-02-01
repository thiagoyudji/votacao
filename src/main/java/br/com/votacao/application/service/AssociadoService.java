package br.com.votacao.application.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
@Service
public class AssociadoService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AssociadoService.class);
    private final AssociadoRepositoryService associadoRepositoryService;
    private final CpfAPIService cpfAPIService;

    public AssociadoService(AssociadoRepositoryService associadoRepositoryService,
                            CpfAPIService cpfAPIService) {
        this.associadoRepositoryService = associadoRepositoryService;
        this.cpfAPIService = cpfAPIService;
    }
    public Associado novo(String cpf) {
        LOGGER.info("Associado do CPF {} esta sendo válidado", cpf);
        cpfAPIService.validaCpf(cpf);
        return associadoRepositoryService.salva(Associado.builder()
                .cpf(cpf)
                .build());
    }
}
