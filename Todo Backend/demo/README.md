SPRING BOOT APPLICATION FOR TASK MANAGER (TODO)

PG ADMIN: run sql query to create table task (id, title, completed)
CREATE TABLE task (
id SERIAL PRIMARY KEY,  
 title VARCHAR(255) NOT NULL,
completed BOOLEAN NOT NULL  
);

mvn spring-boot:run

run in postman:

- get task list: GET http://localhost:8081/api/task/all

- update task detail: PUT http://localhost:8081/api/task/1/update
  body: {
  "title": "Edit Task",
  "completed": true
  }

- add task: POST http://localhost:8081/api/task/add
  body: {
  "title": "New Task",
  "priority": 1
  }
