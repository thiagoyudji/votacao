package br.com.votacao.domain.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.votacao.entrypoint.dto.ResponseCpfApiDTO;
@FeignClient(name = "cpfApi", url="${url.api.cpf}")
public interface CpfAPIRepository {

    @GetMapping("/users/{cpf}")
    ResponseCpfApiDTO validaCpf(@PathVariable("cpf") final String cpf);
}
