import { useState } from 'react';

export const useTasks = () => {
  const [tasks, setTasks] = useState([
    { id: 1, title: "Complete project proposal", isCompleted: false },
    { id: 2, title: "Buy groceries", isCompleted: true },
    { id: 3, title: "Go for a run", isCompleted: false },
  ]);

  const [newTaskText, setNewTaskText] = useState("");
  const [filterTitle, setFilterTitle] = useState("");

  const toggleTaskCompletion = (id) => {
    setTasks(tasks.map((task) => (task.id === id ? { ...task, isCompleted: !task.isCompleted } : task)));
  };

  const addTask = () => {
    if (newTaskText.trim() !== "") {
      const newTask = {
        id: tasks.length > 0 ? Math.max(...tasks.map((task) => task.id)) + 1 : 1,
        title: newTaskText,
        isCompleted: false,
      };
      setTasks([...tasks, newTask]);
      setNewTaskText(""); // Clear input after adding
    }
  };

  const filteredTasks = tasks.filter((task) =>
    task.title.toLowerCase().includes(filterTitle.toLowerCase())
  );

  return {
    tasks,
    filteredTasks,
    newTaskText,
    setNewTaskText,
    filterTitle,
    setFilterTitle,
    toggleTaskCompletion,
    addTask,
  };
};