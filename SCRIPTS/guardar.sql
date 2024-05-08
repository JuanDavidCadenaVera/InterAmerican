-- Crear Base de datos --
CREATE DATABASE ingles;

-- Utilizar la base de datos --
USE ingles;

-- CREACION DE TABLAS --

CREATE TABLE tb_carga_docente (
  TbCD_ID_Detalles varchar(5) NOT NULL PRIMARY KEY,
  TbCD_Contrato varchar(5) NOT NULL,
  TbCD_Horas_Trabajadas int NULL,
  TbCD_Dia_Semana varchar(10) NOT NULL
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

CREATE TABLE tb_horario (
  TbH_ID_Horario varchar (10) NOT NULL PRIMARY KEY,
  TbH_Dia_Semana Varchar (10),
  TbH_Hora_Inicio Time,
  TbH_Hora_Final Time
);

CREATE TABLE tb_nivel_matricula(
  TbNM_ID_Horario varchar (10),
  TbNM_ID_Nivel varchar (5),
  TbNM_ID_Matricula varchar(8)
);

CREATE TABLE tb_nivel(
  TbN_ID_Nivel varchar(5),
  TbN_Duracion_Horas varchar(10)
);


-- Llaves Foraneas --

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

-- Index --
CREATE INDEX idx_tb_nivel_matricula ON tb_nivel_matricula(TbNM_ID_Matricula);

-- Octava Llave --
ALTER TABLE tb_matricula
ADD CONSTRAINT FK_tb_matricula_tb_nivel_matricuka
FOREIGN KEY (TbM_ID_Matricula)
REFERENCES tb_nivel_matricula (TbNM_ID_Matricula);

-- Index --
CREATE INDEX idx_tb_nivel ON tb_nivel(TbN_ID_Nivel);

-- Novena Llave --
ALTER TABLE tb_nivel_matricula
ADD CONSTRAINT FK_tb_nivel_matricula_tb_nivel
FOREIGN KEY (TbNM_ID_Nivel)
REFERENCES tb_nivel (TbN_ID_Nivel);

-- Decima Llave --
ALTER TABLE tb_nivel_matricula
ADD CONSTRAINT FK_tb_nivel_matricula_tb_horario
FOREIGN KEY (TbNM_ID_Horario)
REFERENCES tb_horario (TbH_ID_Horario);

-- Index --
CREATE INDEX idx_tb_horario_dia_semana ON tb_horario(TbH_Dia_Semana);

-- Onceava Llave --
ALTER TABLE tb_carga_docente
ADD CONSTRAINT Fk_tb_carga_docente_tb_horario
FOREIGN KEY (TbCD_Dia_Semana)
REFERENCES tb_horario (TbH_Dia_Semana);