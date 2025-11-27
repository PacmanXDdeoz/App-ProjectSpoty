CREATE DATABASE db_spoty;

CREATE SCHEMA musica;

CREATE TABLE musica.usuarios(
    user_id INT SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);

INSERT INTO musica.usuarios(user_name, email, password) VALUES 
('Manesco7152', 'manesco7152@gmail.com', '123456'),
('Pacmanxddeoz', 'pacmanxddeoz@gmail.com', '123456');

CREATE TABLE musica.generos(
    gender_id SERIAL PRIMARY KEY,
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
    artist_id SERIAL PRIMARY KEY,
    name_artist TEXT,
    name_real TEXT NOT NULL,
    country TEXT,
    type_artist TEXT NOT NULL
);

INSERT INTO musica.artistas(name_artist, name_real, country, type_artist) VALUES 
('Michael Jackson', 'Michael Jackson', 'USA', 'Solista'),
('Alan Walker', 'Alan Walker', 'USA', 'Solista'),
('Bad Bunny', 'Benito Antonio Martinez Ocasio', 'Puerto Rico', 'Solista'),
('Imagine Dragons', 'Dan Reynolds, Wayne Sermon, Ben McKee, Daniel Platzman', 'USA', 'Banda'),
('Shakira', 'Shakira Isabel Mebarak Ripoll', 'Colombia', 'Solista'),
('Coldplay', 'Chris Martin, Jonny Buckland, Guy Berryman, Will Champion', 'Reino Unido', 'Banda'),
('BTS', 'Kim Nam-joon, Kim Seok-jin, Min Yoon-gi, Jung Ho-seok, Park Ji-min, Kim Tae-hyung, Jeon Jung-kook', 'Corea del Sur', 'Grupo'),
('Queen', 'Freddie Mercury, Brian May, Roger Taylor, John Deacon', 'Reino Unido', 'Banda'),
('Metallica', 'James Hetfield, Lars Ulrich, Kirk Hammett, Robert Trujillo', 'USA', 'Banda'),
('The Weeknd', 'Abel Makkonen Tesfaye', 'Canadá', 'Solista');


CREATE TABLE musica.albumes (
    album_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    date_release TEXT NOT NULL,
    count_songs INT,
    artist_id INT REFERENCES musica.artistas(artist_id)
);

INSERT INTO musica.albumes(title, date_release, count_songs, artist_id) VALUES 
('Thriller', '1982-11-30', 9, 1),
('Different World', '2018-12-14', 15, 2),
('YHLQMDLG', '2020-02-29', 20, 3),
('Night Visions', '2012-09-04', 12, 4),
('Fijación Oral Vol. 1', '2005-06-03', 12, 5),
('A Head Full of Dreams', '2015-12-04', 11, 6),
('Map of the Soul: 7', '2020-02-21', 20, 7),
('A Night at the Opera', '1975-11-21', 12, 8),
('Master of Puppets', '1986-03-03', 8, 9),
('After Hours', '2020-03-20', 14, 10);

CREATE TABLE musica.playlist(
    playlist_id SERIAL PRIMARY KEY,
    name_playlist TEXT NOT NULL,
    description TEXT NOT NULL,
    date_create DATE NOT NULL,
    user_id INT REFERENCES musica.usuarios(user_id)
);

CREATE TABLE musica.colaboraciones(
    artist_id INT REFERENCES musica.artistas(artist_id),
    song_id INT REFERENCES musica.canciones(song_id)
);

INSERT into musica.colaboraciones(artist_id, song_id) VALUES 
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5),
(7, 6),
(8, 7),
(1, 8);

CREATE TABLE musica.lyrics(
    lyrics_id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    lenguage TEXT NOT NULL,
    version TEXT NOT NULL
);

('Cause this is thriller, thriller night...', 'English', 'Original'),
('We live, we love, we lie...', 'English', 'Original'),
('Yo perreo sola, sin que me estén molestando...', 'Español', 'Original'),
('Im waking up, I feel it in my bones...', 'English', 'Original'),
('Sigo aqui, queriéndote en silencio...', 'Español', 'Acoustic'),
('A sky full of stars, you light up the path...', 'English', 'Original'),
('I wanna know your name, and I wanna know your heart...', 'Korean/English', 'Original'),
('Is this the real life? Is this just fantasy?', 'English', 'Original'),
('Taste me you will see, more is all you need...', 'English', 'Original'),
('I can’t escape this now, unless you show me how...', 'English', 'Remix');

INSERT INTO musica.lyrics(content, lenguage, version) VALUES 
('Billie Jean is not my lover, she''s just a girl who claims that I am the one...', 'English', 'Original'),
('Where are you now? Was it all in my fantasy?...', 'English', 'Original'),
('Si tu novio no te mama el culo, pa’ eso que no mame...', 'Español', 'Explicit'),
('When you feel my heat, look into my eyes, it''s where my demons hide...', 'English', 'Acoustic'),
('Ay amor, me duele tanto, me duele tanto...', 'Español', 'Original'),
('Oh angel sent from up above, you know you make my world light up...', 'English', 'Original'),
('Do your thang, do your thang with me now...', 'Korean/English', 'Original'),
('Love of my life, you’ve hurt me, you’ve broken my heart...', 'English', 'Live'),
('Lashing out the action, returning the reaction...', 'English', 'Original'),
('I saw you dancing in a crowded room, you looked so happy...', 'English', 'Original');

CREATE TABLE musica.playlist_songs(
    playlist_song_id SERIAL PRIMARY KEY,
    playlist_id INT REFERENCES musica.playlist(playlist_id),
    song_id INT REFERENCES musica.canciones(song_id)
);

INSERT INTO musica.playlist_songs(playlist_id, song_id) VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8);

CREATE TABLE musica.canciones(
    song_id SERIAL PRIMARY KEY,
    song_name TEXT NOT NULL,
    duration INT NOT NULL,
    date_creation DATE NOT NULL,
    reproduction INT NOT NULL,
    lyrics_id INT REFERENCES musica.lyrics(lyrics_id),
    gender_id INT REFERENCES musica.generos(gender_id),
    artist_id INT REFERENCES musica.artistas(artist_id),
    album_id INT REFERENCES musica.albumes(album_id)
);

INSERT INTO musica.canciones(song_name, duration, date_creation, reproduction, lyrics_id, gender_id, artist_id, album_id) VALUES 
