package cursojava.entity;

import javax.persistence.*;

@Entity
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;



    @Column
    private String nombre;
    @Column
    private String nutrientes;
    @Column
    private Integer porcentajeDiario;
    @Column
    private Integer calorias;
    @Column
    private String cantidad;

    @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(String nutrientes) {
        this.nutrientes = nutrientes;
    }

    public Integer getPorcentajeDiario() {
        return porcentajeDiario;
    }

    public void setPorcentajeDiario(Integer porcentajeDiario) {
        this.porcentajeDiario = porcentajeDiario;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
