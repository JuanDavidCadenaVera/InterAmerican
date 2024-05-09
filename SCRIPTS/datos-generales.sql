-- DATOS --
INSERT INTO tb_tipo_persona (TbTP_ID_Tipo_Persona, TbTP_Tipo_Persona) VALUES
('TP-1', 'Estudiante'),
('TP-2', 'Profesor'),
('TP-3', 'Administrador');

INSERT INTO tb_tipo_documento (TbTD_ID_Tipo_Documento, TbTP_Tipo_Documento) VALUES
('TD-01', 'Tarjeta de Identidad'),
('TD-02', 'Cedula'),
('TD-03', 'Cedula Extranjera'),
('TD-04', 'Pasaporte'),
('TD-05', 'Registro  de Civil');

INSERT INTO tb_programa (TbPr_ID_Programa, TbPr_Nombre_Programa, TbPr_Duracion, TbPr_Costo) VALUES
('TPG-1', 'GOLD', '1_año', 280000),
('TPG-2', 'VIP', '1_año', 360000),
('TPG-3', 'SHORT', '6_meses', 600000);

INSERT INTO tb_personas (TbP_ID_Personas, TbP_Tipo_Personas, TbP_Tipo_Documento, TbP_Nombre, TbP_Apellido, TbP_Telefono, TbP_Direccion_Email, TbP_Direccion, TbP_Fecha_Nacimiento) VALUES
('1029141151', 'TP-1', 'TD-02', 'Juan', 'Cadena', '3054394381', 'cadenaverajuandavid@gmail.com', 'Carrera 79 A #5-27Sur', '2005-06-05'),
('','TP-2','Erik','Alarcon','3213229629','Direccion','FechaNacimiento'),
('','TP-3', 'Samuel', 'Ballesteros', '3202573267', 'Direccion', 'FechaNacimiento')
;

INSERT INTO tb_matricula (TbM_ID_Matricula, TbM_ID_Persona, TbM_ID_Programa, TbM_Fecha) VALUES
('TM-0', '1029141151', 'TPG-1', '2023-11-27');
;

INSERT INTO tb_contrato (TbC_ID_Contrato, TbC_ID_Personas, TbC_Fecha, TbC_Salario_Hora) VALUES
('99794', '3374716929', '2021-02-18', 60000);

INSERT INTO tb_carga_docente (TbCD_ID_Detalles, TbCD_Contrato, TbCD_Horas_Trabajadas, TbCD_Semana) VALUES
('DT-9', '25191', 2, 'SM-1');

INSERT INTO tb_Usuarios (TbU_Email, TbU_ID_Contraseña, TbU_Tipo_Persona) VALUES
('cadenaverajuandavid@gmail.com','1029141151','TP-1');
