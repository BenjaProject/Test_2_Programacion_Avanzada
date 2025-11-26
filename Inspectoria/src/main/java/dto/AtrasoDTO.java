/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author usuario
 */
public class AtrasoDTO {
   private int idAtraso;
   private String nombreAlumno;
   private LocalDate fecha;
   private LocalTime hora;
   private String razon;

    public AtrasoDTO() {
    }

    public AtrasoDTO(int idAtraso, String nombreAlumno, LocalDate fecha, LocalTime hora, String razon) {
        this.idAtraso = idAtraso;
        this.nombreAlumno = nombreAlumno;
        this.fecha = fecha;
        this.hora = hora;
        this.razon = razon;
    }

    public int getIdAtraso() {
        return idAtraso;
    }

    public void setIdAtraso(int idAtraso) {
        this.idAtraso = idAtraso;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
   
   
}
