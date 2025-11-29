/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.InasistenciaDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Inasistencia {
    private int idInasistencia;
    private int idAlumno;
    private LocalDate fecha;
    private boolean justificada;

    public Inasistencia() {
    }

    public Inasistencia(int idInasistencia, int idAlumno, LocalDate fecha, boolean justificada) {
        this.idInasistencia = idInasistencia;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.justificada = justificada;
    }

    public int getIdInasistencia() {
        return idInasistencia;
    }

    public void setIdInasistencia(int idInasistencia) throws ExcepcionPersonalizada {
        if(idInasistencia==0){
            throw new ExcepcionPersonalizada("id de inasistencia no puede ser nulo");
        }
        else{
            this.idInasistencia = idInasistencia;
        }
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) throws ExcepcionPersonalizada {
        if(idAlumno==0){
            throw new ExcepcionPersonalizada("id del alumno no puede ser nulo");
        }
        else{
            this.idAlumno = idAlumno;
        }
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
    
    public List listarInasistenciasPorCurso(int idAlumno) throws Exception{
        InasistenciaDAO i = new InasistenciaDAO();
        return i.readPorCurso(idAlumno);
    }
    public Inasistencia obtenerInasistenciaPorId(int id) throws InstantiationException, IllegalAccessException, Exception{
        InasistenciaDAO i = new InasistenciaDAO();
        return i.read(id);
    }
    
    public List filtrarInasistencias(int idCurso, int idAlumno, String fechaInicio, String fechaFin) throws Exception {
    InasistenciaDAO dao = new InasistenciaDAO();
    return dao.filtrar(idCurso, idAlumno, fechaInicio, fechaFin);
    }
    
    public int crearInasistencia() throws InstantiationException, IllegalAccessException, Exception{
        InasistenciaDAO i = new InasistenciaDAO();
        return i.create(this);
    }
    
    public int editarInasistencia() throws InstantiationException, IllegalAccessException, Exception{
        InasistenciaDAO i = new InasistenciaDAO();
        return i.update(this);
    }
    public int eliminarInasistencia() throws InstantiationException, IllegalAccessException, Exception{
        InasistenciaDAO i = new InasistenciaDAO();
        return i.delete(this.getIdInasistencia());
    }
    
    
}
