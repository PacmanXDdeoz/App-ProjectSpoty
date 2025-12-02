# ⚡ Guía Rápida - App ProjectSpoty

## 🚀 Inicio Rápido (5 minutos)

### 1. Verificar Requisitos
```bash
# Verificar Java
java -version

# Verificar PostgreSQL
psql --version
```

### 2. Configurar Base de Datos
```bash
# Conectarse a PostgreSQL
psql -U postgres

# Crear usuario admin (si no existe)
CREATE USER admin WITH PASSWORD '123456';
ALTER USER admin CREATEDB;

# Importar base de datos
\q  # Salir de psql

psql -U admin -f appSpoty.sql
```

### 3. Compilar y Ejecutar
```bash
cd ProjectSpoty
javac -d bin -sourcepath src src/**/*.java
java -cp bin App
```

---

## 🎮 Guía de Uso del Menú

### Menú Principal
```
1. Iniciar Sesión
2. Registrarse
0. Salir
```

**Credenciales de Prueba:**
- Email: `manesco7152@gmail.com`
- Contraseña: `123456`

---

## 📊 Datos de Ejemplo en la BD

### Usuarios Registrados:
- **manesco7152** - manesco7152@gmail.com
- **Pacmanxddeoz** - pacmanxddeoz@gmail.com

### Artistas (10):
- Michael Jackson, Alan Walker, Bad Bunny
- Imagine Dragons, Shakira, Coldplay
- BTS, Queen, Metallica, The Weeknd

### Géneros (10):
Pop, Rock, Reggaeton, Hip Hop, Electrónica, Clásica, Jazz, Blues, Reggae, Metal

### Álbumes:
20 álbumes de los artistas anteriores

### Playlists de Ejemplo:
- Favoritas de Manesco
- Electro Hits
- Fiesta Total
- Clásicos Eternos

---

## 🔧 Solución de Problemas

### Error: "Connection refused"
```
Solución:
1. Verificar que PostgreSQL esté iniciado
   - Linux: sudo service postgresql start
   - macOS: brew services start postgresql
2. Verificar credenciales en Config/Con.java
3. Asegurar que la BD existe: createdb db_spoty
```

### Error: "Driver no encontrado"
```
Solución:
1. Asegurar que postgresql-jdbc.jar esté en el classpath
2. Descargar desde: https://jdbc.postgresql.org/download/
3. Compilar con: javac -cp postgresql-jdbc.jar ...
```

### Error: "Usuario no existe"
```
Solución:
1. Verificar que se importó appSpoty.sql
2. Conectarse a la BD: psql -U admin -d db_spoty
3. Verificar: SELECT * FROM musica.usuarios;
```

---

## 📋 Checklist de Configuración

- [ ] PostgreSQL instalado y ejecutándose
- [ ] Base de datos db_spoty creada
- [ ] Usuario admin con contraseña 123456
- [ ] appSpoty.sql importado correctamente
- [ ] Credenciales en Config/Con.java son correctas
- [ ] JDBC Driver descargado (si es necesario)
- [ ] Proyecto compilado sin errores
- [ ] Aplicación ejecutándose correctamente

---

## 💡 Tips y Trucos

1. **Limpiar Pantalla**: Muchos menús incluyen función de limpiar pantalla
2. **Volver al Menú**: Generalmente con la opción "0" o "Volver"
3. **Búsqueda**: Usa palabras clave parciales (ej: "michael" encuentra "Michael Jackson")
4. **Playlists**: Crea playlists propias y agrega tus canciones favoritas
5. **Debug**: Revisa los mensajes de error en consola para identificar problemas

---

## 🎯 Casos de Uso Comunes

### Buscar una Canción
```
1. Ir a Menú Principal
2. Iniciar sesión
3. Ir a "Buscar Canciones"
4. Escribir término de búsqueda
5. Seleccionar canción
6. Ver detalles o agregar a playlist
```

### Crear una Playlist
```
1. Estar autenticado
2. Ir a "Gestionar Playlists"
3. Seleccionar "Crear Playlist"
4. Ingresar nombre y descripción
5. Agregar canciones
6. Confirmar
```

### Ver Álbumes de un Artista
```
1. Ir a "Artistas"
2. Buscar artista
3. Ver sus álbumes
4. Seleccionar álbum
5. Ver canciones del álbum
```

---

## 📞 Estructura de Carpetas a Conocer

```
ProjectSpoty/src/
├── App.java ..................... Punto de entrada
├── Config/Con.java .............. Conexión BD
├── Controller/ .................. Lógica de negocio
├── Model/ ....................... Objetos de datos
├── Repository/ .................. Consultas BD
├── Service/ ..................... Funciones auxiliares
└── UI/ .......................... Menús y interfaz
```

---

## 🔑 Credenciales por Defecto

```
Base de Datos:
- Host: localhost
- Puerto: 5432
- BD: db_spoty
- Usuario: admin
- Contraseña: 123456

Usuarios de Prueba:
- manesco7152 / 123456
- Pacmanxddeoz / 123456
```

**⚠️ Cambiar estos valores en producción**

---

## 📱 Características Principales

✅ Autenticación de usuarios  
✅ Búsqueda de canciones, artistas y álbumes  
✅ Gestión de playlists  
✅ Base de datos relacional  
✅ Interfaz de consola interactiva  
✅ Manejo de errores robusto  

---

## 🚨 Errores Comunes y Soluciones

| Error | Causa | Solución |
|-------|-------|----------|
| SQLException: "no such table" | Tabla no existe | Importar appSpoty.sql |
| Connection refused | PostgreSQL no está iniciado | Iniciar PostgreSQL |
| ClassNotFoundException | Driver JDBC no encontrado | Descargar driver PostgreSQL |
| Credenciales incorrectas | Email/contraseña incorrectos | Revisar datos en BD |
| null pointer exception | Recursos no inicializados | Verificar conexión a BD |

---

## 📈 Próximos Pasos

1. ✅ Instalar y configurar PostgreSQL
2. ✅ Importar la base de datos
3. ✅ Compilar la aplicación
4. ✅ Probar con credenciales de ejemplo
5. ✅ Crear tu propio usuario
6. ✅ Explorar funcionalidades
7. 🔄 Proponer mejoras al equipo

---

**Última actualización**: Diciembre 2025  
**Versión**: 1.0.0

---

¿Necesitas ayuda? Revisa DOCUMENTACION.md o ARQUITECTURA.md para información más detallada.
