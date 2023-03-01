package edu.puj;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que implementa el Servidor para proveer el servicio de estudiantes
 */
public class ServicioEstudianteImpl extends UnicastRemoteObject implements ServicioEstudianteInterface {

    private final EstudiantesDatabase estudiantes;

    /**
     * Crea el servicio de estudiantes con la base de datos de estudiantes
     *
     * @param database Base de datos de estudiantes
     * @throws RemoteException Error en java.rmi
     * @see edu.puj.EstudiantesDatabase
     */
    public ServicioEstudianteImpl(EstudiantesDatabase database) throws RemoteException {
        super();
        this.estudiantes = database;
    }

    public String getNombreById(Integer id) throws RemoteException {
        var estudiante = estudiantes.getEstudiantes().stream().filter(e -> e.getID().equals(id)).findFirst();
        return estudiante.map(Estudiante::getNombre).orElse(null);
    }

    public List<Double> getNotasById(Integer id) throws RemoteException {
        for (var i : estudiantes.getEstudiantes()) {
            if (i.getID().equals(id)) {
                return i.getNotas();
            }
        }
        return null;
    }

    public List<Double> getNotasByName(String name) throws RemoteException {
        for (var i : estudiantes.getEstudiantes()) {
            if (i.getNombre().equals(name)) {
                return i.getNotas();
            }
        }
        return null;
    }

    public Integer getGrupoById(Integer id) throws RemoteException {
        for (var i : estudiantes.getEstudiantes()) {
            if (i.getID().equals(id)) {
                return i.getGrupo();
            }
        }
        return 0;
    }

    public List<Estudiante> getEstudiantesByGroupId(Integer id) throws RemoteException {
        List<Estudiante> integrantes = new ArrayList<>();
        for (var i : estudiantes.getEstudiantes()) {
            if (i.getGrupo().equals(id)) {
                integrantes.add(i);
            }
        }

        if (integrantes.isEmpty())
            return null;
        return integrantes;
    }

}
