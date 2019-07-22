/*
Created: 20/06/2019
Modified: 21/06/2019
Model: RE PostgreSQL 9.5
Database: PostgreSQL 9.5
*/

-- Create schemas section -------------------------------------------------

CREATE SCHEMA "public" AUTHORIZATION "angelctmex"
;

COMMENT ON SCHEMA "public" IS 'standard public schema'
;

-- Create tables section -------------------------------------------------

-- Table public.authority


CREATE TABLE "oauth_access_token"(
  "token_id" Varchar DEFAULT null,
  "token" Bytea,
  "authentication_id" Varchar DEFAULT null,
  "user_name" Varchar DEFAULT null,
  "client_id" Varchar DEFAULT null,
  "authentication" Bytea,
  "refresh_token" Varchar DEFAULT null
);

CREATE TABLE IF NOT EXISTS "oauth_client_details" (
  "client_id" VARCHAR(256) PRIMARY KEY,
  "resource_ids" VARCHAR(256),
  "client_secret" VARCHAR(256) NOT NULL,
  scope VARCHAR(256),
  "authorized_grant_types" VARCHAR(256),
  "web_server_redirect_uri" VARCHAR(256),
  "authorities" VARCHAR(256),
  "access_token_validity" INTEGER,
  "refresh_token_validity" INTEGER,
  "additional_information" VARCHAR(4000),
  "autoapprove" VARCHAR(256)
);

CREATE TABLE "oauth_refresh_token"(
  "token_id" Varchar DEFAULT null,
  "token" Bytea,
  "authentication" Bytea
);

CREATE TABLE "public"."authority"(
 "name" Varchar NOT NULL
)
;
ALTER TABLE "public"."authority" ALTER COLUMN "name" SET STORAGE EXTENDED
;
COMMENT ON COLUMN "public"."authority"."name" IS 'nombre del rol'
;

-- Add keys for table public.authority

ALTER TABLE "public"."authority" ADD CONSTRAINT "Key7" PRIMARY KEY ("name")
;

-- Table public.cat_bines

CREATE TABLE "public"."cat_bines"(
 "id_bin" Bigint NOT NULL,
 "bin" Varchar NOT NULL,
 "product" Varchar DEFAULT 0 NOT NULL,
 "product_name" Varchar NOT NULL,
 "enable_bin_segment" Integer DEFAULT 0 NOT NULL,
 "timelife_seed" Integer NOT NULL,
 "timelife_cvv" Integer NOT NULL,
 "trandate" Date NOT NULL,
 "trantime" Time NOT NULL
)
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "bin" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "product" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "product_name" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "enable_bin_segment" SET STORAGE PLAIN
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "timelife_seed" SET STORAGE PLAIN
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "trandate" SET STORAGE PLAIN
;
ALTER TABLE "public"."cat_bines" ALTER COLUMN "trantime" SET STORAGE PLAIN
;
COMMENT ON COLUMN "public"."cat_bines"."id_bin" IS 'id del registro'
;
COMMENT ON COLUMN "public"."cat_bines"."bin" IS 'Almacena el bin de la institucion emisora'
;
COMMENT ON COLUMN "public"."cat_bines"."product" IS 'Almacena el producto, segmenta el numero de tarjeta en posicion 7 y 8'
;
COMMENT ON COLUMN "public"."cat_bines"."product_name" IS 'Almacena el nombre del producto'
;
COMMENT ON COLUMN "public"."cat_bines"."enable_bin_segment" IS 'Bandera que indica si el BIN esta segmentado o no'
;
COMMENT ON COLUMN "public"."cat_bines"."timelife_seed" IS 'almacena el tiempo de vida de de semilla una vez generado, el tiempo es expresado en segundos'
;
COMMENT ON COLUMN "public"."cat_bines"."timelife_cvv" IS 'almacena el tiempo de vida de un cvv para que el app genere uno nuevo, el tiempo es expresado en segundos'
;
COMMENT ON COLUMN "public"."cat_bines"."trandate" IS 'Almacena la fecha del alta del BIN'
;
COMMENT ON COLUMN "public"."cat_bines"."trantime" IS 'Almacena la hora del alta'
;

-- Add keys for table public.cat_bines

ALTER TABLE "public"."cat_bines" ADD CONSTRAINT "Key5" PRIMARY KEY ("id_bin")
;

-- Table public.cat_config

CREATE TABLE "public"."cat_config"(
 "id_conf" Bigint NOT NULL,
 "id_bin" Bigint NOT NULL,
 "name" Varchar NOT NULL,
 "value" Varchar DEFAULT 0 NOT NULL,
 "description" Varchar NOT NULL,
 "type" Varchar DEFAULT 0 NOT NULL,
 "trandate" Date NOT NULL,
 "trantime" Time NOT NULL
)
;
ALTER TABLE "public"."cat_config" ALTER COLUMN "id_conf" SET STORAGE PLAIN
;
ALTER TABLE "public"."cat_config" ALTER COLUMN "name" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_config" ALTER COLUMN "value" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_config" ALTER COLUMN "description" SET STORAGE EXTENDED
;
ALTER TABLE "public"."cat_config" ALTER COLUMN "type" SET STORAGE EXTENDED
;
COMMENT ON COLUMN "public"."cat_config"."id_conf" IS 'id del registro'
;
COMMENT ON COLUMN "public"."cat_config"."id_bin" IS 'id del bin al que pertenece esta configuración'
;
COMMENT ON COLUMN "public"."cat_config"."name" IS 'Almacena el nombre del parámetro de configuración'
;
COMMENT ON COLUMN "public"."cat_config"."value" IS 'Almacena el valor de la configuración'
;
COMMENT ON COLUMN "public"."cat_config"."description" IS 'pequeña descripcion'
;
COMMENT ON COLUMN "public"."cat_config"."type" IS 'tipo de configuración'
;
COMMENT ON COLUMN "public"."cat_config"."trandate" IS 'fecha de última modificación de la configuración'
;
COMMENT ON COLUMN "public"."cat_config"."trantime" IS 'hora de última modificación de la configuración'
;

-- Add keys for table public.cat_config

ALTER TABLE "public"."cat_config" ADD CONSTRAINT "id_config_cat" PRIMARY KEY ("id_conf","id_bin")
;

-- Table public.tbl_cards

CREATE TABLE "public"."tbl_cards"(
 "id_pan" Integer NOT NULL,
 "id_user" Bigint NOT NULL,
 "id_bin" Bigint NOT NULL,
 "pan" Varchar NOT NULL,
 "maskcard" Varchar,
 "seed" Varchar NOT NULL,
 "status_pan" Boolean DEFAULT false NOT NULL,
 "is_active_atm" Boolean DEFAULT false NOT NULL,
 "is_active_pos" Boolean DEFAULT false NOT NULL,
 "is_active_telephone" Boolean DEFAULT false NOT NULL,
 "is_active_international" Boolean DEFAULT false NOT NULL,
 "is_active_ecomerce" Boolean DEFAULT false NOT NULL,
 "limitamount_operation_atm" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_operation_pos" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_operation_telephone" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_operation_international" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_operation_ecomerce" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_daily_atm" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_daily_pos" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_daily_telephone" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_daily_international" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limitamount_daily_ecomerce" Numeric(12,2) DEFAULT '-1'::integer NOT NULL,
 "limit_transaction_daily_atm" Smallint DEFAULT '-1'::integer NOT NULL,
 "limit_transaction_daily_pos" Smallint DEFAULT '-1'::integer NOT NULL,
 "limit_transaction_daily_telephone" Smallint DEFAULT '-1'::integer NOT NULL,
 "limit_transaction_daily_international" Smallint DEFAULT '-1'::integer NOT NULL,
 "limit_transaction_daily_ecomerce" Smallint DEFAULT '-1'::integer NOT NULL,
 "date_created" Timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
 "date_update" Timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "id_pan" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "pan" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "maskcard" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "seed" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "status_pan" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "is_active_atm" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "is_active_pos" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "is_active_telephone" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "is_active_international" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "is_active_ecomerce" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_operation_atm" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_operation_pos" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_operation_telephone" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_operation_international" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_operation_ecomerce" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_daily_atm" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_daily_pos" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_daily_telephone" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_daily_international" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limitamount_daily_ecomerce" SET STORAGE MAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limit_transaction_daily_atm" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limit_transaction_daily_pos" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limit_transaction_daily_telephone" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limit_transaction_daily_international" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "limit_transaction_daily_ecomerce" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "date_created" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_cards" ALTER COLUMN "date_update" SET STORAGE PLAIN
;
COMMENT ON COLUMN "public"."tbl_cards"."id_pan" IS 'id del registro'
;
COMMENT ON COLUMN "public"."tbl_cards"."id_user" IS 'id del usuario al que pertenece la tarjeta'
;
COMMENT ON COLUMN "public"."tbl_cards"."id_bin" IS 'id del bin al que pertenece la tarjeta'
;
COMMENT ON COLUMN "public"."tbl_cards"."pan" IS 'almacena el hash de la tarjeta'
;
COMMENT ON COLUMN "public"."tbl_cards"."maskcard" IS 'almacena la tarjeta enmascarada, es opcional y solo es para fines de presentación en la app, ej, "A**** C****** T*****"'
;
COMMENT ON COLUMN "public"."tbl_cards"."seed" IS 'almacena la semilla a partir de la cual se calcularán los dcvv, esta es resgurardado por un HSM'
;
COMMENT ON COLUMN "public"."tbl_cards"."status_pan" IS 'Almacena el estatus de una tarjeta  0=Bloqueo Temporal, 1=Activa, 3=Bloqueo Definitivo'
;
COMMENT ON COLUMN "public"."tbl_cards"."is_active_atm" IS 'Valida si el canal atm está activo'
;
COMMENT ON COLUMN "public"."tbl_cards"."is_active_pos" IS 'valida si el canal POS está activo'
;
COMMENT ON COLUMN "public"."tbl_cards"."is_active_telephone" IS 'valida si el canal compras telefónicas está activo'
;
COMMENT ON COLUMN "public"."tbl_cards"."is_active_international" IS 'valida si el canal Compras internacionales está activo'
;
COMMENT ON COLUMN "public"."tbl_cards"."is_active_ecomerce" IS 'valida si el canal compras por internet está activo'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_operation_atm" IS 'almacena el limite por operación en un atm'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_operation_pos" IS 'almacena el limite por operación en una POS'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_operation_telephone" IS 'almacena el limite por operación para compras por teléfono'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_operation_international" IS 'almacena el limite por operación para compras internacionales'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_operation_ecomerce" IS 'almacena el limite de monto para compras por internet'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_daily_atm" IS 'almacena el limite de monto diario para retiros en atm'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_daily_pos" IS 'almacena el limite de monto diario para compras a través de una POS'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_daily_telephone" IS 'almacena el limite de monto diario para compras por teléfono'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_daily_international" IS 'almacena el limite de monto diario para compras internacionales'
;
COMMENT ON COLUMN "public"."tbl_cards"."limitamount_daily_ecomerce" IS 'almacena el limite de monto diario para compras por internet'
;
COMMENT ON COLUMN "public"."tbl_cards"."limit_transaction_daily_atm" IS 'almacena el limite de transacciones diario para retiros en ATM'
;
COMMENT ON COLUMN "public"."tbl_cards"."limit_transaction_daily_pos" IS 'almacena el limite de transacciones diario para compras en POS'
;
COMMENT ON COLUMN "public"."tbl_cards"."limit_transaction_daily_telephone" IS 'almacena el limite de transacciones diario para compras por teléfono'
;
COMMENT ON COLUMN "public"."tbl_cards"."limit_transaction_daily_international" IS 'almacena el limite de transacciones diario para compras internacionales'
;
COMMENT ON COLUMN "public"."tbl_cards"."limit_transaction_daily_ecomerce" IS 'almacena el limite de transacciones diario para compraspor internet'
;
COMMENT ON COLUMN "public"."tbl_cards"."date_created" IS 'Fecha de ingreso de la tarjeta'
;
COMMENT ON COLUMN "public"."tbl_cards"."date_update" IS 'Fecha de última actualización del registro'
;

-- Add keys for table public.tbl_cards

ALTER TABLE "public"."tbl_cards" ADD CONSTRAINT "pk_cardprev_card_id" PRIMARY KEY ("id_pan","id_user","id_bin")
;

ALTER TABLE "public"."tbl_cards" ADD CONSTRAINT "pan" UNIQUE ("pan")
;

ALTER TABLE "public"."tbl_cards" ADD CONSTRAINT "seed_pan" UNIQUE ("seed")
;

-- Table public.tbl_enrollment_users

CREATE TABLE "public"."tbl_enrollment_users"(
 "id_user" Bigint NOT NULL,
 "issuerclient_id" Varchar NOT NULL,
 "username" Varchar,
 "user_name_mask" Varchar,
 "birthdate" Date,
 "status" Integer NOT NULL,
 "sysdate" Date NOT NULL,
 "systime" Time NOT NULL
)
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "issuerclient_id" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "username" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "user_name_mask" SET STORAGE EXTENDED
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "birthdate" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "status" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "sysdate" SET STORAGE PLAIN
;
ALTER TABLE "public"."tbl_enrollment_users" ALTER COLUMN "systime" SET STORAGE PLAIN
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."id_user" IS 'id del registro'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."issuerclient_id" IS 'Elemento que contiene el numero de cuenta bancario del usuario'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."username" IS 'Almacena el nombre del tarjetahabiente'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."user_name_mask" IS 'Elemento que contiene el nombre enmascarado de usuario'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."birthdate" IS 'Elemento que contiene la fecha de nacimiento'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."status" IS 'Contiene el estatus para saber si esta activo o bloqueado el usuario'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."sysdate" IS 'Elemeto que contiene la fecha de enrolamiento del usuario'
;
COMMENT ON COLUMN "public"."tbl_enrollment_users"."systime" IS 'Elemento que contiene la hora del enrolamiento'
;

-- Add keys for table public.tbl_enrollment_users

ALTER TABLE "public"."tbl_enrollment_users" ADD CONSTRAINT "Key4" PRIMARY KEY ("id_user")
;

ALTER TABLE "public"."tbl_enrollment_users" ADD CONSTRAINT "issuerclient_id" UNIQUE ("issuerclient_id")
;

-- Table public.tbl_trx_log

CREATE TABLE "public"."tbl_trx_log"(
 "id_log" Bigint NOT NULL,
 "log_component" Text NOT NULL,
 "log_description" Text NOT NULL,
 "log_data" Text NOT NULL,
 "log_card_id" Bigint NOT NULL,
 "log_card_number" Bigint NOT NULL,
 "trandate" Timestamp NOT NULL
)
;
ALTER TABLE "public"."tbl_trx_log" ALTER COLUMN "id_log" SET STORAGE PLAIN
;
COMMENT ON COLUMN "public"."tbl_trx_log"."log_component" IS 'nombre del componente que registra el log'
;
COMMENT ON COLUMN "public"."tbl_trx_log"."log_description" IS 'descripción del evento que ocurrió'
;
COMMENT ON COLUMN "public"."tbl_trx_log"."log_data" IS 'trace del error'
;
COMMENT ON COLUMN "public"."tbl_trx_log"."log_card_id" IS 'id de la tarjeta que estaba operando esa transacción'
;
COMMENT ON COLUMN "public"."tbl_trx_log"."log_card_number" IS 'almacena el hash de la tarjeta que disparo el evento'
;

-- Add keys for table public.tbl_trx_log

ALTER TABLE "public"."tbl_trx_log" ADD CONSTRAINT "Key1" PRIMARY KEY ("id_log")
;

-- Table public.user_authority

CREATE TABLE "public"."user_authority"(
 "username" Varchar NOT NULL,
 "authority" Varchar NOT NULL
)
;
ALTER TABLE "public"."user_authority" ALTER COLUMN "username" SET STORAGE EXTENDED
;
ALTER TABLE "public"."user_authority" ALTER COLUMN "authority" SET STORAGE EXTENDED
;

-- Create indexes for table public.user_authority

CREATE INDEX "user_authority_idx_1" ON "public"."user_authority" ("username","authority")
;

-- Add keys for table public.user_authority

ALTER TABLE "public"."user_authority" ADD CONSTRAINT "Key8" PRIMARY KEY ("username","authority")
;

-- Table public.app_users

CREATE TABLE "public"."app_users"(
 "username" Varchar NOT NULL,
 "password" Varchar NOT NULL,
 "activated" Boolean DEFAULT false,
 "date_created" Timestamp NOT NULL,
 "date_updated" Timestamp
)
;
ALTER TABLE "public"."app_users" ALTER COLUMN "username" SET STORAGE EXTENDED
;
ALTER TABLE "public"."app_users" ALTER COLUMN "password" SET STORAGE EXTENDED
;
ALTER TABLE "public"."app_users" ALTER COLUMN "activated" SET STORAGE PLAIN
;
COMMENT ON COLUMN "public"."app_users"."username" IS 'nombre del usuario del cliente REST'
;
COMMENT ON COLUMN "public"."app_users"."activated" IS 'almacena el estatus del usuario'
;
COMMENT ON COLUMN "public"."app_users"."date_created" IS 'fecha de creación del usuario'
;
COMMENT ON COLUMN "public"."app_users"."date_updated" IS 'fecha de última modificación del usuario'
;

-- Add keys for table public.app_users

ALTER TABLE "public"."app_users" ADD CONSTRAINT "Key6" PRIMARY KEY ("username")
;

-- Create relationships section -------------------------------------------------

ALTER TABLE "public"."user_authority" ADD CONSTRAINT "user_uth" FOREIGN KEY ("username") REFERENCES "public"."app_users" ("username") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."user_authority" ADD CONSTRAINT "auth" FOREIGN KEY ("authority") REFERENCES "public"."authority" ("name") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."tbl_cards" ADD CONSTRAINT "user-card" FOREIGN KEY ("id_user") REFERENCES "public"."tbl_enrollment_users" ("id_user") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."cat_config" ADD CONSTRAINT "bin_config" FOREIGN KEY ("id_bin") REFERENCES "public"."cat_bines" ("id_bin") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "public"."tbl_cards" ADD CONSTRAINT "bin_cards" FOREIGN KEY ("id_bin") REFERENCES "public"."cat_bines" ("id_bin") ON DELETE NO ACTION ON UPDATE NO ACTION
;


-- Grant permissions section -------------------------------------------------

GRANT "pg_read_all_settings" TO "pg_monitor"
;
GRANT "pg_read_all_stats" TO "pg_monitor"
;
GRANT "pg_stat_scan_tables" TO "pg_monitor"
;

-- sequemce of ids ---------------------------------------------------

CREATE SEQUENCE enrollment_users_seq START 10
;
CREATE SEQUENCE TBL_CARDS_seq START 10
;