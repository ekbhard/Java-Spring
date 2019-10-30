package com.sb.JavaWithSpring.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(length = 1024)
    private String city;

    public UserProfile() {
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private java.util.Date dateBirthday;

    @Column(insertable = true, updatable = true, length = 40, nullable = true)
    private String profile;

    @Column(insertable = true, updatable = true, length = 40, nullable = true)
    private String hobbies;

    @Column(insertable = true, updatable = true, length = 40, nullable = true)
    private String achievements;

    @Column(insertable = true, updatable = true, length = 40, nullable = true)
    private String career;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user",unique = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
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

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
