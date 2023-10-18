package com.app.lpnotifier.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;
    @Column(name = "pw", length = 60, nullable = false)
    private String pw;
    @Column(name = "enable", nullable = false)
    private boolean enable;
    @Column(name = "username", length = 60, nullable = false)
    private String username;
    @Column(name = "phone", length = 60, nullable = true)
    private Long phone;
    @Column(name = "verify_token", length = 256, nullable = false)
    private String verify_token;

    //Mucho usuarios pueden tener un rol
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private rol role;

    public user() {

    }

    public user(String name, String email, String pw, String username, Long phone, rol role, String verify_token) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.username = username;
        this.phone = phone;
        this.role = role;
        this.verify_token = verify_token;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public boolean getEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public rol getRole() {
        return role;
    }
    public void setRole(rol role) {
        this.role = role;
    }
    public String getVerify_token() {
        return verify_token;
    }
    public void setVerify_token(String verify_token) {
        this.verify_token = verify_token;
    }
}
