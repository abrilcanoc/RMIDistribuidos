package edu.puj;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class Estudiante implements Serializable {
    Integer grupo;
    String nombre;
    Integer ID;
    List<Double> notas;
}
