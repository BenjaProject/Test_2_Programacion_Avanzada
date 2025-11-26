package dao;

import dto.CursoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author benja
 */
public class CursoDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    /**
     * Lista todos los cursos
     */
    public List<CursoDTO> listarCursos() throws InstantiationException, IllegalAccessException, Exception {
        
        List<CursoDTO> lista = new ArrayList<>();
        
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        
        String sql = "SELECT * FROM curso ORDER BY nivel, nombre_curso";
        
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            CursoDTO c = new CursoDTO();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setNombreCurso(rs.getString("nombre_curso"));
            c.setNivel(rs.getInt("nivel"));
            c.setAnioAcademico(rs.getInt("anio_academico"));
            
            lista.add(c);
        }
        
        return lista;
    }
    
    public Curso read(int id) throws Exception {
        Curso c = null;
        String sql = "SELECT * FROM curso WHERE id_curso = "+id;
        
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        stmt = conn.prepareStatement(sql);
        
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            c = new Curso();
            c.setIdCurso(rs.getInt("id_curso"));
            c.setNombreCurso(rs.getString("nombre_curso"));
            c.setNivel(rs.getInt("nivel"));
            c.setAnioAcademico(rs.getInt("anio_academico"));
        }
        return c;
    }
    
    
    /**
     * Crea un curso en la base de datos
     */
    public boolean crearCurso(Curso curso) throws InstantiationException, IllegalAccessException, Exception {
        
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        
        String sql = "INSERT INTO curso (nombre_curso, nivel, anio_academico) VALUES (?, ?, ?)";
        
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, curso.getNombreCurso());
        stmt.setInt(2, curso.getNivel());
        stmt.setInt(3, curso.getAnioAcademico());
        
        int rows = stmt.executeUpdate();
        return rows > 0;
    }
    
    
    /**
     * Elimina un curso según su id
     */
    public boolean eliminarCurso(int idCurso) throws InstantiationException, IllegalAccessException, Exception {
        
        conn = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion();
        
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCurso);
        
        int rows = stmt.executeUpdate();
        return rows > 0;
    }

}