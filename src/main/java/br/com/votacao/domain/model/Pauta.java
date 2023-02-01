package br.com.votacao.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import br.com.votacao.application.service.enumerated.VotoTipo;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    private List<Voto> votos = new ArrayList<>();

    @JsonProperty("sessao_disponivel")
    @Column(name = "sessao_disponivel")
    @Builder.Default
    private boolean sessaoDisponivel = false;

    @JsonProperty("data_expiracao_sessao")
    @Column(name = "data_expiracao_sessao")
    private LocalDateTime dataExpiracaoSessao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ResultadoPauta resultado;

    public void abreSessao(LocalDateTime dataExpiracaoSessao) {
        if (dataExpiracaoSessao == null)
            dataExpiracaoSessao = LocalDateTime.now().plusMinutes(1);
        this.dataExpiracaoSessao = dataExpiracaoSessao;
        this.sessaoDisponivel = true;
    }

    public void finalizaSessao() {
        this.sessaoDisponivel = false;
        this.contabilizaResultado();
    }

    public void contabilizaResultado() {
        long nao = this.votos.parallelStream().filter(v -> v.getVotoTipo() == VotoTipo.NAO).count();
        long sim = this.votos.parallelStream().filter(v -> v.getVotoTipo() == VotoTipo.SIM).count();
        this.resultado = ResultadoPauta
                .builder()
                .pautaId(this.id)
                .totalVotosSim(sim)
                .totalVotosNao(nao)
                .totalVotos(sim+nao)
                .build();
    }

    public void adicionaVoto(Voto voto) {
        this.votos.add(voto);
    }
}
