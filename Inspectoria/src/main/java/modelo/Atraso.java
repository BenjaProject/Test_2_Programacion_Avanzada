/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.AtrasoDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Atraso {
    private int idAtraso;
    private int idAlumno;
    private LocalDate fecha;
    private LocalTime hora;
    private String razon;

    public Atraso() {
    }

    public Atraso(int idAtraso, int idAlumno, LocalDate fecha, LocalTime hora, String razon) {
        this.idAtraso = idAtraso;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.hora = hora;
        this.razon = razon;
    }

    public int getIdAtraso() {
        return idAtraso;
    }

    public void setIdAtraso(int idAtraso) throws ExcepcionPersonalizada {
        if(idAtraso==0){
            throw new ExcepcionPersonalizada(" id atraso no puede ser nulo");
        }
        else{
            this.idAtraso = idAtraso;
        }
        
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) throws ExcepcionPersonalizada {
        if(idAlumno==0){
            throw new ExcepcionPersonalizada("id del alumno no puede ser nulo");
        }
        this.idAlumno = idAlumno;
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

    public void setRazon(String razon) throws ExcepcionPersonalizada {
        if(razon==null||razon.isEmpty()||razon.isBlank()){
            throw new ExcepcionPersonalizada("razon no puede ser nula");
        }
        this.razon = razon;
    }
    public List listarAtrasos() throws InstantiationException, IllegalAccessException, Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.read();
    }
    public List listarAtrasosPorCurso(int idCurso) throws Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.readPorCurso(idCurso);
    }
    public Atraso obtenerAtrasoPorId(int id_atraso) throws InstantiationException, IllegalAccessException, Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.read(id_atraso);
    }
    public int agregarAtraso() throws InstantiationException, IllegalAccessException, Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.create(this);
    }
    public int editarAtraso() throws InstantiationException, IllegalAccessException, Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.update(this);
    }
    public int eliminarAtraso() throws InstantiationException, IllegalAccessException, Exception{
        AtrasoDAO a = new AtrasoDAO();
        return a.delete(this.getIdAtraso());
    }
    
}
