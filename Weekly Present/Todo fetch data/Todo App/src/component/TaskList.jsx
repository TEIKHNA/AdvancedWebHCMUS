function TaskList({ tasks, onToggleTask }) {
  return (
    <ul className="task-list">
      {tasks.map((task) => (
        <li className={`task-item ${task.isCompleted ? "completed" : ""}`} key={task.id}>
          <div className="task-id">{task.id}</div>
          <span className="task-title task-text">{task.title}</span>
          <input
            type="checkbox"
            className="task-checkbox"
            checked={task.isCompleted}
            onChange={() => onToggleTask(task.id)}
          />
        </li>
      ))}
    </ul>
  );
}

export default TaskList;