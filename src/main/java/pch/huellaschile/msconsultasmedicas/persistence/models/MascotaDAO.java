package pch.huellaschile.msconsultasmedicas.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "mascota")
public class MascotaDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private int idMascota;

    private String nombre;

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
