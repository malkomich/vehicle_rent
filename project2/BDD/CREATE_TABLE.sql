CREATE TABLE TIPOCARNET (
    tipo VARCHAR(4) not null primary key
);

CREATE TABlE MODELO (
    IDMODELO VARCHAR(10) not null primary key,
    NOMBRE VARCHAR(30) NOT NULL,
    FABRICANTE VARCHAR(30) NOT NULL,
    COSTEALQUILER REAL NOT NULL,
    LARGO REAL NOT NULL,
    ANCHO REAL NOT NULL,
    PESO REAL NOT NULL,
    PUERTAS INTEGER NOT NULL,
    A�O INTEGER NOT NULL,
    GPS CHAR NOT NULL
);

CREATE TABlE VEHICULO (
    MATRICULA VARCHAR(10) NOT NULL PRIMARY KEY,
    COLOR VARCHAR(30) NOT NULL,
    KILOMETRAJE REAL NOT NULL,
    FECHAADQUISICION DATE NOT NULL,
    COSTEADQUISICION REAL NOT NULL,
    AVERIADO CHAR(1) DEFAULT 'f' NOT NULL,
    IDMODELO VARCHAR(10) not null,
FOREIGN KEY (IDMODELO) REFERENCES MODELO
);

create table USUARIO
(
	NIF VARCHAR(9) not null primary key,
	NOMBRE VARCHAR(30) not null,
	PASSWORD VARCHAR(12) not null,
	DIRECCIONPOSTAL VARCHAR(120),
	CODIGOPOSTAL INTEGER not null,
	DIRECCIONELECTRONICA VARCHAR(50) not null
);

create table CLIENTE
(
	NIF VARCHAR(9) not null primary key,
	BLOQUEADO CHAR(1) default 'f' not null,
FOREIGN KEY (NIF) REFERENCES USUARIO
);


create table EMPLEADO
(
	NIF VARCHAR(9) not null,
	NUMEROEMPLEADO VARCHAR(9) not null primary key,
	FECHACONTRATACION DATE not null,
	TIPOEMPLEADO CHAR(1) not null,
FOREIGN KEY (NIF) REFERENCES USUARIO
);


CREATE TABLE LICENCIA (
    NIF VARCHAR(9), 
    tipo VARCHAR(4),
    FECHARENOVACION DATE,
PRIMARY KEY (nif, tipo), 
FOREIGN KEY (tipo) REFERENCES TIPOCARNET,
FOREIGN KEY (nif) REFERENCES CLIENTE
);

CREATE TABLE LICENCIASPORMODELO (
    IDMODELO VARCHAR(10) not null,
    TIPO VARCHAR(4) not null,
PRIMARY KEY (IDMODELO, TIPO),
FOREIGN KEY (IDMODELO) REFERENCES MODELO,
FOREIGN KEY (TIPO) REFERENCES TIPOCARNET
);

CREATE TABLE ALQUILER 
(
    IDALQUILER INTEGER NOT NULL PRIMARY KEY,
    FECHAINICIO DATE NOT NULL,
    FECHAFIN DATE NOT NULL,
    PRECIO REAL NOT NULL,
    KILOMETRAJESALIDA REAL NOT NULL,
    MATRICULA VARCHAR(10) NOT NULL,
    CLIENTE VARCHAR(9) NOT NULL,
    REALIZADOPOR VARCHAR(9) not null,
FOREIGN KEY (MATRICULA) REFERENCES VEHICULO,
FOREIGN KEY (CLIENTE) REFERENCES CLIENTE,
FOREIGN KEY (REALIZADOPOR) REFERENCES EMPLEADO
);

create table RESERVA
(
	IDRESERVA INTEGER not null primary key,
	FECHARESERVA DATE,
	FECHAINICIOALQUILER DATE,
	FECHAFINALQUILER DATE,
	EJECUTADA CHAR(1) default 'f',
	NIF VARCHAR(9),
	MATRICULA VARCHAR(10),
FOREIGN KEY (MATRICULA) REFERENCES VEHICULO,
FOREIGN KEY (NIF) REFERENCES CLIENTE
);

create table FACTURA
(
	NUMEROFACTURA INTEGER not null primary key,
	FECHAFACTURA DATE NOT NULL,
	IMPORTE REAL NOT NULL,
	PENDIENTEDEPAGO CHAR(1) default 't' NOT NULL,
	FECHACOBRO DATE NOT NULL,
	CONCEPTO CHAR(1) NOT NULL,
	IDALQUILER INTEGER NOT NULL, 
FOREIGN KEY (IDALQUILER) REFERENCES ALQUILER
);

create table ENTREGA
(
	IDENTREGA INTEGER not null primary key,
	FECHAENTREGA DATE NOT NULL,
	RECARGO REAL,
	INCIDENCIAS CHAR(1) default 'F' NOT NULL,
	INFORMEINCIDENCIAS VARCHAR(200),
	IDALQUILER INTEGER NOT NULL, 
	REGISTRADAPOR VARCHAR(9) not null,
FOREIGN KEY (IDALQUILER) REFERENCES ALQUILER,
FOREIGN KEY (REGISTRADAPOR) REFERENCES EMPLEADO
);