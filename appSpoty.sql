CREATE DATABASE db_spoty;

CREATE SCHEMA musica;

CREATE TABLE musica.usuarios(
    user_id INT PRIMARY KEY,
    user_name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE musica.generos(
    gender_id INT PRIMARY KEY,
    gender TEXT
);

CREATE TABLE musica.artistas(
    artist_id INT PRIMARY KEY,
    name_artist TEXT,
    name_real TEXT NOT NULL,
    country TEXT,
    type_artist TEXT NOT NULL
);

CREATE TABLE musica.albumes (
    album_id INT PRIMARY KEY,
    title TEXT NOT NULL,
    date_release TEXT NOT NULL,
    count_songs INT,
    artist_id INT REFERENCES musica.artistas(artist_id)
);

CREATE TABLE musica.playlist(
    playlist_id INT PRIMARY KEY,
    name_playlist TEXT NOT NULL,
    description TEXT NOT NULL,
    date_create DATE,
    user_id INT REFERENCES musica.usuarios(user_id)
);

CREATE TABLE musica.colaboraciones(
    artist_id INT REFERENCES musica.artistas(artist_id),
    song_id INT REFERENCES musica.canciones(song_id),
);

CREATE TABLE musica.lyrics(
    lyrics_id INT PRIMARY KEY,
    content TEXT NOT NULL,
    lenguage TEXT NOT NULL,
    version TEXT NOT NULL
);

CREATE TABLE musica.playlist_songs(
    playlist_song_id INT PRIMARY KEY,
    playlist_id INT REFERENCES musica.playlist(playlist_id),
    song_id INT REFERENCES musica.canciones(song_id)
);

CREATE TABLE musica.canciones(
    song_id INT PRIMARY KEY,
    song_name TEXT NOT NULL,
    duration INT NOT NULL,
    date_creation DATE,
    reproduction INT NOT NULL,
    lyrics_id INT REFERENCES musica.lyrics(lyrics_id),
    gender_id INT REFERENCES musica.generos(gender_id),
    artist_id INT REFERENCES musica.artistas(artist_id),
    album_id INT REFERENCES musica.albumes(album_id)
);