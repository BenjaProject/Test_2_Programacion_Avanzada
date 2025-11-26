/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.AlumnoDAO;
import java.util.List;

/**
 *
 * @author benja
 */
public class Alumno extends Persona {
    private int id;
    private int idCurso;
    private int cantidadAtrasos;
    private int cantidadInasistencias;

    public Alumno() {
    }

    public Alumno(int rut, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion) {
        super(rut, nombre, apellidoPaterno, apellidoMaterno, direccion);
    }

    public Alumno(int id, int idCurso, int cantidadAtrasos, int cantidadInasistencias, int rut, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion) {
        super(rut, nombre, apellidoPaterno, apellidoMaterno, direccion);
        this.id = id;
        this.idCurso = idCurso;
        this.cantidadAtrasos = cantidadAtrasos;
        this.cantidadInasistencias = cantidadInasistencias;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) throws ExcepcionPersonalizada {
        if(id==0){
            throw new ExcepcionPersonalizada("id no puede ser nulo");
        }
        else{
            this.id = id;
        }
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) throws ExcepcionPersonalizada {
        if(idCurso==0){
            throw new ExcepcionPersonalizada("id del curso no puede ser 0");
        }
        else{
            this.idCurso = idCurso;
        }
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
    
    public List listarAlumnos() throws InstantiationException, IllegalAccessException, Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.read();
    }
    public List listarAlumnosPorCurso(int idCurso) throws Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.readPorCurso(idCurso);
    }
    
    public Alumno obtenerAlumnoPorId(int id) throws InstantiationException, IllegalAccessException, Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.read(id);
    }
    
    public int agregarAlumno() throws InstantiationException, IllegalAccessException, Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.create(this);
    }
    
    public int actualizarAlumno() throws InstantiationException, IllegalAccessException, Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.update(this);
    }
    
    public int eliminarAlumno() throws InstantiationException, IllegalAccessException, Exception{
        AlumnoDAO a = new AlumnoDAO();
        return a.delete(this.getId());
    }
    
    
    
    
}
