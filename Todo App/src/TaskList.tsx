interface Task {
    id: number,
    title: string,
    isCompleted: boolean,
}
interface TaskListProps {
    tasks: Task[]
    onToggle: (id: number) => void
  }
function TaskList({ tasks, onToggle }: TaskListProps) {
    return (
        <ul className="task-list">
            {tasks.map((task) => {
                return (
                    <li className={`task-item ${task.isCompleted ? "completed" : ""}`} key={task.id}>
                        <div className="task-id">{task.id}</div>
                        <span className="task-title task-text">{task.title}</span>
                        <input 
                                    type="checkbox"
                                        className="task-checkbox"
                                        checked={task.isCompleted}
                                        onChange={() => onToggle(task.id)}
                        />
                    </li>
                )
            })}
        </ul>
    
    )
}
export default TaskList