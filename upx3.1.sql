drop database if exists upx;
create database upx;
use upx;



 -- truncate usuario;


drop table if exists usuario;
CREATE TABLE usuario (
  Usuario_id    INT          AUTO_INCREMENT,     
  Nome_usuario  VARCHAR(60)  NOT NULL,
  Celular       VARCHAR(20)  NOT NULL,
  Email         VARCHAR(60)  NOT NULL,
  Cidade        VARCHAR(15)  NOT NULL,
  Uf			VARCHAR(25)	 NOT NULL,
  Senha         INT          NOT NULL,
  PRIMARY KEY (Usuario_id)
  );


drop table if exists genero;
create table genero (
  Genero_id     INT NOT NULL AUTO_INCREMENT,
  Genero_nome   VARCHAR(30),
  PRIMARY KEY (Genero_id)
  );
  
  
drop table if exists estado;
create table estado (
	Estado_id    INT NOT NULL AUTO_INCREMENT, 
    Estado_nome  VARCHAR(30),
    PRIMARY KEY (Estado_id)
  );
  
drop table if exists livro;
CREATE TABLE livro (
  Livro_id       INT          AUTO_INCREMENT,
  Nome           VARCHAR(60)  NOT NULL,
  Ano_Publi      INT          NOT NULL,
  Qtde_Paginas   INT          NOT NULL,
  Editora        VARCHAR(30)  NOT NULL,
  Autor          VARCHAR(60)  NOT NULL,
  Genero_id      INT          NOT NULL,
  Estado_id      INT          NOT NULL,
  PRIMARY KEY (Livro_id),
  FOREIGN KEY (Genero_id) REFERENCES genero(Genero_id),
  FOREIGN KEY (Estado_id) REFERENCES estado(Estado_id)
);

insert into genero(Genero_nome) values
('Fantasia'),
('Ficção Científica'),
('Romance'),
('Mistério'),
('Policial'),
('Biografia'),
('História'),
('Suspense'),
('Espiritualidade'),
('Filosofia');

insert into estado(Estado_nome) values
('Novo'),
('Usado'),
('Bem Usado');

insert into usuario values
(null,	'Bigeus',		'15998421996',	'bigeus@gmail.com',		'Tatuí',		'SP',	29082003),
(null,	'Lucas',		'15999999999',	'lucas@gmail.com',		'Tatuí',		'SP',	1234),
(null,	'Felipe',		'15998877777',	'felipe@gmail.com',		'Tatuí',		'SP',	123321),
(null,	'Coxa',			'1599777920',	'coxaUri@gmail.com',	'Tatuí',		'SP',	4815),
(null,	'Teste',		'Celular',		'teste@gmail.com',		'Cidade',		'AC',	123456);

insert into livro (Nome,Ano_Publi,Qtde_Paginas,Editora,Autor,Genero_id,Estado_id)values
('Harry Potter e a Câmara Secreta',		1998,		287,		'Rocco',				'J. K. Rowling',				1,2),
('O Pequeno Príncipe',					1956,		93,			'Agir',					'Antoine de Saint-Exupéry',		1,1),
('Um Estudo em Vermelho',				1998,		192,		'L&PM EDITORES',		'Arthur Conan Doyle',			4,3),
('Técnicas de Invasão',					2019,		296,		'Labrador',				'Bruno Fraga',					5,2);

 drop table if exists genero_usuario;
  create table genero_usuario(
  Genero_id 	int not null,
  Usuario_id	int,
  
  foreign key(Usuario_id) references	usuario(Usuario_id),
  foreign key(Genero_id)  references	genero(Genero_id)
  );
  insert into genero_usuario values
(1,1),										-- 	(1, "Fantasia");
(2,1),										-- 	(2, "Ficção Científica");
(4,1),										-- 	(3, "Romance");
(7,1),										-- 	(4, "Mistério");
(10,1),										-- 	(5, "Policial");	
(1,2),										-- 	(6, "Biografia");
(2,2),										-- 	(7, "História");
(4,2),										-- 	(8, "Suspense");
(7,2),										-- 	(9, "Espiritualidade");
(2,3),										-- 	(10, "Filosofia");
(10,3),										
(5,3),
(4,3),
(7,3),
(2,4),
(1,4),
(8,4),
(4,4);

drop table if exists Trocas;
create table Trocas(
Usuario_id int ,
Livro_id int,

FOREIGN KEY (Usuario_id) references usuario(Usuario_id),
FOREIGN KEY	(Livro_id) 	references	livro(Livro_id),
CONSTRAINT UC_UsuarioLivro unique (Usuario_id, Livro_id)
);

insert into trocas values
(1,1),
(1,2),
(1,3);          -- trocas de Vinicius pelo script
