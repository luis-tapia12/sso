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