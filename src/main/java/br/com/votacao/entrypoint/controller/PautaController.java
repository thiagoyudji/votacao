package br.com.votacao.entrypoint.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import br.com.votacao.application.service.PautaService;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.entrypoint.filter.PautaFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;

@Configuration
@Validated
@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private PautaFilter pautaFilter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria uma nova pauta")
    @PostMapping("/nova")
    public Pauta nova(
            @RequestParam(name = "nome", required = true)
            @NotBlank(message = "Nome de pauta é obrigatório!") final String nome) {
        pautaFilter.validaNovaPauta(nome);
        return pautaService.nova(nome);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Abre a sessão de votos de uma pauta")
    @PutMapping("/nova-sessao")
    public Pauta abreSessao(
            @RequestParam(name = "nome", required = true)
            @NotBlank(message = "Nome da pauta é obrigatório!") final String nome,
            @RequestParam(name = "data_expiracao", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") final LocalDateTime dataExpiracao) {
        pautaFilter.validaAberturaSessao(dataExpiracao);
        return pautaService.abreSessao(nome, dataExpiracao);
    }

}