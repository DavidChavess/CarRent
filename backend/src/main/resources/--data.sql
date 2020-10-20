    CREATE TABLE Customer(
        id INT IDENTITY,
        cpf BIGINT NOT NULL,
        name VARCHAR(100) NOT NULL,
        birthdate DATE NOT NULL,
        status BOOLEAN DEFAULT(TRUE),
        CONSTRAINT pk_customer PRIMARY KEY (id)
    );