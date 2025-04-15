-- Demoblaze Registration Feature - Testable Fake Database

-- 1. Create table for users
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Insert sample users
INSERT INTO users (username, password, email) VALUES 
('john_doe', 'hashed123', 'john@example.com'),
('sara92', 'hashed456', 'sara@example.com'),
('mohamed_ali', 'hashed789', 'mohamed@example.com');

-- 3. TEST CASES

-- TC_DB_001: Select user to verify registration success
SELECT * FROM users WHERE username = 'john_doe';

-- TC_DB_002: Try to register with existing username (Should Fail)
-- Uncomment to test in environment that supports errors
-- INSERT INTO users (username, password, email) VALUES ('john_doe', 'pass123', 'dup@example.com');

-- TC_DB_003: Register new user (Expected: Success)
INSERT INTO users (username, password, email) VALUES ('new_user', 'pass999', 'new_user@example.com');

-- TC_DB_004: Verify the new user was added
SELECT * FROM users WHERE username = 'new_user';

-- TC_DB_005: Attempt to register with empty username (Expected: Fail)
-- Uncomment to test invalid input
-- INSERT INTO users (username, password) VALUES ('', 'nopassword');

-- TC_DB_006: Attempt to register with NULL password (Expected: Fail)
-- Uncomment to test invalid input
-- INSERT INTO users (username, password) VALUES ('user_with_null_pass', NULL);

-- TC_DB_007: Check timestamp is auto-generated
SELECT username, created_at FROM users WHERE username = 'new_user';