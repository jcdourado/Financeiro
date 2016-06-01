CREATE DATABASE FINANCEIRO;

USE FINANCEIRO;

CREATE TABLE USUARIO(
	USUARIO VARCHAR(20) UNIQUE, 
	NOME VARCHAR(50),
	EMAIL VARCHAR(50) UNIQUE,
	SENHA VARCHAR(20),
	PRIMARY KEY (USUARIO,EMAIL)
);

CREATE TABLE CONTA(
	ID INT PRIMARY KEY,
	FREQUENCIA INT,
	NOME VARCHAR(50),
	DESCRICAO VARCHAR(255),
	VALOR FLOAT,
	USUARIO VARCHAR(20),
	FOREIGN KEY (USUARIO) REFERENCES USUARIO(USUARIO)
);

CREATE TABLE RECEBIMENTO(
	ID INT PRIMARY KEY,
	FREQUENCIA INT,
	NOME VARCHAR(50),
	DESCRICAO VARCHAR(255),
	VALOR FLOAT,
	USUARIO VARCHAR(20),
	FOREIGN KEY (USUARIO) REFERENCES USUARIO(USUARIO)
);