CREATE USER 'debezium'@'%' IDENTIFIED BY 'dbzpassword';

-- Global privileges needed by Debezium
GRANT RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'debezium'@'%';

-- Database-specific privileges
GRANT ALL PRIVILEGES ON testdb.* TO 'debezium'@'%';

-- Apply changes
FLUSH PRIVILEGES;
