👉 Students make a simple task management application (todoApp) from scratch that provides some following features in only 01 view/screen
------ Show all tasks
------ Add new task
------ Mark a task as a completed task. Comleted task(s) should have different style from incomplete task(s)
------ Filter some tasks by their title/name

👉 Technical requiments for version 1
------ todoApp main view should be a combination of at least 3 components: taskList, addTask, filterTask, ...
------ No backend is required; task data can be set up within your source code or read/written from localStorage.

npm install
npm run dev
Step1: Show all tasks:
    TaskList.tsx:
        interface Task, TaskListProps
        ul {task.map(task => {return (li)})}
    useTasks.ts
        interface
        const [tasks,setTasks]
        const onToggle = (id: number) => {
            setTasks(tasks.map(task => (task.id === id ? { ...task, isCompleted: !task.isCompleted } : task)))
        };
    App.ccs 
        .task-list 
        .task-item 
Step2: Add task:
    useTasks:
        const [newTaskTitle, setNewTaskTitle]
        addTask newTaskTitle
    App
        const handleSubmit = (e: React.FormEvent) => {
            e.preventDefault()
            addTask(newTaskTitle)
        }
        form onSubmit={handleSubmit}
            input onChange={(e) => setNewTaskTitle(e.target.value)} 
    App.css 
        .add-task
Step3: Filter task:
    useTasks:
        const filterTitle, setFilterTitle ""
        const filtererdTasks tasks.filter(task => 
            task.title.toLowerCase().includes(filterTitle.toLowerCase()))
    App:
        input filter-input