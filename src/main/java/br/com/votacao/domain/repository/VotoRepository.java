package br.com.votacao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.votacao.domain.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Voto save(final Voto Voto);

    Optional<Voto> findById(final String id);

    @Query("SELECT v FROM Voto v WHERE v.associado.id = ?1 AND v.pauta.id = ?2")
    Optional<Voto> findByAssociadoAndPauta(final String associadoId, final String pautaId);
}
