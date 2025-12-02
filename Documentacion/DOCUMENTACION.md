# 📚 Documentación del Proyecto - App ProjectSpoty

## 📋 Tabla de Contenidos
1. [Descripción General](#descripción-general)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Requisitos del Sistema](#requisitos-del-sistema)
4. [Instalación y Configuración](#instalación-y-configuración)
5. [Descripción de Módulos](#descripción-de-módulos)
6. [Base de Datos](#base-de-datos)
7. [Guía de Uso](#guía-de-uso)
8. [Tecnologías Utilizadas](#tecnologías-utilizadas)
9. [Autores](#autores)

---

## 📱 Descripción General

**App ProjectSpoty** es una aplicación de escritorio desarrollada en Java que simula una interfaz similar a la plataforma de streaming de música **Spotify**. El proyecto fue creado por estudiantes como ejercicio educativo y permite a los usuarios gestionar playlists, buscar canciones, artistas y álbumes desde una base de datos PostgreSQL.

### Características Principales:
- ✅ Autenticación de usuarios (Login/Register)
- ✅ Búsqueda de canciones, artistas y álbumes
- ✅ Gestión de playlists personalizadas
- ✅ Interfaz de usuario basada en menús de consola
- ✅ Base de datos relacional PostgreSQL
- ✅ Arquitectura modular (MVC)

---

## 🗂️ Estructura del Proyecto

```
App-ProjectSpoty/
├── appSpoty.sql                 # Script SQL de la base de datos
├── README.md                    # README principal
├── DOCUMENTACION.md             # Este archivo
└── ProjectSpoty/
    ├── README.md
    └── src/
        ├── App.java             # Punto de entrada de la aplicación
        ├── Config/              # Configuración de base de datos
        │   └── Con.java         # Clase de conexión a BD
        ├── Controller/          # Controladores de lógica
        │   ├── Login.java       # Gestión de login
        │   └── Register.java    # Gestión de registro
        ├── Model/               # Modelos de datos
        │   ├── Playlist.java    # Modelo de playlists
        │   └── Users.java       # Modelo de usuarios
        ├── Repository/          # Acceso a datos
        │   ├── BusquedaCanciones.java
        │   ├── ConsultaAlbumes.java
        │   ├── ConsultaArtistas.java
        │   ├── ConsultaCanciones.java
        │   └── ConsultaPlaylists.java
        ├── Service/             # Servicios generales
        │   └── GeneralService.java
        └── UI/                  # Interfaz de usuario
            ├── Menu.java              # Menú principal
            ├── MenuSpotify.java       # Menú Spotify
            ├── MenuAlbumes.java       # Menú de álbumes
            ├── MenuArtistas.java      # Menú de artistas
            ├── MenuCancion.java       # Menú de canciones
            └── MenuPlaylist.java      # Menú de playlists
```

---

## 💻 Requisitos del Sistema

### Requisitos Mínimos:
- **Java**: JDK 8 o superior
- **PostgreSQL**: 10 o superior
- **Sistema Operativo**: Windows, macOS o Linux
- **RAM**: 2 GB mínimo
- **Espacio en disco**: 500 MB

### Dependencias:
- Driver JDBC de PostgreSQL (postgresql-X.X.X.jar)
- Librerías de Java estándar

---

## ⚙️ Instalación y Configuración

### 1. Configuración de PostgreSQL

**Paso 1:** Instalar PostgreSQL
```bash
# En Ubuntu/Debian:
sudo apt-get install postgresql postgresql-contrib

# En macOS (usando Homebrew):
brew install postgresql

# En Windows: Descargar desde https://www.postgresql.org/download/windows/
```

**Paso 2:** Iniciar el servidor PostgreSQL
```bash
# Linux/macOS:
sudo service postgresql start

# Windows:
# El servicio se inicia automáticamente tras la instalación
```

**Paso 3:** Crear un usuario administrador (opcional)
```bash
sudo -u postgres createuser -P admin
# Contraseña: 123456
```

**Paso 4:** Importar la base de datos
```bash
psql -U admin -d postgres -f appSpoty.sql
# O desde psql:
\c postgres
\i /ruta/a/appSpoty.sql
```

### 2. Configuración de la Aplicación

**Paso 1:** Verificar credenciales en `Config/Con.java`
```java
private final static String URL = "jdbc:postgresql://localhost:5432/db_spoty";
private final static String USER = "admin";
private final static String PASS = "123456";
```

Ajusta estos valores según tu configuración de PostgreSQL.

**Paso 2:** Compilar el proyecto
```bash
cd ProjectSpoty
javac -d bin src/**/*.java
```

**Paso 3:** Ejecutar la aplicación
```bash
java -cp bin App
```

---

## 📦 Descripción de Módulos

### 🔐 **Config - Configuración**
Gestiona la conexión con la base de datos PostgreSQL.

#### `Con.java`
- **Método `getConn()`**: Establece conexión a la BD
- **Método `closeConnetion()`**: Cierra la conexión de forma segura
- **Credenciales**: Usuario: `admin`, Contraseña: `123456`

```java
public static Connection getConn() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
}
```

---

### 🎮 **Controller - Controladores**

#### `Login.java`
Gestiona la autenticación de usuarios.
- **Método `login()`**: Valida email y contraseña
- **Retorna**: Objeto `Users` si es exitoso, null si falla
- **Consulta SQL**: `SELECT * FROM musica.usuarios WHERE email = ? AND password = ?`

#### `Register.java`
Gestiona el registro de nuevos usuarios.
- Valida que el email no exista
- Inserta nuevo usuario en la BD
- Confirma el registro exitoso

---

### 📊 **Model - Modelos de Datos**

#### `Users.java`
Representa un usuario del sistema.
```java
- user_id: int          // ID único del usuario
- name: String          // Nombre del usuario
- email: String         // Email del usuario
- password: String      // Contraseña
```
**Métodos**: Getters y setters para todos los atributos, `toString()`

#### `Playlist.java`
Representa una playlist de música.
- playlist_id: int
- name_playlist: String
- description: String
- date_create: Date
- user_id: int (FK)
- canciones: List

---

### 🔍 **Repository - Acceso a Datos**

#### `BusquedaCanciones.java`
Busca canciones por título o artista.
- Consultas dinámicas en tabla `musica.canciones`
- Retorna lista de resultados

#### `ConsultaCanciones.java`
Obtiene información detallada de canciones.
- Información de canción, artista y álbum
- Consultas JOIN para datos relacionados

#### `ConsultaArtistas.java`
Busca y consulta artistas.
- Listado de artistas por género
- Información de artistas y sus álbumes

#### `ConsultaAlbumes.java`
Gestiona consultas de álbumes.
- Álbumes por artista
- Información detallada de álbumes

#### `ConsultaPlaylists.java`
Operaciones sobre playlists de usuario.
- Crear playlist
- Listar playlists del usuario
- Agregar/eliminar canciones

---

### 🛠️ **Service - Servicios Generales**

#### `GeneralService.java`
Servicios auxiliares de la aplicación.

**Métodos principales:**
- `cleanScreen()`: Limpia la pantalla de consola
- `showLoading()`: Muestra animación de carga
- `pauseMenu()`: Pausa el menú (presionar Enter)
- Funciones de validación de entrada
- Funciones de formato de salida

---

### 🎨 **UI - Interfaz de Usuario**

Todos los menús operan en modo consola interactivo.

#### `Menu.java` - Menú Principal
```
=============================
    SPOTIFY - BIENVENIDO
=============================
1. Iniciar Sesión
2. Registrarse
0. Salir
```
- Gestiona flujo de autenticación
- Redirecciona a menús según opción

#### `MenuSpotify.java` - Menú Principal de Spotify
Menú principal una vez autenticado.
- Opciones de búsqueda
- Acceso a playlists
- Navegación a submenús

#### `MenuCancion.java`
- Ver detalles de canción
- Agregar a playlist
- Reproducción simulada

#### `MenuArtistas.java`
- Listar artistas
- Ver álbumes de artista
- Información del artista

#### `MenuAlbumes.java`
- Listar álbumes
- Ver canciones del álbum
- Información detallada

#### `MenuPlaylist.java`
- Ver playlists del usuario
- Crear nueva playlist
- Agregar/eliminar canciones
- Eliminar playlist

---

## 🗄️ Base de Datos

### Diagrama de Tablas

```
┌─────────────────┐
│   USUARIOS      │
├─────────────────┤
│ user_id (PK)    │
│ user_name       │
│ email           │
│ password        │
└────────┬────────┘
         │
         ├──────────┬─────────────┬──────────────┐
         │          │             │              │
    ┌────┴─────┐ ┌──┴──────┐ ┌───┴──────┐ ┌────┴───────┐
    │ PLAYLIST  │ │ GENEROS │ │ ARTISTAS │ │ ALBUMES    │
    └──────────┘ └─────────┘ └──────────┘ └────────────┘
```

### Tablas Principales

#### **musica.usuarios**
```sql
CREATE TABLE musica.usuarios(
    user_id INT SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);
```
**Registros de ejemplo:**
- Manesco7152 / manesco7152@gmail.com
- Pacmanxddeoz / pacmanxddeoz@gmail.com

#### **musica.generos**
10 géneros musicales: Pop, Rock, Reggaeton, Hip Hop, Electrónica, Clásica, Jazz, Blues, Reggae, Metal

#### **musica.artistas**
```sql
CREATE TABLE musica.artistas(
    artist_id SERIAL PRIMARY KEY,
    name_artist TEXT,
    name_real TEXT NOT NULL,
    country TEXT,
    type_artist TEXT NOT NULL
);
```
**Tipos**: Solista, Banda, Grupo

#### **musica.albumes**
```sql
CREATE TABLE musica.albumes (
    album_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    date_release TEXT NOT NULL,
    count_songs INT,
    artist_id INT REFERENCES musica.artistas(artist_id)
);
```

#### **musica.playlist**
```sql
CREATE TABLE musica.playlist(
    playlist_id SERIAL PRIMARY KEY,
    name_playlist TEXT NOT NULL,
    description TEXT NOT NULL,
    date_create DATE NOT NULL,
    user_id INT REFERENCES musica.usuarios(user_id)
);
```

#### **musica.colaboraciones**
Registra colaboraciones entre artistas en canciones.

---

## 🚀 Guía de Uso

### 1. Inicio de la Aplicación
```
1. Ejecutar: java -cp bin App
2. Se mostrará el menú de bienvenida
```

### 2. Autenticación
```
Opción 1: Iniciar Sesión
  - Email: manesco7152@gmail.com
  - Contraseña: 123456

Opción 2: Registrarse
  - Completa el formulario
  - Recibirás confirmación
```

### 3. Explorar Música
Una vez autenticado, puedes:
- 🎵 Buscar canciones por título
- 🎤 Ver artistas y sus álbumes
- 💾 Crear y gestionar playlists
- ➕ Agregar canciones a tus playlists

### 4. Gestionar Playlists
- Crear nueva playlist
- Ver tus playlists
- Agregar canciones
- Eliminar playlists

---

## 🔧 Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| **Java** | 8+ | Lenguaje principal |
| **PostgreSQL** | 10+ | Base de datos |
| **JDBC** | Incluido | Conexión a BD |
| **SQL** | - | Consultas a BD |

---

## 📝 Notas de Desarrollo

### Puntos Importantes:
1. **Seguridad**: Las contraseñas se almacenan en texto plano (no es seguro en producción)
2. **Error Handling**: Implementado try-catch para manejo de excepciones
3. **Conexiones**: Siempre se cierran recursos en bloques finally
4. **Interfaz**: Basada en consola, no es GUI
5. **Transacciones**: No hay control transaccional explícito

### Mejoras Futuras Sugeridas:
- [ ] Hashear contraseñas con bcrypt o similar
- [ ] Implementar interfaz gráfica (Swing/JavaFX)
- [ ] Agregar validación de datos más robusta
- [ ] Implementar paginación en resultados
- [ ] Agregar sistema de puntuaciones y recomendaciones
- [ ] Implementar búsqueda full-text en BD
- [ ] Agregar historial de reproducción
- [ ] Implementar sistema de notificaciones

---

## 👥 Autores

- **Manesco7152** - Desarrollador
- **Pacmanxddeoz** - Desarrollador

**Fecha de Creación**: Diciembre 2025
**Estado**: En Desarrollo

---

## 📞 Soporte y Contacto

Para reportar problemas o sugerencias, por favor abre un issue en el repositorio.

---

## 📄 Licencia

Este proyecto es de código abierto y fue creado con fines educativos.

---

**Última actualización**: Diciembre 2025
**Versión**: 1.0.0
