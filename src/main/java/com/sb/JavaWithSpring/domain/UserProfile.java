package com.sb.JavaWithSpring.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    //хз что с этим делать как то либо так либо
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    //либо так
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String city;

    @Column()
    private Date birthdayDate;

    @Column(insertable = true, updatable = true, length = 40)
    private String profile;

    @Column(insertable = true, updatable = true, length = 40)
    private String hobbies;

    @Column(insertable = true, updatable = true, length = 40)
    private String achivments;

    @Column(insertable = true, updatable = true, length = 40)
    private String careere;


    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAchivments() {
        return achivments;
    }

    public void setAchivments(String achivments) {
        this.achivments = achivments;
    }

    public String getCareere() {
        return careere;
    }

    public void setCareere(String careere) {
        this.careere = careere;
    }
}
