package model.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBd {

    private static Connection connection;

    public static Properties cargarPropiedades() throws IOException {
        Properties propiedades = new Properties();
        try (InputStream input = ConexionBd.class.getResourceAsStream("ConfiguracionBD.properties")) {
            if (input == null) {
                throw new IOException("Archivo 'database.properties' no encontrado");
            }
            propiedades.load(input);
        }
        return propiedades;
    }

    public static Connection obtenerConexion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties propiedades = cargarPropiedades();
                String url = propiedades.getProperty("db.url");
                String user = propiedades.getProperty("db.user");
                String password = propiedades.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException e) {
                throw new SQLException("Error al cargar las configuraciones de la base de datos", e);
            }
        }
        return connection;
    }

    public static void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
