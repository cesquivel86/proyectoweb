package cursojava.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alimento")
@SQLDelete(sql = "UPDATE alimento SET deleted = '1' WHERE id = ?")
@Where(clause = "deleted <> '1'")
public class Alimento implements Serializable {
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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false, columnDefinition="tinyint(1) default 0")
    char deleted='0';

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

    public char getDeleted() {
        return deleted;
    }

    public void setDeleted(char deleted) {
        this.deleted = deleted;
    }
}
