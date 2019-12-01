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

//    @Lob
//    @Basic(fetch = FetchType.LAZY)

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }
//    private byte[] profilePicture;

    @Column(name = "img", updatable = false, nullable = false, length = 8000)
    private String base64Img;

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

//    public byte[] getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(byte[] profilePicture) {
//        this.profilePicture = profilePicture;
//    }
}
