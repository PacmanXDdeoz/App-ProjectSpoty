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

INSERT INTO musica.generos(gender) VALUES 
('Pop'),
('Rock'),
('Reggueton'),
('Hip Hop'),
('Electronica'),
('Clasica'),
('Jazz'),
('Blues'),
('Reggae'),
('Metal');

CREATE TABLE musica.artistas(
    artist_id INT PRIMARY KEY,
    name_artist TEXT,
    name_real TEXT NOT NULL,
    country TEXT,
    type_artist TEXT NOT NULL
);

INSERT INTO musica.artistas(name_artist, name_real, country, type_artist) VALUES 
('The Beatles', 'The Beatles', 'UK', 'Rock'),
('Queen', 'Fredy Mercury', 'UK', 'Rock'),
('Elvis Presley', 'Elvis Presley', 'USA', 'Rock'),
('Michael Jackson', 'Michael Jackson', 'USA', 'Rock'),
('Madonna', 'Madonna', 'USA', 'Rock'),
('Beyonce', 'Beyonce', 'USA', 'Rock'),
('Shakira', 'Shakira', 'USA', 'Rock'),
('Lady Gaga', 'Lady Gaga', 'USA', 'Rock');


CREATE TABLE musica.albumes (
    album_id INT PRIMARY KEY,
    title TEXT NOT NULL,
    date_release TEXT NOT NULL,
    count_songs INT,
    artist_id INT REFERENCES musica.artistas(artist_id)
);

INSERT INTO musica.albumes(title, date_release, count_songs, artist_id) VALUES 
('The Beatles', '1962', 7, 1),
('Queen', '1981', 10, 2),
('Elvis Presley', '1956', 9, 3),
('Michael Jackson', '1957', 9, 4),
('Madonna', '1987', 9, 5),
('Beyonce', '2011', 9, 6),
('Shakira', '1994', 9, 7),
('Lady Gaga', '2009', 9, 8);

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