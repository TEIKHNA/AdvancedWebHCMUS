import React, { createContext, useReducer, useContext } from 'react';

const initialState = {
  tasks: [
    { id: 1, title: "Complete project proposal", isCompleted: false },
    { id: 2, title: "Review team feedback", isCompleted: true },
    { id: 3, title: "Prepare presentation slides", isCompleted: false },
  ],
  newTaskTitle: "",
  filterTitle: "",
};

const taskReducer = (state, action) => {
  switch (action.type) {
    case "SET_TASKS":
      return { ...state, tasks: action.payload, loading: false };

    case "ADD_TASK":
      if (action.payload.trim() === "") return state;

      const newTask = {
        id: state.tasks.length > 0 ? Math.max(...state.tasks.map((t) => t.id)) + 1 : 1,
        title: action.payload,
        isCompleted: false,
      };

      return {
        ...state,
        tasks: [...state.tasks, newTask],
        newTaskTitle: "",
      };

    case "TOGGLE_TASK":
      const updatedTasks = state.tasks.map((task) => {
        if (task.id === action.payload) {
          return { ...task, isCompleted: !task.isCompleted };
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
  return (
    <TaskContext.Provider value={{ state, dispatch }}>
      {children}
    </TaskContext.Provider>
  );
}; 

export const useTaskContext = () => {
  return useContext(TaskContext);
};
