import { setAvailability } from "./expertApi";
export { setAvailability };

export const getAvailability = (expertId) =>
  api.get(`/availability?expertId=${expertId}`);

export const createAvailability = (data) =>
  api.post("/availability", data);