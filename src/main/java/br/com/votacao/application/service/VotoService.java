package br.com.votacao.application.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.votacao.application.service.enumerated.VotoTipo;
import br.com.votacao.domain.model.Associado;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.service.AssociadoRepositoryService;
import br.com.votacao.domain.repository.service.PautaRepositoryService;

@Service
public class VotoService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

    private final PautaService pautaService;

    private final AssociadoRepositoryService associadoRepository;

    private final PautaRepositoryService pautaRepository;

    public VotoService(PautaService pautaService,
                       AssociadoRepositoryService associadoRepository,
                       PautaRepositoryService pautaRepository) {
        this.pautaService = pautaService;
        this.associadoRepository = associadoRepository;
        this.pautaRepository = pautaRepository;
    }

    public Voto novo(String associadoId, VotoTipo votoTipo, String pautaId) {
        Pauta pauta = pautaRepository.buscaPorId(pautaId);
        Associado associado = associadoRepository.buscaPorId(associadoId);
        Voto voto = Voto.builder()
                        .votoTipo(votoTipo)
                        .associado(associado)
                        .pauta(pauta)
                        .build();
        pautaService.contabilizaVoto(pauta, voto);
        LOGGER.info("Novo voto criado para a pauta: {}", pauta.getNome());
        return voto;
    }

}
