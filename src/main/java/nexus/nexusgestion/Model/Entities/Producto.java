package nexus.nexusgestion.Model.Entities;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigoIdentificacion", length = 13)
    private String codigoIdentificacion;

    @Size(max = 50)
    @NotBlank(message = "La descripcion es requerida.")
    private String descripcion;

    @Size(max = 30)
    @NotBlank(message = "La descripcion es requerida.")
    private String nombreComun;

    @Size(max = 30)
    @NotBlank(message = "La descripcion es requerida.")
    private String nombreTecnico;

    @NotNull(message = "El precio es requerido.")
    @NumberFormat(pattern = "#,##0.00", style = Style.CURRENCY)
    private BigDecimal precio;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private boolean activo;

    @NotNull(message = "Ingrese la cantidad de Stock")
    private int stockSucursalUno;

    @NotNull(message = "Ingrese la cantidad de Stock")
    private int stockSucursalDos;


    private int stockGeneral;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    private Proveedor proveedor;

    public Producto() {
        activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(String codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public int getStockSucursalUno() {
        return stockSucursalUno;
    }

    public int getStockSucursalDos() {
        return stockSucursalDos;
    }

    public int getStockGeneral() {
        return stockGeneral;
    }

    public void setStockGeneral(int stockGeneral) {
        this.stockGeneral = stockGeneral;
    }

    // Método personalizado para actualizar stockSucursalUno
    public void setStockSucursalUno(int stockSucursalUno) {
        this.stockSucursalUno = stockSucursalUno;
        actualizarStockGeneral();
    }

    // Método personalizado para actualizar stockSucursalDos
    public void setStockSucursalDos(int stockSucursalDos) {
        this.stockSucursalDos = stockSucursalDos;
        actualizarStockGeneral();
    }

    // Método privado para actualizar stockGeneral
    private void actualizarStockGeneral() {
        this.stockGeneral = stockSucursalUno + stockSucursalDos;
    }

    @Override
    public String toString() {
        return id + " - " + descripcion + " - " + precio;
    }

}
