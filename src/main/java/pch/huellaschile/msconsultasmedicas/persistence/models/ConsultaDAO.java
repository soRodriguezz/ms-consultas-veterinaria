package pch.huellaschile.msconsultasmedicas.persistence.models;


import javax.persistence.*;

@Entity
@Table(name = "consulta")
public class ConsultaDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private int idConsulta;

    @Column(name = "id_mascota")
    private int idMascota;

    @OneToOne(targetEntity = MascotaDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fk_id_mascota")
    private MascotaDAO mascota;

    @Column(name = "id_dueno")
    private int idDueno;

    @OneToOne(targetEntity = DuenoDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fk_id_dueno")
    private DuenoDAO dueno;

    @Column(name = "id_veterinaria")
    private int idVeterinaria;

    @OneToOne(targetEntity = VeterinariaDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fk_id_veterinaria")
    private VeterinariaDAO veterinaria;

    private int valor;

    @Column(name = "forma_pago")
    private String formaPago;

    @Column(name = "descripcion_proceso")
    private String descripcionProceso;

    @Column(name = "estado_consulta")
    private String estadoConsulta;

    @Column(name = "esta_en_tratamiento")
    private boolean estaEnTratamiento;

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public MascotaDAO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDAO mascota) {
        this.mascota = mascota;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public DuenoDAO getDueno() {
        return dueno;
    }

    public void setDueno(DuenoDAO dueno) {
        this.dueno = dueno;
    }

    public int getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(int idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public VeterinariaDAO getVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(VeterinariaDAO veterinaria) {
        this.veterinaria = veterinaria;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDescripcionProceso() {
        return descripcionProceso;
    }

    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }

    public String getEstadoConsulta() {
        return estadoConsulta;
    }

    public void setEstadoConsulta(String estadoConsulta) {
        this.estadoConsulta = estadoConsulta;
    }

    public boolean isEstaEnTratamiento() {
        return estaEnTratamiento;
    }

    public void setEstaEnTratamiento(boolean estaEnTratamiento) {
        this.estaEnTratamiento = estaEnTratamiento;
    }
}
