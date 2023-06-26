INSERT INTO usuario(telefono, nombre_usuario, tipo_usuario)
VALUES (156453,'Miguel','CLIENTE'),
       (654896,'Carlos', 'AGENTE_DE_VENTAS'),
       (984866,'Juan', 'ADMINISTRADOR');

INSERT INTO vuelo(disponible, capacidad, aerolinea, horario_partida, horario_llegada, precio, origen, destino)
VALUES (TRUE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (FALSE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (FALSE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (TRUE,50,'Aerolineas Uruguayas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay');

INSERT INTO reserva(tipo_pago, usuario_id, vuelo_id)
VALUES ('TARJETA_CREDITO',1,1);

INSERT INTO butaca(disponible, posicion, vuelo_id)
VALUES (TRUE, 'AE04', 1),
       (TRUE, 'AE05', 1);