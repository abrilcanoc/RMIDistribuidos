package edu.puj;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase Main para el programa del Cliente
 */
public class Cliente {

    static boolean retry = true;
    static ServicioEstudianteInterface servicioEstudiante;

    public static void main(String[] args) throws InterruptedException {

        // Intentar conexión con el servidor
        while (true) {
            if (args.length == 0) {
                System.err.println("Debe especificar la IP del servidor y su puerto...");
                System.exit(1);
            }
            System.out.println("Conectándose al servidor: " + args[0]);

            // Inicializar el servicio de estudiante
            try {
                servicioEstudiante = (ServicioEstudianteInterface) Naming.lookup("rmi://" + args[0] + "/ServicioEstudiante");
            } catch (Exception e) {
                System.err.println("Error de conexión con el Servidor!");
                Thread.sleep(5000);

                if (!retry) {
                    System.err.println("No se ha podido establecer conexión con el servidor");
                    System.exit(1);
                } else {
                    retry = false;
                    continue;
                }
            }

            System.out.println("Conexión exitosa!\n");
            break;
        }


        // Mostrar el menú
        var scanner = new Scanner(System.in);
        int opcion = 0;
        int idBusqueda = 0;
        List<Double> notas;
        do {
            try {
                System.out.println("\nBienvenido!...");
                System.out.println("1. Obtener nombre por ID");
                System.out.println("2. Obtener notas por ID");
                System.out.println("3. Obtener notas por nombre");
                System.out.println("4. Obtener grupo por ID");
                System.out.println("5. Obtener estudiantes por grupo");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.print("Digite un ID: ");
                        idBusqueda = scanner.nextInt();
                        var estudiante = servicioEstudiante.getNombreById(idBusqueda);
                        System.out.println("Estudiante: " + Objects.requireNonNullElse(estudiante, "No existe estudiante con el ID indicado"));
                        break;

                    case 2:
                        System.out.print("Digite un ID: ");
                        idBusqueda = scanner.nextInt();
                        notas = servicioEstudiante.getNotasById(idBusqueda);
                        System.out.println("Notas: " + Objects.requireNonNullElse(notas, "No existe un estudiante con el ID indicado"));
                        break;

                    case 3:
                        System.out.print("Digite un nombre: ");
                        String nombreBusqueda = scanner.next();
                        System.out.println("Nombre a buscar: " + nombreBusqueda);
                        notas = servicioEstudiante.getNotasByName(nombreBusqueda);
                        System.out.println("Notas: " + Objects.requireNonNullElse(notas, "No existe un estudiante con el nombre indicado"));
                        break;

                    case 4:
                        System.out.print("Digite un ID: ");
                        idBusqueda = scanner.nextInt();

                        var grupo = servicioEstudiante.getGrupoById(idBusqueda);
                        System.out.println("Grupo: " + Objects.requireNonNullElse(grupo, "No existe un estudiante con el ID indicado"));

                        break;

                    case 5:
                        System.out.print("Digite un Grupo: ");
                        idBusqueda = scanner.nextInt();

                        var estudiantes = servicioEstudiante.getEstudiantesByGroupId(idBusqueda);
                        System.out.println("Estudiantes: ");

                        if (estudiantes != null)
                            estudiantes.forEach(System.out::println);
                        else
                            System.out.println("No existe el grupo especificado");

                        break;

                    case 0:
                        System.out.println("Adiós...");
                        break;

                    default:
                        System.err.println("Opción incorrecta...");
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } while (opcion != 0);
    }
}
