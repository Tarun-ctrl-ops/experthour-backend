import api from "./client";

export const getAvailabilityByExpert = async (expertId) => {
  const res = await api.get(`/availability/expert/${expertId}`);
  return res.data;
};

export const createAvailability = async (data) => {
  const res = await api.post("/availability", data);
  return res.data;
};
