# Nombre de la aplicación: LeKing James Casino 👑

## Integrantes: 
| Nombre                | Email                          | Github                                      |
|-----------------------|--------------------------------|---------------------------------------------|
| Andrés Muñoz Muñoz    | a.munozm.2022@alumnos.urjc.es  | [AndresMunozcuadrado](https://github.com/AndresMunozcuadrado) |
| Héctor González Viñas | h.gonzalezv.2022@alumnos.urjc.es| [hhectorgonzlez](https://github.com/hhectorgonzlez)           |
| Raúl Sánchez Benítez  | r.sanchezb.2022@alumnos.urjc.es| [raulsnchz](https://github.com/raulsnchz)                     |

## Descripción del Proyecto
LeKing James Casino es una aplicación de casino en línea que permite a los usuarios jugar a diferentes juegos de azar, ganar premios y gestionar sus perfiles. La aplicación está diseñada para ser intuitiva y fácil de usar, con un enfoque en la experiencia del usuario.

## Entidades
A continuación, se presenta un breve resumen de las entidades y las relaciones entre ellas.

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

## Diagramas

### Diagrama de Entidad-Relación
![Diagrama ERD](/Diagrams/relational%20diagram.png)

Este diagrama muestra las relaciones entre las entidades principales del sistema: `User`, `Play`, `Game`, y `Prize`.

### Diagrama de Navegación
![Diagrama de Navegación](/Diagrams/navigationDiagram.png)
Este diagrama ilustra la estructura de navegación de la aplicación, mostrando cómo los usuarios pueden moverse entre las diferentes secciones del casino, como los juegos, los premios y su perfil, todo esto desde el inicio, ya que le hemos dado un enfoque para que principalmente se tenga que acceder al menú principal para reañizar las acciones. Cabe destacar, que las barras de la izquierda y de arriba, se mantienen en las pantallas de los juegos, los premios y perfil.

### Diagrama de Clases
![Diagrama de Clases](/Diagrams/template%20Diagram.jpg)
El diagrama de clases representa la estructura de las clases en el código, incluyendo sus atributos, métodos y relaciones. Este diagrama es útil para entender la arquitectura del software.

## Capturas de Pantalla
![Captura de Pantalla 1](/Diagrams/Screenshots/main.png)
Esta es la pantalla principal, desde la que se accede a la gran mayoria de funcionalidades. Tenemos botones que nos llevan a los juegos que indican, los botones para iniciar sesion y para registrarse, y una vez iniciado sesion, aparece un boton para cerrar sesion.
![Captura de Pantalla 2](/Diagrams/Screenshots/register.png)
![Captura de Pantalla 3](/Diagrams/Screenshots/login.png)
Este es el login
![Captura de Pantalla 4](/Diagrams/Screenshots/logout.png)
Este es el logout
![Captura de Pantalla 5](/Diagrams/Screenshots/prizes.png)
![Captura de Pantalla 6](/Diagrams/Screenshots/roulette.png)
![Captura de Pantalla 7](/Diagrams/Screenshots/dice.png)
![Captura de Pantalla 10](/Diagrams/Screenshots/slot.png)
![Captura de Pantalla 8](/Diagrams/Screenshots/error.png)
![Captura de Pantalla 9](/Diagrams/Screenshots/profile.png)
Estas capturas de pantalla muestran la interfaz de usuario de la aplicación, incluyendo la página de inicio, los juegos disponibles y la sección de premios.

## Participación

### Raúl Sánchez Benítez
- **5 commits más significativos:**
  1. [Commit 1](enlace_al_commit)
  2. [Commit 2](enlace_al_commit)
  3. [Commit 3](enlace_al_commit)
  4. [Commit 4](enlace_al_commit)
  5. [Commit 5](enlace_al_commit)
- **5 ficheros en los que se ha participado:**
  1. `fichero1.java`
  2. `fichero2.java`
  3. `fichero3.java`
  4. `fichero4.java`
  5. `fichero5.java`

### Andrés Muñoz Muñoz
- **5 commits más significativos:**
  1. [Commit 1](enlace_al_commit)
  2. [Commit 2](enlace_al_commit)
  3. [Commit 3](enlace_al_commit)
  4. [Commit 4](enlace_al_commit)
  5. [Commit 5](enlace_al_commit)
- **5 ficheros en los que se ha participado:**
  1. `fichero1.java`
  2. `fichero2.java`
  3. `fichero3.java`
  4. `fichero4.java`
  5. `fichero5.java`

### Héctor González Viñas
- **5 commits más significativos:**
  1. [Commit 1](enlace_al_commit)
  2. [Commit 2](enlace_al_commit)
  3. [Commit 3](enlace_al_commit)
  4. [Commit 4](enlace_al_commit)
  5. [Commit 5](enlace_al_commit)
- **5 ficheros en los que se ha participado:**
  1. `fichero1.java`
  2. `fichero2.java`
  3. `fichero3.java`
  4. `fichero4.java`
  5. `fichero5.java`

## Instrucciones de Ejecución

1. Descarga este repositorio y descomprímelo.
2. Ejecuta el proyecto en tu IDE de preferencia. Recomendamos `VS Code`.
3. Una vez que la aplicación esté en ejecución, abre tu navegador y ve a `https://localhost:8080/`.

### Requisitos
- **Java: JDK 21**
  - En caso de que no tengas el JDK instalado en tu ordenador, aquí te mostramos cómo obtenerlo:
    - Para Windows: [Descargar JDK 21](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.zip).
    - Para Linux, ejecuta el siguiente comando en la terminal:
      ```sh
      sudo apt install openjdk-21
      ```
- **Maven v0.44.0**
  - Si estás usando `VS Code`, puedes obtenerlo con la extensión `Maven for Java`.
  - En otros casos, puedes descargarlo [aquí](https://maven.apache.org/download.cgi).
- **Spring Boot 3.4.2**
  - Si estás usando `Visual Studio Code`, necesitarás la extensión `Spring Boot Extension Pack`.
