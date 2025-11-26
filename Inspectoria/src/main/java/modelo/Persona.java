/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author benja
 */
public abstract class Persona {
    private int rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;

    public Persona() {
    }

    public Persona(int rut, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
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

    public void setNombre(String nombre) throws ExcepcionPersonalizada {
        if(nombre==null||nombre.isEmpty()||nombre.isBlank()){
            throw new ExcepcionPersonalizada("nombre no puede ser nulo");
        }
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellido) throws ExcepcionPersonalizada {
        if(apellido==null||apellido.isEmpty()||apellido.isBlank()){
            throw new ExcepcionPersonalizada("Apellido no puede ser nulo");
        }
        else{
            this.apellidoPaterno = apellido;
        }
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) throws ExcepcionPersonalizada {
        if(apellidoMaterno==null||apellidoMaterno.isEmpty()||apellidoMaterno.isBlank()){
            throw new ExcepcionPersonalizada("Apellido materno no puede ser nulo");
        }
        this.apellidoMaterno = apellidoMaterno;
    }
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws ExcepcionPersonalizada {
        if(direccion==null||direccion.isEmpty()||direccion.isBlank()){
            throw new ExcepcionPersonalizada("direccion no puede ser nula");
        }
        else{
            this.direccion = direccion;
        }
    }

    
}
