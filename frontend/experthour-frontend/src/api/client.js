import axios from "axios";

const api = axios.create({
  baseURL: "https://experthour-backend.onrender.com/api",
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;