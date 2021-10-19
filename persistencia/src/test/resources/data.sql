insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Pereira");
insert into ciudad values (3, "Cali");

insert into usuario values ("123", "pepe@email.com", "Pepe" , "13434", "pepe", 1);
insert into usuario values ("124", "maria@email.com", "Maria" , "43433", "maria", 2);
insert into usuario values ("125", "juan@email.com", "Juan" , "3333", "juan", 1);

insert into usuario_telefonos values ("123", "718718");
insert into usuario_telefonos values ("123", "2232");


insert into producto values (1, "Telefono Samsung s21 como nuevo", 0 , "2021/10/30", "Samsung S21", "Samsung S21 x", 4000000,3,1,"123");
insert into producto values (2, "Nintendo Switch nueva sellada", 0 , "2021/10/30", "Nintendo Switch", "Nintendo Switch OLED", 2000000,2,2,"124");
insert into producto values (3, "Tennis Adidas Originales", 0.05 , "2021/10/30", "Tennis Adidas", "Tennis Adidas originales", 230000,3,2,"124");

insert into producto_categorias values (1, 0);
insert into producto_categorias values (2, 0);
insert into producto_categorias values (3, 1);
insert into producto_categorias values (3, 2);

insert into producto_imagenes values (1, "ruta1/img1.jpg");
insert into producto_imagenes values (1, "ruta1/img1.jpg");
insert into producto_imagenes values (2, "ruta2/img.jpg");
insert into producto_imagenes values (3, "ruta3/img2.jpg");
insert into producto_imagenes values (3, "ruta3/img1.jpg");

insert into compra values (1, "2021/09/05 20:12:05","PSE", "125");
insert into compra values (2, "2021/10/05 12:10:09", "tarjeta crédito VISA", "123");
insert into compra values (3, "2021/10/05 12:10:09", "tarjeta  crédito", "124");

insert into detalle_compra values(1, 2000000, 1, 1, 2);
insert into detalle_compra values(2, 225000, 1, 1, 3);
insert into detalle_compra values(3, 4000000, 1, 2, 1);

insert into comentario values (1, 3, "2021/10/06 07:12:32", "Producto regular, no me gustó tanto", "", 3, "125");
insert into comentario values (2, 5, "2021/10/07 13:29:48", "Excelente celular, xd", "",1, "123");

insert into usuario_productos_favoritos values ("123", 2);
insert into usuario_productos_favoritos values ("123", 3);
insert into usuario_productos_favoritos values ("125", 2);

insert into chat values(1, 2, "123");
insert into chat values(2, 1, "124");
insert into chat values(3, 3, "125");

insert into mensaje values(1,"Sebastian", "2021/09/05 20:12:05", "hola este es mi msje", 1);
insert into mensaje values(2,"Dario", "2021/09/05 20:12:05", "hola este es mi msje", 1);
insert into mensaje values(3,"Maria", "2021/09/05 20:12:05", "hola este es mi msje", 1);


insert into subasta values (1, "2021/09/05 20:12:05", 1);
insert into subasta values (2, "2021/09/05 20:12:05", 2);
insert into subasta values (3, "2021/09/05 20:12:05", 3);


insert into detalle_subasta values(1, "2021/09/05 20:12:05", 40000,1,"123");
insert into detalle_subasta values(2, "2021/09/05 20:12:05", 60000,2,"124");
insert into detalle_subasta values(3, "2021/09/05 20:12:05", 80000,3,"125");


