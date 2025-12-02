# 🎵 App-ProjectSpoty

Una aplicación de prueba hecha por estudiantes simulando una interfaz completa de **Spotify** utilizando **Java** y **PostgreSQL**.

## 📚 Documentación

Este proyecto cuenta con documentación completa y detallada:

### 📖 Documentos Disponibles:

1. **[GUIA_RAPIDA.md](GUIA_RAPIDA.md)** ⚡
   - Inicio rápido en 5 minutos
   - Solución de problemas comunes
   - Credenciales y datos de prueba
   - Tips y trucos

2. **[DOCUMENTACION.md](DOCUMENTACION.md)** 📚
   - Descripción completa del proyecto
   - Estructura de directorios
   - Descripción de todos los módulos
   - Base de datos y tablas
   - Guía detallada de uso
   - Tecnologías utilizadas

3. **[ARQUITECTURA.md](ARQUITECTURA.md)** 🏗️
   - Diagrama arquitectónico (MVC)
   - Flujos de ejecución detallados
   - Esquema de base de datos
   - Patrones de código
   - Ejemplos de consultas SQL
   - Consideraciones de seguridad

---

## 🚀 Inicio Rápido

### Requisitos Previos:
- Java JDK 8+
- PostgreSQL 10+

### Instalación (3 pasos):

```bash
# 1. Importar base de datos
psql -U postgres -f appSpoty.sql

# 2. Compilar proyecto
cd ProjectSpoty
javac -d bin -sourcepath src src/**/*.java

# 3. Ejecutar
java -cp bin App
```

### Credenciales de Prueba:
```
Email: manesco7152@gmail.com
Contraseña: 123456
```

---

## ✨ Características

- ✅ **Autenticación**: Login y registro de usuarios
- ✅ **Búsqueda**: Canciones, artistas y álbumes
- ✅ **Playlists**: Crear y gestionar playlists personalizadas
- ✅ **Base de Datos**: PostgreSQL con 10+ tablas
- ✅ **Interfaz**: Menús interactivos en consola
- ✅ **Arquitectura**: Patrón MVC limpio y modular

---

## 📊 Estructura del Proyecto

```
App-ProjectSpoty/
├── DOCUMENTACION.md          📚 Documentación completa
├── ARQUITECTURA.md           🏗️ Guía técnica
├── GUIA_RAPIDA.md           ⚡ Inicio rápido
├── README.md                 📖 Este archivo
├── appSpoty.sql             🗄️ Script base de datos
└── ProjectSpoty/
    └── src/
        ├── App.java              # Punto de entrada
        ├── Config/               # Conexión BD
        ├── Controller/           # Lógica de negocio
        ├── Model/                # Modelos de datos
        ├── Repository/           # Acceso a datos
        ├── Service/              # Servicios auxiliares
        └── UI/                   # Interfaz de usuario
```

---

## 🎯 Próximos Pasos

1. Lee **[GUIA_RAPIDA.md](GUIA_RAPIDA.md)** para empezar en 5 minutos
2. Consulta **[DOCUMENTACION.md](DOCUMENTACION.md)** para detalles completos
3. Revisa **[ARQUITECTURA.md](ARQUITECTURA.md)** para entender el diseño técnico

---

## 👥 Autores

- **Manesco7152** - Desarrollador
- **Pacmanxddeoz** - Desarrollador

**Fecha**: Diciembre 2025

---

## 📝 Licencia

Proyecto de código abierto con fines educativos.

---

## 💡 Necesitas ayuda?

- **Problemas de instalación**: Revisa GUIA_RAPIDA.md
- **Detalles del código**: Consulta DOCUMENTACION.md
- **Arquitectura y flujos**: Ver ARQUITECTURA.md
