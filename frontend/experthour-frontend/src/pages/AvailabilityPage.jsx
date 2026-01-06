import { useEffect, useState } from "react";
import axios from "axios";

export default function AvailabilityPage() {
  const [experts, setExperts] = useState([]);
  const [slots, setSlots] = useState([]);
  const [expertId, setExpertId] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/api/experts").then(r => setExperts(r.data));
    loadSlots();
  }, []);

  const loadSlots = async () => {
    const res = await axios.get("http://localhost:8080/api/availability");
    setSlots(res.data);
  };

  const createSlot = async () => {
    await axios.post("http://localhost:8080/api/availability", {
      expertId,
      startTime,
      endTime
    });
    loadSlots();
  };

  return (
    <>
      <h2>Availability</h2>

      <div className="card">
        <select onChange={e => setExpertId(e.target.value)}>
          <option>Select Expert</option>
          {experts.map(e => (
            <option key={e.id} value={e.id}>{e.name}</option>
          ))}
        </select>

        <input type="datetime-local" onChange={e => setStartTime(e.target.value)} />
        <input type="datetime-local" onChange={e => setEndTime(e.target.value)} />

        <button onClick={createSlot}>Add Slot</button>
      </div>

      <h3>Slots</h3>

      {slots.map(s => (
        <div className="card" key={s.id}>
          <b>{s.expertName || s.expertId}</b><br />
          {s.startTime} → {s.endTime}
        </div>
      ))}
    </>
  );
}