import axios from "./axios";

export const getTasks = async () => {
  const response = await axios.get("/api/");
  return response.data;
};

export const addTask = async (task) => {
  const response = await axios.post("/api/task/add", task);
  return response.data;
};

export const updateTask = async (taskId, task) => {
  const response = await axios.put(`/api/task/${taskId}`, task);
  return response.data;
};

export const deleteTask = async (taskId) => {
  const response = await axios.delete(`/api/task/${taskId}`);
  return response.data;
};
