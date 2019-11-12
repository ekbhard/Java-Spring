package com.sb.JavaWithSpring.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 40)
    private String place;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 2048)
    private String text;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "dateStart")
    private java.util.Date dateStart;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "dateEnd")
    private java.util.Date dateEnd;

    @ManyToMany
    @JoinTable(name="user_event",
            joinColumns=@JoinColumn(name="event_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;


    public Events() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users.add(users);
    }

}
