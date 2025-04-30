import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import TaskList from './component/TaskList';
import AddTask from './component/AddTask';
import FilterTask from './component/FilterTask';
import { useTasks } from './hook/useTasks';

function App() {
  const {
    filteredTasks,
    newTaskText,
    setNewTaskText,
    filterTitle,
    setFilterTitle,
    toggleTaskCompletion,
    addTask,
  } = useTasks();

  const handleSubmit = (e) => {
    e.preventDefault();
    addTask();
  };

  return (
    <div className="todo-app">
      <h1>Todo App</h1>
      <AddTask
        newTaskText={newTaskText}
        setNewTaskText={setNewTaskText}
        handleSubmit={handleSubmit}
      />
      <FilterTask
        filterTitle={filterTitle}
        setFilterTitle={setFilterTitle}
      />
      <TaskList tasks={filteredTasks} onToggle={toggleTaskCompletion} />
    </div>
  );
}

export default App;