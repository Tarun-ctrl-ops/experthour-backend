import client from "./client";

export const getAllExperts = async () => {
  const res = await client.get("/experts");
  return res.data;
};

export const createExpert = async (data) => {
  const res = await client.post("/experts", data);
  return res.data;
};

export const setAvailability = async (id, from, to) => {
  const res = await client.put(`/experts/${id}/availability`, { from, to });
  return res.data;
};


