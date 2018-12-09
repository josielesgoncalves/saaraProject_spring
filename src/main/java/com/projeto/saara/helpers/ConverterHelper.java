package com.projeto.saara.helpers;

import com.projeto.saara.enums.DiaEnum;
import com.projeto.saara.enums.LembreteTypeEnum;
import com.projeto.saara.enums.NotaTypeEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.exceptions.ParametroInvalidoException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class ConverterHelper {

    private ConverterHelper() {
    }

    public static String convertLongToString(Long id) {
        if (id == null)
            throw new ParametroInvalidoException("Long a ser convertido nulo");

        return id.toString();
    }

    public static Long convertStringToLong(String id) {
        if (!validaString(id))
            throw new ParametroInvalidoException("String a ser convertida nula");

        return Long.parseLong(id);
    }

    public static String convertCalendarToString(Calendar data) {
        if (data == null)
            throw new ParametroInvalidoException("Data a ser convertida nula");

        DateFormat df = DateFormat.getDateTimeInstance();

        return df.format(data);
    }

    public static Calendar convertStringToCalendar(String data) {
        if (data == null)
            throw new ParametroInvalidoException("String de data a ser convertida null");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar calendar = Calendar.getInstance();

        try
        {
            calendar.setTime(sdf.parse(data));
        }catch(ParseException pex){
            throw new ParametroInvalidoException("Data nao pode ser convertida");
        }
        return calendar;
    }

    public static String convertDoubleToString(double valor) {
        return String.valueOf(valor);
    }

    public static double convertStringToDouble(String valor) {
        if (valor == null) {
            throw new ParametroInvalidoException("valor para converter para string nulo");
        }

        return Double.valueOf(valor);
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

    public static DiaEnum convertIdToDiaEnum(Long id) {
        DiaEnum diaEnum = null;
        for (DiaEnum tipo : DiaEnum.values()) {
            if (tipo.getId().equals(id)) {
                diaEnum = tipo;
            }
        }
        return diaEnum;
    }

    private static boolean validaString(String string)
    {
        if(string == null)
            return false;
        return !string.equals("");
    }
}
