package com.projeto.saara.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resposta {
    private int code;
    private String error;
    private Object body;

}
