import React, { createContext, useReducer, useContext, useEffect } from 'react';
import { fetchTasksFromAPI, addTaskToAPI, updateTaskInAPI } from '../service/taskService';
const initialState = {
  tasks: [],
  newTaskTitle: "",
  filterTitle: "",
  loading: true,
};


const taskReducer = (state, action) => {
  switch (action.type) {
    case "SET_TASKS":
      const tasksWithMappedFields = action.payload.map((task) => ({
        ...task,
        isCompleted: task.completed, // Map `completed` from the database to `isCompleted`
      }));

      return { ...state, tasks: tasksWithMappedFields, loading: false };

    case "ADD_TASK":
      if (action.payload.trim() === "") return state;

      const newTask = {
        id: state.tasks.length > 0 ? Math.max(...state.tasks.map((t) => t.id)) + 1 : 1,
        title: action.payload,
        isCompleted: false,
      };

      addTaskToAPI(newTask).catch((error) => alert(error.message));

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
          }).catch((error) => alert(error.message));
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

    case "SET_LOADING":
      return { ...state, loading: action.payload };

    default:
      return state;
  }
};

const TaskContext = createContext();

export const TaskProvider = ({ children }) => {
  const [state, dispatch] = useReducer(taskReducer, initialState);
  const fetchTasks = async (dispatch) => {
    try {
      dispatch({ type: "SET_LOADING", payload: true });
  
      const tasks = await fetchTasksFromAPI();
  
      // Delay fetch loading 
      setTimeout(() => {
        dispatch({ type: "SET_TASKS", payload: tasks });
      }, 1000);
    } catch (error) {
      alert(error.message);
      dispatch({ type: "SET_LOADING", payload: false });
    }
  };
  useEffect(() => {
    fetchTasks(dispatch);
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
