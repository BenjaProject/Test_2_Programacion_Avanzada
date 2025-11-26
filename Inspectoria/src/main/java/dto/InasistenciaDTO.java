/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class InasistenciaDTO {
    private int idInasistencia;
    private String nombreAlumno;
    private LocalDate fecha;
    private boolean justificada;

    public InasistenciaDTO() {
    }

    public InasistenciaDTO(int idInasistencia, String nombreAlumno, LocalDate fecha, boolean justificada) {
        this.idInasistencia = idInasistencia;
        this.nombreAlumno = nombreAlumno;
        this.fecha = fecha;
        this.justificada = justificada;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) {
        this.idInasistencia = idInasistencia;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isJustificada() {
        return justificada;
    }

    public void setJustificada(boolean justificada) {
        this.justificada = justificada;
    }

    @Override
    public String toString() {
        return "InasistenciaDTO{" + "idInasistencia=" + idInasistencia + ", nombreAlumno=" + nombreAlumno + ", fecha=" + fecha + ", justificada=" + justificada + '}';
    }
    
    

    
}
