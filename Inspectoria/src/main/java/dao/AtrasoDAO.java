/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AtrasoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Atraso;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author usuario
 */
public class AtrasoDAO implements iCrud<Atraso, AtrasoDTO> {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public ArrayList<AtrasoDTO> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<AtrasoDTO> list = new ArrayList<>();
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "SELECT \n"
                + "    a.id_atraso,\n"
                + "    CONCAT(p.nombre, ' ', p.apellido, ' (', p.rut, ')') AS info_alumno,\n"
                + "    a.fecha, \n"
                + "    a.hora, \n"
                + "    a.razon\n"
                + "FROM Atraso a\n"
                + "INNER JOIN Alumno al ON a.id_alumno = al.id\n"
                + "INNER JOIN Persona p ON al.rut = p.rut\n"
                + "ORDER BY a.fecha DESC;";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
            AtrasoDTO aDTO = new AtrasoDTO();
            aDTO.setIdAtraso(rs.getInt("id_atraso"));
            aDTO.setNombreAlumno(rs.getString("info_alumno"));
            aDTO.setFecha(rs.getDate("fecha").toLocalDate());
            aDTO.setHora(rs.getTime("hora").toLocalTime());
            aDTO.setRazon(rs.getString("razon"));
            list.add(aDTO);
        }
        return list;

    }
    
   
    public ArrayList<AtrasoDTO> readPorCurso(int idCurso) throws Exception {
        ArrayList<AtrasoDTO> list = new ArrayList<>();
  
        String sql = "SELECT "
                   + "    at.id_atraso, "
                   + "    CONCAT(p.nombre, ' ', p.apellido_paterno, ' ', p.apellido_materno) AS nombre_completo, "
                   + "    at.fecha, "
                   + "    at.hora, "
                   + "    at.razon "
                   + "FROM Atraso at "
                   + "INNER JOIN Alumno al ON at.id_alumno = al.id "
                   + "INNER JOIN Persona p ON al.rut = p.rut "
                   + "WHERE al.id_curso = ? " 
                   + "ORDER BY at.fecha DESC"; 
                   
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCurso);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            AtrasoDTO dto = new AtrasoDTO();
            dto.setIdAtraso(rs.getInt("id_atraso"));
            
            // Ahora recuperamos el nombre completo concatenado
            dto.setNombreAlumno(rs.getString("nombre_completo"));
            
            dto.setFecha(rs.getDate("fecha").toLocalDate());
            dto.setHora(rs.getTime("hora").toLocalTime());
            dto.setRazon(rs.getString("razon"));
            
            list.add(dto);
        }
        return list;
    }

    @Override
    public Atraso read(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        Atraso a = null;
        conn  = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "SELECT \n"
                + "    a.id_atraso,\n"
                + "    a.id_alumno,\n"
                + "    a.fecha, \n"
                + "    a.hora, \n"
                + "    a.razon\n"
                + "FROM Atraso a\n"
                +"where id_atraso="+id;
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        if(rs.next()){
            a = new Atraso();
            a.setIdAtraso(rs.getInt("id_atraso"));
            a.setIdAlumno(rs.getInt("id_alumno"));
            a.setFecha(rs.getDate("fecha").toLocalDate());
            a.setHora(rs.getTime("hora").toLocalTime());
            a.setRazon(rs.getString("razon"));
            
        }
        return a;
        
    }

    @Override
    public int create(Atraso t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "insert into Atraso(id_alumno,fecha,hora,razon) values (" + t.getIdAlumno() + ",'" + t.getFecha() + "','" + t.getHora() + "','" + t.getRazon() + "')";
        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    @Override
    public int update(Atraso t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "update Atraso set fecha = '" + t.getFecha() + "',hora ='" + t.getHora() + "',razon ='" + t.getRazon() + "' where id_atraso = " + t.getIdAtraso();        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "delete from Atraso where id_atraso=" + id;
        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();

    }

}
