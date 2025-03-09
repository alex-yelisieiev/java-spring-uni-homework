-- Create Users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Create Employees table
CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    position VARCHAR(100) NOT NULL,
    salary NUMERIC(10, 2) NOT NULL CHECK (salary > 0)
);

-- Insert sample users
INSERT INTO users (username, password, email) VALUES
('admin', 'password123', 'admin@example.com'),
('user1', 'password123', 'user1@example.com'),
('user2', 'password123', 'user2@example.com')
ON CONFLICT (username) DO NOTHING;

-- Insert sample employees
INSERT INTO employees (first_name, last_name, email, position, salary) VALUES
('John', 'Doe', 'john.doe@example.com', 'Software Engineer', 75000.00),
('Jane', 'Smith', 'jane.smith@example.com', 'Project Manager', 85000.00),
('Michael', 'Johnson', 'michael.johnson@example.com', 'UI/UX Designer', 65000.00),
('Emily', 'Williams', 'emily.williams@example.com', 'QA Engineer', 60000.00),
('Robert', 'Brown', 'robert.brown@example.com', 'DevOps Engineer', 80000.00)
ON CONFLICT (email) DO NOTHING;