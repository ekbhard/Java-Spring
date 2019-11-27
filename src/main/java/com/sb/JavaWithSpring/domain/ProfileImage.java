package com.sb.JavaWithSpring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userImages")
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user",unique = true)
    private User user;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profilePicture;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
