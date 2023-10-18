package com.app.lpnotifier.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "licitaciones")
public class licitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "id_proceso", length = 100, nullable = false, unique = true)
    private String id_proceso;
    @Column(name = "entidad", nullable = false)
    private String entidad;
    @Column(name = "nit_entidad", nullable = false)
    private String nit_entidad;
    @Column(name = "nombre_procedimiento", nullable = false)
    private String nombre_procedimiento;
    @Column(name = "fase", length = 50, nullable = false)
    private String fase;
    @Column(name = "fecha_publicacion", nullable = false)
    private Date fecha_publicacion;
    @Column(name = "precio_base", nullable = false)
    private Long precio_base;
    @Column(name = "justificacion_modalidad", length = 100, nullable = false)
    private String justificacion_modalidad;
    @Column(name = "duracion", nullable = false)
    private Integer duracion;
    @Column(name = "unidad_de_duracion", length = 50, nullable = false)
    private String unidad_de_duracion;
    @Column(name = "ciudad_de_la_unidad", length = 50, nullable = false)
    private String ciudad_de_la_unidad;
    @Column(name = "nombre_de_la_unidad", length = 150, nullable = false)
    private String nombre_de_la_unidad;
    @Column(name = "tipo_de_contrato", length = 100, nullable = false)
    private String tipoDeContrato;
    @Column(name = "url", length = 256, nullable = false)
    private String url;

    public licitation() {

    }

    public licitation(String id_proceso, String entidad, String nit_entidad, String nombre_procedimiento, String fase, Date fecha_publicacion, Long precio_base, String justificacion_modalidad, Integer duracion, String unidad_de_duracion, String ciudad_de_la_unidad, String nombre_de_la_unidad, String tipoDeContrato, String url) {
        this.id_proceso = id_proceso;
        this.entidad = entidad;
        this.nit_entidad = nit_entidad;
        this.nombre_procedimiento = nombre_procedimiento;
        this.fase = fase;
        this.fecha_publicacion = fecha_publicacion;
        this.precio_base = precio_base;
        this.justificacion_modalidad = justificacion_modalidad;
        this.duracion = duracion;
        this.unidad_de_duracion = unidad_de_duracion;
        this.ciudad_de_la_unidad = ciudad_de_la_unidad;
        this.nombre_de_la_unidad = nombre_de_la_unidad;
        this.tipoDeContrato = tipoDeContrato;
        this.url = url;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(String id_proceso) {
        this.id_proceso = id_proceso;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getNit_entidad() {
        return nit_entidad;
    }

    public void setNit_entidad(String nit_entidad) {
        this.nit_entidad = nit_entidad;
    }

    public String getNombre_procedimiento() {
        return nombre_procedimiento;
    }

    public void setNombre_procedimiento(String nombre_procedimiento) {
        this.nombre_procedimiento = nombre_procedimiento;
    }

    public String isFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Long getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(Long precio_base) {
        this.precio_base = precio_base;
    }

    public String getJustificacion_modalidad() {
        return justificacion_modalidad;
    }

    public void setJustificacion_modalidad(String justificacion_modalidad) {
        this.justificacion_modalidad = justificacion_modalidad;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getUnidad_de_duracion() {
        return unidad_de_duracion;
    }

    public void setUnidad_de_duracion(String unidad_de_duracion) {
        this.unidad_de_duracion = unidad_de_duracion;
    }

    public String getCiudad_de_la_unidad() {
        return ciudad_de_la_unidad;
    }

    public void setCiudad_de_la_unidad(String ciudad_de_la_unidad) {
        this.ciudad_de_la_unidad = ciudad_de_la_unidad;
    }

    public String getNombre_de_la_unidad() {
        return nombre_de_la_unidad;
    }

    public void setNombre_de_la_unidad(String nombre_de_la_unidad) {
        this.nombre_de_la_unidad = nombre_de_la_unidad;
    }

    public String getTipo_de_contrato() {
        return tipoDeContrato;
    }

    public void setTipo_de_contrato(String tipo_de_contrato) {
        this.tipoDeContrato = tipo_de_contrato;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
