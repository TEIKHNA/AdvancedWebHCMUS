import { useState } from 'react'
import './App.css'
import TaskList from './TaskList'
interface Task {
  id: number
  title: string
  isCompleted: boolean
}
function App() {
  const [tasks, setTasks] = useState<Task[]>([
    { id: 1, title: "Complete project proposal", isCompleted: false },
    { id: 2, title: "Buy groceries", isCompleted: true },
    { id: 3, title: "Go for a run", isCompleted: false },
  ])
  const [newTaskText, setNewTaskText] = useState("")
  const [filterTitle, setFilterTitle] = useState("")
  const toggleTaskCompletion = (id: number) => {
    setTasks(tasks.map((task) => (task.id === id ? { ...task, isCompleted: !task.isCompleted } : task)))
  }
  const addTask = () => {
    if (newTaskText.trim() !== "") {
      const newTask: Task = {
        id: tasks.length > 0 ? Math.max(...tasks.map((task) => task.id)) + 1 : 1,
        title: newTaskText,
        isCompleted: false,
      }
      setTasks([...tasks, newTask])
      setNewTaskText("") // Clear input after adding
    }
  }
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    addTask()
  }
  const filteredTasks = tasks.filter((task) => task.title.toLowerCase().includes(filterTitle.toLowerCase()))
  return (
    <div className="todo-app">
      <h1>Todo App</h1>
      <form onSubmit={handleSubmit} className="add-task">
        <input
          type="text"
          value={newTaskText}
          onChange={(e) => setNewTaskText(e.target.value)}
          placeholder="Add a new task..."
        />
        <button type="submit">Add</button>
      </form>
      <div className="filter-container">
        <input
          type="text"
          value={filterTitle}
          onChange={(e) => setFilterTitle(e.target.value)}
          placeholder="Filter tasks by title..."
          className="filter-input"
        />
      </div>
      <TaskList tasks={filteredTasks} onToggle={toggleTaskCompletion} />
    </div>
  )
}

export default App
