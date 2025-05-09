import { useState, useEffect } from "react";
import { Box, Button, TextField, Container, Typography, Stack } from "@mui/material";
import { useNavigate } from "react-router-dom";
import TodoList from "../components/TodoList";
import { getTasks, addTask as addTaskApi } from "../apis/task";
import { Formik, Form } from "formik";
import * as Yup from "yup";
import axios from "axios";

export default function TodoPage() {
  const [tasks, setTasks] = useState([]);
  const navigate = useNavigate();

  const fetchTasks = async () => {
    try {
      const response = await getTasks();
      setTasks(response.user.tasks);
    } catch (error) {
      console.error("Failed to fetch tasks:", error);
    }
  };

  const handleAddTask = async (values, { resetForm }) => {
    if (!values.newTask.trim()) return;
    try {
      await addTaskApi({ title: values.newTask, isCompleted: false, ordinalNumber: tasks.length });
      resetForm();
      fetchTasks();
    } catch (error) {
      console.error("Failed to add task:", error);
    }
  };

  const handleLogout = async () => {
    try {
      const refreshToken = localStorage.getItem("refreshToken");
      if (refreshToken) {
        await axios.post("/api/auth/logout", null, { params: { refreshToken } });
      }
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
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
        <Formik
          initialValues={{ newTask: "" }}
          validationSchema={Yup.object({
            newTask: Yup.string().required("Task title is required")
          })}
          onSubmit={handleAddTask}
        >
          {({ values, handleChange, handleBlur, errors, touched }) => (
            <Form>
              <TextField
                name="newTask"
                label="New Task"
                fullWidth
                value={values.newTask}
                onChange={handleChange}
                onBlur={handleBlur}
                error={touched.newTask && Boolean(errors.newTask)}
                helperText={touched.newTask && errors.newTask}
                sx={{ mb: 2 }}
              />
              <Button type="submit" variant="contained" fullWidth>
                Add Task
              </Button>
            </Form>
          )}
        </Formik>

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
