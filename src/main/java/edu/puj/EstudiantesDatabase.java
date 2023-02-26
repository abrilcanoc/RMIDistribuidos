package edu.puj;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Getter
public class EstudiantesDatabase {

    private List<Estudiante> estudiantes;

    StringBuilder contenidoArchivo = new StringBuilder();

    public EstudiantesDatabase(String resourceName) throws IOException {
        Path path = Path.of(Objects.requireNonNull(Servidor.class.getClassLoader().getResource(resourceName)).getPath());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            Gson gson = new Gson();
            estudiantes = gson.fromJson(reader, new TypeToken<List<Estudiante>>() {
            }.getType());
        }
    }

}
