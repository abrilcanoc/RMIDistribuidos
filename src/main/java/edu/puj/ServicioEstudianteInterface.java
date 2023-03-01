package edu.puj;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz que define el Servicio de consulta de estudiantes
 */
public interface ServicioEstudianteInterface extends java.rmi.Remote {
    /**
     * Obtener el nombre de un estudiante dado su ID
     *
     * @param id ID del estudiante
     * @return Nombre del estudiante o NULL si no se encuentra
     * @throws RemoteException Error en llamado a procedimiento remoto
     */
    String getNombreById(Integer id) throws RemoteException;

    /**
     * Obtener las notas de un estudiante dado su ID
     *
     * @param id ID del estudiante
     * @return Lista de Double o NULL si no existe el ID
     * @throws RemoteException Error en llamado a procedimiento remoto
     */
    List<Double> getNotasById(Integer id) throws RemoteException;

    /**
     * Obtener las notas de un estudiante dado su nombre
     *
     * @param name Nombre del estudiante a buscar
     * @return Lista de Double o NULL si no existe el ID
     * @throws RemoteException Error en llamado a procedimiento remoto
     */
    List<Double> getNotasByName(String name) throws RemoteException;

    /**
     * Obtener el grupo de un estudiante dado su ID
     *
     * @param id ID del estudiante
     * @return Entero que representa su grupo o NULL si no existe el ID
     * @throws RemoteException Error en llamado a procedimiento remoto
     */
    Integer getGrupoById(Integer id) throws RemoteException;

    /**
     * Obtener los estudiantes de un grupo dado el ID del grupo
     *
     * @param id ID del grupo
     * @return Lista de estudiantes que pertenecen al grupo o NULL si no existe o no tiene estudiantes
     * @throws RemoteException Error en llamado a procedimiento remoto
     */
    List<Estudiante> getEstudiantesByGroupId(Integer id) throws RemoteException;
}
