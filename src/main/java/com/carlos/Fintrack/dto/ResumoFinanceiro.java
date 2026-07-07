package com.carlos.Fintrack.dto;

public record ResumoFinanceiro(
        Double totalReceitas,
        Double totalDespesas,
        Double saldo
) {}