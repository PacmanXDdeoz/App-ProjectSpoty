# 🗄️ Diagrama Entidad-Relación y Esquema de BD

## 📊 Diagrama Entidad-Relación (ER)

```
┌──────────────────────┐
│     USUARIOS         │
├──────────────────────┤
│ PK user_id (SERIAL)  │
│    user_name         │
│    email (UNIQUE)    │
│    password          │
└──────────┬───────────┘
           │ 1:N
           │
           ▼
┌──────────────────────┐
│     PLAYLIST         │
├──────────────────────┤
│ PK playlist_id       │
│    name_playlist     │
│    description       │
│    date_create       │
│ FK user_id           │
└──────────┬───────────┘
           │ N:N
           │
           ▼
┌──────────────────────────┐
│ PLAYLIST_CANCIONES       │
│ (tabla intermedia)       │
├──────────────────────────┤
│ FK playlist_id           │
│ FK cancion_id            │
│ date_added (opcional)    │
└──────────────────────────┘
           ▲
           │ N:N
           │
┌──────────┴──────────────────┐
│    CANCIONES                │
├─────────────────────────────┤
│ PK cancion_id (SERIAL)      │
│    titulo                   │
│    duracion                 │
│    numero_pista             │
│ FK album_id                 │
└──────────┬──────────────────┘
           │ N:1
           │
           ▼
┌──────────────────────────────┐
│      ALBUMES                 │
├──────────────────────────────┤
│ PK album_id (SERIAL)         │
│    title                     │
│    date_release              │
│    count_songs               │
│ FK artist_id                 │
└──────────┬───────────────────┘
           │ N:1
           │
           ▼
┌──────────────────────────────┐
│      ARTISTAS                │
├──────────────────────────────┤
│ PK artist_id (SERIAL)        │
│    name_artist               │
│    name_real                 │
│    country                   │
│    type_artist               │
└──────────┬───────────────────┘
           │ N:N
           │
           ▼
┌──────────────────────────────┐
│   COLABORACIONES             │
│ (tabla intermedia)           │
├──────────────────────────────┤
│ FK artist_id_1               │
│ FK artist_id_2               │
│    year_collaboration        │
└──────────────────────────────┘

┌──────────────────────────────┐
│      GENEROS                 │
├──────────────────────────────┤
│ PK gender_id (SERIAL)        │
│    gender                    │
└──────────┬───────────────────┘
           │ N:N
           │
           ▼
┌──────────────────────────────┐
│   ARTISTA_GENERO             │
│ (tabla intermedia)           │
├──────────────────────────────┤
│ FK artist_id                 │
│ FK gender_id                 │
└──────────────────────────────┘
```

---

## 📋 Definición Detallada de Tablas

### 1. USUARIOS
```sql
CREATE TABLE musica.usuarios(
    user_id INT SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Registros:**
| user_id | user_name | email | password |
|---------|-----------|-------|----------|
| 1 | Manesco7152 | manesco7152@gmail.com | 123456 |
| 2 | Pacmanxddeoz | pacmanxddeoz@gmail.com | 123456 |

**Cardinalidad:**
- 1 usuario : N playlists
- Cada usuario puede tener múltiples playlists

---

### 2. GENEROS
```sql
CREATE TABLE musica.generos(
    gender_id SERIAL PRIMARY KEY,
    gender TEXT NOT NULL
);
```

**Registros (10):**
```
1. Pop
2. Rock
3. Reggaeton
4. Hip Hop
5. Electronica
6. Clasica
7. Jazz
8. Blues
9. Reggae
10. Metal
```

**Cardinalidad:**
- 1 género : N artistas
- Cada artista puede pertenecer a varios géneros (N:N)

---

### 3. ARTISTAS
```sql
CREATE TABLE musica.artistas(
    artist_id SERIAL PRIMARY KEY,
    name_artist TEXT NOT NULL,
    name_real TEXT NOT NULL,
    country TEXT,
    type_artist TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Tipos de Artista:**
- Solista (individual)
- Banda (grupo de personas)
- Grupo (conjunto)

**Registros Incluidos:**
```
1. Michael Jackson (Solista, USA)
2. Alan Walker (Solista, USA)
3. Bad Bunny (Solista, Puerto Rico)
4. Imagine Dragons (Banda, USA)
5. Shakira (Solista, Colombia)
6. Coldplay (Banda, Reino Unido)
7. BTS (Grupo, Corea del Sur)
8. Queen (Banda, Reino Unido)
9. Metallica (Banda, USA)
10. The Weeknd (Solista, Canadá)
```

**Cardinalidad:**
- 1 artista : N álbumes
- 1 artista : N colaboraciones
- N artistas : N géneros

---

### 4. ALBUMES
```sql
CREATE TABLE musica.albumes (
    album_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    date_release TEXT NOT NULL,
    count_songs INT,
    artist_id INT NOT NULL REFERENCES musica.artistas(artist_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Ejemplo de Registros:**
```
album_id | title                      | date_release | count_songs | artist_id
---------|----------------------------|--------------|-------------|----------
1        | Thriller                   | 1982-11-30   | 9           | 1
2        | Different World            | 2018-12-14   | 15          | 2
3        | YHLQMDLG                   | 2020-02-29   | 20          | 3
4        | Night Visions              | 2012-09-04   | 12          | 4
5        | Fijación Oral Vol. 1       | 2005-06-03   | 12          | 5
```

**Cardinalidad:**
- 1 artista : N álbumes
- 1 álbum : N canciones

---

### 5. CANCIONES (inferida de estructura)
```sql
CREATE TABLE musica.canciones (
    cancion_id SERIAL PRIMARY KEY,
    titulo TEXT NOT NULL,
    duracion INT,  -- en segundos
    numero_pista INT,
    album_id INT NOT NULL REFERENCES musica.albumes(album_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Cardinalidad:**
- 1 álbum : N canciones
- 1 canción : N playlists (a través de tabla intermedia)

---

### 6. PLAYLIST
```sql
CREATE TABLE musica.playlist(
    playlist_id SERIAL PRIMARY KEY,
    name_playlist TEXT NOT NULL,
    description TEXT NOT NULL,
    date_create DATE NOT NULL,
    user_id INT NOT NULL REFERENCES musica.usuarios(user_id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**Registros de Ejemplo:**
```
playlist_id | name_playlist        | description | date_create | user_id
------------|----------------------|-------------|-------------|--------
1           | Favoritas de Manesco | Favoritas   | 2024-05-10  | 1
2           | Electro Hits         | EDM y Electro | 2024-06-01 | 1
3           | Fiesta Total         | Para fiestas | 2024-07-15 | 2
4           | Clásicos Eternos     | Rock clásico | 2024-08-20 | 2
```

**Cardinalidad:**
- 1 usuario : N playlists
- 1 playlist : N canciones (a través de tabla intermedia)

---

### 7. PLAYLIST_CANCIONES (Tabla Intermedia)
```sql
CREATE TABLE musica.playlist_canciones (
    playlist_id INT NOT NULL REFERENCES musica.playlist(playlist_id) ON DELETE CASCADE,
    cancion_id INT NOT NULL REFERENCES musica.canciones(cancion_id) ON DELETE CASCADE,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (playlist_id, cancion_id)
);
```

**Propósito:**
- Relaciona N canciones con N playlists
- Permite rastrear cuándo se agregó cada canción

**Cardinalidad:**
- N canciones : N playlists

---

### 8. ARTISTA_GENERO (Tabla Intermedia)
```sql
CREATE TABLE musica.artista_genero (
    artist_id INT NOT NULL REFERENCES musica.artistas(artist_id) ON DELETE CASCADE,
    gender_id INT NOT NULL REFERENCES musica.generos(gender_id) ON DELETE CASCADE,
    PRIMARY KEY (artist_id, gender_id)
);
```

**Propósito:**
- Relaciona N artistas con N géneros
- Un artista puede trabajar en varios géneros

---

### 9. COLABORACIONES
```sql
CREATE TABLE musica.colaboraciones(
    colaboracion_id SERIAL PRIMARY KEY,
    artist_id_1 INT NOT NULL REFERENCES musica.artistas(artist_id),
    artist_id_2 INT NOT NULL REFERENCES musica.artistas(artist_id),
    cancion_id INT REFERENCES musica.canciones(cancion_id),
    year INT,
    UNIQUE(artist_id_1, artist_id_2, cancion_id)
);
```

**Propósito:**
- Registra colaboraciones entre artistas
- Permite rastrear quiénes colaboraron y cuándo

---

## 🔍 Consultas SQL Importantes

### Obtener Usuario y sus Playlists
```sql
SELECT u.user_id, u.user_name, p.playlist_id, p.name_playlist
FROM musica.usuarios u
LEFT JOIN musica.playlist p ON u.user_id = p.user_id
WHERE u.user_id = 1;
```

### Obtener Canciones de una Playlist
```sql
SELECT c.cancion_id, c.titulo, c.duracion, a.name_artist, al.title as album
FROM musica.canciones c
JOIN musica.albumes al ON c.album_id = al.album_id
JOIN musica.artistas a ON al.artist_id = a.artist_id
JOIN musica.playlist_canciones pc ON c.cancion_id = pc.cancion_id
WHERE pc.playlist_id = 1
ORDER BY pc.date_added DESC;
```

### Obtener Álbumes de un Artista
```sql
SELECT a.album_id, a.title, a.date_release, a.count_songs
FROM musica.albumes a
WHERE a.artist_id = 1
ORDER BY a.date_release DESC;
```

### Buscar Canciones por Título
```sql
SELECT c.cancion_id, c.titulo, c.duracion, 
       a.name_artist, al.title as album
FROM musica.canciones c
JOIN musica.albumes al ON c.album_id = al.album_id
JOIN musica.artistas a ON al.artist_id = a.artist_id
WHERE LOWER(c.titulo) LIKE LOWER('%' || ? || '%')
LIMIT 20;
```

### Obtener Artistas de un Género
```sql
SELECT DISTINCT a.artist_id, a.name_artist, a.type_artist
FROM musica.artistas a
JOIN musica.artista_genero ag ON a.artist_id = ag.artist_id
WHERE ag.gender_id = 1  -- Pop
ORDER BY a.name_artist;
```

### Obtener Colaboradores de un Artista
```sql
SELECT DISTINCT a.artist_id, a.name_artist
FROM musica.artistas a
JOIN musica.colaboraciones c ON (
    (c.artist_id_1 = ? AND c.artist_id_2 = a.artist_id) OR
    (c.artist_id_2 = ? AND c.artist_id_1 = a.artist_id)
);
```

---

## 📈 Índices Recomendados

```sql
-- Para búsquedas de usuarios por email
CREATE INDEX idx_usuarios_email ON musica.usuarios(email);

-- Para búsquedas de canciones por título
CREATE INDEX idx_canciones_titulo ON musica.canciones(titulo);

-- Para playlists por usuario
CREATE INDEX idx_playlist_user ON musica.playlist(user_id);

-- Para canciones en playlist
CREATE INDEX idx_playlist_canciones ON musica.playlist_canciones(playlist_id, cancion_id);

-- Para artistas en géneros
CREATE INDEX idx_artista_genero ON musica.artista_genero(artist_id, gender_id);
```

---

## 🔑 Restricciones de Integridad

### Claves Primarias (PK)
- Cada tabla tiene una PK única
- Identifican unívocamente cada registro

### Claves Foráneas (FK)
- `playlist.user_id` → `usuarios.user_id`
- `albumes.artist_id` → `artistas.artist_id`
- `canciones.album_id` → `albumes.album_id`
- `playlist_canciones.playlist_id` → `playlist.playlist_id`
- `playlist_canciones.cancion_id` → `canciones.cancion_id`

### Restricciones de Eliminación
```sql
ON DELETE CASCADE  -- Elimina registros relacionados
ON UPDATE CASCADE  -- Actualiza registros relacionados
```

**Ejemplo:**
- Si se elimina un usuario, todas sus playlists se eliminan
- Si se elimina un álbum, todas sus canciones se eliminan

---

## 📊 Estadísticas de Datos

| Tabla | Registros | Propósito |
|-------|-----------|----------|
| usuarios | 2 | Usuarios del sistema |
| generos | 10 | Categorías musicales |
| artistas | 10 | Artistas/Bandas |
| albumes | 20 | Álbumes de artistas |
| canciones | ~200+ | Canciones en álbumes |
| playlist | 4 | Playlists de ejemplo |
| colaboraciones | Variable | Colaboraciones entre artistas |

---

## 🎯 Ejemplos de Operaciones CRUD

### CREATE (Insertar)
```sql
-- Insertar nueva canción
INSERT INTO musica.canciones(titulo, duracion, numero_pista, album_id)
VALUES ('Song Title', 240, 5, 1);

-- Insertar canción a playlist
INSERT INTO musica.playlist_canciones(playlist_id, cancion_id)
VALUES (1, 1);
```

### READ (Leer)
```sql
-- Obtener playlist completa
SELECT * FROM musica.playlist WHERE playlist_id = 1;

-- Obtener canciones de playlist
SELECT c.* FROM musica.canciones c
JOIN musica.playlist_canciones pc ON c.cancion_id = pc.cancion_id
WHERE pc.playlist_id = 1;
```

### UPDATE (Actualizar)
```sql
-- Actualizar descripción de playlist
UPDATE musica.playlist
SET description = 'Nueva descripción'
WHERE playlist_id = 1;

-- Actualizar nombre de playlist
UPDATE musica.playlist
SET name_playlist = 'Nuevo nombre'
WHERE playlist_id = 1;
```

### DELETE (Eliminar)
```sql
-- Eliminar canción de playlist
DELETE FROM musica.playlist_canciones
WHERE playlist_id = 1 AND cancion_id = 5;

-- Eliminar playlist completa
DELETE FROM musica.playlist
WHERE playlist_id = 1;
```

---

**Última actualización**: Diciembre 2025
**Versión**: 1.0.0
