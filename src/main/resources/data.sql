INSERT INTO app_user
    (id, name, password, role, enabled, mobile_no, code)
VALUES
    (nextval('app_user_seq'), 'pyra', '$2a$10$dn6lNXblW6sTchxRZMxzaOviTb9EikgBWk7vD4Sa5ByzMGeM.U9uq', 'ROLE_ADMIN', 1, '9870057600', '9999');
