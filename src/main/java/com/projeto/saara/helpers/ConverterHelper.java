package com.projeto.saara.helpers;

public final class ConverterHelper {

    private ConverterHelper() {
    }

    public static String convertLongToString(Long id) throws ValidationException {
        String newId = id.toString();

        if (newId == null)
            throw new ValidationException();
        return newId;
    }

    public static Long convertStringToLong(String id) throws ValidationException {
        Long newId = Long.parseLong(id);

        if (newId == null)
            throw new ValidationException();

        return newId;
    }
}
