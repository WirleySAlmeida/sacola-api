INSERT INTO restaurant (id, postal_code, complement, name) VALUES
(1L, '00000001', 'Complemento Endereço Restaurante 1', 'Restaurante 1'),
(2L, '00000002', 'Complemento Endereço Restaurante 2', 'Restaurante 2');

INSERT INTO customer (id, postal_code, complement, name) VALUES
(1L, '00000001', 'Complemento Endereço Cliente 1', 'Cliente 1');

INSERT INTO product (id, available, name, unit_value, restaurant_id) VALUES
(1L, true, 'Produto 1', 5.0, 1L),
(2L, true, 'Produto 2', 6.0, 1L),
(3L, true, 'Produto 3', 7.0, 2L);

INSERT INTO bag (id, payment_method, closed, total_value, customer_id) VALUES
(1L, 0, false, 0.0, 1L);