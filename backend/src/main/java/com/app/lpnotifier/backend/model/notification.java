package com.app.lpnotifier.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "notificaciones")
public class notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "concesion", nullable = false)
    private Boolean concesion;

    @Column(name = "Compraventa", nullable = false)
    private Boolean compraventa;

    @Column(name = "Seguros", nullable = false)
    private Boolean seguros;

    @Column(name = "ND", nullable = false)
    private Boolean nd;

    @Column(name = "Suministros", nullable = false)
    private Boolean suministros;

    @Column(name = "Servicios_de_aprovisionamiento", nullable = false)
    private Boolean servicios_de_aprovisionamiento;

    @Column(name = "Obra", nullable = false)
    private Boolean obra;

    @Column(name = "Otros_servicios", nullable = false)
    private Boolean otros_servicios;

    public notification() {
    }

    public notification(String correo, Boolean concesion, Boolean compraventa, Boolean seguros, Boolean ND, Boolean suministros, Boolean servicios_de_aprovisionamiento, Boolean obra, Boolean otros_servicios) {
        this.correo = correo;
        this.concesion = concesion;
        this.compraventa = compraventa;
        this.seguros = seguros;
        this.nd = ND;
        this.suministros = suministros;
        this.servicios_de_aprovisionamiento = servicios_de_aprovisionamiento;
        this.obra = obra;
        this.otros_servicios = otros_servicios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getConcesion() {
        return concesion;
    }

    public void setConcesion(Boolean concesion) {
        this.concesion = concesion;
    }

    public Boolean getCompraventa() {
        return compraventa;
    }

    public void setCompraventa(Boolean compraventa) {
        this.compraventa = compraventa;
    }

    public Boolean getSeguros() {
        return seguros;
    }

    public void setSeguros(Boolean seguros) {
        this.seguros = seguros;
    }

    public Boolean getND() {
        return nd;
    }

    public void setND(Boolean ND) {
        this.nd = ND;
    }

    public Boolean getSuministros() {
        return suministros;
    }

    public void setSuministros(Boolean suministros) {
        this.suministros = suministros;
    }

    public Boolean getServicios_de_aprovisionamiento() {
        return servicios_de_aprovisionamiento;
    }

    public void setServicios_de_aprovisionamiento(Boolean servicios_de_aprovisionamiento) {
        this.servicios_de_aprovisionamiento = servicios_de_aprovisionamiento;
    }

    public Boolean getObra() {
        return obra;
    }

    public void setObra(Boolean obra) {
        this.obra = obra;
    }

    public Boolean getOtros_servicios() {
        return otros_servicios;
    }

    public void setOtros_servicios(Boolean otros_servicios) {
        this.otros_servicios = otros_servicios;
    }
}
