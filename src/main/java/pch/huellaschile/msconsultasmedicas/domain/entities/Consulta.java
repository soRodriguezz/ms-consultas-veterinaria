package pch.huellaschile.msconsultasmedicas.domain.entities;

public class Consulta {
    private int idConsulta;
    private int idMascota;
    private Mascota mascota;
    private int idDueno;
    private Dueno dueno;
    private int idVeterinaria;
    private Veterinaria veterinaria;
    private int valor;
    private String formaPago;
    private String descripcionProceso;
    private String estadoConsulta;
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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public int getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(int idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public Veterinaria getVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(Veterinaria veterinaria) {
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
