package br.com.votacao.entrypoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PautaException extends ResponseStatusException {

    public PautaException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
