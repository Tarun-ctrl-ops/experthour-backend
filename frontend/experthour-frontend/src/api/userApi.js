import client from "./client";

export const register = async (data) => {
  const res = await client.post("/auth/register", data);
  return res.data;
};

export const login = async (email, password) => {
  const res = await client.post("/auth/login", { email, password });
  return res.data;
};
