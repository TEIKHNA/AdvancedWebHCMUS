import axios from "./axios";

export const login = async (credentials) => {
  const response = await axios.post("/api/auth/login", credentials);
  return response.data;
};

export const register = async (registrationData) => {
  const response = await axios.post("/api/auth/register", registrationData);
  return response.data;
};

export const refreshToken = async (refreshTokenRequest) => {
  const response = await axios.post("/api/auth/refresh-token", refreshTokenRequest);
  return response.data;
};
