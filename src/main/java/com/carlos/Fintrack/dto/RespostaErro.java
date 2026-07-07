package com.carlos.Fintrack.dto;

import java.time.LocalDateTime;

public record RespostaErro (
        int status,
        String mensagem,
        LocalDateTime timestamp,
        String caminho
) {}


