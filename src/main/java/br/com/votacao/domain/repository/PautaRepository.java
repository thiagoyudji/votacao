package br.com.votacao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.votacao.domain.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Pauta save(final Pauta pauta);

    Optional<Pauta> findById(final String id);

    Optional<Pauta> findByNome(final String nome);
}

