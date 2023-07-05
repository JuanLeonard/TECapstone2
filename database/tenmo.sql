BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);


COMMIT;

CREATE TABLE status (
	status_code serial NOT NULL,
	status_name varchar(50) NOT NULL,
	CONSTRAINT PK_status PRIMARY KEY (status_code),
	CONSTRAINT UQ_status_name UNIQUE (status_name)
);
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;

CREATE TABLE transfer(
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	transfer_type varchar(50) NOT NULL,
	transfer_amount numeric NOT NULL,
	transfer_status int NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfer FOREIGN KEY (transfer_status) REFERENCES status (status_code)
);
CREATE TABLE user_transfer(
	transfer_id int NOT NULL,
	to_user int NOT NULL,
	from_user int NOT NULL,
	CONSTRAINT PK_user_transfer PRIMARY KEY(transfer_id, to_user, from_user),
	CONSTRAINT FK_user_transfer_to FOREIGN KEY (to_user) REFERENCES tenmo_user (user_id),
	CONSTRAINT FK_user_transfer_from FOREIGN KEY (from_user) REFERENCES tenmo_user (user_id),
	CONSTRAINT FK_user_transfer_tfer_id FOREIGN KEY (transfer_id) REFERENCES transfer (transfer_id)
);

