import client from "./client";

export const createBooking = async (expertId, start, end) => {
  const res = await client.post("/bookings", {
    expertId,
    start,
    end
  });
  return res.data;
};

export const getMyBookings = async () => {
  const res = await client.get("/bookings/my");
  return res.data;
};

