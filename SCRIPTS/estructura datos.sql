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
('1029141151','TP-1','TD-02','Juan','Cadena','3054394381','cadenaverajuandavid@gmail.com','Carrera 79 A #5-27Sur','2005-06-05');

INSERT INTO tb_matricula (TbM_ID_Matricula, TbM_ID_Persona, TbM_ID_Programa, TbM_Fecha) VALUES
('TM-53','1029141151','TPG-3','2024-7-20');


INSERT INTO tb_contrato (TbC_ID_Contrato, TbC_ID_Personas, TbC_Fecha, TbC_Salario_Hora) VALUES
('C-15','1033097397','2024-6-20',6300);

INSERT INTO tb_horario (TbH_ID_Horario, TbH_Dia_Semana, TbH_Hora_Inicio, TbH_Hora_Final) VALUES 
('1','LUNES','07:00:00','05:00:00'),
('2','MARTES','07:00:00','05:00:00'),
('3','MIÉRCOLES','07:00:00','05:00:00'),
('4','JUEVES','07:00:00','05:00:00'),
('5','VIERNES','07:00:00','05:00:00');

INSERT INTO tb_carga_docente (TbCD_ID_Detalles, TbCD_Contrato, TbCD_Horas_Trabajadas, TbCD_Dia_Semana) VALUES
('DT-15','C-15','5','Lunes');

INSERT INTO tb_Usuarios (TbU_Email, TbU_ID_Contraseña, TbU_Tipo_Persona) VALUES
('cadenaverajuandavid@gmail.com','1029141151','TP-1');

INSERT INTO tb_nivel (TbN_ID_Nivel, TbN_Duracion_Horas) VALUES 
('A1','10 Horas semanales'),
('A2','10 Horas semanales'),
('B1','12 Horas semanales'),
('B2','12 Horas semanales'),
('C1','15 Horas semanales'),
('C2','15 Horas semanales');

INSERT INTO tb_curso_nombre (TbCN_ID_Curso, TbCN_Nombre_Curso) VALUES 
('A-101','Curso Aprendizaje'),
('A-102','Curso Basico'),
('B-201','Curso Avanzado'),
('B-202','Curso Intermedio'),
('C-301','Curso Maestro'),
('C-302','Curso Perfecto');

INSERT INTO tb_curso(TbCo_ID_Curso, TbCo_ID_Matricula, TbCo_ID_Nivel, TbCo_ID_Horario, TbCo_ID_Contrato) VALUES 
('B-202','TM-53','B2','1','C-04');

INSERT INTO tb_notas (TbN_ID_Nota, TbN_ID_Curso, TbN_ID_Matricula, TbN_ID_Contrato) VALUES
('3.8','B-202','TM-53','C-04');

INSERT INTO tb_nivel_matricula (TbNM_ID_Horario, TbNM_ID_Nivel, TbNM_ID_Matricula, TbNM_ID_Contrato, TbNM_ID_Curso) VALUES 
('1','B2','TM-53','C-04','B-202');
