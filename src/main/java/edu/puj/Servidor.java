package edu.puj;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.NoSuchFileException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Servidor {
    private static final int PORT = 34053;

    public static void main(String[] args) {
        try {
            var database = new EstudiantesDatabase("estudiantes.json");
            var servicio = new ServicioEstudianteImpl(database);

            System.out.println("Dirección del Servidor: " + Inet4Address.getLocalHost().getHostAddress() + ":" + PORT);
            String connectionPath = "rmi://localhost:" + PORT + "/ServicioEstudiante";
            System.out.println("Connecting to: " + connectionPath);

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
