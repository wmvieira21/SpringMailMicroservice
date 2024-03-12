package com.microservice.mail.email.enums;

public enum StatusEmail {

    SENT("Enviado"), DRAFT("Rascunho"), RECEIVED("Recebido"), ERROR("Erro");

    private String statusName;


    StatusEmail(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return this.statusName;
    }

}
