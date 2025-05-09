import {
  List,
  ListItem,
  ListItemText,
  Checkbox,
  IconButton
} from "@mui/material";
import { useState, useEffect } from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { updateTask, deleteTask } from "../apis/task";
import { Formik } from "formik";

export default function TodoList({ tasks }) {

  const handleEdit = async (task) => {
    try {
      await updateTask(task.taskId, task);
      setLocalTasks(prev =>
      prev.map(t => t.taskId === task.taskId ? task : t));
      setEditingId(null);
    } catch (error) {
      console.error('Failed to update task:', error);
    }
  }

  const handleDelete = async (taskId) => {
    try {
      await deleteTask(taskId);
      setLocalTasks(prev => prev.filter(t => t.taskId !== taskId));
    } catch (error) {
      console.error('Failed to delete task:', error);
    }
  }
  const [editingId, setEditingId] = useState(null);
  const [editText, setEditText] = useState('');
  const [localTasks, setLocalTasks] = useState(tasks);

  useEffect(() => {
    setLocalTasks(tasks);
  }, [tasks]);


  return (
    <List>
      {localTasks.map((task) => (
        <ListItem key={task.ordinalNumber}>
          {editingId === task.taskId ? (
            <Formik
              initialValues={{ title: task.title }}
              onSubmit={async (values) => {
                await handleEdit({
                  ...task,
                  title: values.title
                });
                setEditingId(null);
              }}
            >
              {({ values, handleChange, handleSubmit }) => (
                <form onSubmit={handleSubmit} style={{ display: 'flex', alignItems: 'center' }}>
                  <input
                    name="title"
                    value={values.title}
                    onChange={handleChange}
                    autoFocus
                    style={{ marginRight: '10px' }}
                  />
                  <button type="submit">Submit</button>
                </form>
              )}
            </Formik>
          ) : (
            <ListItemText primary={task.title} />
          )}
          {editingId !== task.taskId && (<Checkbox
            checked={task.isCompleted}
            onChange={async () => {
              try {
                await handleEdit({
                  ...task,
                  isCompleted: !task.isCompleted
                });
              } catch (error) {
                console.error('Failed to update task:', error);
              }
            }}
          />
          )}
          {editingId !== task.taskId && (
            <IconButton onClick={() => {
              setEditingId(task.taskId);
              setEditText(task.title);
            }}>
              <EditIcon />
            </IconButton>
          )}
          {editingId !== task.taskId && (
            <IconButton onClick={() => handleDelete(task.taskId)}>
              <DeleteIcon />
            </IconButton>
          )}
        </ListItem>
      ))}
    </List>
  );
}
