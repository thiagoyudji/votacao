package br.com.votacao.entrypoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CpfApiException extends ResponseStatusException {

    public CpfApiException(HttpStatus status, String message) {
        super(status, message);
    }

}
