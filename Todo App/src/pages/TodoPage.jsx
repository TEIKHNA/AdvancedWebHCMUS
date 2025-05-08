import { useState, useEffect } from "react";
import { Box, Button, TextField, Container, Typography, Stack } from "@mui/material";
import { useNavigate } from "react-router-dom";
import TodoList from "../components/TodoList";
import { getTasks, addTask as addTaskApi } from "../apis/task";

export default function TodoPage() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState("");
  const navigate = useNavigate();

  const fetchTasks = async () => {
    try {
      const response = await getTasks();
      setTasks(response.data);
    } catch (error) {
      console.error("Failed to fetch tasks:", error);
    }
  };

  const handleAddTask = async () => {
    if (!newTask.trim()) return;
    try {
      await addTaskApi({ name: newTask });
      setNewTask("");
      fetchTasks();
    } catch (error) {
      console.error("Failed to add task:", error);
    }
  };

  const handleLogout = async () => {
    try {
      const refreshToken = localStorage.getItem("refreshToken");
  
      // Call backend logout API
      if (refreshToken) {
        await axios.post("/api/auth/logout", null, {
          params: { refreshToken },
        });
      }
  
      // Clear tokens from localStorage
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
  
      // Redirect to login page
      navigate("/login");
    } catch (error) {
      console.error("Logout failed:", error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <Box sx={{ maxWidth: 800, mx: "auto", mt: 8 }}>
      <Stack direction="row" justifyContent="center" alignItems="center" mb={2}>
        <Typography variant="h4">Todo App</Typography>
        
      </Stack>

      <Container maxWidth="sm">
        <TextField
          label="New Task"
          fullWidth
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
          sx={{ mb: 2 }}
        />
        <Button onClick={handleAddTask} variant="contained" fullWidth>
          Add Task
        </Button>
        <TodoList tasks={tasks} />
      </Container>

      <Stack direction="row" justifyContent="center" alignItems="center" spacing={2}>
        <Button variant="outlined" color="error" onClick={handleLogout}>
          Log Out
        </Button>
      </Stack>
    </Box>
  );
}
