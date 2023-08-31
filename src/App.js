import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Dashboard from "./pages/Dashboard";
import Menu from "./pages/Menu";
import Orders from "./pages/Orders";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Box from "@mui/material/Box";

const App = () => {
  return (
    <Router>
      <CssBaseline />
      <Box display="flex">
        <Container>
          <Routes>
            <Route exact path="/" element={<Dashboard />} />
            <Route path="/menu" element={<Menu />} />
            <Route path="/orders" element={<Orders />} />
          </Routes>
        </Container>
      </Box>
    </Router>
  );
};

export default App;
