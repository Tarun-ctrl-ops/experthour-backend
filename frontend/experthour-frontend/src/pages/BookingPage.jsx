import { useEffect, useState } from "react";
import { getAllUsers } from "../api/userApi";
import { getAllExperts } from "../api/expertApi";
import { createBooking, getAllBookings } from "../api/bookingApi";

export default function BookingPage() {
  const [users, setUsers] = useState([]);
  const [experts, setExperts] = useState([]);
  const [bookings, setBookings] = useState([]);
  const [form, setForm] = useState({
    userId:"", expertId:"", startTime:"", endTime:""
  });

  useEffect(() => {
    getAllUsers().then(setUsers);
    getAllExperts().then(setExperts);
    getAllBookings().then(setBookings);
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    await createBooking(form);
    setBookings(await getAllBookings());
  };

  return (
    <>
      <h2>Bookings</h2>

      <form onSubmit={submit}>
        <select onChange={e => setForm({ ...form, userId: e.target.value })}>
          <option value="">User</option>
          {users.map(u => <option key={u.id} value={u.id}>{u.name}</option>)}
        </select>

        <select onChange={e => setForm({ ...form, expertId: e.target.value })}>
          <option value="">Expert</option>
          {experts.map(e => <option key={e.id} value={e.id}>{e.name}</option>)}
        </select>

        <input type="datetime-local" onChange={e => setForm({ ...form, startTime: e.target.value })} />
        <input type="datetime-local" onChange={e => setForm({ ...form, endTime: e.target.value })} />
        <button>Book</button>
      </form>

      <ul>
        {bookings.map(b => (
          <li key={b.id}>{b.startTime} → {b.endTime} | ₹{b.price}</li>
        ))}
      </ul>
    </>
  );
}