package com.recommendation.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "user")
public class User {
    @Id
    private Long phone;
    private String name;
    private String password;
    private int[] cast;
    private int[] crew;
    private ArrayList<Integer> shows;

    public User (Long phone, String name, String password) {this.phone = phone; this.name = name; this.password = password;}
    public Long getPhone() {return phone;}
    public void setPhone(Long phone) {this.phone = phone;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int[] getCast() {return cast;}
    public void setCast(int[] cast) {this.cast = cast;}
    public int[] getCrew() {return crew;}
    public void setCrew(int[] crew) {this.crew = crew;}
    public ArrayList<Integer> getShows() {return shows;}
    public void setShows(ArrayList<Integer> shows) {this.shows = shows;}
}
