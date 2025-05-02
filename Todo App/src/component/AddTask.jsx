function AddTask({ newTaskTitle, setNewTaskTitle, handleSubmit }) {
  return (
    <form onSubmit={handleSubmit} className="add-task">
      <input
        type="text"
        value={newTaskTitle}
        onChange={(e) => setNewTaskTitle(e.target.value)}
        placeholder="Add a new task..."
      />
      <button type="submit">Add</button>
    </form>
  );
}

export default AddTask;