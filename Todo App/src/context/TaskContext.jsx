import React, { createContext, useReducer, useContext, useEffect } from 'react';

const initialState = {
  tasks: [],
  newTaskTitle: "", 
  filterTitle: "",  
};

const addTaskToAPI = async (newTask) => {
  try {
    const response = await fetch("http://localhost:8081/api/task/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newTask),
    });

    const data = await response.json();
    if (!response.ok || !data.success) {
      alert("Failed to add task. Please check the API.");
    }
  } catch (error) {
    alert("Unable to connect to the API. Please ensure the server is running.");
  }
};

const updateTaskInAPI = async (taskId, updatedTask) => {
  try {
    const response = await fetch(`http://localhost:8081/api/task/${taskId}/update`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedTask),
    });

    const data = await response.json();
    if (!response.ok || !data.success) {
      console.error("Failed to update task:", data.message || "Unknown error");
      alert("Failed to update task. Please check the API.");
    }
  } catch (error) {
    console.error("Error updating task in API:", error);
    alert("Unable to connect to the API. Please ensure the server is running.");
  }
};

const taskReducer = (state, action) => {
  switch (action.type) {
    case "SET_TASKS":
      return { ...state, tasks: action.payload };

    case "ADD_TASK":
      if (action.payload.trim() === "") return state;

      const newTask = {
        id: state.tasks.length > 0 ? Math.max(...state.tasks.map((t) => t.id)) + 1 : 1,
        title: action.payload,
        isCompleted: false, 
      };

      addTaskToAPI(newTask);

      return {
        ...state,
        tasks: [...state.tasks, newTask],
        newTaskTitle: "",
      };

    case "TOGGLE_TASK":
      const updatedTasks = state.tasks.map((task) => {
        if (task.id === action.payload) {
          const updatedTask = { ...task, isCompleted: !task.isCompleted };
          updateTaskInAPI(task.id, {
            title: updatedTask.title,
            completed: updatedTask.isCompleted,
          }); 
          return updatedTask;
        }
        return task;
      });

      return {
        ...state,
        tasks: updatedTasks,
      };

    case "SET_NEW_TASK_TITLE":
      return { ...state, newTaskTitle: action.payload };

    case "SET_FILTER_TITLE":
      return { ...state, filterTitle: action.payload };

    default:
      return state;
  }
};

const TaskContext = createContext();

export const TaskProvider = ({ children }) => {
  const [state, dispatch] = useReducer(taskReducer, initialState);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await fetch("http://localhost:8081/api/task/all");
        const data = await response.json();

        if (data.success) {
          const tasks = data.data.map((task) => ({
            ...task,
            isCompleted: task.completed ?? false, 
          }));
          dispatch({ type: "SET_TASKS", payload: tasks });
        } else {
          alert("Failed to fetch tasks. Please check the API.");
        }
      } catch (error) {
        alert("Unable to connect to the API. Please ensure the server is running.");
      }
    };

    fetchTasks();
  }, []);

  return (
    <TaskContext.Provider value={{ state, dispatch }}>
      {children}
    </TaskContext.Provider>
  );
};

export const useTaskContext = () => {
  return useContext(TaskContext);
};
