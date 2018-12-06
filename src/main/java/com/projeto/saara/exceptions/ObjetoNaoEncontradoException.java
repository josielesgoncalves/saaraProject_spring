package com.projeto.saara.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code = 1;

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}
