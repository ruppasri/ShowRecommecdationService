package com.recommendation.dto;

public class CashCreditDto {
    private boolean self;
    private boolean voice;
    private EmbededDTO _embedded;

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public EmbededDTO get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbededDTO _embedded) {
        this._embedded = _embedded;
    }
}
