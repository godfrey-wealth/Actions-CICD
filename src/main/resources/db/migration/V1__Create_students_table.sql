CREATE TABLE IF NOT EXISTS students (
                                        id VARCHAR(24) PRIMARY KEY,    -- MongoDB ObjectId is a 24-character hex string
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    student_id VARCHAR(100) UNIQUE NOT NULL,
    course VARCHAR(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
