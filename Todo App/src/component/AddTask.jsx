function AddTask({ newTaskText, setNewTaskText, handleSubmit }) {
    return (
      <form onSubmit={handleSubmit} className="add-task">
        <input
          type="text"
          value={newTaskText}
          onChange={(e) => setNewTaskText(e.target.value)}
          placeholder="Add a new task..."
        />
        <button type="submit">Add</button>
      </form>
    );
  }
  
  export default AddTask;