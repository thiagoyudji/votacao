package br.com.votacao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.votacao.domain.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado save(final Associado Associado);

    Optional<Associado> findById(final String id);

    Optional<Associado> findByCpf(final String cpf);
}