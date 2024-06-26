package com.leo.teste.api.enums;

public enum TipoTransacaoEnum {
    PIX('P',"",0),
    DEBITO('D',"Debito", 3),
    CREDITO('C',"Credito", 5);

    private final char codigoChar;
    private final String descricao;
    private final float percentualTaxa;
    TipoTransacaoEnum(char codigoChar , String descr, float taxa){
        this.codigoChar = codigoChar;
        this.descricao = descr;
        this.percentualTaxa = taxa;
    }

    public String getDescricao() {
        return descricao;
    }
    public char getCodigoChar(){return codigoChar;}
    public float getPercentualTaxa(){return percentualTaxa;}

    public static TipoTransacaoEnum fromChar(Character valor) {
        for (TipoTransacaoEnum v : TipoTransacaoEnum.values()) {
            if (Character.valueOf(v.codigoChar).equals(valor)) {
                return v;
            }
        }
        return null;
    }
}
