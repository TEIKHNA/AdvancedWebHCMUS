👉 Students make a simple task management application (todoApp) from scratch that provides some following features in only 01 view/screen
------ Show all tasks
------ Add new task
------ Mark a task as a completed task. Comleted task(s) should have different style from incomplete task(s)
------ Filter some tasks by their title/name

👉 Technical requiments for version 1
------ todoApp main view should be a combination of at least 3 components: taskList, addTask, filterTask, ...
------ No backend is required; task data can be set up within your source code or read/written from localStorage.
1 Show all task:
TaskList.tsx:
    interface Task,TaskListProps
    function TaskList({tasks, onToggle}: TaskListProps) - task-item:task-id,task-title, task-checkbox
App.css : .task-list .task-item .task-text .task-id
2. Add task
