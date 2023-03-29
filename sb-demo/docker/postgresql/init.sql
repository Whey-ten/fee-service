CREATE USER cvicenie_docker WITH PASSWORD 'cvicenie_docker';

CREATE DATABASE cvicenie_docker WITH ENCODING 'UTF8';

GRANT ALL PRIVILEGES ON DATABASE cvicenie_docker to cvicenie_docker;

\connect cvicenie_docker;
create extension if not exists pg_trgm;
create extension if not exists unaccent;

create or replace function public.f_unaccent(text)
  returns text as
$func$
select public.unaccent('public.unaccent', $1)  -- schema-qualify function and dictionary
$func$  language sql immutable;

-- normalizes a text expression for searching purpose: 
-- converts text to lowercase and unaccents it
create or replace function public.f_normalize(text)
  returns text as
$func$
select lower( public.f_unaccent ($1) )
$func$  language sql immutable;

CREATE SCHEMA cvicenie_docker AUTHORIZATION cvicenie_docker;

-- cvicenie_docker."transaction" definition

-- Drop table

-- DROP TABLE cvicenie_docker."transaction";

CREATE TABLE cvicenie_docker."transaction" (
	transaction_id varchar(255) NOT NULL,
	amount numeric(38, 2) NOT NULL,
	currency varchar(255) NOT NULL,
	description varchar(255) NULL,
	effective_date date NULL,
	constant_symbol varchar(255) NULL,
	payer_reference varchar(255) NULL,
	specific_symbol varchar(255) NULL,
	variable_symbol varchar(255) NULL,
	sender_account_balance_value numeric(38, 2) NULL,
	sender_account_balance_currency varchar(255) NULL,
	sender_account_bic varchar(255) NULL,
	sender_account_iban varchar(255) NULL,
	sender_account_name varchar(255) NULL,
	status varchar(255) NOT NULL,
	target_account_balance_value numeric(38, 2) NULL,
	target_account_balance_currency varchar(255) NULL,
	target_account_bic varchar(255) NULL,
	target_account_iban varchar(255) NULL,
	target_account_name varchar(255) NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id)
);

insert into cvicenie_docker."transaction" (transaction_id, amount, currency, description, effective_date, constant_symbol, payer_reference, specific_symbol, variable_symbol, sender_account_balance_value, sender_account_balance_currency, sender_account_bic, sender_account_iban, sender_account_name, status, target_account_balance_value, target_account_balance_currency, target_account_bic, target_account_iban, target_account_name) values ('5ae57f2a-5296-4643-876b-da3d5532feb2', 42, 'EUR', 'Anvil', '2022-09-24', null, null, null, '123456', 958, 'EUR', null, 'SK8975000000000012345671', null, 'PROCESSED', null, null, 'DEUTDEFFXXX', 'DE89370400440532013000', 'Acme corp.');
insert into cvicenie_docker."transaction" (transaction_id, amount, currency, description, effective_date, constant_symbol, payer_reference, specific_symbol, variable_symbol, sender_account_balance_value, sender_account_balance_currency, sender_account_bic, sender_account_iban, sender_account_name, status, target_account_balance_value, target_account_balance_currency, target_account_bic, target_account_iban, target_account_name) values ('6fdd23ca-abd3-40e7-8e3d-ecec2ec80036', 12, 'EUR', 'Cashback', '2022-09-25', null, null, null, null, null, null, 'DEUTDEFFXXX', 'DE89370400440532013000', 'Acme corp.', 'PROCESSED', 970, 'EUR', null, 'SK8975000000000012345671', null);
insert into cvicenie_docker."transaction" (transaction_id, amount, currency, description, effective_date, constant_symbol, payer_reference, specific_symbol, variable_symbol, sender_account_balance_value, sender_account_balance_currency, sender_account_bic, sender_account_iban, sender_account_name, status, target_account_balance_value, target_account_balance_currency, target_account_bic, target_account_iban, target_account_name) values ('a77faf16-7a19-45ea-b625-9587701510e2', 10, 'EUR', 'Cashback', '2022-09-26', null, null, null, null, null, null, 'DEUTDEFFXXX', 'DE89370400440532013000', 'Acme corp.', 'PROCESSED', 1010, 'EUR', null, 'SK3112000000198742637541', null);

