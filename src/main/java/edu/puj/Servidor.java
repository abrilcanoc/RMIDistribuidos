package edu.puj;

import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.NoSuchFileException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


/**
 * Clase Main para el programa Servidor
 */
public class Servidor {
    private static final int PORT = 34053;

    public static void main(String[] args) {
        try {
            // Crear la base de datos y el servicio de estudiantes
            var database = new EstudiantesDatabase("estudiantes.json");
            var servicio = new ServicioEstudianteImpl(database);

            // Mostrar la URI para conexión con el servidor
            String connectionPath = "rmi://localhost:" + PORT + "/ServicioEstudiante";
            System.out.println("Dirección del Servidor: " + Inet4Address.getLocalHost().getHostAddress() + ":" + PORT);
            System.out.println("Aceptando conexiónes: " + connectionPath);

            // Crear el Java registry para RMI y hacer el bind con la URI de conexión
            LocateRegistry.createRegistry(PORT);
            Naming.rebind(connectionPath, servicio);

        } catch (UnknownHostException e) {
            System.err.println("No se ha encontrado una dirección IP válida");
        } catch (NoSuchFileException e) {
            System.err.println("No se ha podido cargar la base de datos");
        } catch (ConnectException e) {
            System.err.println("Error de conexión");
        } catch (Exception e) {
            System.err.println("System exception" + e);
            e.printStackTrace();
        }
    }
}
