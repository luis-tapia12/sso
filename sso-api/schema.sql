CREATE TABLE SSO_CLIENTS(
    ID VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(50),
    CLIENT_ID VARCHAR(50) UNIQUE,
    PASSWORD VARCHAR(100),
    REDIRECT_URL VARCHAR(100),
    LOGO VARCHAR(100)
);

CREATE TABLE SSO_USERS(
    ID VARCHAR(36) PRIMARY KEY,
    GENDER CHAR,
    FIRST_NAME VARCHAR(50),
    LAST_NAME VARCHAR(100),
    USER_NAME VARCHAR(50) UNIQUE,
    PASSWORD VARCHAR(100),
    DATE_OF_BIRTH TIMESTAMP,
    PHONE VARCHAR(15),
    CITY VARCHAR(50),
    PROFILE_PICTURE VARCHAR(100)
);

CREATE TABLE SSO_GROUPS(
    ID VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(50),
    DESCRIPTION VARCHAR(200)
);

CREATE TABLE SSO_GROUP_MEMBERS(
    ID VARCHAR(36) PRIMARY KEY,
    USER_ID VARCHAR(36),
    GROUP_ID VARCHAR(36),
    FOREIGN KEY (USER_ID) REFERENCES SSO_USERS(ID)
        ON DELETE CASCADE,
    FOREIGN KEY (GROUP_ID) REFERENCES SSO_GROUPS(ID)
    ON DELETE CASCADE
);