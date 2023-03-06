# Taller RMI
Integrantes: Angel Talero, Juan Durán, Juan Robledo, y Abril Cano

# Introducción 

RMI por sus siglas en ingles de Remote Method Invocation es un mecanismo que permite invocar métodos de manera remota. 
La realización de este taller busca implementar este mecanismo de comunicación en un código en Java para ver el correcto funcionamiento de este metodo y la comunicación en diferentes máquinas.


# Contexto del taller

Para la realización de este taller se proponen los siguientes métodos para acceder remotamente.

* Nombre: Dado el identificador de un estudiante, retorna su nombre completo.
* Notas: Dado el nombre o el Identificador de un estudiante retorna el promedio de las
evaluaciones realizadas hasta el momento.
* Grupo: Dado el ID de un estudiante, retorna el identificador de su grupo de trabajo.
* Miembros-Grupo: Dado el identificador de un determinado grupo, el método devuelve sus
miembros

Asimismo se tienen lo siguientes datos de prueba a los caules pueden hacer referencia los métodos anteriores

| Grupo  | Id  | Nombre          | Taller 1 | Taller 2 |
|--------|-----|-----------------|----------|----------|
| G3     | 334 | Aguilar,María   | 5        | 5        |
| G5     | 444 | Rodriguez, Jose | 4        | 3        |
| ...    | ... | ...             | ...      | ...      |
| G7     | 995 | Cabrales, Elisa | 4,5      | 5        |

# Solución Propuesta 

En la implementación se utilizaron herramientas de apoyo como Apache Maven para la gestión y construcción del proyecto y sus dependencias. Entre estas dependencias
se utilizó Lombok para facilitar las consultas, al igual que gson para la manupulación de archivos json, en donde se guardaron los estudiantes.


Para la realización del taller se creó una clase cliente para que actue como tal y pueda realizar las consultas. 
Esta se encuantra implementada en `Cliente.java` y su función comienza con realizar la conexión al servidor e inicializar el servicio de estudiente para poder acceder a los datos. 
En caso de que este proceso anterior sea exitoso se accede a un menú que permite hacer las diferentes consultas de los estudiantes.

Para la gestión y creación de los datos, se utilizó una clase que representa a un estudiante y los datos que se tienen de este en `Estudiante.java`. Adicionalmente se utilizó 
la clase `EstudiantesDatabase.java` para leer los estudiantes del archivo .json a una lista de Estudiantes para facilitar su manejo en las consultas. 

Para la implemantación del RMI se utilizó la interfaz `ServicioEstudianteInterface.java` implementada en `ServicioEstudianteImpl.java`que contienen los siguientes métodos de consulta a acceder remotamente:
* getNombreById: Dado el identificador de un estudiante, retorna su nombre completo.
* getNotasById: Dado el Identificador de un estudiante retorna el promedio de las evaluaciones realizadas hasta el momento.
* getNotasByName: Dado el nombre de un estudiante retorna el promedio de las evaluaciones realizadas hasta el momento.
* getGrupoById: Dado el identificador de un determinado un estudiante, retorna el identificador de su grupo de trabajo.
* getEstudiantesByGroupId: Dado el identificador de un determinado grupo, el método devuelve sus
miembros

Finalmente en `Servidor.java` se implementó el programa que actua como servidor en donde se instancian los datos de los estudiantes y la conexión con el cliente para que este pueda acceder remotamente a los métodos.

# Ejecución del código 

Para correr de manera correcta el código debido a que se utilizó el gestor de dependencias Maven para ejecutar el cliente se debe utilizar el comando:
```shell
$ mvn exec:java -Dexec.mainClass="edu.puj.Cliente" -Dexec.args="[SERVER IP]:[PORT]"
```
Para el lado del sevidor es recomendado correrlo desde un IDE como IntelliJ para asegurarse de que el programa corra correctamente. Este no recibe ningun argumento. 
De lo misma manera es importante asegurar que el archivo donde reposan los datos de los estudiantes: Estudiantes.json se encuentre en la carpeta de resources.



