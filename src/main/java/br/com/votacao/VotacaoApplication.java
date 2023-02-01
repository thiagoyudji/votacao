package br.com.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import br.com.votacao.domain.repository.CpfAPIRepository;
import feign.Feign;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableKafka
@SpringBootApplication
@EnableFeignClients
public class VotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

	@KafkaListener(topics = "resultado-pautas-topic", groupId = "votacao")
	public void resultadoPautaListener(Object message) {
		System.out.println("Sessão de pauta finalizada: " + message);
	}
}
