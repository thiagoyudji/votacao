package br.com.votacao.entrypoint.controller;

import br.com.votacao.application.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.votacao.domain.model.Associado;
import br.com.votacao.entrypoint.filter.AssociadoFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;

@Configuration
@Validated
@RestController
@RequestMapping("/v1/associado")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private AssociadoFilter associadoFilter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cria associado")
    @PostMapping("/novo")
    public Associado novo(
            @RequestParam(name = "cpf", required = true)
            @NotBlank(message = "CPF é obrigatório!") final String cpf) {
        associadoFilter.validaNovoAssociado(cpf);
        return associadoService.novo(cpf);
    }
}
