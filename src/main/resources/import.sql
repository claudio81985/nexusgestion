/* Script para la generación de valores iniciales */
/* Roles */


/* Usuarios - en las pruebas todas las claves son 'usuario' */
INSERT INTO `nexusgestion_db`.`usuarios` (`activo`, `clave`,`email`,`nombre`,`id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'claudio', '2');
INSERT INTO `nexusgestion_db`.`usuarios` (`activo`, `clave`, `email`, `nombre`, `id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'alan', '1');

/*Cargar Categoria*/
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('1', 1, 'Embragues');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('2', 1, 'Valvulas');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('3', 1, 'Amortiguadores');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('4', 1, 'Cables');


/*Cargar Proveedores*/
INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`, `id_provincias`) VALUES ('1', 1, '20318720993', 'avenida itala', 'proveedor@gmail.com', 'resistencia', 'Proveedor Uno', '3827002208', '2');



/*Cargar Provincias*/
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('1', 'Buenos Aires');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('2', 'Catamarfca');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('3', 'Chaco');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('4', 'Chubut');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('5', 'Cordoba');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('6', 'Corrientes');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('7', 'Jujuy');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('8', 'La Pampa');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('9', 'La Rioja');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('10', 'Mendoza');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('11', 'Misiones');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('12', 'Neuquen');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('13', 'Rio Negro');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('14', 'Salta');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('15', 'San Juan');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('16', 'Santa Cruz');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('17', 'Santa Fe');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('18', 'Santiago del Estero');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('19', 'Tierra del Fuego');
INSERT INTO `nexusgestion_db`.`provincias` (`id`, `nombre`) VALUES ('20', 'Tucuman');