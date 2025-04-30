import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import TaskList from './component/TaskList';
import AddTask from './component/AddTask';
import FilterTask from './component/FilterTask';
import { useTasks } from './hook/useTasks';

function App() {
  return (
    <div className="todo-app">
      <h1>Todo App</h1>
    </div>
  );
}

export default App;