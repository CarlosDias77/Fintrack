package com.carlos.Fintrack.config;

import com.carlos.Fintrack.dto.RespostaErro;
import com.carlos.Fintrack.exception.LancamentoNaoEncontradoException;
import com.carlos.Fintrack.exception.UsuarioInvalidoException;
import com.carlos.Fintrack.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RespostaErro> tratarRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespostaErro(400, ex.getMessage(), LocalDateTime.now(), request.getDescription(false)));
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<RespostaErro> tratarUsuarioInvalidoException(UsuarioInvalidoException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespostaErro(400, ex.getMessage(), LocalDateTime.now(), request.getDescription(false)));
    }

    @ExceptionHandler(LancamentoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> tratarLancamentoNaoEncontradoException(LancamentoNaoEncontradoException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespostaErro(404, ex.getMessage(), LocalDateTime.now(), request.getDescription(false)));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> tratarUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespostaErro(404, ex.getMessage(), LocalDateTime.now(), request.getDescription(false)));
    }
}