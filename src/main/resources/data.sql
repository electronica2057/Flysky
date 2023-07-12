INSERT INTO usuario(telefono, nombre_usuario, tipo_usuario)
VALUES (156453,'Miguel','CLIENTE'),
       (654896,'Carlos', 'AGENTE_DE_VENTAS'),
       (984866,'Juan', 'ADMINISTRADOR'),
       (666666,'Mariano','CLIENTE');

INSERT INTO vuelo(disponible, capacidad, aerolinea, fecha_hora_partida, fecha_hora_llegada, precio, origen, destino)
VALUES (TRUE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (FALSE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (FALSE,50,'Aerolineas Argentinas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay'),
       (TRUE,50,'Aerolineas Uruguayas','2023-06-25 23:53:30','2023-06-25 23:53:30', 15000, 'Buenos Aires', 'Uruguay');

INSERT INTO butaca(disponible, posicion, vuelo_id)
VALUES (FALSE, 'AE04', 1),
       (FALSE, 'AE05', 1),
       (FALSE, 'AE06', 1),
       (FALSE, 'AE07', 1),
       (TRUE, 'AE08', 1);

INSERT INTO reserva(tipo_pago, usuario_id, vuelo_id, monto_pago, fecha_reserva, butaca_id)
VALUES ('TARJETA_CREDITO', 1, 1, 1500, '2023-06-25', 1),
       ('TRANSFERENCIA_BANCARIA', 1, 2, 2000, '2023-06-29', 2),
       ('EFECTIVO', 1, 2, 3000, '2023-06-29', 3),
       ('EFECTIVO', 4, 4, 1700, '2023-06-30', 4);


