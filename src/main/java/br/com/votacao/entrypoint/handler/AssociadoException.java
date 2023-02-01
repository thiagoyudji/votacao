package br.com.votacao.entrypoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AssociadoException extends ResponseStatusException {

    public AssociadoException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
