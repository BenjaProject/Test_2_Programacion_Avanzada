/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.InasistenciaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Inasistencia;
import servicios.FactoriaServiciosImpl;


/**
 *
 * @author usuario
 */
public class InasistenciaDAO implements iCrud<Inasistencia, InasistenciaDTO>{
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public ArrayList<InasistenciaDTO> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<InasistenciaDTO> list = new ArrayList<>();
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "Select i.*, p.nombre from inasistencia as i join alumno as a on i.id_alumno=a.id join Persona as p on a.rut=p.rut";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while(rs.next()){
            InasistenciaDTO iDTO = new InasistenciaDTO();
            iDTO.setIdInasistencia(rs.getInt("id_inasistencia"));
            iDTO.setNombreAlumno(rs.getString("nombre"));
            iDTO.setFecha(rs.getDate("fecha").toLocalDate());
            iDTO.setJustificada(rs.getBoolean("justificada"));
            list.add(iDTO);
        }
        return list;
    }

    @Override
    public Inasistencia read(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        Inasistencia i = null;
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "Select i.* from Inasistencia as i where i.id_inasistencia="+id;
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        if(rs.next()){
            i = new Inasistencia();
            i.setIdInasistencia(rs.getInt("id_inasistencia"));
            i.setIdAlumno(rs.getInt("id_alumno"));
            i.setFecha(rs.getDate("fecha").toLocalDate());
            i.setJustificada(rs.getBoolean("justificada"));
            
        }
        return i;
        
    }
    public ArrayList<InasistenciaDTO> readPorCurso(int idCurso) throws Exception {
        ArrayList<InasistenciaDTO> list = new ArrayList<>();
  
        String sql = "SELECT "
                   + "    i.id_inasistencia, "
                   + "    CONCAT(p.nombre, ' ', p.apellido_paterno, ' ', p.apellido_materno) AS nombre_completo, "
                   + "    i.fecha, "
                   + "    i.justificada "
                 
                   + "FROM Inasistencia i "
                   + "INNER JOIN Alumno al ON i.id_alumno = al.id "
                   + "INNER JOIN Persona p ON al.rut = p.rut "
                   + "WHERE al.id_curso = ? " 
                   + "ORDER BY i.fecha DESC"; 
                   
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCurso);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            InasistenciaDTO dto = new InasistenciaDTO();
            dto.setIdInasistencia(rs.getInt("id_inasistencia"));
            
            
            dto.setNombreAlumno(rs.getString("nombre_completo"));
            
            dto.setFecha(rs.getDate("fecha").toLocalDate());
            dto.setJustificada(rs.getBoolean("justificada"));
            
            
            list.add(dto);
        }
        return list;
    }
    public ArrayList<InasistenciaDTO> filtrar(int idCurso, int idAlumno, String fechaInicio, String fechaFin) throws Exception {
    ArrayList<InasistenciaDTO> list = new ArrayList<>();
    String sql = "SELECT i.id_inasistencia, "
               + "CONCAT(p.nombre, ' ', p.apellido_paterno, ' ', p.apellido_materno) AS nombre_completo, "
               + "i.fecha, i.justificada "
               + "FROM Inasistencia i "
               + "INNER JOIN Alumno al ON i.id_alumno = al.id "
               + "INNER JOIN Persona p ON al.rut = p.rut "
               + "WHERE al.id_curso = " + idCurso;

    
    if (idAlumno > 0) {
        sql += " AND i.id_alumno = " + idAlumno;
    }

   
    if (fechaInicio != null && !fechaInicio.isEmpty() && fechaFin != null && !fechaFin.isEmpty()) {
        sql += " AND i.fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
    }
    sql += " ORDER BY i.fecha DESC";

    conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
    stmt = conn.prepareStatement(sql);
    rs = stmt.executeQuery();

    while (rs.next()) {
        InasistenciaDTO dto = new InasistenciaDTO();
        dto.setIdInasistencia(rs.getInt("id_inasistencia"));
        dto.setNombreAlumno(rs.getString("nombre_completo"));
        dto.setFecha(rs.getDate("fecha").toLocalDate());
        dto.setJustificada(rs.getBoolean("justificada"));
        list.add(dto);
    }
    return list;
}

    @Override
    public int create(Inasistencia t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn =  FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "insert into Inasistencia(id_alumno,fecha, justificada) values ("+t.getIdAlumno()+",'"+t.getFecha()+"',"+t.isJustificada()+")";
        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
        
    }

    @Override
    public int update(Inasistencia t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql = "update Inasistencia set fecha ='"+t.getFecha()+"', justificada ="+t.isJustificada()+" where id_inasistencia = "+t.getIdInasistencia();
        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        String sql  = "delete from Inasistencia where id_inasistencia="+id;
        stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
    }
    
}
