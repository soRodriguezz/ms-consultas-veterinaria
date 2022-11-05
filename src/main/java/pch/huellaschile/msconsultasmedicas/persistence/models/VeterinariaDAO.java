package pch.huellaschile.msconsultasmedicas.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "veterinaria")
public class VeterinariaDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veterinaria")
    private int idVeterinaria;

    private String nombre;

    public int getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(int idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
