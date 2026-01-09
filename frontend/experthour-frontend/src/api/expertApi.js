import api from "./client";

export const getExperts = () => api.get("/experts");
export const createExpert = (data) => api.post("/experts", data);