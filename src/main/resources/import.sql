/* Script para la generación de valores iniciales */
/* Roles */
INSERT INTO `nexusgestion_db`.`permisos` (`nombre`) VALUES ('ROLE_SUCURSALFONTANA');
INSERT INTO `nexusgestion_db`.`permisos` (`nombre`) VALUES ('ROLE_SUCURSALSAUSALITO');




/* Usuarios - en las pruebas todas las claves son 'usuario' */
INSERT INTO `nexusgestion_db`.`usuarios` ( `activo`, `clave`, `nombre`, `id_permiso`) VALUES ( 1, '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'claudio', '1');
INSERT INTO `nexusgestion_db`.`usuarios` ( `activo`, `clave`, `nombre`, `id_permiso`) VALUES ( 1,'$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'alan', '2');

/*Cargar Categoria*/
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('1', 1, 'Embragues');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('2', 1, 'Valvulas');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('3', 1, 'Amortiguadores');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('4', 1, 'Cables');


/*Cargar Proveedores*/
INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`, `id_provincias`) VALUES ('1', 1, '20343339990', 'mz 20 pc 11', 'proveedoruno@gmail.com', 'Resistencia', 'ProveedorUno', '2093330099', '2');
INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`, `id_provincias`) VALUES ('2', 1, '20343339990', 'mz 20 pc 11', 'proveedoruno@gmail.com', 'Resistencia', 'ProveedorUno', '2093330099', '2');

/*Cargar Provincias*/
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('1', 'Buenos Aires');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('2', 'Catamarfca');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('3', 'Chaco');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('4', 'Chubut');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('5', 'Cordoba');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('6', 'Corrientes');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('7', 'Jujuy');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('8', 'La Pampa');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('9', 'La Rioja');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('10', 'Mendoza');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('11', 'Misiones');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('12', 'Neuquen');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('13', 'Rio Negro');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('14', 'Salta');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('15', 'San Juan');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('16', 'Santa Cruz');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('17', 'Santa Fe');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('18', 'Santiago del Estero');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('19', 'Tierra del Fuego');
-- INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('20', 'Tucuman');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('1', 'Chaco');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('2', 'Corrientes');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('3', 'Formosa');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('4', 'Misiones');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('5', 'Jujuy');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('6', 'Salta');


INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`, `id_provincias`) VALUES ('1', '1', '12323432', 'mz 20 pc', 'proveedor@gmail.com', 'resietencia', 'proveedor', '1234534567', '1');

/*Cargar Producto*/
INSERT INTO `nexusgestion_db`.`productos` (`id`, `activo`, `codigo_identificacion`, `descripcion`, `nombre_comun`, `nombre_tecnico`, `precio`, `stock_general`, `stock_sucursal_dos`, `stock_sucursal_uno`, `id_categoria`, `id_proveedor`) VALUES ('1', 1, '1234567', 'para 125 cc', 'cable', 'cable universal', '2800', '8', '5', '3', '1', '1');