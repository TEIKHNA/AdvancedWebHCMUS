import React from "react";
import { TextField, Button, Box, Typography } from "@mui/material";
import { useFormik } from "formik";
import * as Yup from "yup";
import { register } from "../apis/auth";
import { useNavigate } from "react-router-dom";

const RegisterForm = () => {
  const navigate = useNavigate();

  const validationSchema = Yup.object({
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref("password"), null], "Passwords must match")
      .required("Confirm Password is required"),
  });

  const formik = useFormik({
    initialValues: {
      username: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema,
    onSubmit: async (values) => {
      try {
        await register(values);
        navigate("/login"); // Redirect to login page after successful registration
      } catch (err) {
        console.error("Registration failed", err);
      }
    },
  });

  return (
    <Box sx={{ maxWidth: 400, mx: "auto", mt: 8 }}>
      <Typography variant="h5" gutterBottom>
        Register
      </Typography>
      <form onSubmit={formik.handleSubmit}>
      <TextField
          label="Display Name"
          fullWidth
          margin="normal"
          name="displayName"
          value={formik.values.displayName}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={formik.touched.displayName && Boolean(formik.errors.displayName)}
          helperText={formik.touched.udisplayName && formik.errors.displayName}
        />
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
        <TextField
          label="Confirm Password"
          type="password"
          fullWidth
          margin="normal"
          name="confirmPassword"
          value={formik.values.confirmPassword}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          error={
            formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)
          }
          helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          fullWidth
          sx={{ mt: 2 }}
        >
          Register
        </Button>
      </form>
      <Box sx={{ mt: 2 }}>
        <Typography variant="body2" align="center">
          Already have an account?{" "}
          <a href="/login" style={{ textDecoration: "underline", color: "#1976d2" }}>
            Login here
          </a>
        </Typography>
      </Box>
    </Box>
  );
};

export default RegisterForm;
