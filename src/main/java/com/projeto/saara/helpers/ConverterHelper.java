package com.projeto.saara.helpers;

import com.projeto.saara.enums.StatusEnum;

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

    public static StatusEnum convertStringToStatusEnum(String status) throws
            ValidationException {

        StatusEnum statusEnum = null;

        if (status == null ||
                !status.equals(StatusEnum.CURSADA.name()) ||
                !status.equals(StatusEnum.CURSANDO.name()) ||
                !status.equals(StatusEnum.NAO_CURSADA.name())) {
            throw new ValidationException();
        }

        if (status.equals(StatusEnum.CURSADA.name())) {
            statusEnum = StatusEnum.CURSADA;
        }

        if (status.equals(StatusEnum.CURSANDO.name())) {
            statusEnum = StatusEnum.CURSANDO;
        }

        if (status.equals(StatusEnum.NAO_CURSADA.name())) {
            statusEnum = StatusEnum.NAO_CURSADA;
        }
        return statusEnum;
    }
}
