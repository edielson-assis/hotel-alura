CREATE DATABASE hotel_alura;

USE hotel_alura;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS User;
CREATE TABLE User (
    UserID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(100) DEFAULT NULL,
    Email VARCHAR(255) DEFAULT NULL,
    Password VARCHAR(255) DEFAULT NULL,
    VerifyCode CHAR(6) DEFAULT NULL,
    Status CHAR(8) DEFAULT ''
);

-- ----------------------------
-- Table structure for Reservas
-- ----------------------------
CREATE TABLE Reservas (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    DataEntrada DATE,
    DataSaida DATE,
    Valor DECIMAL(10, 2),
    FormaPagamento VARCHAR(50)
);

-- ----------------------------
-- Table structure for Hospedes
-- ----------------------------
CREATE TABLE Hospedes (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100),
    Sobrenome VARCHAR(100),
    DataNascimento DATE,
    Nacionalidade VARCHAR(50),
    Telefone VARCHAR(20),
    IdReserva INT UNSIGNED,
    FOREIGN KEY (IdReserva) REFERENCES Reservas(Id) ON DELETE CASCADE
);