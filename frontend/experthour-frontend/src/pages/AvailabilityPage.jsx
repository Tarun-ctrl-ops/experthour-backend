import { useEffect, useState } from "react";
import { getExperts } from "../api/expertApi";
import {
  getAvailability,
  createAvailability,
} from "../api/availabilityApi";

export default function AvailabilityPage() {
  const [experts, setExperts] = useState([]);
  const [slots, setSlots] = useState([]);
  const [expertId, setExpertId] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  useEffect(() => {
    loadExperts();
  }, []);

  const loadExperts = async () => {
    try {
      const res = await getExperts();
      setExperts(res.data);
    } catch (err) {
      console.error("Failed to load experts", err);
    }
  };

  const loadSlots = async (id) => {
    if (!id) {
      setSlots([]);
      return;
    }

    try {
      const res = await getAvailability(id);
      setSlots(res.data);
    } catch (err) {
      console.error("Failed to load slots", err);
    }
  };

  const createSlot = async () => {
    if (!expertId || !startTime || !endTime) {
      alert("Select expert and time");
      return;
    }

    try {
      await createAvailability({
        expertId,
        startTime,
        endTime,
      });

      loadSlots(expertId);
    } catch (err) {
      console.error("Failed to create slot", err);
    }
  };

  return (
    <>
      <h2>Availability</h2>

      <div className="card">
        <select
          value={expertId}
          onChange={(e) => {
            const id = e.target.value;
            setExpertId(id);
            loadSlots(id);
          }}
        >
          <option value="">Select Expert</option>
          {experts.map((e) => (
            <option key={e.id} value={e.id}>
              {e.name}
            </option>
          ))}
        </select>

        <input
          type="datetime-local"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
        />

        <input
          type="datetime-local"
          value={endTime}
          onChange={(e) => setEndTime(e.target.value)}
        />

        <button onClick={createSlot}>Add Slot</button>
      </div>

      <h3>Slots</h3>

      {slots.map((s) => (
        <div className="card" key={s.id}>
          <b>{s.expert?.name || s.expertId}</b>
          <br />
          {s.startTime} → {s.endTime}
        </div>
      ))}
    </>
  );
}
