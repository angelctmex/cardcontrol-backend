/*
Created: 16/08/2016
Modified: 17/062019
Model: PostgreSQL 9.5
Database: PostgreSQL 9.4
Design: Angel Contreras Torres
Modified by:Rolando Ardelio
*/
ALTER TABLE "tbl_cards" DROP CONSTRAINT "cardholder-accounts" ;
ALTER TABLE "cat_bines" DROP CONSTRAINT "issuers-bines" ;
ALTER TABLE "tbl_enrolements_users" DROP CONSTRAINT "bin-clent" ;
ALTER TABLE "user_authority" DROP CONSTRAINT "user_uth";
ALTER TABLE "user_authority" DROP CONSTRAINT "auth";
ALTER TABLE "tbl_enrolements_users" DROP CONSTRAINT "Relationship3";
ALTER TABLE "tbl_cardsprev" DROP CONSTRAINT "card-old" ;
ALTER TABLE "cat_config" DROP CONSTRAINT "config-card-defult";

DROP TABLE "tbl_trx_log";
DROP TABLE "tbl_cards";
DROP TABLE "tbl_cardsprev";
DROP TABLE "tbl_issuers";
DROP TABLE "tbl_enrolements_users";
DROP TABLE "cat_bines";
DROP TABLE "cat_config";
DROP TABLE "users";
DROP TABLE "authority";
DROP TABLE "user_authority";
DROP TABLE "oauth_access_token";
DROP TABLE "oauth_refresh_token";
DROP TABLE "oauth_client_details";
DROP TABLE log ;