package com.cadastro.relacional.empresa.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPJFormatterUtil {
    private static final Pattern UNFORMATTED_CNPJ_PATTERN = Pattern.compile("\\d{14}");
    private static final String CNPJ_FORMAT = "%s.%s.%s/%s-%s";

    public static String formatCNPJ(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo");
        }

        String digitsOnly = cnpj.replaceAll("\\D", "");

        Matcher matcher = UNFORMATTED_CNPJ_PATTERN.matcher(digitsOnly);
        if (matcher.matches()) {
            return String.format(CNPJ_FORMAT,
                    digitsOnly.substring(0, 2),
                    digitsOnly.substring(2, 5),
                    digitsOnly.substring(5, 8),
                    digitsOnly.substring(8, 12),
                    digitsOnly.substring(12, 14));
        }

        return cnpj;
    }


}
