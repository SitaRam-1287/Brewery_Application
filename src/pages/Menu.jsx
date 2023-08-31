import React, { useState, useEffect } from "react";
import {
  Grid,
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
  Modal,
  TextField,
  Button,
  createTheme,
  ThemeProvider,
  FormControl,
  Select,
  MenuItem,
  CssBaseline,
  Container,
} from "@mui/material";
import { fetchData, postData } from "../api";
import Sidebar from "../components/Sidebar";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

const Menu = () => {
  const [menuItems, setMenuItems] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);
  const [name, setName] = useState("");
  const [details, setDetails] = useState("");
  const [price, setPrice] = useState("");
  const [image, setImage] = useState("");

  useEffect(() => {
    async function fetchMenu() {
      const data = await fetchData("item"); // Fetch menu items from your API
      setMenuItems(data);
    }
    fetchMenu();
  }, []);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setImage(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleOpenModal = (item) => {
    setSelectedItem(item);
    if (item) {
      setName(item.name);
      setDetails(item.details);
      setPrice(item.price);
      setImage(item.image);
    } else {
      setName("");
      setDetails("");
      setPrice("");
      setImage("");
    }
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setSelectedItem(null);
  };

  const handleSaveItem = async () => {
    const newItem = {
      name,
      details,
      price,
      image,
    };

    if (selectedItem) {
      // Update existing item
      await postData(`menu/update/${selectedItem.id}`, newItem);
    } else {
      // Add new item
      await postData("menu/add", newItem);
    }

    // Refresh menu items after saving
    const updatedMenu = await fetchData("/item/getAll");
    setMenuItems(updatedMenu);

    handleCloseModal();
  };

  const handleAddItem = () => {
    setSelectedItem(null);
    setName("");
    setDetails("");
    setPrice("");
    setImage("");
    setModalOpen(true);
  };

  const handleDeleteItem = async (itemId) => {
    await postData(`menu/delete/${itemId}`);
    const updatedMenu = await fetchData("menu/getAll");
    setMenuItems(updatedMenu);
  };

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div
        style={{
          display: "flex",
          backgroundColor: "#121212",
          padding: "2rem",
          width: "100%",
        }}
      >
        <Sidebar />
        <Container
          maxWidth="lg"
          style={{ paddingTop: "1rem", marginLeft: "5rem" }}
        >
          <Typography variant="h4" gutterBottom style={{ color: "#fff" }}>
            Menu
          </Typography>
          <Button
            onClick={handleAddItem}
            style={{
              color: "#01b075",
              opacity: "0.7",
              marginTop: "1rem",
              marginLeft: "1rem",
              marginBottom: "3rem",
            }}
          >
            Add Item
          </Button>
          <Grid container spacing={2}>
            {menuItems.map((item) => (
              <Grid item key={item.id} xs={12} sm={6} md={4} lg={3}>
                <Card
                  style={{
                    backgroundColor: "#1d1d1d",
                    borderRadius: "12px",
                    border: "1px solid #333",
                  }}
                >
                  <CardActionArea onClick={() => handleOpenModal(item)}>
                    <CardMedia
                      component="img"
                      height="140"
                      src={`data:image/jpeg;base64,${item.image}`}
                      alt={item.name}
                    />
                    <CardContent>
                      <Typography
                        variant="h6"
                        component="div"
                        style={{ color: "#fdab00" }}
                      >
                        {item.name}
                      </Typography>
                      <Typography
                        variant="body2"
                        color="text.secondary"
                        style={{ color: "#ccc" }}
                      >
                        {item.details.substring(0, 100)}...
                      </Typography>
                      <Typography
                        variant="body2"
                        color="text.primary"
                        style={{ color: "#7fcf00", marginTop: "8px" }}
                      >
                        ₹{item.price}
                      </Typography>
                    </CardContent>
                  </CardActionArea>
                  <div
                    style={{ display: "flex", justifyContent: "space-between" }}
                  >
                    <Button
                      onClick={() => handleOpenModal(item)}
                      style={{
                        color: "#fdab00",
                        opacity: "0.7",
                      }}
                    >
                      Edit
                    </Button>
                    <Button
                      onClick={() => handleDeleteItem(item.id)}
                      style={{
                        color: "#e53935",
                        opacity: "0.7",
                      }}
                    >
                      Delete
                    </Button>
                  </div>
                </Card>
              </Grid>
            ))}
          </Grid>

          <Modal open={modalOpen} onClose={handleCloseModal}>
            <div
              style={{
                backgroundColor: "#333",
                padding: "1rem",
                borderRadius: "8px",
                maxWidth: "400px",
                margin: "auto",
                marginTop: "15vh",
              }}
            >
              <Typography
                variant="h5"
                style={{ marginBottom: "1rem", color: "#fff" }}
              >
                {selectedItem ? "Edit Item" : "Add New Item"}
              </Typography>
              <TextField
                label="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                fullWidth
                margin="dense"
                variant="outlined"
                color="primary"
                InputLabelProps={{ style: { color: "#fff" } }}
                InputProps={{ style: { color: "#fff" } }}
              />
              <TextField
                label="Details"
                value={details}
                onChange={(e) => setDetails(e.target.value)}
                fullWidth
                margin="dense"
                variant="outlined"
                color="primary"
                multiline
                rows={4}
                InputLabelProps={{ style: { color: "#fff" } }}
                InputProps={{ style: { color: "#fff" } }}
              />
              <TextField
                label="Price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                fullWidth
                margin="dense"
                variant="outlined"
                color="primary"
                InputLabelProps={{ style: { color: "#fff" } }}
                InputProps={{ style: { color: "#fff" } }}
              />
              {selectedItem ? (
                <div style={{ maxHeight: "200px", overflowY: "auto" }}>
                  <img
                    src={`data:image/jpeg;base64,${image}`}
                    alt="Preview"
                    style={{ width: "100%", borderRadius: "8px" }}
                  />
                </div>
              ) : (
                <>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleFileChange}
                    style={{ marginBottom: "1rem", color: "#fff" }}
                  />
                  {image && (
                    <div style={{ maxHeight: "200px", overflowY: "auto" }}>
                      <img
                        src={image}
                        alt="Preview"
                        style={{ width: "100%", borderRadius: "8px" }}
                      />
                    </div>
                  )}
                </>
              )}
              <Button
                onClick={handleSaveItem}
                style={{
                  marginTop: "1rem",
                  backgroundColor: "#01b075",
                  color: "#fff",
                  fontWeight: "bold",
                }}
                fullWidth
              >
                {selectedItem ? "Save" : "Add"}
              </Button>
            </div>
          </Modal>
        </Container>
      </div>
    </ThemeProvider>
  );
};

export default Menu;
