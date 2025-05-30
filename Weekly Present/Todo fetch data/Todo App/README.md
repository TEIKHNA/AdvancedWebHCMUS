# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.

1. Install:
   npm create vite@latest
   cd Todo App
   npm install
   npm run dev
2. V1: Components: TaskList, FilterTask, AddTask
   Hooks: useTask
3. V2: useContext useReducer for one-way data flow
   context/TaskContext.jsx
   Wrap App with TaskContext
   App.jsx handle based on type from context
4. V3: implement api fetch, add, update
   service/taskService.js
   TaskList loading spinner
   run Todo Backend/demo
