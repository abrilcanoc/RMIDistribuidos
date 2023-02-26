package edu.puj;

import java.rmi.RemoteException;
import java.util.List;

public interface ServicioEstudianteInterface extends java.rmi.Remote {
    public String getNombreById(Integer id) throws RemoteException;

    public List<Double> getNotasById(Integer id) throws RemoteException;

    public List<Double> getNotasByName(String name) throws RemoteException;

    public Integer getGrupoById(Integer id) throws RemoteException;

    public List<Estudiante> getEstudiantesByGroupId(Integer id) throws RemoteException;
}
