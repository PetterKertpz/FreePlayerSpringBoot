FreePlayerSpringBoot
Este es un proyecto de reproductor de música de escritorio desarrollado con Java y la integración de Spring Boot para la gestión del backend y JavaFX para la interfaz de usuario. La aplicación está diseñada para ser un sistema de gestión y reproducción de música completo, con funcionalidades para usuarios, canciones, álbumes, listas de reproducción y más.

🚀 Características Destacadas
Arquitectura Híbrida: Combina la robustez del framework de backend Spring Boot para el manejo de la lógica de negocio y persistencia de datos (mediante JPA) con la flexibilidad y potencia de JavaFX para una interfaz gráfica de escritorio.

Gestión de Música: Permite la administración de canciones, álbumes, artistas y géneros.

Listas de Reproducción: Soporte para la creación y gestión de listas de reproducción personalizadas.

Control de Reproducción: Interfaz de usuario con controles de reproducción estándar (play, pause, siguiente, anterior) y una barra de progreso.

Búsqueda Avanzada: Función de búsqueda que filtra canciones por título, artista, álbum y género.

Manejo Centralizado de Excepciones: Utiliza AspectJ para el registro y manejo de excepciones de forma centralizada en la capa de servicios, lo que mejora la mantenibilidad y la depuración.

Configuración Flexible: Permite la configuración de la conexión a la base de datos y otros ajustes a través del archivo application.properties.

🛠️ Tecnologías Utilizadas
Backend:

Spring Boot: Framework principal para el backend.

Spring Data JPA: Abstracción para la capa de persistencia de datos.

MySQL Connector/J: Driver para la conexión con la base de datos MySQL.

Lombok: Librería para reducir el boilerplate code en las clases de modelo.

Log4j2: Sistema de logging para la aplicación.

AspectJ: Framework para la programación orientada a aspectos (AOP), utilizado para el manejo de excepciones.

Frontend:

JavaFX: Toolkit para el desarrollo de la interfaz de usuario.

FXML: Lenguaje basado en XML para definir la estructura de la interfaz de usuario.

CSS: Hojas de estilo para personalizar la apariencia de la aplicación.

Herramientas de Construcción:

Apache Maven: Herramienta de gestión y automatización de la construcción del proyecto.

📂 Estructura del Proyecto
El proyecto sigue una estructura de directorios estándar de Maven con una clara separación de responsabilidades:

src/main/java/PMK/free_player/: Contiene el código fuente de la aplicación.

application/: Clases de arranque de la aplicación JavaFX (Reproductor.java) y de Spring Boot (SpringApplication.java), que actúan como el punto de entrada.

controller/: Controladores de JavaFX que manejan la lógica de la interfaz de usuario, como MainController.java y ListasCancionesController.java.

dto/: Objetos de transferencia de datos (CancionDto.java) utilizados para pasar información entre capas, desacoplando las entidades JPA de la UI.

exception/: Clases de excepción personalizadas para manejar errores específicos de la aplicación (NoDataFoundException.java, UsuarioNoEncontradoException.java, etc.).

models/: Entidades JPA que representan la estructura de la base de datos (Cancion.java, Usuario.java, Album.java, etc.).

repository/: Interfaces de Spring Data JPA para el acceso a la base de datos (CancionRepositorio.java, UsuarioRepositorio.java, etc.).

service/: Clases de servicio que contienen la lógica de negocio de la aplicación (CancionServicio.java, AlbumServicio.java, etc.).

util/: Clases de utilidad, como Paths.java para gestionar rutas de archivos.

src/main/resources/: Contiene los recursos de la aplicación.

css/: Archivos CSS para el estilo de las diferentes vistas.

fxml/: Archivos FXML que definen las interfaces de usuario.

images/: Archivos de imagen utilizados en la aplicación.

application.properties: Archivo de configuración de Spring Boot.

log4j2.xml: Configuración del sistema de logging.

📝 Resumen del Modelo de Datos
El modelo de datos se basa en entidades JPA que representan los conceptos clave de un reproductor de música:

Usuario: Almacena información de los usuarios (nombre de usuario, correo, contraseña, etc.).

Artista: Representa a los artistas musicales.

Album: Agrupa canciones de un artista.

Genero: Categorías de música.

Cancion: La entidad principal de música, con relaciones a Artista, Album y Genero.

ListaReproduccion: Permite a los usuarios crear colecciones de canciones.

DetalleListaReproduccion: Tabla de unión para la relación muchos a muchos entre ListaReproduccion y Cancion.

Favorito: Tabla de unión para las canciones favoritas de un usuario, también una relación muchos a muchos.

HistorialReproduccion: Registra el historial de reproducción de los usuarios.

ConfiguracionUsuario y ConfiguracionReproduccion: Entidades para guardar las preferencias de los usuarios.

⚙️ Configuración y Ejecución
1. Requisitos
JDK 22 o superior.

Maven 3.9.10 o superior.

Una base de datos MySQL.

2. Configuración de la Base de Datos
Crea una base de datos llamada free_player_mejorado en tu servidor MySQL.

Asegúrate de que el usuario y la contraseña de la base de datos en src/main/resources/application.properties coincidan con los de tu entorno:

Properties

spring.datasource.url=jdbc:mysql://localhost:3306/free_player_mejorado?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=Mariam2025*
Spring Data JPA con spring.jpa.hibernate.ddl-auto=update se encargará de crear y actualizar el esquema de la base de datos automáticamente al ejecutar la aplicación.

3. Ejecución del Proyecto
Abre una terminal en la raíz del proyecto.

Usa el Maven Wrapper para construir y ejecutar la aplicación:

Bash

# En sistemas tipo Unix
./mvnw clean install
./mvnw spring-boot:run

# En Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run
La aplicación de escritorio se lanzará y se conectará a la base de datos configurada.

🚧 Sugerencias de Mejora y Puntos a Destacar
Normalización de Nombres: Se ha observado una mezcla de nombres en español e inglés en clases, métodos y variables (por ejemplo, CancionServicio.java y AlbumServicio.java tienen nombres en español, mientras que MainController.java usa TituloLabel, ArtistaLabel, etc., con Label en inglés). Se recomienda unificar la nomenclatura para una mayor coherencia.

Controladores FXML: Los archivos FXML para Main, Albumes, Generos, etc. tienen una estructura muy similar con el panel izquierdo, el panel superior con la barra de búsqueda y el menú, y una tabla central. Una refactorización para reutilizar estos componentes de UI (por ejemplo, creando un FXML base o un controlador padre) podría reducir la redundancia y simplificar el mantenimiento.

Clase LoginController: La clase LoginController.java está definida pero no contiene ninguna lógica. Es un punto de partida para implementar la funcionalidad de inicio de sesión y registro, que podría interactuar con el servicio de UsuarioServicio.java.

Manejo de Transacciones: La clase CancionServicio.java utiliza la anotación @Transactional(readOnly = true) en el método listarCancionesDto(), lo cual es una buena práctica para asegurar que la sesión de Hibernate permanezca abierta durante la carga de las relaciones perezosas y evitar la excepción LazyInitializationException. Es recomendable revisar otros métodos de servicio que involucren relaciones para aplicar esta misma lógica si es necesario.

Maven Wrapper: El uso de mvnw y mvnw.cmd es una excelente práctica, ya que permite a cualquier desarrollador construir el proyecto sin necesidad de tener Maven instalado globalmente, garantizando que todos usen la misma versión.
