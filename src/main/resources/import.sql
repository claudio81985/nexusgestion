USE nexusgestion_db;

/* Script para la generación de valores iniciales */
/* Roles */
INSERT INTO `permisos` (`nombre`) 
VALUES 
    ('ROLE_SUPERUSUARIO'),
    ('ROLE_ADMINISTRADOR'),
    ('ROLE_EMPLEADO');


INSERT INTO `sucursales` (`nombre`) 
VALUES 
    ('Sucursal Sauzalito'),
    ('Sucursal Fontana');


/*Cargar Categoria*/
-- INSERT INTO categorias (id, activo, nombre)
-- VALUES
--     ('1',, 'Embragues'),
--     ('2',, 'Frenos'),
--     ('3',, 'Suspensiones'),
--     ('4',, 'Aceites y lubricantes'),
--     ('5',, 'Sistemas eléctricos'),
--     ('6',, 'Neumáticos'),
--     ('7',, 'Accesorios'),
--     ('8',, 'Sistemas de escape'),
--     ('9',, 'Transmisión'),
--     ('10',, 'Refrigeración');


/*Cargar Provincias*/
INSERT INTO `provincias` (`id`, `nombre`)
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
-- INSERT INTO proveedores (id, activo, cuil, direccion, email, localidad, razon_soc, telefono, id_provincias)
-- VALUES
--     ('1',, '20343339990', 'mz0 pc1', 'proveedoruno@gmail.com', 'Resistencia', 'ProveedorUno', '2093330099', '2'),
--     ('2',, '20343339991', 'av. principal23', 'proveedordos@gmail.com', 'Buenos Aires', 'ProveedorDos', '2093330098', '1'),
--     ('3',, '20343339992', 'calle 45 # 67-89', 'proveadortres@gmail.com', 'Medellin', 'ProveedorTres', '2093330097', '3'),
--     ('4',, '20343339993', 'ul. Libertatii 56', 'proveedorcuatro@gmail.com', 'Bucarest', 'ProveedorCuatro', '2093330096', '4'),
--     ('5',, '20343339994', 'via Roma01', 'proveedorcinco@gmail.com', 'Roma', 'ProveedorCinco', '2093330095', '5'),
--     ('6',, '20343339995', 'Rue de la Paix2', 'proveedorseis@gmail.com', 'París', 'ProveedorSeis', '2093330094', '6'),
--     ('7',, '20343339996', 'Main Street 789', 'proveedorsiete@gmail.com', 'Nueva York', 'ProveedorSiete', '2093330093', '7'),
--     ('8',, '20343339997', 'Mittelweg0', 'proveedorocho@gmail.com', 'Hamburgo', 'ProveedorOcho', '2093330092', '8'),
--     ('9',, '20343339998', 'Rua Augusta 456', 'proveedornueve@gmail.com', 'Sao Paulo', 'ProveedorNueve', '2093330091', '9'),
--     ('10',, '20343339999', 'Carrer de Balmes 33', 'proveedordiez@gmail.com', 'Barcelona', 'ProveedorDiez', '2093330090', '10');


INSERT INTO `proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`, `id_provincias`) 
VALUES 
    ('1', 1, '11111111111', '', 'mail@mail.com', '', 'Proveedor S.A.', '', '1');


INSERT INTO `categorias` (`id`, `activo`, `nombre`)
VALUES 
    ('1', 1, 'categoria');


INSERT INTO `productos`
(
    `activo`, 
    `codigo_identificacion`, 
    `descripcion`, 
    `nombre_comun`, 
    `nombre_tecnico`, 
    `precio`, 
    `precio_compra`, 
    `stock_general`,
    `stock_sucursal_dos`,
    `stock_sucursal_uno`,
    `id_categoria`,
    `id_proveedor`
) 
VALUES
    (1, 'PAISA001', 'x440cc x6u','ARRANCA MOTOR', 'SPEEDWAY', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA002', 'x5L x4u', 'AGUA REFRIGERANTE', 'WANDER RENIUM ROSA', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA003', 'x5L x4u', 'AGUA REFRIGERANTE', 'WANDER DESMIN', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA004', 'SURTIDO x235CC x12U', 'SILICONA LIQUIDA', 'SPEEDWAY', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA005', 'SURTIDO x420CC', 'RENOVADOR SILICONADO', 'SPEEDWAY', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA006', '30x1L', 'CADENA', 'WANDER', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA007', '', 'BUJIA', 'NITRO C7HSA', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA008', '', 'BUJIA', 'NSP C7HSA', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA009', '', 'BUJIA', 'NGK BRASIL D8EA -BR-', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA010', '150/XR25L14/XR50', 'JUNTA BASE CILINDRO ORIGINAL', 'H.CG', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA011', 'HONDA WAVE10S14 BIZZ25 L/ENC', 'REGULADOR VOLTAJE', 'K92', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA012', '26x37x10,5 GILLERA SMASH/CRYPTON', 'RETEN SUSPENSION', 'JGOX2', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA013', 'KELLER TITAN10', 'REGULADOR VOLTAJE', '(1416)', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA014', 'HONDA CD00 0,25/BIZ00', 'ARO', 'YAMASIDA', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA015', 'HONDA CD00', 'ARO', '(CH)', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA016', 'HONDA BIZZ25CC', 'VALVULA A/E', 'REPNOR', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA017', 'HONDA NXR125/XR125', 'CORONA/PIÑON KIT', 'Z54/17 NITRO', 0, '7450',0, '0', '0', '1', '1'),
    (1, 'PAISA018', 'HONDA XR25L13', 'JUNTA CABEZAL', '(CAD) PREM.', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA019', 'HONDA CG125 TITAN00/NXR25 BROSS', 'JUNTA CABEZAL', '-', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA020', 'HONDA CG150/XR150', 'GUIA DE VALVULA x2', 'A/E', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA021', 'HONDA CG125 TITAN', 'GUIA VALVULA', 'A/E 5,0MM', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA022', 'GILERA FUTURA110', 'FUNDA', 'BIG BEAST', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA023', 'ZANELLA RX150', 'EJE BALANCIN', 'INF.', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA024', 'YAMAHA YBR125', 'CABLE EMBRAGUE', 'NR.', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA025', 'HONDA BIZ100/WAVE', 'RETEN DE VALVULA', '5,0MM VINI', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA026', 'HONDA CG125/ZANELLA RX150/SKUA50', 'JUNTA CARBURADOR', 'MCS', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA027', 'ZANELLA ZTT200/DK200/GXR200', 'JUNTA CABEZAL', '(VAR) PREM.', 0, 0,0, '0', '0', '1', '1'), 
    (1, 'PAISA028', 'ZANELLA ZTT200/GILERA SMX200', 'JUNTA CABEZAL C/BAL', 'PREM.', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA029', 'YAMAHA FZ160/BAJAJ NS200', 'PASTILLA FRENO', 'FA181', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA030', 'DUKE00/BAJAJ ROUSER220 TR', 'PASTILLA DE FRENO', 'FA208=213', 0, 0,0, '0', '0', '1', '1'),    
    (1, 'PAISA031', 'HONDA NXR125/XT125L', 'CORONA/PIÑON KIT', 'Z-54/17', 0, '7450',0, '0', '0', '1', '1'),
    (1, 'PAISA032', 'CROSS VARIAS', 'RESORTE MULETA', 'UNIVERSAL', 0, '500',0, '0', '0', '1', '1'),
    (1, 'PAISA033', 'YAMAHA CRYPTON110 P/DIAFR', 'CABLE ACELERADOR', '-', 0, '3750',0, '0', '0', '1', '1'), 
    (1, 'PAISA034', 'HONDA BIZ125', 'CADENA DISTRIBUCION', 'VINI 404x92', 0, '7950',0, '0', '0', '1', '1'),
    (1, 'PAISA035', 'GILERA SMASH', 'PALANCA CAMBIO', 'ESTRIA LARGA', 0, '2400',0, '0', '0', '1', '1'),
    (1, 'PAISA036', 'HONDA NXR125 BROSS', 'CABLE EMBRAGUE', '(CH)', 0, '1100',0, '0', '0', '1', '1'), 
    (1, 'PAISA037', 'YAMAHA CRYPTON110 STD', 'ARO', '(CH)', 0, '850',0, '0', '0', '1', '1'),
    (1, 'PAISA038', 'HONDA XR125L', 'CDI VARILLERO C/AV', '12V 03/12 D01', 0, '13950',0, '0', '0', '1', '1'),
    (1, 'PAISA039', 'HONDA XRL150', 'REENVIO VELOCIMETRO', '(ENGRANAJE) ORIGINAL', 0, '2340',0, '0', '0', '1', '1'), 
    (1, 'PAISA040', 'MOTOMEL SKUA200/CORVEN TRIAX250', 'JUNTA JUEGO', 'C/BAL PREM.', 0, 0,0, '0', '0', '1', '1'),
    (1, 'PAISA041', '', 'CAMARA OSAKA', '2,50/2,75/17 NR', 0, '1900',0, '0', '0', '1', '1'),
    (1, 'PAISA042', '', 'CAMARA OSAKA', '2,75/300/14', 0, '1800',0, '0', '0', '1', '1'), 
    (1, 'PAISA043', '', 'CAMARA YAMASIDA', '2,75/300/18', 0, '1600',0, '0', '0', '1', '1'),
    (1, 'PAISA044', '', 'CAMARA YAMASIDA', '3,60/4,00/17', 0, '3100',0, '0', '0', '1', '1'),
    (1, 'PAISA045', 'CORVEN TRIAX50', 'LLAVE CONTACTO KIT', '', 0, 0,0, '0', '0', '1', '1');





insert into `usuarios` (`activo`, `clave`, `nombre`, `id_permiso`, `id_sucursal`)
values (
	1, 
	'$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO',
    'alanzanazzo',
    '1',
    '1'
);