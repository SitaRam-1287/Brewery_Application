import React, { useState, useEffect } from "react";
import {
  Container,
  Typography,
  Grid,
  Card,
  CardContent,
  CssBaseline,
  ThemeProvider,
  createTheme,
} from "@mui/material";
import Sidebar from "../components/Sidebar";
import { fetchData } from "../api";
import { Bar } from "react-chartjs-2";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

const Dashboard = () => {
  const [statistics, setStatistics] = useState({});
  const [orderData, setOrderData] = useState({});
  const [revenueData, setRevenueData] = useState({});

  useEffect(() => {
    async function fetchDashboardData() {
      const data = await fetchData("dashboard"); // Replace with your API endpoint
      setStatistics(data.statistics);
      setOrderData(data.orderData);
      setRevenueData(data.revenueData);
    }
    fetchDashboardData();
  }, []);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div style={{ display: "flex" }}>
        <Sidebar />
        <Container maxWidth="lg" style={{ paddingTop: "1rem" }}>
          <Typography variant="h4" gutterBottom>
            Dashboard
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6} md={3}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Total Orders</Typography>
                  <Typography variant="h4">{statistics.totalOrders}</Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Total Customers</Typography>
                  <Typography variant="h4">
                    {statistics.totalCustomers}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Total Revenue</Typography>
                  <Typography variant="h4">
                    ${statistics.totalRevenue}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={12} sm={6} md={3}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Total Menu Items</Typography>
                  <Typography variant="h4">
                    {statistics.totalMenuItems}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Orders Per Day</Typography>
                  <Bar data={orderData} />
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={12} sm={6}>
              <Card>
                <CardContent>
                  <Typography variant="h6">Revenue Per Day</Typography>
                  <Bar data={revenueData} />
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Container>
      </div>
    </ThemeProvider>
  );
};

export default Dashboard;
