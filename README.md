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

  Esta es la pantalla principal, desde la que se accede a la gran mayoria de funcionalidades. Tenemos botones que nos llevan a los juegos que indican, los botones para iniciar sesion y para registrarse(arriba a la derecha), y una vez iniciado sesion, aparece un boton para cerrar sesion. Además, una vez has iniciado sesión, puedes añadir dinero con un boton en la parte izquierda de la pantalla:
![Captura de Pantalla 2](/Diagrams/Screenshots/main_Session.png)
  En esta fotografía se muestra como es el menú de inicio al haber iniciado sesión.

![Captura de Pantalla 3](/Diagrams/Screenshots/register.png)

  Aqui se muestra la pantalla de registro.

![Captura de Pantalla 4](/Diagrams/Screenshots/login.png)

  Aqui se muestra la pantalla de login o inicio de sesión.

![Captura de Pantalla 5](/Diagrams/Screenshots/logout.png)

  Aqui se muestra la pantalla de cerrar sesión.

![Captura de Pantalla 5](/Diagrams/Screenshots/prizes.png)

  Aqui se muestra la pantalla de premios, debajo de los premios hay un botón de comprar, pero cuando se ha comprado ese premio, se cambia por el nombre del usuario que lo tiene.

![Captura de Pantalla 6](/Diagrams/Screenshots/roulette.png)

  Aqui se muestra la pantalla del primero de nuestros juegos, la ruleta, puedes ingresar el dinero a apostar y el numero que apuestas que va a salir, además, abajo a la izquierda hay un botón que te lleva al historial de las partidas de la ruleta.

![Captura de Pantalla 7](/Diagrams/Screenshots/dice.png)

  Aqui se muestra la pantalla del segundo de nuestros juegos, los dados, puedes ingresar el dinero a apostar y el numero que apuestas que va a salir, abajo a la izquierda, vuelve a aparecer el botón del historial, pero esta vez te muestra el de los dados.

![Captura de Pantalla 10](/Diagrams/Screenshots/slot.png)

  Aqui se muestra la pantalla del tercer juego, la tragaperras, esta carece de utilidad, ya que por falta de tiempo no ha sido posible hacerla funcional, pero puedes girarla de forma gratuita y divertirte viendo como gira.

![Captura de Pantalla 8](/Diagrams/Screenshots/error.png)

  Esta es nuestra pantalla de error, asi cuando te de un error verás a LeBron triste y sabrás que hasta los más grandes cometen errores.

![Captura de Pantalla 9](/Diagrams/Screenshots/profile.png)

  En esta captura, se muestra la pagina de perfil, en la que puedes ver tu nombre, saldo, cambiar tu foto de perfil, además de tu historial. El historial le puedes borrar entero para que nadie sepa que eres un ludópata, o puedes borrar las partidas de forma individual, así como borrar tu cuenta.


Todas estas capturas de pantalla muestran las interfaces principales de la web, pero tenemos algunas más que no son tan destacables.

## Participación

### Raúl Sánchez Benítez
- **Descripción textual de las tareas realizadas en la práctica:**

    Al principio de la practica, me centré en buscar código de la ruleta y la máquina de slots que pudisemos adaptar para nuestra aplicación. Tras probar que funcionaban y que se pueden adaptar, decidí centrarme en la implementación de la entidad `Prize.java` al completo. 
    
    Primero, hice los botones y la ventana de `prizes.html`. Luego hice el `PrizeRepository.java` y me aseguré de que se visualizaba todo bien en la bbdd para poder hacer el `PrizeService.java` e inicializar premios por defecto con los que trabajar. Finalmente estuve trabajando en la funcionalidad de adquirir un premio, siempre que no lo haya adquirido otro usuario antes, y que se viera reflejado que usuario lo habia adquirido.

    Por ultimo, me centré en implementar la funcionalidad de borrar las partidas del historial individualmente. Ya teniamos la de borrar todo el historial entero, pero hacia falta tambien que pudieras borrar las que tu quisieras.
- **5 commits más significativos:**
  1. [Borrar partidas individualmente](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/a1877be2a3e240b32278f8dc8dafefcacc76efab)
  2. [Premios bbdd](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/d8297de278d8acf60647008c870c72c1b72a0b19)
  3. [Ventana de premios](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/d7cf54a7788f3bb9dd670565c5c7b1dbb495d048)
  4. [Creacion servicio de premios](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/d9e4732f4e6265373bac155779733375f0aac0e2)
  5. [Añadir funcionalidad de comparar premios](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/b477732c4a1562260c713f023c0cb976541c1da5)
- **5 ficheros en los que se ha participado:**
  1. `UserController.java`
  2. `Prize.java`
  3. `PrizeController.java`
  4. `PrizeService.java`
  5. `prize.html`

### Andrés Muñoz Muñoz
- **Descripción textual de las tareas realizadas en la práctica:**

  Para esta práctica, empecé ocupándome de buscar y adaptar el código de la ruleta y los dados para que funcionase en nuestra aplicación. Estos códigos los conseguí de distintas páginas web, y les realicé diversas modificaciones para que cumpliesen con lo que buscábamos. Para ello, tenían código `.html`, `.css` y `.js`, pero al ir desarrollando la web, nos enfrentamos al problema de que si generábamos un número aleatorio para el resultado en un archivo `.js`, no sabíamos cómo pasarlo a los controladores para que estos determinasen, mediante una comparación, si habíamos acertado. 

  Después de esto, hice la ventana de `profile.html`, con los métodos necesarios para que funcionase de la manera esperada. Lo más destacable de esto es la posibilidad de añadir fotografías usando la base de datos, así como de poder borrar al usuario o sus partidas. Al realizar esto, tuve que subsanar unos errores en la forma en la que se generaba la base de datos y las relaciones de las tablas. 

  Además, me ocupé de la migración de H2 a MySQL y de la realización de diversos cambios para subsanar errores que afectaban a la funcionalidad de la web, como por ejemplo quitar el acceso a los repositorios desde los controladores.


- **5 commits más significativos:**
  1. [Creación de ventana de perfil](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/1eba7481c57ec44ea5512d0e4e98d1e1be36cdeb)
  2. [Arreglo de repositorios y servicios(creación de servicios)](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/41cfab590c10956810f049a0fd8e024993423eeb)
  3. [Añadir fotos de usuario a la bbdd](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/5380878df7208bc1c325dbd9fea7ead447a3b3a8)
  4. [Borrar usuario y partidas de la bbdd](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/5155a422e52e3159b0ae131acae75a00359ab01c)
  5. [Migración a MySQL](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/d7f7eaed0b65d9d29f953f926b076d3732817846)
- **5 ficheros en los que se ha participado:**
  1. `UserController.java`
  2. `profile.html`
  3. `error.html`
  4. `PlayService.java`
  5. `UserService.java`

### Héctor González Viñas
- **Descripción textual de las tareas realizadas en la práctica:**
  
En esta práctica empecé creando el código html y css básico que engloba cada página además del código en javaScript usado para las redirecciones. Después enlace las páginas de login y registro con la página principal y el uso del HttpSession para distinguir que usuario ha iniciado sesión.

Después me centré en el PlayController en el que se crean partidas para los dados y ruleta eligiendo el número y el dinero para apostar con otra redirección para procesar la apuesta en la que se generarán los números aleatorios que se compararán con el número introducido por el usuario y actuar en consecuencia de si se ha acertado o no con las plantillas de mustache.También hice algunas de las relacciones entre entidades y comprobaciones de los datos de entrada del login, registro y juegos.

- **5 commits más significativos:**
  1. [Creación proyecto y html global](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/c593af9370ff2a99745b5e160401b5944b4f8b48)
  2. [Conexión entre usuario y dados para comprobar victoria](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/f3ca6de8697152f7107e8396bc18bf7e98bfb9af)
  3. [Base de datos de las partidas y relacciones ](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/da7fc94c9794862afbccebb1ddca508e1e278e6c)
  4. [Conexión entre usuario y ruleta para comprobar victoria](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/ea9d2dc7b1ab49dc26cfea5739158ac431bbb27b)
  5. [bbdd juegos](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/6fd5d45e988710f7cae643e205364b3eea1c2d3b)
- **5 ficheros en los que se ha participado:**
  1. `UserController.java`
  2. `main.html`
  3. `PlayController.java`
  4. `GameController.java`
  5. `dice.html`

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
- **Configurar la base de datos MySQL**:
    - Crear una base de datos en MySQL:
      ```sql
      CREATE DATABASE bbdd;
      ```
    - Actualizar las credenciales de la base de datos en `src/main/resources/application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/bbdd
      spring.datasource.username=root
      spring.datasource.password=password
      spring.jpa.hibernate.ddl-auto=create-drop
      ```





# Parte 2
Hemos actualizado el diagrama de templates y añadido funcinonalidades para hacer uso de la api rest. También hemos añadido SpringSecurity para que nuestra aplicación web esté protegida con https.

### Diagrama de Clases
![Diagrama de Clases](/Diagrams/templateDiagram2.jpg)

  El diagrama de clases representa la estructura de las clases en el código, incluyendo sus atributos, métodos y relaciones. Este diagrama es útil para entender la arquitectura del software.

### Diagrama de Navegación
![Diagrama de Navegación](/Diagrams/navigationDiagram2.jpeg)

  Este diagrama ilustra la estructura de navegación de la aplicación, mostrando cómo los usuarios pueden moverse entre las diferentes secciones del casino, como los juegos, los premios y su perfil, todo esto desde el inicio, ya que le hemos dado un enfoque para que principalmente se tenga que acceder al menú principal para reañizar las acciones. Cabe destacar, que las barras de la izquierda y de arriba, se mantienen en las pantallas de los juegos, los premios y perfil.

## Participación

Todos hemos aportado a todas las partes, ya sea para implementación de nuevo código o para arreglo de errores.

### Raúl Sánchez Benítez

- **5 commits más significativos:**
  1. [Creación PlayRestController](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/2736d73ec4250a874445c2591cbcb27f815191ab)
  2. [Creación PriceRestController](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/3b335e06ca32df7958c3e85718dc65c3f73c3e34)
  3. [securityConfig changes to accept api](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/1cadbf81980a8f4fbe31be8a6a2db715a1e901ca)
  4. [Actualizacion de permisos, csrf y paginacion con AJAX en historial personal](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/b4e42f9f9b1e16e690d2d2fa6e79926927529928)
  5. [Play desde PostMan](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/d88f55891490f4cf4f8b0ac4a9e9e530d5374d66)
- **5 ficheros en los que se ha participado:**
  1. `UserRestController.java`
  2. `PrizeRestController.html`
  3. `GameRestController.html`
  4. `PlayRestConrtoller.java`
  5. `PlayController.java`

### Andrés Muñoz Muñoz

- **5 commits más significativos:**
  1. [Creación UserrRestController](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/8ac72f56b1bc3d8944e129bb24de20d565cf5268)
  2. [Todo a DTOs](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/461e4fad7f965cf4edd2ec58b99b7798352adfb9)
  3. [Arreglo de DTOs](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/f0762c1b3d53fbb91ae447b67335bad3d7014262)
  4. [Arreglo gameSave](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/9d3c57b66a2d66641e85eabf59a3f2fbdd104bc1)
  5. [Arreglo mySQL](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/68c5c4a636344711fe6f18239ba0685c08f06f5f)
- **5 ficheros en los que se ha participado:**
  1. `UserRestController.java`
  2. `PrizeRestController.html`
  3. `GameRestController.html`
  4. `PlayRestConrtoller.java`
  5. `UserrDTO.java`

### Héctor González Viñas

- **5 commits más significativos:**
  1. [springSecurity 1](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/38daa8ee87f2017d1f8c8a77350876be7f154f5c)
  2. [springSecurity2](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/29d60c00ebd46592d2bf42f97eeca9e7a8ae6491)
  3. [funcionalidad juego con fichero](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/b0eecc2e02498b7ab4c9564e26af4293a378fb5b)
  4. [creacion dtos y mappers](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/5e9caa4fa1f984fdd1febac154b9272442448834)
  5. [editar juegos](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-7/commit/897990ab9d1d994686367515b87a98df528d3569)
- **5 ficheros en los que se ha participado:**
  1. `UserController.java`
  2. `SecurityConfiguration.java`
  3. `GameRestController.html`
  4. `PlayConrtoller.java`
  5. `GameController.java`


# Parte 3

ESTO ES UN SUCIO

Práctica 3
Construcción y publicación de la imagen Docker
La imagen Docker se construye con un Dockerfile /docker, la etapa final contiene solo la aplicación compilada y sus dependencias.
    • Crear imagen Docker: ./proyecto/docker/create_image.sh 
Con esto se genera la imagen con la etiqueta lkj-casino:1.0.0, compilandolo con Maven

    • Publicar imagen en DockerHub: ./proyecto/docker/publish_image.sh 
La imagen se publica con el nombre: docker.io/hhectorgonzlez/lkj-casino:1.0.0

    • Crear imagen con Buildpacks: en caso de usar Buildpacks, compilamos la aplicación con:
mvn spring-boot:build-image -DskipTests #NO SE TENGO NI IDEA DE QUE ES ESTO
Docker Compose
Para contruir la imagen localmente, y levantar la aplicación junto con las base de datos MySQL(usando la imagen oficial mysql:9.2).
    • Ejecución en entorno local: docker compose -f ./docker/docker-compose.local.yml up
Este archivo utiliza la imagen ya publicada en DockerHub y arranca la aplicación configurada en el puerto 8443 con HTTPS.
    • Ejecución en entorno de producción: docker compose -f docker/docker-compose.prod.yml up -d
Despliegue en máquinas virtuales
Para desplegar en las máquinas proporcionadas por la universidad tenemos que:
    • Subir la imagen a DockerHub (publish_image.sh)
    • Acceder por SSH a sidiXX-2 y ejecutar el siguiente comando para levantar la base de datos:
docker run -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=bbdd -p 3306:3306 -d -v ./mysql:/var/lib/mysql mysql:9.2
    • Asegurarse de que la base de datos está activa y accesible desde sidi07-1
    • Acceder a sidi07-1 y ejecutar :
docker run -d 
-e SPRING_DATASOURCE_URL=jdbc:mysql://192.168.110.90 :3306/bbdd -p 8443:8443 hhectorgonzlez/lkj-casino:1.0.0
URL del despliegue:
https://193.147.60.47:8443/
