package com.app.lpnotifier.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "urlPreferencias")
public class urlPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "concatenacion", nullable = false)
    private String concatenacion;

    public urlPreferences() {

    }

    public urlPreferences(String correo, String concatenacion) {
        this.correo = correo;
        this.concatenacion = concatenacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConcatenacion() {
        return concatenacion;
    }

    public void setConcatenacion(String concatenacion) {
        this.concatenacion = concatenacion;
    }
}
