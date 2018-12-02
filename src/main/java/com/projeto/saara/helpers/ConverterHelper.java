package com.projeto.saara.helpers;

import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.enums.LembreteTypeEnum;
import com.projeto.saara.enums.NotaTypeEnum;
import com.projeto.saara.enums.StatusEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public static String convertCalendarToString(Calendar data)
            throws ValidationException {
        if (data == null) {
            throw new ValidationException();
        }

        DateFormat df = DateFormat.getDateTimeInstance();
        String dataString = df.format(data);

        return dataString;
    }

    public static Calendar convertStringToCalendar(String data)
            throws ValidationException, ParseException {

        if (data == null) {
            throw new ValidationException();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(data));

        return calendar;
    }

    public static String convertDoubleToString(double valor) {
        String resultado = String.valueOf(valor);
        return resultado;
    }

    public static double convertStringToDouble(String valor) throws ValidationException {
        if (valor == null) {
            throw new ValidationException();
        }

        double resultado = Double.valueOf(valor);
        return resultado;
    }

    public static NotaTypeEnum convertIdToNotaTypeEnum(Long id) {
        NotaTypeEnum notaTypeEnum = null;
        for (NotaTypeEnum tipo : NotaTypeEnum.values()) {
            if (tipo.getId().equals(id)) {
                notaTypeEnum = tipo;
            }
        }
        return notaTypeEnum;
    }

    public static StatusEnum convertIdToStatusEnum(Long id) {
        StatusEnum statusEnum = null;
        for (StatusEnum tipo : StatusEnum.values()) {
            if (tipo.getId().equals(id)) {
                statusEnum = tipo;
            }
        }
        return statusEnum;
    }

    public static LembreteTypeEnum convertIdToLembreteTypeEnum(Long id) {
        LembreteTypeEnum lembreteTypeEnum = null;
        for (LembreteTypeEnum tipo : LembreteTypeEnum.values()) {
            if (tipo.getId().equals(id)) {
                lembreteTypeEnum = tipo;
            }
        }
        return lembreteTypeEnum;
    }

}
