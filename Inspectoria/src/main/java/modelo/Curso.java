/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.CursoDAO;
import java.util.List;

/**
 *
 * @author benja
 */
public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int nivel;
    private int anioAcademico;

    public Curso() {
    }

    public Curso(int idCurso, String nombreCurso, int nivel, int anioAcademico) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.nivel = nivel;
        this.anioAcademico = anioAcademico;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) throws ExcepcionPersonalizada {
        if(idCurso==0){
            throw new ExcepcionPersonalizada("id curso no puede ser nulo");
        }
        else{
            this.idCurso = idCurso;
        }
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) throws ExcepcionPersonalizada {
        if(nombreCurso==null||nombreCurso.isEmpty()||nombreCurso.isBlank()){
            throw new ExcepcionPersonalizada("nombre del curso no puede ser nulo");
        }
        else{
            this.nombreCurso = nombreCurso;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) throws ExcepcionPersonalizada {
        if(nivel==0){
            throw new ExcepcionPersonalizada("nivel no puede ser nulo");
        }
        else{
            this.nivel = nivel;
        }
    }

    public int getAnioAcademico() {
        return anioAcademico;
    }

    public void setAnioAcademico(int anioAcademico) throws ExcepcionPersonalizada {
        if(anioAcademico==0){
            throw new ExcepcionPersonalizada("año academico no puede ser nulo");
        }
        else{
            this.anioAcademico = anioAcademico;
        }
    }

    @Override
    public String toString() {
        return "Curso{" + "idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", nivel=" + nivel + ", anioAcademico=" + anioAcademico + '}';
    }
    
    public List listarCursos() throws IllegalAccessException, Exception{
        CursoDAO c = new CursoDAO();
        return c.listarCursos();
    }
    public Curso obtenerCursoPorId(int id) throws Exception{
        CursoDAO c = new CursoDAO();
        return c.read(id);
    }
    public boolean agregarCurso() throws IllegalAccessException, Exception{
        CursoDAO c = new CursoDAO();
        return c.crearCurso(this);
    }
    public boolean eliminarCurso() throws IllegalAccessException, Exception{
        CursoDAO c = new CursoDAO();
        return c.eliminarCurso(this.getIdCurso());
    }
}
