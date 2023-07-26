/* Script para la generación de valores iniciales */
/* Roles */


/* Usuarios - en las pruebas todas las claves son 'usuario' */
INSERT INTO `nexus_db`.`usuarios` (`activo`, `clave`,`email`,`nombre`,`id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'claudio', '2');
INSERT INTO `nexus_db`.`usuarios` (`activo`, `clave`, `email`, `nombre`, `id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'alan', '1');

/*Cargar Categoria*/
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('1', 1, 'Embragues');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('2', 1, 'Valvulas');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('3', 1, 'Amortiguadores');
INSERT INTO `nexusgestion_db`.`categorias` (`id`, `activo`, `nombre`) VALUES ('4', 1, 'Cables');


/*Cargar Proveedores*/
INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `provincia`, `razon_soc`, `telefono`) VALUES ('1', 1, '2011111111', 'Avenida Rivadavia 500', 'proveedor1@gmail.com', 'Resistencia', 'Chaco', 'Proveedor Uno', '3624000000');
INSERT INTO `nexusgestion_db`.`proveedores` (`id`, `activo`, `cuil`, `direccion`, `email`, `localidad`, `provincia`, `razon_soc`, `telefono`) VALUES ('2', 1, '312222222', 'Jose Bursaco', 'proveedor2@gmail.com', 'Castelli', 'Chaco', 'Proveedor Dos', '3624000000');
