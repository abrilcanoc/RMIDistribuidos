package edu.puj;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * POJO que representa a un Estudiante
 */
@Value
public class Estudiante implements Serializable {
    Integer grupo;
    String nombre;
    Integer ID;
    List<Double> notas;
}
