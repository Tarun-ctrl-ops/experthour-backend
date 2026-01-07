import api from "./client";

export const getExperts = () => api.get("/experts");

export const getAvailability = (expertId) =>
  api.get(`/availability?expertId=${expertId}`);

export const createAvailability = (data) =>
  api.post("/availability", data);