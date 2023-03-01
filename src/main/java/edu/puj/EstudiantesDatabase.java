package edu.puj;

import lombok.Getter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Clase encargada de leer el archivo de base de datos de estudiantes
 * para desacoplar el formato (.json) de la lógica del programa
 */
@Getter
public class EstudiantesDatabase {

    private final List<Estudiante> estudiantes;

    /**
     * Constructor que lee el archivo para poblar el arreglo de estudiantes
     *
     * @param resourceName Nombre del recurso (.json)
     * @throws IOException En caso de que el archivo no exista
     */
    public EstudiantesDatabase(String resourceName) throws IOException {
        // Hallar el 'path' dentro de los recursos donde se encuentra la base de datos
        Path path = Path.of(Objects.requireNonNull(Servidor.class.getClassLoader().getResource(resourceName)).getPath());

        // try-with-resources Que lee el archivo (y lo cierra automáticamente)
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            // Parser de JSON y Deserialización
            Gson gson = new Gson();

            // Gson deserializa la lista de estudiantes y mediante [java.api.reflection] asigna las variables del Json a las variables de la clase [Estudiante]
            estudiantes = gson.fromJson(reader, new TypeToken<List<Estudiante>>() {
            }.getType());
        }
    }

}
