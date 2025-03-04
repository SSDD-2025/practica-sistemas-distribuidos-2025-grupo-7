
# Nombre de la aplicación: LeKing James Casino 👑

## Integrantes: 
| Name | Email | Github |
|-----------|-----------|-----------|
| Andrés Muñoz Muñoz    | a.munozm.2022@alumnos.urjc.es |[AndresMunozcuadrado](https://github.com/AndresMunozcuadrado) |             |
| Héctor González Viñas | h.gonzalezv.2022@alumnos.urjc.es | [hhectorgonzlez](https://github.com/hhectorgonzlez)                             |
| Raúl Sánchez Benítez       | r.sanchezb.2022@alumnos.urjc.es | [raulsnchz](https://github.com/raulsnchz)                   

## Entidades
A continuación, un breve resumen de las entidades y las relaciones entre ellos.
### User
<table>
  <thead>
    <th>Se relaciona con</th>
    <th>Cardinalidad</th>
  </thead>
  <tbody>
    <tr>
      <td>Play</td>
      <td>1..N</td>
    </tr>
    <tr>
      <td>Prize</td>
      <td>1..N</td>
    </tr>
  </tbody>
</table>

### Play
<table>
  <thead>
    <th>Se relaciona con</th>
    <th>Cardinalidad</th>
  </thead>
  <tbody>
    <tr>
      <td>User</td>
      <td>N..1</td>
    </tr>
    <tr>
      <td>Game</td>
      <td>N..1</td>
    </tr>
  </tbody>
</table>

### Game
<table>
  <thead>
    <th>Se relaciona con</th>
    <th>Cardinalidad</th>
  </thead>
  <tbody>
    <tr>
      <td>Play</td>
      <td>1..N</td>
    </tr>
  </tbody>
</table>

### Prize
<table>
  <thead>
    <th>Se relaciona con</th>
    <th>Cardinalidad</th>
  </thead>
  <tbody>
    <tr>
      <td>User</td>
      <td>N..1</td>
    </tr>
  </tbody>
</table>

Para mayor claridad, mirar el siguiente diagrama:


## Capturas de pantalla: 


## Diagrama de navegación: 


## Diagrama de clases y templates: 



## Participación
- **Raúl Sánchez Benítez:** 
    - 
     - 5 commits más significativos:
       - [Commit 1]
       - [Commit 2]
       - [Commit 3]
       - [Commit 4]
       - [Commit 5]
    - 5 ficheros en los que se ha participado:
      - []
      - []
      - []
      - []
      - []
- **Andres Muñoz Muñoz:** 
    - 
     - 5 commits más significativos:
       - [Commit 1]
       - [Commit 2]
       - [Commit 3]
       - [Commit 4]
       - [Commit 5]
    - 5 ficheros en los que se ha participado:
      - []
      - []
      - []
      - []
      - []
- **Héctor González Viña:** 
    - 
     - 5 commits más significativos:
       - [Commit 1]
       - [Commit 2]
       - [Commit 3]
       - [Commit 4]
       - [Commit 5]
    - 5 ficheros en los que se ha participado:
      - []
      - []
      - []
      - []
      - []

## Instrucciones de ejecución

1. Descarga este repositorio y descomprímelo
2. Ejecute en su IDE de preferencia. Recomendamos `VS`
3. Una vez que se esta ejecutando la aplicacion, vaya en su navegador a https://localhost:8080/ 

### Se necesita
- Java: JDK 21 
    * En caso de que no tengas el JDK instalado en tu ordenador, aquí te mostramos cómo obtenerlo:
        * Para Windows: [Click Here](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.zip).
        * Para Linux, ejecute el siguiente comando en la terminal:
            ```sh
            sudo apt install openjdk-21
            ````
- Maven v0.44.0
    * Si estas usando `VS`, puedes obtenerlo con la extensión `Maven for Java`.

        In other case, you can get it [here](https://maven.apache.org/download.cgi).

- Spring Boot 3.4.2
    *  Si estas usando `Visual Studio Code`, necesitarás la extension `Spring Boot Extension Pack`.



