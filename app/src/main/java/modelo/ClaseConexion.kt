package modelo

import oracle.ons.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): java.sql.Connection? {
        try {
            val url = "jdbc:oracle:thin:@192.168.1.20:1521:xe"
            val usuario = "THOMAS_DEVELOPER"
            val contrasena = "PjLPIGD3"

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        }
        catch (e: Exception){
            println("Error: $e")
            return null
        }
    }
}