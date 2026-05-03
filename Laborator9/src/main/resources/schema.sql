CREATE TABLE IF NOT EXISTS customers
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS agents
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL,
    status      VARCHAR(30)  NOT NULL,
    priority    VARCHAR(30)  NOT NULL,
    customer_id INT          NOT NULL,
    agent_id    INT,
    category_id INT          NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (agent_id) REFERENCES agents (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    ticket_id INT  NOT NULL,
    message   TEXT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (id)
);