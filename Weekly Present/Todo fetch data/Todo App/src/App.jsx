import React from 'react';
import './App.css';
import TaskList from './component/TaskList';
import AddTask from './component/AddTask';
import FilterTask from './component/FilterTask';
import { useTaskContext } from './context/TaskContext';

function App() {
  const { state, dispatch } = useTaskContext();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (state.newTaskTitle.trim() !== "") {
      dispatch({ type: "ADD_TASK", payload: state.newTaskTitle });
    }
  };

  const handleToggleTask = (taskId) => {
    dispatch({ type: "TOGGLE_TASK", payload: taskId });
  };

  const handleSetNewTaskTitle = (title) => {
    dispatch({ type: "SET_NEW_TASK_TITLE", payload: title });
  };

  const handleSetFilterTitle = (title) => {
    dispatch({ type: "SET_FILTER_TITLE", payload: title });
  };

  const filteredTasks = state.tasks.filter((task) =>
    task.title.toLowerCase().includes(state.filterTitle.toLowerCase())
  );

  return (
    <div className="todo-app">
      <h1>Todo App</h1>
      <AddTask
        newTaskTitle={state.newTaskTitle}
        setNewTaskTitle={handleSetNewTaskTitle}
        handleSubmit={handleSubmit}
      />
      <FilterTask
        filterTitle={state.filterTitle}
        setFilterTitle={handleSetFilterTitle}
      />
      <TaskList tasks={filteredTasks} onToggleTask={handleToggleTask} />
    </div>
  );
}

export default App;