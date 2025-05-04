const API_BASE_URL = "http://localhost:8081/api/task";

export const fetchTasksFromAPI = async () => {
  const response = await fetch(`${API_BASE_URL}/all`);
  const data = await response.json();
  if (!response.ok || !data.success) {
    throw new Error(data.message || "Failed to fetch tasks");
  }
  return data.data;
};

export const addTaskToAPI = async (newTask) => {
  const response = await fetch(`${API_BASE_URL}/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newTask),
  });
  const data = await response.json();
  if (!response.ok || !data.success) {
    throw new Error(data.message || "Failed to add task");
  }
  return data;
};

export const updateTaskInAPI = async (taskId, updatedTask) => {
  const response = await fetch(`${API_BASE_URL}/${taskId}/update`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updatedTask),
  });
  const data = await response.json();
  if (!response.ok || !data.success) {
    throw new Error(data.message || "Failed to update task");
  }
  return data;
};