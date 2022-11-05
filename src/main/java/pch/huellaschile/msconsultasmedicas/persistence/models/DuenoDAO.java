package pch.huellaschile.msconsultasmedicas.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "dueno")
public class DuenoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dueno")
    private int idDueno;

    private String nombre;

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
