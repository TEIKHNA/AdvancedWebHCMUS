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
  const toggleTaskCompletion = (id: number) => {
    setTasks(tasks.map((task) => (task.id === id ? { ...task, isCompleted: !task.isCompleted } : task)))
  }
  return (
    <div className="todo-app">
      <h1>Todo App</h1>
      
      <TaskList tasks={tasks} onToggle={toggleTaskCompletion} />
    </div>
  )
}

export default App
