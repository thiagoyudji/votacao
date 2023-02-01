package br.com.votacao.entrypoint.filter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.votacao.domain.repository.PautaRepository;
import br.com.votacao.entrypoint.handler.PautaException;

@Component
public class PautaFilter {

    private final PautaRepository pautaRepository;

    public PautaFilter(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public void validaAberturaSessao(final LocalDateTime dataExpiracao){
        if (dataExpiracao.isBefore(LocalDateTime.now()))
            throw new PautaException("Data de expiração da sessão é inválida.");
    }
    public void validaNovaPauta(final String nome){
        if (pautaRepository.findByNome(nome).isPresent())
            throw new PautaException("Já existe uma pauta com esse nome.");
    }
}
