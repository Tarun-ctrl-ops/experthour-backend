import { useEffect, useState } from "react";
import { getAllUsers, createUser } from "../api/userApi";

export default function UserPage() {
  const [users, setUsers] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const loadUsers = async () => {
    setUsers(await getAllUsers());
  };

  useEffect(() => {
    loadUsers();
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    await createUser({
      name,
      email,
      role: "USER",
      skills: "Java",
      experience: 1,
      hourlyRate: 0,
    });
    setName("");
    setEmail("");
    loadUsers();
  };

  return (
    <>
      <h2>Users</h2>
      <form onSubmit={submit}>
        <input value={name} onChange={e => setName(e.target.value)} placeholder="Name" required />
        <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" required />
        <button>Create</button>
      </form>

      <ul>
        {users.map(u => (
          <li key={u.id}>{u.name} ({u.email})</li>
        ))}
      </ul>
    </>
  );
}