-- The encrypted client_secret it `secret`
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('rajithapp', '{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 1800);
  
INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO app_users (username, password, activated, date_created, date_updated) VALUES
('admin', '{bcrypt}$2a$10$.mqkAZldK4O0XQktJ/IlVOMaZaaf1mM1HsY0EpklkximKELxaVXvG', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO app_users (username, password, activated, date_created, date_updated) VALUES
('93737013', '{bcrypt}$2a$10$.mqkAZldK4O0XQktJ/IlVOMaZaaf1mM1HsY0EpklkximKELxaVXvG', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO app_users (username, password, activated, date_created, date_updated) VALUES
('user', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO app_users (username, password, activated, date_created, date_updated) VALUES
('pegaso', '{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO user_authority (username,authority) VALUES ('93737013', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('93737013', 'ROLE_ADMIN');

-- INGRESANDO ALTA DE BINES --
INSERT INTO cat_bines VALUES(1, '565811', '00', 'Intercam Divisas.', 0, 30, 20, CURRENT_DATE, CURRENT_TIME);
INSERT INTO cat_bines VALUES(2, '565812', '00', 'Intercam Despensa.', 0, 30, 20, CURRENT_DATE, CURRENT_TIME);
INSERT INTO cat_bines VALUES(3, '565813', '00', 'Intercam ORO.', 0, 30, 20, CURRENT_DATE, CURRENT_TIME);

-- INGRESANDO ALTA DE USUARIOS ENROLADOS --
INSERT INTO tbl_enrollment_users VALUES ( 1,  '12345678','username', 'A**** C******** T*****', CURRENT_DATE, 1, CURRENT_DATE, CURRENT_TIME );
INSERT INTO tbl_enrollment_users VALUES ( 2, '93737013', 'username', 'A**** C******** T*****', CURRENT_DATE, 1, CURRENT_DATE, CURRENT_TIME );

-- INGRESANDO TARJETAS A UN CLIENTE --
INSERT INTO TBL_CARDS VALUES(1,1,1,'56581189523471257689', '5658 **** **** 5264','5658118952347125', true, true, true, true, true, true, 1000, 1000, 1000, 1000, 1000,3000, 3000, 3000, 3000, 3000,5,2,3,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TBL_CARDS VALUES(2,1,1,'56581189523852650987', '5759 **** **** 1260','5658118952347126', true, false, true, false, true, false, 900, 1100, 1100, 900, 900,3000, 1000, 800, 1500, 900, 5,2,3,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TBL_CARDS VALUES(1,2,1,'56581189523471257685', '5658 **** **** 5212','5658118952347121', true, true, true, true, true, true, 1000, 1000, 1000, 1000, 1000,3000, 3000, 3000, 3000, 3000,5,2,3,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TBL_CARDS VALUES(2,2,2,'56581189523852650981', '5759 **** **** 1214','5658118952347122', true, false, true, false, true, false, 900, 1100, 1100, 900, 900,3000, 1000, 800, 1500, 900, 5,2,3,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- INSERTANDO CONFIGURACIONES DE TARJETAS --
    INSERT INTO cat_config VALUES(1,1,'pan_status','1','status del pan','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(2,1,'atm_status','1','status del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(3,1,'pos_status','1','status del canal POS','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(4,1,'ecomerce_status','1','status del canal ECOMERCE','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(5,1,'telephone_status','1','status del canal TELEFONO','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(6,1,'international_status','1','status del canal INTERNACIONAL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(7,1,'atm_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(8,1,'pos_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(9,1,'ecomerce_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(10,1,'telephone_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(11,1,'international_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(12,1,'atm_limitamountDaily','1000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(13,1,'pos_limitamountDaily','1000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(14,1,'ecomerce_limitamountDaily','1000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(15,1,'telephone_limitamountDaily','1000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(16,1,'international_limitamountDaily','1000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(17,1,'atm_limitTransactionDaily','10','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(18,1,'pos_limitTransactionDaily','10','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(19,1,'ecomerce_limitTransactionDaily','10','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(20,1,'telephone_limitTransactionDaily','10','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(21,1,'international_limitTransactionDaily','10','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(22,2,'pan_status','1','status del pan','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(23,2,'atm_status','1','status del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(24,2,'pos_status','1','status del canal POS','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(25,2,'ecomerce_status','1','status del canal ECOMERCE','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(26,2,'telephone_status','1','status del canal TELEFONO','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(27,2,'international_status','1','status del canal INTERNACIONAL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(28,2,'atm_limitamountOperation','5000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(29,2,'pos_limitamountOperation','5000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(30,2,'ecomerce_limitamountOperation','500','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(31,2,'telephone_limitamountOperation','1500','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(32,2,'international_limitamountOperation','1000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(33,2,'atm_limitamountDaily','5000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(34,2,'pos_limitamountDaily','5000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(35,2,'ecomerce_limitamountDaily','5000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(36,2,'telephone_limitamountDaily','5000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(37,2,'international_limitamountDaily','5000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(38,2,'atm_limitTransactionDaily','5','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(39,2,'pos_limitTransactionDaily','5','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(40,2,'ecomerce_limitTransactionDaily','5','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(41,2,'telephone_limitTransactionDaily','5','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(42,2,'international_limitTransactionDaily','5','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(43,3,'pan_status','1','status del pan','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(44,3,'atm_status','1','status del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(45,3,'pos_status','1','status del canal POS','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(46,3,'ecomerce_status','1','status del canal ECOMERCE','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(47,3,'telephone_status','1','status del canal TELEFONO','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(48,3,'international_status','1','status del canal INTERNACIONAL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(49,3,'atm_limitamountOperation','20000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(50,3,'pos_limitamountOperation','20000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(51,3,'ecomerce_limitamountOperation','20000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(52,3,'telephone_limitamountOperation','20000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(53,3,'international_limitamountOperation','20000','limitamount Operation del canal ATM','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(54,3,'atm_limitamountDaily','20000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(55,3,'pos_limitamountDaily','20000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(56,3,'ecomerce_limitamountDaily','20000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(57,3,'telephone_limitamountDaily','20000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(58,3,'international_limitamountDaily','20000','limit amount Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(59,3,'atm_limitTransactionDaily','12','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(60,3,'pos_limitTransactionDaily','12','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(61,3,'ecomerce_limitTransactionDaily','12','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(62,3,'telephone_limitTransactionDaily','12','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);
    INSERT INTO cat_config VALUES(63,3,'international_limitTransactionDaily','12','limit Transaction Daily OF CHANEL','config',CURRENT_DATE,CURRENT_TIME);