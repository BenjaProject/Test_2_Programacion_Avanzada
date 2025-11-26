/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AlumnoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author usuario
 */
public class AlumnoDAO implements iCrud<Alumno, AlumnoDTO>{
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public ArrayList<AlumnoDTO> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<AlumnoDTO> list = new ArrayList<>();
        String sql = "SELECT a.id, a.rut, p.nombre, p.apellido_paterno,p.apellido_materno, c.nombre_curso, a.cantidad_atrasos, a.cantidad_inasistencias "
                   + "FROM Alumno a "
                   + "INNER JOIN Persona p ON a.rut = p.rut "
                   + "INNER JOIN Curso c ON a.id_curso = c.id_curso";
        con = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            AlumnoDTO alumDTO = new AlumnoDTO();
            alumDTO.setId(rs.getInt("id"));
            alumDTO.setNombreAlumno(rs.getString("nombre"));
            alumDTO.setApellidoPAlumno(rs.getString("apellido_paterno"));
            alumDTO.setApellidoMAlumno(rs.getString("apellido_materno"));
            alumDTO.setRut(rs.getInt("rut"));
            alumDTO.setCurso(rs.getString("nombre_curso"));
            alumDTO.setCantidadAtrasos(rs.getInt("cantidad_atrasos"));
            alumDTO.setCantidadInasistencias(rs.getInt("cantidad_inasistencias"));
            list.add(alumDTO);
        }
        return list;
    }
    
    
    public ArrayList<AlumnoDTO> readPorCurso(int idCurso) throws Exception {
        ArrayList<AlumnoDTO> list = new ArrayList<>();
        
        // SQL filtrado por curso
        String sql = "SELECT a.id, a.rut, p.nombre, p.apellido_paterno, p.apellido_materno, "
                   + "c.nombre_curso, a.id_curso, a.cantidad_atrasos, a.cantidad_inasistencias "
                   + "FROM Alumno a "
                   + "INNER JOIN Persona p ON a.rut = p.rut "
                   + "INNER JOIN Curso c ON a.id_curso = c.id_curso "
                   + "WHERE a.id_curso = ? " 
                   + "ORDER BY p.apellido_paterno ASC"; 
                   
        con = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCurso);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            AlumnoDTO dto = new AlumnoDTO();
            dto.setId(rs.getInt("id"));
            dto.setRut(rs.getInt("rut"));
            dto.setNombreAlumno(rs.getString("nombre"));
            dto.setApellidoPAlumno(rs.getString("apellido_paterno"));
            dto.setApellidoMAlumno(rs.getString("apellido_materno"));
            
            dto.setCurso(rs.getString("nombre_curso")); 
            dto.setIdCurso(rs.getInt("id_curso")); 
            
            dto.setCantidadAtrasos(rs.getInt("cantidad_atrasos"));
            dto.setCantidadInasistencias(rs.getInt("cantidad_inasistencias"));
            
            list.add(dto);
        }
        return list;
    }

    @Override
    public Alumno read(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        Alumno a = null;
        String sql =  "select a.id, a.rut, a.id_curso, a.cantidad_atrasos, a.cantidad_inasistencias, p.nombre, p.apellido_paterno,p.apellido_materno, p.direccion"
                    + " from Alumno as a join Persona as p on a.rut=p.rut where id = " + id;
        con = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next()) {
            a = new Alumno();
            a.setId(rs.getInt("id"));
            a.setRut(rs.getInt("rut"));
            a.setIdCurso(rs.getInt("id_curso"));
            a.setCantidadAtrasos(rs.getInt("cantidad_atrasos"));
            a.setCantidadInasistencias(rs.getInt("cantidad_inasistencias"));
            a.setNombre(rs.getString("nombre"));
            a.setApellidoPaterno(rs.getString("apellido_paterno"));
            a.setApellidoMaterno(rs.getString("apellido_materno"));
            a.setDireccion(rs.getString("direccion"));
        }
        return a;
    }

    @Override
    public int create(Alumno a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sqlPersona = "insert into Persona (rut, nombre, apellido_paterno, apellido_materno) values (" 
                          + a.getRut() + ", '" + a.getNombre() + "', '" + a.getApellidoPaterno()+ "','"+a.getApellidoMaterno()+"')";
        String sql = "insert into Alumno (rut, id_curso, cantidad_atrasos, cantidad_inasistencias) values ("+ a.getRut() +","+a.getIdCurso()+","+ a.getCantidadAtrasos() +","+ a.getCantidadInasistencias() +")";
        
        con=FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps=con.prepareStatement(sqlPersona);
        ps.executeUpdate();
        ps=con.prepareStatement(sql);
        return ps.executeUpdate();
    }

    @Override
    public int update(Alumno a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "update Alumno set rut = "+ a.getRut() +", id_curso = "+ a.getIdCurso() +", cantidad_atrasos = "+ a.getCantidadAtrasos() +", cantidad_inasistencias = "+ a.getCantidadInasistencias() +" where id = "+ a.getId();
        con=FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps=con.prepareStatement(sql);
        return ps.executeUpdate();
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql="delete from Alumno where id = " + id;
        con=FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        ps=con.prepareStatement(sql);
        return ps.executeUpdate();
    }

    
}
