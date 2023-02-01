package br.com.votacao.entrypoint.controller;

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

import br.com.votacao.application.service.VotoService;
import br.com.votacao.application.service.enumerated.VotoTipo;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.entrypoint.filter.VotoFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;

@Configuration
@Validated
@RestController
@RequestMapping("/v1/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @Autowired
    private VotoFilter votoFilter;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Novo voto")
    @PostMapping("/novo")
    public Voto novo(@RequestParam(name = "associado_id", required = true)
                     @NotBlank(message = "ID de associado é obrigatório!") final String associadoId,
                     @RequestParam(name = "voto", required = true) final VotoTipo voto,
                     @RequestParam(name = "pauta_id", required = true)
                     @NotBlank(message = "ID de pauta é obrigatório!") final String pautaId) {
        votoFilter.validaNovoVoto(pautaId, associadoId);
        return votoService.novo(associadoId, voto, pautaId);
    }

}