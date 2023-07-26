/* Script para la generación de valores iniciales */
/* Roles */
INSERT INTO `nexus_db`.`permisos` (`id`, `nombre`) VALUES ('1', 'ROLE_USER');
INSERT INTO `nexus_db`.`permisos` (`id`, `nombre`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `nexus_db`.`permisos` (`id`, `nombre`) VALUES ('3', 'ROLE_CLIENTE');

/* Usuarios - en las pruebas todas las claves son 'usuario' */
INSERT INTO `nexus_db`.`usuarios` (`activo`, `clave`,`email`,`nombre`,`id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'claudio', '2');
INSERT INTO `nexus_db`.`usuarios` (`activo`, `clave`, `email`, `nombre`, `id_permiso`) VALUES ('1', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', 'cmarangoni8@gmail.com', 'alan', '1');

/*Cargar Categoria*/
INSERT INTO `nexus_db`.`categorias` (`nombre`) VALUES ('Embragues'); 
INSERT INTO `nexus_db`.`categorias` (`nombre`) VALUES ('Pistones');
INSERT INTO `nexus_db`.`categorias` (`nombre`) VALUES ('Valvulas');
INSERT INTO `nexus_db`.`categorias` (`nombre`) VALUES ('Amortiguadores');

/*Cargar Proveedores*/
INSERT INTO `nexus_db`.`proveedores` (`activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`) VALUES (1, '20318720852', 'Jose Hernandez 321', 'proveedor1@gamil.com', 'Fontana', 'Proveedor Uno', '3624883252');
INSERT INTO `nexus_db`.`proveedores` (`activo`, `cuil`, `direccion`, `email`, `localidad`, `razon_soc`, `telefono`) VALUES (1, '27320889389', 'Avenida 9 de Julio 315', 'proveedor2@gamil.com', 'Resistencia', 'Proveedor Dos', '3624883252');