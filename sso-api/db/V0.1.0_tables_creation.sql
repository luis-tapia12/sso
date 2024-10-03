CREATE DATABASE carbonx;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA sso;

CREATE TABLE sso.users(
    id UUID PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    profile_picture_url VARCHAR(100),
    language CHAR(2)
);

CREATE TABLE sso.applications(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    state CHAR(1),
    description VARCHAR(255),
    client_id VARCHAR(50) UNIQUE NOT NULL,
    client_secret VARCHAR(100) NOT NULL,
    redirect_url VARCHAR(100) NOT NULL,
    logo_url VARCHAR(100)
);

CREATE TABLE sso.application_roles(
    id UUID PRIMARY KEY,
    application_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (application_id) REFERENCES sso.applications(id)
);

CREATE TABLE sso.application_user_roles(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    enabled BOOLEAN,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES sso.users(id),
    FOREIGN KEY (role_id) REFERENCES sso.application_roles(id)
);

CREATE TABLE sso.role_permissions(
    id UUID PRIMARY KEY,
    role_id UUID NOT NULL,
    name VARCHAR(100),
    description VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES sso.application_roles(id)
);

CREATE TABLE sso.user_passwords(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    old_password VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES sso.users(id)
);
