/* Drops de DB y User en caso necesario */
drop schema if exists sneakers_society;
drop user if exists ss_user;

/* Creación DB */
CREATE SCHEMA sneakers_society;

/*Se crea un usuario para la base de datos */
create user 'ss_user'@'%' identified by 'ss.User1';

/*Se asignan los prvilegios sobre la base de datos Sneakers Society al usuario creado */
grant all privileges on sneakers_society.* to 'ss_user'@'%';
flush privileges;

/* Creación tabla Marca*/
create table sneakers_society.marca (
  id_marca INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_marca)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Creación tabla Categoria*/
create table sneakers_society.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/* Creación tabla Estilo*/
create table sneakers_society.estilo (
  id_estilo INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  activo bool,
  PRIMARY KEY (id_estilo)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table sneakers_society.producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_marca INT NOT NULL,
  id_categoria INT NOT NULL,
  id_estilo INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1024), 
  precio double,
  existencias int,  
  imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  FOREIGN KEY fk_producto_marca (id_marca) REFERENCES marca(id_marca),
  FOREIGN KEY fk_producto_categoria (id_categoria) REFERENCES categoria(id_categoria),
  FOREIGN KEY fk_producto_estilo (id_estilo) REFERENCES estilo(id_estilo)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se crea la tabla de clientes llamada cliente... igual que la clase Cliente */
CREATE TABLE sneakers_society.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(512) NOT NULL, /* Posible modificación de longitud o tipo para hacer match con el encriptado */
  nombre VARCHAR(20) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  email VARCHAR(25) NULL,
  telefono VARCHAR(15) NULL,
  imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table sneakers_society.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  FOREIGN KEY fk_id_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table sneakers_society.factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_factura_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table sneakers_society.venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_producto (id_producto) references producto(id_producto) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*FALTA POR VERIFICAR */
/*Se insertan 3 registros en la tabla cliente como ejemplo */
INSERT INTO sneakers_society.usuario (id_usuario, username, password, nombre, apellido, email, telefono, imagen, activo) VALUES 
(1,'admin','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Admin', 'Default',    'admin@default.com',    '4556-8978', 'https://unavatar.io/random/',true),
(2,'user1','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','User1',  'Default', 'user1@default.com', '5456-8789','https://unavatar.io/random1/',true),
(3,'user2','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','User2', 'Default',     'user2@default.com',      '7898-8936','https://unavatar.io/random2/',true);

/*Insertar Marcas de prueba */
INSERT INTO sneakers_society.marca (id_marca, descripcion, imagen, activo) VALUES 
('1','Nike', 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg',   true), 
('2','Adidas',  'https://assets.adidas.com/images/w_600,f_auto,q_auto/a16142ed99fe4ceb9d5fae6f00ceb094_9366/Tenis_adi2000_Blanco_GV9544_01_standard.jpg', true),
('3','Rebook','https://f.fcdn.app/imgs/cba8bd/www.zooko.com.uy/zoo/5cfb/original/catalogo/ADIF3001-WHI-1/460x460/adidas-superstar-xlg-white.jpg',true),
('4','New Balance','https://www.kicks.cr/media/catalog/product/d/h/dh6927-161-phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700&format=jpeg',    false);

/*Insertar Categorias de prueba */
INSERT INTO sneakers_society.categoria (id_categoria, descripcion, imagen, activo) VALUES 
(1,'Deportivo', 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true), 
(2,'Casual',  'https://assets.adidas.com/images/w_600,f_auto,q_auto/a16142ed99fe4ceb9d5fae6f00ceb094_9366/Tenis_adi2000_Blanco_GV9544_01_standard.jpg', true),
(3,'Retro','https://f.fcdn.app/imgs/cba8bd/www.zooko.com.uy/zoo/5cfb/original/catalogo/ADIF3001-WHI-1/460x460/adidas-superstar-xlg-white.jpg', true),
(4,'Skate', 'https://www.kicks.cr/media/catalog/product/d/h/dh6927-161-phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700&format=jpeg', false);

/*Insertar Estilos de prueba */
INSERT INTO sneakers_society.estilo (id_estilo, descripcion, activo) VALUES 
(1,'Hombre', true), 
(2,'Mujer', true),
(3,'Niño',true);

/*Insertar Productos de prueba */
INSERT INTO sneakers_society.producto (id_producto, id_marca, id_categoria, id_estilo, descripcion, detalle, precio, existencias, imagen,activo) VALUES
(1,1,1,1,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true),
(2,1,1,2,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true),
(3,1,1,3,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true),
(4,2,2,1,'Adidas Superstar', 'Zapatillas Adidas Superstar', 45000, 10, 'https://assets.adidas.com/images/w_600,f_auto,q_auto/a16142ed99fe4ceb9d5fae6f00ceb094_9366/Tenis_adi2000_Blanco_GV9544_01_standard.jpg', true),
(5,2,2,2,'Adidas Superstar', 'Zapatillas Adidas Superstar', 45000, 10, 'https://assets.adidas.com/images/w_600,f_auto,q_auto/a16142ed99fe4ceb9d5fae6f00ceb094_9366/Tenis_adi2000_Blanco_GV9544_01_standard.jpg', true),
(6,2,2,3,'Adidas Superstar', 'Zapatillas Adidas Superstar', 45000, 10, 'https://assets.adidas.com/images/w_600,f_auto,q_auto/a16142ed99fe4ceb9d5fae6f00ceb094_9366/Tenis_adi2000_Blanco_GV9544_01_standard.jpg', true),
(7,3,3,1,'Rebook Classic', 'Zapatillas Rebook Classic', 45000, 10, 'https://f.fcdn.app/imgs/cba8bd/www.zooko.com.uy/zoo/5cfb/original/catalogo/ADIF3001-WHI-1/460x460/adidas-superstar-xlg-white.jpg', true),
(8,3,3,2,'Rebook Classic', 'Zapatillas Rebook Classic', 45000, 10, 'https://f.fcdn.app/imgs/cba8bd/www.zooko.com.uy/zoo/5cfb/original/catalogo/ADIF3001-WHI-1/460x460/adidas-superstar-xlg-white.jpg', true),
(9,3,3,3,'Rebook Classic', 'Zapatillas Rebook Classic', 45000, 10, 'https://f.fcdn.app/imgs/cba8bd/www.zooko.com.uy/zoo/5cfb/original/catalogo/ADIF3001-WHI-1/460x460/adidas-superstar-xlg-white.jpg', true),
(10,4,4,1,'New Balance 574', 'Zapatillas New Balance 574', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh6927-161-phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700&format=jpeg', true),
(11,4,4,2,'New Balance 574', 'Zapatillas New Balance 574', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh6927-161-phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700&format=jpeg', true),
(12,4,4,3,'New Balance 574', 'Zapatillas New Balance 574', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh6927-161-phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700&format=jpeg', true),
(13,1,1,1,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true),
(14,1,1,2,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true),
(15,1,1,3,'Nike Air Max 90', 'Zapatillas Nike Air Max 90', 45000, 10, 'https://www.kicks.cr/media/catalog/product/d/h/dh2920-111-frontf1-001.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg', true);

/* Se crean 6 facturas 'Activa','Pagada','Anulada')*/
INSERT INTO sneakers_society.factura (id_factura,id_usuario,fecha,total,estado) VALUES
(1,1,'2022-01-05',211560,2),
(2,2,'2022-01-07',554340,2),
(3,3,'2022-01-07',871000,2),
(4,1,'2022-01-15',244140,1),
(5,2,'2022-01-17',414800,1),
(6,3,'2022-01-21',420000,1);

INSERT INTO sneakers_society.venta (id_venta,id_factura,id_producto,precio,cantidad) values
(1,1,5,45000,3),
(2,1,9,15780,2),
(3,1,10,15000,3),
(4,2,5,45000,1),
(5,2,14,154000,3),
(6,2,9,15780,3),
(7,3,14,154000,1),
(8,3,6,57000,1),
(9,3,15,330000,2),
(10,1,6,57000,2),
(11,1,8,27600,3),
(12,1,9,15780,3),
(13,2,8,27600,3),
(14,2,14,154000,2),
(15,2,3,24000,1),
(16,3,15,330000,1),
(17,3,12,45000,1),
(18,3,10,15000,3);

insert into sneakers_society.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN', 1), (2, 'ROLE_USER', 1),
 (3,'ROLE_USER', 2), (4, 'ROLE_USER', 3);