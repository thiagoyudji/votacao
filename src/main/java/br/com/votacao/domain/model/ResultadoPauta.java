package br.com.votacao.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resultado_pauta")
public class ResultadoPauta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonProperty("pauta_id")
    @Column(name = "pauta_id")
    private String pautaId;

    @JsonProperty("votos_totais")
    @Column(name = "votos_totais")
    private long totalVotos;

    @JsonProperty("votos_totais_sim")
    @Column(name = "votos_totais_sim")
    private long totalVotosSim;

    @JsonProperty("votos_totais_nao")
    @Column(name = "votos_totais_nao")
    private long totalVotosNao;

}
