package edu.puj;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    static boolean retry = false;
    static ServicioEstudianteInterface servicioEstudiante;

    public static void main(String[] args) throws InterruptedException {
        do {
            if (args.length == 0) {
                System.err.println("Debe especificar la IP del servidor y su puerto...");
                System.exit(1);
            }
            System.out.println("Conectándose al servidor: " + args[0]);

            try {
                servicioEstudiante = (ServicioEstudianteInterface) Naming.lookup("rmi://" + args[0] + "/ServicioEstudiante");
            } catch (Exception e) {
                System.err.println("Error de conexión con el Servidor!");
                Thread.sleep(5000);
                retry = !retry;
                continue;
            }

            break;
        } while (retry);


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
                        System.out.println("Estudiante: " + estudiante);
                        break;

                    case 2:
                        System.out.print("Digite un ID: ");
                        idBusqueda = scanner.nextInt();
                        notas = servicioEstudiante.getNotasById(idBusqueda);
                        System.out.println("Notas: " + notas);
                        break;

                    case 3:
                        System.out.print("Digite un nombre: ");
                        String nombreBusqueda = scanner.next();
                        System.out.println("Nombre a buscar: " + nombreBusqueda);
                        notas = servicioEstudiante.getNotasByName(nombreBusqueda);
                        System.out.println("Notas: " + notas);
                        break;

                    case 4:
                        System.out.print("Digite un ID: ");
                        idBusqueda = scanner.nextInt();

                        var grupo = servicioEstudiante.getGrupoById(idBusqueda);
                        System.out.println("Grupo: " + grupo);

                        break;

                    case 5:
                        System.out.print("Digite un Grupo: ");
                        idBusqueda = scanner.nextInt();

                        var estudiantes = servicioEstudiante.getEstudiantesByGroupId(idBusqueda);
                        System.out.println("Estudiantes: ");
                        estudiantes.forEach(System.out::println);

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
