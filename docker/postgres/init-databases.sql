-- task-service
CREATE USER task_user WITH PASSWORD 'task_password';
CREATE DATABASE task OWNER task_user;

-- user-service ("user" is a reserved word in PostgreSQL)
CREATE USER user_user WITH PASSWORD 'user_password';
CREATE DATABASE "user" OWNER user_user;

\c task
CREATE SCHEMA IF NOT EXISTS task AUTHORIZATION task_user;
GRANT ALL ON SCHEMA task TO task_user;
ALTER ROLE task_user SET search_path TO task;

\c "user"
CREATE SCHEMA IF NOT EXISTS "user" AUTHORIZATION user_user;
GRANT ALL ON SCHEMA "user" TO user_user;
ALTER ROLE user_user SET search_path TO "user";
