# 🏗️ Guía Técnica y Arquitectura - App ProjectSpoty

## 📐 Arquitectura del Proyecto

### Patrón MVC (Model-View-Controller)

```
┌─────────────────────────────────────────────────────────────────┐
│                        APLICACIÓN JAVA                          │
└─────────────────────────────────────────────────────────────────┘
                              │
                ┌─────────────┼─────────────┐
                │             │             │
        ┌───────▼──────┐ ┌────▼────┐ ┌─────▼──────┐
        │     VIEW     │ │ CONTROL  │ │   MODEL    │
        │     (UI)     │ │          │ │            │
        ├──────────────┤ ├──────────┤ ├────────────┤
        │ Menu.java    │ │ Login.ja │ │ Users.java │
        │ MenuSpotify  │ │ Register │ │ Playlist   │
        │ MenuCancion  │ │          │ │            │
        │ MenuArtistas │ │          │ │            │
        │ MenuAlbumes  │ │          │ │            │
        │ MenuPlaylist │ │          │ │            │
        └──────────────┘ └──────────┘ └────────────┘
                │             │             │
                └─────────────┼─────────────┘
                              │
                   ┌──────────▼──────────┐
                   │    REPOSITORY       │
                   │  (Data Access)      │
                   ├─────────────────────┤
                   │ BusquedaCanciones   │
                   │ ConsultaArtistas    │
                   │ ConsultaAlbumes     │
                   │ ConsultaCanciones   │
                   │ ConsultaPlaylists   │
                   └──────────┬──────────┘
                              │
                   ┌──────────▼──────────┐
                   │   CONFIGURACIÓN     │
                   │  (Database Config)  │
                   ├─────────────────────┤
                   │  Con.java           │
                   │  - Connection       │
                   │  - Close            │
                   └──────────┬──────────┘
                              │
                   ┌──────────▼──────────┐
                   │   PostgreSQL DB     │
                   │   db_spoty          │
                   └─────────────────────┘
```

---

## 🔄 Flujo de Ejecución

### 1. Inicio de la Aplicación

```
START
  │
  ▼
┌─────────────────────────┐
│ App.main()              │
│ - Carga controladores   │
│ - Muestra menú principal│
└────────┬────────────────┘
         │
    ┌────▼─────────────────────────────────┐
    │ Menu.mostrarMenuPrincipal()          │
    └────┬──────────┬─────────┬────────────┘
         │          │         │
    ┌────▼──┐  ┌───▼──┐  ┌───▼──┐
    │Login  │  │Register  │Exit
    └────┬──┘  └───┬──┘  └───┬──┘
         │         │         │
    ┌────▼─────────▼──┐      │
    │ MenuSpotify()   │      │
    │ (menú principal)│      │
    └────┬───────────┘       │
         │                   │
    ┌────▼──────────────────┐│
    │ • Buscar Canciones    ││
    │ • Ver Artistas        ││
    │ • Ver Álbumes         ││
    │ • Gestionar Playlists ││
    │ • Salir               ││
    └───────────────────────┘│
                             │
         ┌───────────────────┴──┐
         │                      │
    ┌────▼──┐             ┌─────▼──┐
    │ LOOP  │             │  END   │
    └───────┘             └────────┘
```

### 2. Flujo de Login

```
┌──────────────────────────────────────────┐
│ iniciarSesion()                          │
├──────────────────────────────────────────┤
│ 1. Solicita email y contraseña           │
│ 2. Valida entrada de usuario             │
│ 3. Llama a Login.login()                 │
└────────┬─────────────────────────────────┘
         │
    ┌────▼─────────────────────────────────┐
    │ Login.login(connection, email, pass) │
    ├──────────────────────────────────────┤
    │ 1. Obtiene conexión a BD             │
    │ 2. Prepara consulta SQL              │
    │ 3. Ejecuta query                     │
    │ 4. Verifica credenciales             │
    └────┬─────────────────────────────┬───┘
         │ ✓ Exitoso                  │ ✗ Fallo
    ┌────▼─────────────────┐  ┌───────▼──────────┐
    │ Crea objeto Users    │  │ Retorna null     │
    │ Retorna usuario      │  │ Imprime error    │
    └────┬─────────────────┘  └───────┬──────────┘
         │                            │
    ┌────▼─────────────────┐  ┌───────▼──────────┐
    │ Acceso a MenuSpotify │  │ Vuelve al menú   │
    │ (usuario autenticado)│  │ principal        │
    └──────────────────────┘  └──────────────────┘
```

### 3. Flujo de Búsqueda de Canciones

```
┌────────────────────────────────────────────┐
│ MenuCancion.buscarCanciones()              │
├────────────────────────────────────────────┤
│ 1. Solicita término de búsqueda            │
│ 2. Valida entrada                          │
└────────┬─────────────────────────────────┘
         │
    ┌────▼──────────────────────────────────┐
    │ BusquedaCanciones.buscar()             │
    ├───────────────────────────────────────┤
    │ Conecta a BD                          │
    │ Ejecuta SQL:                          │
    │ SELECT * FROM musica.canciones        │
    │ WHERE titulo LIKE '%' || ? || '%'     │
    └────┬───────────────────────────────┬──┘
         │ Resultados encontrados        │ Sin resultados
    ┌────▼────────────────────┐  ┌──────▼─────────────┐
    │ Retorna List<Cancion>   │  │ Retorna lista vacía│
    └────┬────────────────────┘  └──────┬─────────────┘
         │                               │
    ┌────▼────────────────────┐  ┌──────▼─────────────┐
    │ Muestra resultados      │  │ Mensaje "No hay    │
    │ • Canción               │  │ resultados"        │
    │ • Artista               │  └────────────────────┘
    │ • Álbum                 │
    │ • Duración              │
    │ Opción: Agregar a       │
    │ playlist o volver       │
    └────────────────────────┘
```

---

## 💾 Esquema de Base de Datos Detallado

### Relaciones y Claves Foráneas

```sql
-- USUARIOS
usuarios (PK: user_id)
├── Relación 1:N con playlist

-- GENEROS
generos (PK: gender_id)
├── Relación N:N con artistas (a través de tabla intermedia)

-- ARTISTAS
artistas (PK: artist_id)
├── Relación 1:N con albumes
├── Relación N:N con generos
└── Relación N:N con colaboraciones

-- ALBUMES
albumes (PK: album_id)
├── FK: artist_id → artistas
├── Relación 1:N con canciones

-- CANCIONES (inferida de estructura)
canciones
├── FK: album_id → albumes

-- PLAYLIST
playlist (PK: playlist_id)
├── FK: user_id → usuarios
└── Relación N:N con canciones

-- COLABORACIONES
colaboraciones
├── FK: artist_id → artistas
└── FK: colaborador_id → artistas
```

---

## 🔌 Flujo de Conexión a Base de Datos

```java
┌─────────────────────────────────────┐
│ Solicitud de Conexión               │
└────────┬────────────────────────────┘
         │
    ┌────▼─────────────────────────────┐
    │ Con.getConn()                    │
    ├──────────────────────────────────┤
    │ URL: jdbc:postgresql://localhost │
    │      :5432/db_spoty              │
    │ USER: admin                      │
    │ PASS: 123456                     │
    └────┬─────────────────────────────┘
         │
    ┌────▼─────────────────────────────┐
    │ DriverManager.getConnection()    │
    └────┬──────────────────┬──────────┘
         │ ✓ Éxito          │ ✗ Error
    ┌────▼────────────┐  ┌──▼──────────────┐
    │ Retorna         │  │ SQLException    │
    │ Connection      │  │ "Driver no      │
    │ (válida)        │  │  encontrado"    │
    │                 │  │                 │
    │ Se usa en       │  │ Propaga excepción
    │ PreparedState   │  │                 │
    │ ment, ResultSet │  │                 │
    └────┬────────────┘  └──┬──────────────┘
         │                  │
    ┌────▼──────────────────▼──────────┐
    │ Con.closeConnetion()             │
    │ - Cierra recursos                │
    │ - Maneja excepciones             │
    └──────────────────────────────────┘
```

---

## 📝 Patrones de Código

### 1. Patrón de Conexión Segura

```java
Connection connection = null;
PreparedStatement ps = null;
ResultSet resultSet = null;

try {
    connection = Con.getConn();
    String query = "SELECT * FROM tabla WHERE condicion = ?";
    ps = connection.prepareStatement(query);
    ps.setString(1, valor);
    resultSet = ps.executeQuery();
    
    // Procesar resultados
    
} catch (SQLException e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
} finally {
    // Siempre cerrar recursos
    try {
        if (resultSet != null) resultSet.close();
        if (ps != null) ps.close();
    } catch (SQLException e) {
        System.err.println("Error al cerrar: " + e.getMessage());
    }
    Con.closeConnetion(connection);
}
```

### 2. Patrón de Validación de Entrada

```java
Scanner sc = new Scanner(System.in);
String entrada = sc.nextLine();

// Validar que no esté vacío
if (entrada == null || entrada.trim().isEmpty()) {
    System.out.println("Por favor ingresa un valor válido");
    return;
}

// Validar formato email
if (!entrada.contains("@") || !entrada.contains(".")) {
    System.out.println("Email inválido");
    return;
}
```

### 3. Patrón de Consulta Parametrizada

```java
// ✓ CORRECTO - Previene SQL Injection
String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, email);
ps.setString(2, password);
ResultSet rs = ps.executeQuery();

// ✗ INCORRECTO - Vulnerable
String query = "SELECT * FROM usuarios WHERE email = '" + email + 
               "' AND password = '" + password + "'";
```

---

## 🐛 Manejo de Errores

### Tipos de Excepciones Manejadas

| Excepción | Ubicación | Causa |
|-----------|-----------|-------|
| `SQLException` | Config, Controllers | Error de BD |
| `ClassNotFoundException` | Config | Driver no encontrado |
| `InputMismatchException` | UI | Input inválido |
| `NullPointerException` | Controllers | Objeto null |

### Estrategia de Manejo

```
Excepción Capturada
    │
    ├─► Sistema.err.println(mensaje)
    ├─► e.printStackTrace() (para debug)
    └─► Retornar null o valor por defecto
                    │
                    ▼
            Recuperación Grácil
            (volver al menú)
```

---

## 📊 Ejemplos de Consultas SQL Utilizadas

### 1. Login
```sql
SELECT * FROM musica.usuarios 
WHERE email = ? AND password = ?
```

### 2. Registrar Usuario
```sql
INSERT INTO musica.usuarios(user_name, email, password)
VALUES (?, ?, ?)
```

### 3. Buscar Canciones
```sql
SELECT c.*, a.name_artist, al.title as album_title
FROM musica.canciones c
JOIN musica.albumes al ON c.album_id = al.album_id
JOIN musica.artistas a ON al.artist_id = a.artist_id
WHERE c.titulo LIKE '%' || ? || '%'
```

### 4. Listar Álbumes por Artista
```sql
SELECT a.*, ar.name_artist
FROM musica.albumes a
JOIN musica.artistas ar ON a.artist_id = ar.artist_id
WHERE ar.artist_id = ?
```

### 5. Crear Playlist
```sql
INSERT INTO musica.playlist(name_playlist, description, date_create, user_id)
VALUES (?, ?, CURRENT_DATE, ?)
```

### 6. Agregar Canción a Playlist (tabla intermedia)
```sql
INSERT INTO musica.playlist_canciones(playlist_id, cancion_id)
VALUES (?, ?)
```

---

## 🚀 Compilación y Ejecución

### Compilar Todo el Proyecto

```bash
cd ProjectSpoty

# Compilar con estructura de directorios
javac -d bin -sourcepath src src/**/*.java

# O compilar archivo por archivo
javac -d bin src/App.java
javac -d bin -cp bin src/Config/Con.java
javac -d bin -cp bin src/Model/*.java
javac -d bin -cp bin src/Controller/*.java
javac -d bin -cp bin src/Repository/*.java
javac -d bin -cp bin src/Service/*.java
javac -d bin -cp bin src/UI/*.java
```

### Ejecutar la Aplicación

```bash
# Asegúrate de tener JDBC Driver en el classpath
java -cp bin:lib/postgresql-jdbc.jar App

# O simplemente:
java -cp bin App
```

### Limpiar Compilación

```bash
rm -rf bin/*
```

---

## 📈 Diagrama de Clases

```
┌─────────────────────────┐
│        Users            │
├─────────────────────────┤
│ - user_id: int          │
│ - name: String          │
│ - email: String         │
│ - password: String      │
├─────────────────────────┤
│ + getUser_id()          │
│ + setUser_id()          │
│ + getName()             │
│ + setName()             │
│ + getEmail()            │
│ + setEmail()            │
│ + getPassword()         │
│ + setPassword()         │
│ + toString()            │
└─────────────────────────┘

┌─────────────────────────┐
│      Playlist           │
├─────────────────────────┤
│ - playlist_id: int      │
│ - name: String          │
│ - description: String   │
│ - date_create: Date     │
│ - user_id: int          │
├─────────────────────────┤
│ + getPlaylistId()       │
│ + setPlaylistId()       │
│ + getName()             │
│ + setName()             │
│ + ... (getters/setters) │
└─────────────────────────┘

┌─────────────────────────┐
│       Login             │
├─────────────────────────┤
│ (static methods)        │
├─────────────────────────┤
│ + login(Connection,     │
│   String, String)       │
│   : Users               │
└─────────────────────────┘

┌─────────────────────────┐
│      Register           │
├─────────────────────────┤
│ (static methods)        │
├─────────────────────────┤
│ + register(Connection,  │
│   String, String)       │
│   : boolean             │
└─────────────────────────┘
```

---

## 🔐 Consideraciones de Seguridad

### Vulnerabilidades Conocidas:

1. **Contraseñas en Texto Plano**
   - ❌ Actual: `password TEXT NOT NULL`
   - ✅ Recomendado: Usar hash (bcrypt, SHA-256)

2. **SQL Injection**
   - ✅ Mitigado: Uso de PreparedStatement
   - ✅ Parametrización de queries

3. **Sesiones**
   - ❌ No hay token de sesión
   - ❌ No hay expiración de sesión
   - ✅ Recomendado: JWT o sesiones con servidor

4. **Validación de Entrada**
   - ⚠️ Validación básica
   - ✅ Recomendado: Más exhaustiva en frontend

---

## 📚 Recursos Adicionales

### Documentación Oficial:
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Java Collections](https://docs.oracle.com/javase/tutorial/collections/)

### Herramientas Útiles:
- **pgAdmin**: Interfaz gráfica para PostgreSQL
- **DBeaver**: Cliente universal de bases de datos
- **IntelliJ IDEA**: IDE recomendado para Java

---

**Última actualización**: Diciembre 2025
**Versión**: 1.0.0
