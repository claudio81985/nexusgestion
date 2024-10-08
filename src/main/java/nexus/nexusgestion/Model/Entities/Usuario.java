package nexus.nexusgestion.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, min = 5)
    @Column(name = "nombre", unique = true)
    // @Pattern(regexp = "[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+\\.?)*", message = "Escribe nuevamente tu nombre")
    private String nombre;

    @Size(max = 70, min = 6, message = "La contraseña debe contener al menos 6 caracteres.")
    private String clave;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", referencedColumnName = "id")
    private Permiso permiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id")
    private Sucursal sucursalAsignada;

    public Usuario() {
        activo = true;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Sucursal getSucursalAsignada() {
        return sucursalAsignada;
    }

    public void setSucursalAsignada(Sucursal sucursalAsignada) {
        this.sucursalAsignada = sucursalAsignada;
    }

    @Override
    public String toString() {
        return nombre + permiso.getNombre();
    }

}
