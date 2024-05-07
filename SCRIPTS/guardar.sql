-- Crear Base de datos --
CREATE DATABASE ingles;

-- Utilizar la base de datos --
USE ingles;

-- CREACION DE TABLAS --

CREATE TABLE tb_carga_docente (
  TbCD_ID_Detalles varchar(5) NOT NULL PRIMARY KEY,
  TbCD_Contrato varchar(5) NOT NULL,
  TbCD_Horas_Trabajadas int NULL,
  TbCD_Semana varchar(5) NOT NULL
);


CREATE TABLE tb_contrato (
  TbC_ID_Contrato varchar(5) NOT NULL PRIMARY KEY,
  TbC_ID_Personas varchar(10) NULL,
  TbC_Fecha date NULL,
  TbC_Salario_Hora int NULL
);


CREATE TABLE tb_matricula (
  TbM_ID_Matricula varchar(8) NOT NULL PRIMARY KEY,
  TbM_ID_Persona varchar(10) NULL,
  TbM_ID_Programa varchar(5) NULL,
  TbM_Fecha date NULL
);


CREATE TABLE tb_personas (
  TbP_ID_Personas varchar(10) NOT NULL PRIMARY KEY,
  TbP_Tipo_Personas varchar(15) NULL,
  TbP_Tipo_Documento varchar(20) NULL,
  TbP_Nombre varchar(15) NULL,
  TbP_Apellido varchar(15) NULL,
  TbP_Telefono varchar(11) NULL,
  TbP_Direccion_Email varchar(30) NULL,
  TbP_Direccion varchar(40) NULL,
  TbP_Fecha_Nacimiento date NULL
);


CREATE TABLE tb_programa (
  TbPr_ID_Programa varchar(5) NOT NULL PRIMARY KEY,
  TbPr_Nombre_Programa varchar(8) NULL,
  TbPr_Duracion varchar(10) NULL,
  TbPr_Costo int NULL
);


CREATE TABLE tb_tipo_documento (
  TbTD_ID_Tipo_Documento varchar(20) NOT NULL PRIMARY KEY,
  TbTP_Tipo_Documento varchar(25) NULL
);


CREATE TABLE tb_tipo_persona (
  TbTP_ID_Tipo_Persona varchar(15) NOT NULL PRIMARY KEY,
  TbTP_Tipo_Persona varchar(15) NOT NULL
);

CREATE TABLE tb_usuarios (
  TbU_Email varchar(30),
  TbU_ID_Contraseña varchar(10) PRIMARY KEY,
  TbU_Tipo_Persona varchar(15)
);

-- Primera llave--
ALTER TABLE tb_carga_docente
  ADD CONSTRAINT FK_tb_carga_docente_tb_contrato 
  FOREIGN KEY (TbCD_Contrato) 
  REFERENCES tb_contrato (TbC_ID_Contrato);

-- Segunda llave--

ALTER TABLE tb_contrato
  ADD CONSTRAINT FK_tb_contrato_tb_personas 
  FOREIGN KEY (TbC_ID_Personas) 
  REFERENCES tb_personas (TbP_ID_Personas);

-- Tercera llave--

ALTER TABLE tb_matricula
  ADD CONSTRAINT FK_tb_matricula_tb_personas 
  FOREIGN KEY (TbM_ID_Persona) 
  REFERENCES tb_personas (TbP_ID_Personas);

-- Cuarta llave--

ALTER TABLE tb_personas
  ADD CONSTRAINT FK_tb_personas_tb_tipo_documento 
  FOREIGN KEY (TbP_Tipo_Documento) 
  REFERENCES tb_tipo_documento (TbTD_ID_Tipo_Documento);

-- Quinta llave--

ALTER TABLE  tb_matricula
ADD CONSTRAINT FK_tb_programa_tb_matricula 
FOREIGN KEY (TbM_ID_Programa) 
REFERENCES tb_programa (TbPr_ID_Programa);

-- Sexta llave--

ALTER TABLE  tb_personas
ADD CONSTRAINT FK_tb_programa_tb_tipo_persona
FOREIGN KEY (TbP_Tipo_Personas)
REFERENCES tb_tipo_persona (TbTP_ID_Tipo_Persona);


-- Indece --
CREATE INDEX idx_tb_contraseña ON tb_personas(TbP_ID_Personas);

-- Septima  llave--

ALTER TABLE tb_usuarios
ADD CONSTRAINT FK_tb_usuarios_tb_personas
FOREIGN KEY (TbU_ID_Contraseña)
REFERENCES tb_personas (TbP_ID_Personas);

