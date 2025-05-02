import React, { createContext, useReducer, useContext } from 'react';

const initialState = {
  tasks: [
    { id: 1, title: "Complete project proposal", isCompleted: false },
    { id: 2, title: "Buy groceries", isCompleted: true },
    { id: 3, title: "Go for a run", isCompleted: false },
  ],
  newTaskTitle: "", // add task input
  filterTitle: "", // filter task input
};

const taskReducer = (state, action) => {
  switch (action.type) {
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
      return {
        ...state,
        tasks: state.tasks.map((task) =>
          task.id === action.payload ? { ...task, isCompleted: !task.isCompleted } : task
        ),
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

  return (
    <TaskContext.Provider value={{ state, dispatch }}>
      {children}
    </TaskContext.Provider>
  );
};

// Custom hook 
export const useTaskContext = () => {
  return useContext(TaskContext);
};