package br.com.votacao.application.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.service.PautaRepositoryService;

@Service
public class PautaService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PautaService.class);

    private final PautaRepositoryService pautaRepositoryService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PautaService(PautaRepositoryService pautaRepositoryService,
                        KafkaTemplate<String, Object> kafkaTemplate) {
        this.pautaRepositoryService = pautaRepositoryService;
        this.kafkaTemplate = kafkaTemplate;
    }
    public Pauta nova(String nome) {
        LOGGER.info("Nova pauta: {} esta sendo criada.", nome);
        return pautaRepositoryService.salva(Pauta.builder().nome(nome).build());
    }

    public Pauta abreSessao(String nome, LocalDateTime dataExpiracao) {
        Pauta pauta = pautaRepositoryService.buscaPorNome(nome);
        pauta.abreSessao(dataExpiracao);
        agendaFimSessao(pauta);
        LOGGER.info("Nova sessão: {} com a data de expiração: {} esta sendo criada.", pauta.getId(), dataExpiracao);
        return pautaRepositoryService.salva(pauta);
    }

    public Pauta contabilizaVoto(Pauta pauta, Voto voto) {
        pauta.adicionaVoto(voto);
        LOGGER.info("Novo voto: {} na pauta: {} foi feito.", voto.getId(), pauta.getNome());
        return pautaRepositoryService.salva(pauta);
    }

    private void agendaFimSessao(Pauta pauta) {
        new Timer().schedule(finalizaSessao(pauta.getId()), Date.from(pauta.getDataExpiracaoSessao().atZone(ZoneId.systemDefault()).toInstant()));
        LOGGER.info("Fim da sessão criado para a pauta: {}", pauta.getNome());
    }

    private TimerTask finalizaSessao(String pautaId) {
        return new TimerTask() {
            @Override
            public void run() {
                Pauta pauta = pautaRepositoryService.buscaPorId(pautaId);
                pauta.finalizaSessao();
                pautaRepositoryService.salva(pauta);
                LOGGER.info("Resultado enviado na fila para a pauta {}", pauta.getNome());
                kafkaTemplate.send("resultado-topic", pauta.getResultado());
            }
        };
    }
}
