import React, { useState, useEffect } from "react";
import { fetchData, postData } from "../api";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  CssBaseline,
  Container,
  Grid,
  Typography,
  Modal,
  TextField,
  createTheme,
  ThemeProvider,
  Box,
  FormControl,
  Select,
  MenuItem,
} from "@mui/material";
import Sidebar from "../components/Sidebar";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [editedOrders, setEditedOrders] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("");

  useEffect(() => {
    async function fetchOrders() {
      const data = await fetchData("order/getAll"); // Fetch orders from your API
      setOrders(data);
      setEditedOrders([...data]);
    }

    fetchOrders();
  }, []);

  const handleStatusChange = (orderId, newStatus) => {
    const updatedOrders = editedOrders.map((order) =>
      order.id === orderId ? { ...order, status: newStatus } : order
    );
    setEditedOrders(updatedOrders);
  };

  const handleSaveChanges = async (orderId) => {
    const orderToUpdate = editedOrders.find((order) => order.id === orderId);
    if (
      orderToUpdate &&
      orderToUpdate.status !==
        orders.find((order) => order.id === orderId).status
    ) {
      await postData(
        `order/${orderId}?orderStatus=${orderToUpdate.status.toUpperCase()}`,
        {}
      );
      const updatedData = await fetchData("order/getAll");
      setOrders(updatedData);
      setEditedOrders([...updatedData]);
    }
  };

  const handleModalOpen = (orderId, currentStatus) => {
    setSelectedOrderId(orderId);
    setSelectedStatus(currentStatus);
    setModalOpen(true);
  };

  const handleModalClose = () => {
    setModalOpen(false);
  };

  const handleModalSave = async () => {
    if (selectedOrderId && selectedStatus) {
      await postData(
        `order/${selectedOrderId}?orderStatus=${selectedStatus.toUpperCase()}`,
        {}
      );
      const updatedData = await fetchData("order/getAll");
      setOrders(updatedData);
      setEditedOrders([...updatedData]);
      handleModalClose();
    }
  };

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div style={{ display: "flex" }}>
        <Sidebar />
        <Container maxWidth="lg" style={{ paddingTop: "1rem" }}>
          <Typography variant="h4" gutterBottom>
            Orders
          </Typography>
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Time</TableCell>
                  <TableCell>Customer Name</TableCell>
                  <TableCell>Status</TableCell>
                  <TableCell>Actions</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {orders.map((order) => (
                  <TableRow key={order.id}>
                    <TableCell>{order.orderedTime}</TableCell>
                    <TableCell>{`${order.userFirstName} ${order.userLastName}`}</TableCell>
                    <TableCell>{order.status}</TableCell>
                    <TableCell>
                      <Button
                        variant="contained"
                        onClick={() => handleModalOpen(order.id, order.status)}
                      >
                        Edit Status
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Container>
        <Modal open={modalOpen} onClose={handleModalClose}>
          <Container
            maxWidth="xs"
            sx={{
              position: "absolute",
              top: "50%",
              left: "50%",
              transform: "translate(-50%, -50%)",
              bgcolor: "background.paper",
              border: "2px solid #000",
              boxShadow: 24,
              p: 4,
            }}
          >
            <Typography variant="h6" component="h2" gutterBottom>
              Edit Status
            </Typography>
            <FormControl fullWidth>
              <Select
                value={selectedStatus}
                onChange={(e) => setSelectedStatus(e.target.value)}
              >
                <MenuItem value="pending">Pending</MenuItem>
                <MenuItem value="processing">Processing</MenuItem>
                <MenuItem value="outfordelivery">Out For Delivery</MenuItem>
                <MenuItem value="delivered">Delivered</MenuItem>
              </Select>
            </FormControl>
            <Button
              variant="contained"
              onClick={handleModalSave}
              style={{ marginTop: "1rem" }}
            >
              Save
            </Button>
          </Container>
        </Modal>
      </div>
    </ThemeProvider>
  );
};

export default Orders;
