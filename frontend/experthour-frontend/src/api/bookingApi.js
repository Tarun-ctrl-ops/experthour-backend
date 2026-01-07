import api from "./client";

export const getAllBookings = async () => {
  const res = await api.get("/bookings");
  return res.data;
};

export const getBookingsByExpert = async (expertId) => {
  const res = await api.get(`/bookings/expert/${expertId}`);
  return res.data;
};

export const createBooking = async (data) => {
  const res = await api.post("/bookings", data);
  return res.data;
};
