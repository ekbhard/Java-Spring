package com.sb.JavaWithSpring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    //хз что с этим делать как то либо так либо
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    //либо так


    @Column(length = 1024)
    private String city;

    @Column()
    private Date birhdayDate;

    @Column(insertable = true, updatable = true, length = 40)
    private String profile;

    @Column(insertable = true, updatable = true, length = 40)
    List<String> hobbies;

    @Column(insertable = true, updatable = true, length = 40)
    List<String> achivments;

    @Column(insertable = true, updatable = true, length = 40)
    List<String> careere;


    public Date getBirhdayDate() {
        return birhdayDate;
    }

    public void setBirhdayDate(Date birhdayDate) {
        this.birhdayDate = birhdayDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getAchivments() {
        return achivments;
    }

    public void setAchivments(List<String> achivments) {
        this.achivments = achivments;
    }

    public List<String> getCareere() {
        return careere;
    }

    public void setCareere(List<String> careere) {
        this.careere = careere;
    }

}
