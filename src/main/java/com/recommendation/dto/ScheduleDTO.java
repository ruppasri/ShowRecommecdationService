package com.recommendation.dto;

public class ScheduleDTO {
    private int id;
    private ShowDTO show;
    private EmbededDTO _embedded;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    public EmbededDTO get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbededDTO _embedded) {
        this._embedded = _embedded;
    }
}
