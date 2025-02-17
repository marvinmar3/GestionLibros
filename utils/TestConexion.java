
package utils;
import java.sql.Connection;
/**
 *
 * @author marvin
 */
public class TestConexion {
    public static void main(String[] args) {
        Connection conexion = Conexion.getConnection();
        if(conexion != null)
        {
            System.out.println("✅ Conexión exitosa a la base de datos.");
        }
        else
        {
            System.out.println("👎🏼 No se pudo conectar.");
        }
    }
}
