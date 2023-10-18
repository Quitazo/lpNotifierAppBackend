package com.app.lpnotifier.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class rol {

    @Id
    private Long id;
    @Column(name = "typeName")
    private String typeName;

    public rol() {

    }

    public Long getId(long l) {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
