import { useEffect, useState } from "react";
import api from "../api/client";

export default function AvailabilityPage() {
  const [experts, setExperts] = useState([]);
  const [slots, setSlots] = useState([]);
  const [expertId, setExpertId] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  useEffect(() => {
    loadExperts();
    loadSlots();
  }, []);

  const loadExperts = async () => {
    try {
      const res = await api.get("/experts");
      setExperts(res.data);
    } catch (err) {
      console.error("Failed to load experts", err);
    }
  };

  const loadSlots = async (id = expertId) => {
    if (!id) {
      setSlots([]);
      return;
    }

    try {
      const res = await api.get(`/availability?expertId=${id}`);
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
      await api.post("/availability", {
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
          onChange={e => {
            setExpertId(e.target.value);
            loadSlots(e.target.value);
          }}
        >
          <option value="">Select Expert</option>
          {experts.map(e => (
            <option key={e.id} value={e.id}>
              {e.name}
            </option>
          ))}
        </select>

        <input
          type="datetime-local"
          value={startTime}
          onChange={e => setStartTime(e.target.value)}
        />

        <input
          type="datetime-local"
          value={endTime}
          onChange={e => setEndTime(e.target.value)}
        />

        <button onClick={createSlot}>Add Slot</button>
      </div>

      <h3>Slots</h3>

      {slots.map(s => (
        <div className="card" key={s.id}>
          <b>{s.expert?.name || s.expertId}</b><br />
          {s.startTime} → {s.endTime}
        </div>
      ))}
    </>
  );
}