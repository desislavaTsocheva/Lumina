USE Lumina;

CREATE TABLE Artists (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    name VARCHAR(50) NOT NULL,
    price_per_client FLOAT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    photo VARCHAR(255) NOT NULL
);

CREATE TABLE Clients (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number CHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    password VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    photo VARCHAR(255) NOT NULL
);

CREATE TABLE Clubs (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    name NVARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    count INT NOT NULL DEFAULT 0,
    schedule VARCHAR(MAX) NOT NULL,
    town VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    artist_id UNIQUEIDENTIFIER NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES dbo.Artists(id)
);

CREATE TABLE Reservations (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    date DATETIME2(7) NOT NULL,
    count INT NOT NULL,
    type VARCHAR(100) NOT NULL,
    discount INT NOT NULL,
    table_number INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    clients_id UNIQUEIDENTIFIER NULL,
    club_id UNIQUEIDENTIFIER NULL,
    FOREIGN KEY (clients_id) REFERENCES dbo.Clients(id),
    FOREIGN KEY (club_id) REFERENCES dbo.Clubs(id)
);
