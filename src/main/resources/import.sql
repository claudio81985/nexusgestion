/* Script para la generación de valores iniciales */
/* Roles */
INSERT INTO `nexusgestion_db`.`permisos` (`nombre`) VALUES ('ROLE_SUCURSALFONTANA');
INSERT INTO `nexusgestion_db`.`permisos` (`nombre`) VALUES ('ROLE_SUCURSALSAUSALITO');





/*Cargar Categoria*/
INSERT INTO categorias (id, activo, nombre)
VALUES
    ('1', 1, 'Embragues'),
    ('2', 1, 'Frenos'),
    ('3', 1, 'Suspensiones'),
    ('4', 2, 'Aceites y lubricantes'),
    ('5', 1, 'Sistemas eléctricos'),
    ('6', 1, 'Neumáticos'),
    ('7', 2, 'Accesorios'),
    ('8', 1, 'Sistemas de escape'),
    ('9', 1, 'Transmisión'),
    ('10', 1, 'Refrigeración');


/*Cargar Provincias*/
INSERT INTO provincias (id, nombre)
VALUES
    ('1', 'Buenos Aires'),
    ('2', 'Catamarca'),
    ('3', 'Chaco'),
    ('4', 'Chubut'),
    ('5', 'Córdoba'),
    ('6', 'Corrientes'),
    ('7', 'Entre Ríos'),
    ('8', 'Formosa'),
    ('9', 'Jujuy'),
    ('10', 'La Pampa'),
    ('11', 'La Rioja'),
    ('12', 'Mendoza'),
    ('13', 'Misiones'),
    ('14', 'Neuquén'),
    ('15', 'Río Negro'),
    ('16', 'Salta'),
    ('17', 'San Juan'),
    ('18', 'San Luis'),
    ('19', 'Santa Cruz'),
    ('20', 'Santa Fe'),
    ('21', 'Santiago del Estero'),
    ('22', 'Tierra del Fuego'),
    ('23', 'Tucumán');



/*Cargar Proveedores*/
INSERT INTO proveedores (id, activo, cuil, direccion, email, localidad, razon_soc, telefono, id_provincias)
VALUES
    ('1', 1, '20343339990', 'mz 20 pc 11', 'proveedoruno@gmail.com', 'Resistencia', 'ProveedorUno', '2093330099', '2'),
    ('2', 1, '20343339991', 'av. principal 123', 'proveedordos@gmail.com', 'Buenos Aires', 'ProveedorDos', '2093330098', '1'),
    ('3', 2, '20343339992', 'calle 45 # 67-89', 'proveadortres@gmail.com', 'Medellin', 'ProveedorTres', '2093330097', '3'),
    ('4', 1, '20343339993', 'ul. Libertatii 56', 'proveedorcuatro@gmail.com', 'Bucarest', 'ProveedorCuatro', '2093330096', '4'),
    ('5', 2, '20343339994', 'via Roma 101', 'proveedorcinco@gmail.com', 'Roma', 'ProveedorCinco', '2093330095', '5'),
    ('6', 1, '20343339995', 'Rue de la Paix 22', 'proveedorseis@gmail.com', 'París', 'ProveedorSeis', '2093330094', '6'),
    ('7', 1, '20343339996', 'Main Street 789', 'proveedorsiete@gmail.com', 'Nueva York', 'ProveedorSiete', '2093330093', '7'),
    ('8', 1, '20343339997', 'Mittelweg 10', 'proveedorocho@gmail.com', 'Hamburgo', 'ProveedorOcho', '2093330092', '8'),
    ('9', 2, '20343339998', 'Rua Augusta 456', 'proveedornueve@gmail.com', 'Sao Paulo', 'ProveedorNueve', '2093330091', '9'),
    ('10', 1, '20343339999', 'Carrer de Balmes 33', 'proveedordiez@gmail.com', 'Barcelona', 'ProveedorDiez', '2093330090', '10');



