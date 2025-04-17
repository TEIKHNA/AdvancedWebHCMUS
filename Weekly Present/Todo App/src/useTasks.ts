import { useState } from 'react';
export interface Task {
    id: number,
    title: string,
    isCompleted: boolean,
}

export const useTasks = () => {
    const [tasks, setTasks] = useState<Task[]>([
        { id: 1, title: "Complete project proposal", isCompleted: false },
        { id: 2, title: "Buy groceries", isCompleted: true },
        { id: 3, title: "Go for a run", isCompleted: false },
    ])
    const [newTaskTitle, setNewTaskTitle] = useState<string>("")
    const [filterTitle, setFilterTitle] = useState<string>("")
    const onToggle = (id: number) => {
        setTasks(tasks.map(task => (task.id === id ? { ...task, isCompleted: !task.isCompleted } : task)))
    };
    const addTask = (newTaskTitle:string) =>{
        if(newTaskTitle.trim()!==""){
            const newTask: Task ={
                id: tasks.length+1,
                title: newTaskTitle,
                isCompleted: false,
            }
            setTasks([...tasks, newTask])
        }
    }
    const filteredTasks = tasks.filter(task => 
        task.title.toLowerCase().includes(filterTitle.toLowerCase()))
    return {
        tasks: filteredTasks,
        onToggle,
        addTask,
        newTaskTitle,
        setNewTaskTitle,
        filterTitle,
        setFilterTitle,
    };
};
