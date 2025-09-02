CREATE TABLE IF NOT EXISTS books (
                                        id VARCHAR(24) PRIMARY KEY,    -- MongoDB ObjectId is a 24-character hex string
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    year VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
