# 🎓 Mejores Prácticas y Procedimientos

## 📖 Índice de Contenidos
1. [Procedimientos Comunes](#procedimientos-comunes)
2. [Mejores Prácticas Java](#mejores-prácticas-java)
3. [Mejores Prácticas BD](#mejores-prácticas-bd)
4. [Manejo de Errores](#manejo-de-errores)
5. [Testing](#testing)
6. [Performance](#performance)
7. [Seguridad](#seguridad)
8. [Documentación de Código](#documentación-de-código)

---

## 🔧 Procedimientos Comunes

### Procedimiento: Agregar Nueva Función de Búsqueda

**Paso 1:** Crear método en Repository
```java
// En Repository/BusquedaCanciones.java
public static List<Cancion> buscarPorArtista(Connection con, String artista) {
    List<Cancion> canciones = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
        String query = "SELECT c.*, a.name_artist FROM musica.canciones c " +
                      "JOIN musica.albumes al ON c.album_id = al.album_id " +
                      "JOIN musica.artistas a ON al.artist_id = a.artist_id " +
                      "WHERE LOWER(a.name_artist) LIKE LOWER(?)";
        
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + artista + "%");
        rs = ps.executeQuery();
        
        while(rs.next()) {
            Cancion cancion = new Cancion();
            // Mapear resultados
            canciones.add(cancion);
        }
    } catch(SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        // Cerrar recursos
    }
    return canciones;
}
```

**Paso 2:** Crear opción en UI Menu
```java
// En UI/MenuCancion.java
case 2:
    System.out.print("Ingresa artista: ");
    String artista = sc.nextLine();
    Connection con = Con.getConn();
    List<Cancion> resultados = BusquedaCanciones.buscarPorArtista(con, artista);
    mostrarResultados(resultados);
    break;
```

**Paso 3:** Documentar el cambio
```
Descripción: Agregar búsqueda por artista
Tabla: musica.canciones, musica.artistas
Método: BusquedaCanciones.buscarPorArtista()
Parámetros: Connection, String (nombre artista)
Retorna: List<Cancion>
```

---

### Procedimiento: Agregar Nueva Tabla

**Paso 1:** Definir esquema SQL
```sql
CREATE TABLE musica.nueva_tabla (
    id SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL,
    creado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT REFERENCES musica.usuarios(user_id) ON DELETE CASCADE
);
```

**Paso 2:** Crear modelo Java
```java
public class NuevaTabla {
    private int id;
    private String nombre;
    private Date creado;
    private int usuario_id;
    
    // Constructor, getters, setters
}
```

**Paso 3:** Crear repositorio
```java
public class ConsultaNuevaTabla {
    public static NuevaTabla obtenerPorId(Connection con, int id) {
        // Implementar lógica de consulta
    }
    
    public static void insertar(Connection con, NuevaTabla tabla) {
        // Implementar inserción
    }
}
```

**Paso 4:** Integrar en UI
```java
// Crear menu para nueva funcionalidad
public class MenuNuevaTabla {
    public static void mostrar(Users usuario) {
        // Mostrar opciones
    }
}
```

---

## 📝 Mejores Prácticas Java

### 1. Manejo de Recursos - Try-with-resources

**❌ Incorrecto:**
```java
Connection con = Con.getConn();
PreparedStatement ps = con.prepareStatement(query);
// ... código ...
ps.close();
con.close();
// No maneja excepciones en el cierre
```

**✅ Correcto:**
```java
try (Connection con = Con.getConn();
     PreparedStatement ps = con.prepareStatement(query)) {
    // ... código ...
} catch(SQLException e) {
    System.err.println("Error: " + e.getMessage());
}
// Recursos se cierran automáticamente
```

### 2. Validación de Entrada

**❌ Incorrecto:**
```java
String email = sc.nextLine();
// Usar directamente sin validar
```

**✅ Correcto:**
```java
String email = sc.nextLine().trim();
if (email == null || email.isEmpty()) {
    System.out.println("Email no puede estar vacío");
    return;
}
if (!email.contains("@")) {
    System.out.println("Email inválido");
    return;
}
```

### 3. Constantes en lugar de Strings Mágicos

**❌ Incorrecto:**
```java
ps.setString(1, "SELECT * FROM musica.usuarios...");
```

**✅ Correcto:**
```java
private static final String QUERY_GET_USER = 
    "SELECT * FROM musica.usuarios WHERE user_id = ?";

// ... en método ...
ps = connection.prepareStatement(QUERY_GET_USER);
```

### 4. Nombres Significativos

**❌ Incorrecto:**
```java
int a = 5;
String b = "test@email.com";
List c = new ArrayList();
```

**✅ Correcto:**
```java
int maxSearchResults = 5;
String userEmail = "test@email.com";
List<Cancion> canciones = new ArrayList<>();
```

### 5. Métodos Pequeños y Enfocados

**❌ Incorrecto:**
```java
public void procesarTodo(Connection con, String email, String password) {
    // 100+ líneas de código
    // Validación, login, búsqueda, etc.
}
```

**✅ Correcto:**
```java
public boolean validarEmail(String email) {
    return email.contains("@") && email.contains(".");
}

public Users autenticar(Connection con, String email, String password) {
    if (!validarEmail(email)) return null;
    return Login.login(con, email, password);
}
```

### 6. Logging vs System.out

**❌ Incorrecto:**
```java
System.out.println("Debug: valor = " + valor);
System.out.println("Error: " + excepcion.getMessage());
```

**✅ Correcto:**
```java
// Usar logger (si disponible)
logger.info("Procesando usuario: " + userId);
logger.error("Error en login", excepcion);

// O al menos:
System.err.println("Error: " + excepcion.getMessage());
System.out.println("Info: Usuario autenticado");
```

---

## 🗄️ Mejores Prácticas Base de Datos

### 1. Nunca Concatenar Strings en SQL

**❌ VULNERABLE A SQL INJECTION:**
```java
String query = "SELECT * FROM usuarios WHERE email = '" + email + "'";
```

**✅ SEGURO - Usar PreparedStatement:**
```java
String query = "SELECT * FROM usuarios WHERE email = ?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1, email);
```

### 2. Siempre Usar Transacciones

**✅ Correcto:**
```java
try {
    con.setAutoCommit(false);
    
    // Múltiples operaciones
    insertar(con, datos1);
    insertar(con, datos2);
    
    con.commit();
} catch(SQLException e) {
    con.rollback();
    throw e;
} finally {
    con.setAutoCommit(true);
}
```

### 3. Usar Índices para Búsquedas

```sql
-- Crear índices en columnas frecuentemente buscadas
CREATE INDEX idx_usuario_email ON musica.usuarios(email);
CREATE INDEX idx_cancion_titulo ON musica.canciones(titulo);
CREATE INDEX idx_playlist_usuario ON musica.playlist(user_id);
```

### 4. Validar Tipos en BD

```sql
-- ❌ Incorrecto: Sin especificar tipo
CREATE TABLE usuarios (id, nombre, edad);

-- ✅ Correcto: Con tipos definidos
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INT CHECK (edad > 0)
);
```

### 5. Usar Constraints de Integridad

```sql
-- NOT NULL
ALTER TABLE usuarios ALTER COLUMN email SET NOT NULL;

-- UNIQUE
ALTER TABLE usuarios ADD CONSTRAINT email_unique UNIQUE(email);

-- CHECK
ALTER TABLE albumes ADD CONSTRAINT year_valid 
    CHECK (date_release >= '1900-01-01');

-- DEFAULT
ALTER TABLE usuarios ALTER COLUMN created_at 
    SET DEFAULT CURRENT_TIMESTAMP;
```

### 6. Documentar Relaciones

```sql
-- Comentar el propósito de las tablas
COMMENT ON TABLE musica.playlist IS 
'Playlists creadas por usuarios con sus canciones favoritas';

COMMENT ON COLUMN musica.playlist.user_id IS 
'Referencia al usuario propietario de la playlist';
```

---

## 🚨 Manejo de Errores

### Estructura Try-Catch-Finally Correcta

```java
Connection connection = null;
PreparedStatement ps = null;
ResultSet rs = null;

try {
    // Paso 1: Obtener conexión
    connection = Con.getConn();
    if (connection == null) {
        throw new SQLException("No se pudo obtener conexión");
    }
    
    // Paso 2: Preparar consulta
    String query = "SELECT * FROM musica.usuarios WHERE user_id = ?";
    ps = connection.prepareStatement(query);
    ps.setInt(1, userId);
    
    // Paso 3: Ejecutar
    rs = ps.executeQuery();
    
    // Paso 4: Procesar resultados
    if (rs.next()) {
        return mapearUsuario(rs);
    }
    return null;
    
} catch (SQLException e) {
    System.err.println("Error SQL: " + e.getMessage());
    System.err.println("Estado SQL: " + e.getSQLState());
    System.err.println("Código Error: " + e.getErrorCode());
    e.printStackTrace();
    return null;
    
} catch (NullPointerException e) {
    System.err.println("Error de null pointer: " + e.getMessage());
    e.printStackTrace();
    return null;
    
} finally {
    // Cerrar recursos en orden inverso (RS -> PS -> Connection)
    if (rs != null) {
        try { rs.close(); } 
        catch (SQLException e) { 
            System.err.println("Error cerrando ResultSet: " + e.getMessage());
        }
    }
    
    if (ps != null) {
        try { ps.close(); } 
        catch (SQLException e) { 
            System.err.println("Error cerrando PreparedStatement: " + e.getMessage());
        }
    }
    
    Con.closeConnetion(connection);
}
```

### Errores Comunes y Manejo

| Error | Causa | Solución |
|-------|-------|----------|
| `SQLException: connection refused` | BD no activa | Iniciar PostgreSQL |
| `SQLSyntaxErrorException` | SQL inválido | Revisar sintaxis SQL |
| `NullPointerException` | Variable null | Validar antes de usar |
| `InputMismatchException` | Input no esperado | Validar entrada usuario |
| `NoClassDefFoundError` | Classpath incorrecto | Revisar compilación |

---

## ✅ Testing

### Crear Casos de Test

```java
public class LoginTest {
    static Connection connection;
    
    @BeforeClass
    public static void setUp() throws SQLException {
        connection = Con.getConn();
    }
    
    @Test
    public void testLoginValido() {
        Users usuario = Login.login(connection, 
            "manesco7152@gmail.com", "123456");
        assertNotNull(usuario);
        assertEquals("Manesco7152", usuario.getName());
    }
    
    @Test
    public void testLoginInvalido() {
        Users usuario = Login.login(connection, 
            "invalido@email.com", "contraseña");
        assertNull(usuario);
    }
    
    @Test
    public void testEmailVacio() {
        Users usuario = Login.login(connection, "", "123456");
        assertNull(usuario);
    }
    
    @AfterClass
    public static void tearDown() throws SQLException {
        Con.closeConnetion(connection);
    }
}
```

### Pruebas Manuales

```
1. Login correcto
   - Credenciales: manesco7152@gmail.com / 123456
   - Esperado: Acceso a MenuSpotify

2. Login incorrecto
   - Credenciales: invalido@test.com / 123456
   - Esperado: Mensaje de error, volver a menú

3. Registro nuevo usuario
   - Correo no registrado
   - Esperado: Registro exitoso

4. Búsqueda de canciones
   - Término: "michael"
   - Esperado: Resultados con Michael Jackson

5. Crear playlist
   - Nombre: "Test Playlist"
   - Esperado: Playlist creada exitosamente
```

---

## ⚡ Performance

### Optimizaciones

1. **Usar Índices**
```sql
CREATE INDEX idx_email ON musica.usuarios(email);
-- Búsquedas por email ahora son más rápidas
```

2. **Limitar Resultados**
```sql
SELECT * FROM musica.canciones 
WHERE titulo LIKE '%test%'
LIMIT 50;
-- Evita cargar miles de registros
```

3. **Paginación**
```java
int pagina = 1;
int tamanoPagina = 20;
int offset = (pagina - 1) * tamanoPagina;

String query = "SELECT * FROM musica.canciones LIMIT ? OFFSET ?";
ps.setInt(1, tamanoPagina);
ps.setInt(2, offset);
```

4. **Connection Pooling**
```java
// En lugar de crear conexión cada vez:
// Usar pool de conexiones (HikariCP, etc.)
```

5. **Caché de Datos**
```java
// Guardar datos consultados frecuentemente
private static Map<Integer, Artista> artistasCache = new HashMap<>();

public static Artista obtenerArtista(int id) {
    if (artistasCache.containsKey(id)) {
        return artistasCache.get(id);
    }
    // Consultar BD si no está en caché
}
```

---

## 🔐 Seguridad

### 1. Proteger Contraseñas

**❌ Actual (No Seguro):**
```sql
INSERT INTO usuarios VALUES ('user', 'email@test.com', 'micontraseña123');
```

**✅ Recomendado:**
```java
// Usar bcrypt
String hashed = BCrypt.hashpw("micontraseña123", BCrypt.gensalt());
// Guardar: hashed
```

### 2. Validar Entrada

```java
public boolean validarEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
}

public boolean validarPassword(String password) {
    return password.length() >= 8 &&
           password.matches(".*[A-Z].*") &&  // Mayúscula
           password.matches(".*[0-9].*");    // Número
}
```

### 3. Usar HTTPS en Conexiones

```java
// Para futuras conexiones a APIs
SSLContext ssl = SSLContext.getInstance("TLSv1.2");
ssl.init(null, null, null);
```

### 4. No Logear Información Sensible

**❌ Incorrecto:**
```java
System.out.println("Login: " + email + " / " + password);
```

**✅ Correcto:**
```java
System.out.println("Intento de login para usuario");
```

### 5. Control de Acceso

```java
// Verificar que usuario tenga acceso a sus propios datos
public boolean validarAcceso(Users usuario, int playlistId) {
    Playlist playlist = ConsultaPlaylists.obtenerPorId(playlistId);
    return playlist.getUserId() == usuario.getUser_id();
}
```

---

## 📚 Documentación de Código

### 1. Javadoc

```java
/**
 * Autentica un usuario contra la base de datos.
 * 
 * @param connection Conexión activa a la base de datos
 * @param email Email del usuario (validado previamente)
 * @param password Contraseña del usuario
 * @return Objeto Users si autenticación es exitosa, null si falla
 * @throws SQLException Si ocurre error en la conexión
 * 
 * @example
 * Users user = Login.login(connection, "test@email.com", "password123");
 * if (user != null) {
 *     System.out.println("Bienvenido " + user.getName());
 * }
 */
public static Users login(Connection connection, 
                         String email, 
                         String password) throws SQLException {
    // ... implementación ...
}
```

### 2. Comentarios Inline

```java
// ✅ Útil
// Usar PreparedStatement para evitar SQL Injection
PreparedStatement ps = con.prepareStatement(query);

// ❌ Inútil
// Incrementar contador
i++;
```

### 3. Comentarios TODO/FIXME

```java
// TODO: Implementar búsqueda full-text en futuro
// FIXME: Esta validación no funciona con emails internacionales
// NOTE: Se debe revisar esta lógica después de que se implemente X
```

### 4. README de Módulo

```
// En cada paquete crear README.md
Repository/
├── README.md
├── BusquedaCanciones.java
├── ConsultaArtistas.java
└── ...

# Repository - Acceso a Datos

Este paquete contiene todas las clases responsables de
acceder a la base de datos.

## Clases

- **BusquedaCanciones**: Búsquedas de canciones
- **ConsultaArtistas**: Consultas de artistas
- **ConsultaAlbumes**: Operaciones con álbumes
- **ConsultaCanciones**: Detalles de canciones
- **ConsultaPlaylists**: Gestión de playlists

## Ejemplo de Uso

```java
Connection con = Con.getConn();
List<Cancion> resultados = BusquedaCanciones.buscar(con, "michael");
```
```

---

## 📋 Checklist de Calidad de Código

- [ ] Código compilable sin warnings
- [ ] No hay variables no utilizadas
- [ ] Nombres de clases/métodos significativos
- [ ] Máximo 20 líneas por método
- [ ] Manejo de excepciones completo
- [ ] Recursos siempre cerrados
- [ ] Validación de entrada de usuario
- [ ] Preparadas contra SQL Injection
- [ ] Documentado con comentarios
- [ ] Código probado manualmente
- [ ] Sin código duplicado
- [ ] Sigue convenciones Java

---

**Última actualización**: Diciembre 2025
**Versión**: 1.0.0
