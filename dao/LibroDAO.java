
package dao;

import modelo.Libro;
import utils.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marvin
 */
public class LibroDAO {
    
    //agregar un libro a la bd
    public boolean agregarLibro(Libro libro, String nombreAutor, String nombreGenero, String nombreEditorial)
    {
        String buscarAutor= "select id_autor from Autor where nombre_autor = ?";
        String insertarAutor = "insert into Autor(nombre_autor) values (?)";
        
        String buscarGenero= "select id_genero from Genero where nombre_genero = ?";
        String insertarGenero = "insert into Genero(nombre_genero) values(?)";
        
        String buscarEditorial="select id_editorial from Editorial where nombre_edi = ?";
        String insertarEditorial = "insert into Editorial(nombre_edi) values (?)";
        
        String insertarLibro= "insert into Libro(isbn, nombre_lib, r_autor, r_genero,r_editorial, anio_publicacion) values (?,?,?,?,?,?)";
        try(Connection conn= Conexion.getConnection())
        {
            conn.setAutoCommit(false);
            
            int idAutor=obtenerId(conn, buscarAutor, insertarAutor, nombreAutor);
            int idGenero= obtenerId(conn, buscarGenero, insertarGenero, nombreGenero);
            int idEditorial = obtenerId(conn, buscarEditorial, insertarEditorial, nombreEditorial);
            
            try(PreparedStatement stmtLibro= conn.prepareStatement(insertarLibro))
            {
                stmtLibro.setString(1, libro.getIsbn());
                stmtLibro.setString(2, libro.getTitulo());
                stmtLibro.setInt(3, idAutor);
                stmtLibro.setInt(4, idGenero);
                stmtLibro.setInt(5, idEditorial);
                stmtLibro.setString(6, libro.getAnioPub());
                
                int filasAfectadas = stmtLibro.executeUpdate();
                conn.commit();
                
                return filasAfectadas >0;
            }catch(SQLException e)
            {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    private int obtenerId(Connection conn, String buscarSQL, String insertarSQL, String valor) throws SQLException
    {
        try(PreparedStatement buscarStmt= conn.prepareStatement(buscarSQL))
        {
            buscarStmt.setString(1, valor);
            try(ResultSet rs = buscarStmt.executeQuery())
            {
                if(rs.next())
                {
                    return rs.getInt(1); // retorna el id si ya existe
                }
            }
        }
        // si no existe, se inserta y se obtiene el id
        try(PreparedStatement insertarStmt = conn.prepareStatement(insertarSQL, Statement.RETURN_GENERATED_KEYS))
        {
            insertarStmt.setString(1, valor);
            insertarStmt.executeUpdate();
            
            try(ResultSet rs = insertarStmt.getGeneratedKeys())
            {
                if(rs.next())
                {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se pudo obtener el id de "+valor);
    }
    
    //obtener todos los libros
    public List<Libro> obtenerLibros()
    {
        List<Libro>libros = new ArrayList<>();
        String consulta = """
                        SELECT Libro.isbn, Libro.nombre_lib, Autor.nombre_autor, Genero.nombre_genero, Editorial.nombre_edi, Libro.anio_publicacion
                        FROM Libro
                        JOIN Autor 
                        ON Libro.r_autor = Autor.id_autor
                        JOIN Genero 
                        ON Libro.r_genero = Genero.id_genero
                        JOIN Editorial 
                        ON Libro.r_editorial = Editorial.id_editorial
                          """;
        
        try(Connection conn= Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(consulta))
        {
            while(rs.next())
            {
                Libro libro = new Libro(
                        rs.getString("isbn"),
                        rs.getString("nombre_lib"),
                        rs.getString("nombre_autor"),
                        rs.getString("nombre_genero"),
                        rs.getString("nombre_edi"),
                        rs.getString("anio_publicacion")
                );
                 
                libros.add(libro);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return libros;
    }
    
    //eliminar libro por id
    public boolean eliminarLibro(String isbn)
    {
        String consulta = "delete from Libro where isbn = ?";
        
        try(Connection conn= Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(consulta))
        {
            stmt.setString(1, isbn);
            return stmt.executeUpdate() > 0;
            //System.out.println("😍Libro eliminado correctamente");
            
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
