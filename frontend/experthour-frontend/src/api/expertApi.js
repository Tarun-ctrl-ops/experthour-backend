import api from "./client";

export const getAllExperts = async () => {
  const res = await api.get("/experts");
  return res.data;
};

export const createExpert = async (data) => {
  const res = await api.post("/experts", data);
  return res.data;
};