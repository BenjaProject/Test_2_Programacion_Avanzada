/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author usuario
 */
public class PersonaDTO {
    private int rut;
    private String nombre;
    private String apellido;
    private String dirección;

    public PersonaDTO() {
    }

    public PersonaDTO(int rut, String nombre, String apellido, String dirección) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dirección = dirección;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" + "rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", direcci\u00f3n=" + dirección + '}';
    }
    
    
}

    

