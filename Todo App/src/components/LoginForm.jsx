import React from "react";
import { TextField, Button, Box, Typography } from "@mui/material";
import { useFormik } from "formik";
import * as Yup from "yup";
import { login } from "../apis/auth";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
  const navigate = useNavigate();

  const validationSchema = Yup.object({
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
  });

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      try {
        const data = await login(values);
        localStorage.setItem("accessToken", data.accessToken);
        navigate("/");
      } catch (err) {
        console.error("Login failed", err);
      }
    },
  });

  return (
    <Box sx={{ maxWidth: 400, mx: "auto", mt: 8 }}>
      <Typography variant="h5" gutterBottom>
        Login
      </Typography>
      <form onSubmit={formik.handleSubmit}>
        <TextField
          label="Username"
          fullWidth
          margin="normal"
          name="username"
          value={formik.values.username}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.username && Boolean(formik.errors.username)}
          helperText={formik.touched.username && formik.errors.username}
        />
        <TextField
          label="Password"
          type="password"
          fullWidth
          margin="normal"
          name="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.password && Boolean(formik.errors.password)}
          helperText={formik.touched.password && formik.errors.password}
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          fullWidth
          sx={{ mt: 2 }}
        >
          Login
        </Button>
      </form>
      <Box sx={{ mt: 2 }}>
        <Typography variant="body2" align="center">
          Don’t have an account?{" "}
          <a href="/register" style={{ textDecoration: "underline", color: "#1976d2" }}>
            Register here
          </a>
        </Typography>
      </Box>
    </Box>
  );
};

export default LoginForm;
