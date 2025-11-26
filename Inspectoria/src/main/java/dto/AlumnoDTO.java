/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author benja
 */
public class AlumnoDTO {
    private int id;
    private String nombreAlumno;
    private String apellidoPAlumno;
    private String apellidoMalumno;
    private int rut;
    private String curso;
    private int idCurso;
    private int cantidadAtrasos;
    private int cantidadInasistencias;

    public AlumnoDTO() {
    }

    public AlumnoDTO(int id, String nombreAlumno, String apellidoPAlumno, String apellidoMalumno, int rut, String curso,int idCurso, int cantidadAtrasos, int cantidadInasistencias) {
        this.id = id;
        this.nombreAlumno = nombreAlumno;
        this.apellidoPAlumno = apellidoPAlumno;
        this.apellidoMalumno = apellidoMalumno;
        this.rut = rut;
        this.curso = curso;
        this.idCurso=idCurso;
        this.cantidadAtrasos = cantidadAtrasos;
        this.cantidadInasistencias = cantidadInasistencias;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    

    public int getCantidadAtrasos() {
        return cantidadAtrasos;
    }

    public void setCantidadAtrasos(int cantidadAtrasos) {
        this.cantidadAtrasos = cantidadAtrasos;
    }

    public int getCantidadInasistencias() {
        return cantidadInasistencias;
    }

    public void setCantidadInasistencias(int cantidadInasistencias) {
        this.cantidadInasistencias = cantidadInasistencias;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoPAlumno() {
        return apellidoPAlumno;
    }

    public void setApellidoPAlumno(String apellidoPAlumno) {
        this.apellidoPAlumno = apellidoPAlumno;
    }

    public String getApellidoMAlumno() {
        return apellidoMalumno;
    }

    public void setApellidoMAlumno(String apellidoMalumno) {
        this.apellidoMalumno = apellidoMalumno;
    }

    @Override
    public String toString() {
        return "AlumnoDTO{" + "id=" + id + ", rut=" + rut + ", curso=" + curso + ", cantidadAtrasos=" + cantidadAtrasos + ", cantidadInasistencias=" + cantidadInasistencias + '}';
    }
    
    
    
    
}
