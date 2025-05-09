import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080", // ← your Spring Boot API base URL
  headers: {
    "Content-Type": "application/json",
  },
});

// Add Authorization header automatically for each request
instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("accessToken");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

instance.interceptors.response.use(
  response => response,
  error => {
    if (error.response.status === 401) {
      localStorage.removeItem("token");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default instance;
