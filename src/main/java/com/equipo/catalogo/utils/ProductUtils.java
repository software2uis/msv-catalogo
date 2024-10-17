package com.equipo.catalogo.utils;

import java.text.Normalizer;

public interface ProductUtils {
    public static String removeAccents(String input) {
        // Normaliza el texto y elimina los acentos/diacríticos
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

}
