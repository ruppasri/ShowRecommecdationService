package com.recommendation.dto;

public class CrewCreditDto {
    private String type;
    private EmbededDTO _embedded;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EmbededDTO get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbededDTO _embedded) {
        this._embedded = _embedded;
    }
}
