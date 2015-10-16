create database album_s;

use album_s;

create table user(
    user_name varchar(20),
    user_password varchar(20)
);

create table album(
    album_title varchar(20),
    album_time varchar(20),
    album_isopen varchar(20),
    album_number varchar(20),
    album_user varchar(20)
);

create table photo(
    photo_bi varchar(20),
    photo_time varchar(20),
    photo_number varchar(20),
    photo_position varchar(100),
    photo_user varchar(20),
    photo_album_title varchar(20)
);