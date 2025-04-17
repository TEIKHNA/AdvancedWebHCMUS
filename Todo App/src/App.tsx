import './App.css';
import TaskList from './TaskList';
import { useTasks } from './useTasks';

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

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    addTask();
  };

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
  );
}

export default App;