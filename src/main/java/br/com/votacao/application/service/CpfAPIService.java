package br.com.votacao.application.service;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.votacao.domain.repository.CpfAPIRepository;
import br.com.votacao.entrypoint.dto.ResponseCpfApiDTO;
import br.com.votacao.entrypoint.handler.CpfApiException;

@Service
public class CpfAPIService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CpfAPIService.class);

    private final CpfAPIRepository cpfApiRepository;

    public CpfAPIService(CpfAPIRepository cpfApiRepository) {
        this.cpfApiRepository = cpfApiRepository;
    }

    public void validaCpf(String cpf) {
        try {
            ResponseCpfApiDTO responseCpfApiDTO = cpfApiRepository.validaCpf(cpf);
            validaCpfPodeVotar(responseCpfApiDTO);
        } catch (feign.FeignException e){
            LOGGER.info("Algo deu errado na requisição: {} status: {}", e.getMessage(), e.status());
            throw new CpfApiException(HttpStatus.BAD_GATEWAY, "CPF inválido.");
        }
    }

    private void validaCpfPodeVotar(final ResponseCpfApiDTO responseCpfApiDTO) {
        if(responseCpfApiDTO.getStatus().equals("UNABLE_TO_VOTE"))
            throw new CpfApiException(HttpStatus.BAD_REQUEST, "O Associado não pode votar");
    }
}
