import { useEffect, useState } from "react";
import { getAllExperts, createExpert } from "../api/expertApi";

export default function ExpertPage() {
  const [experts, setExperts] = useState([]);
  const [form, setForm] = useState({
    name: "", email: "", domain: "", experience: "", hourlyRate: ""
  });

  const loadExperts = async () => {
    setExperts(await getAllExperts());
  };

  useEffect(() => {
    loadExperts();
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    await createExpert({
      ...form,
      experience: Number(form.experience),
      hourlyRate: Number(form.hourlyRate),
    });
    setForm({ name:"", email:"", domain:"", experience:"", hourlyRate:"" });
    loadExperts();
  };

  return (
    <>
      <h2>Experts</h2>
      <form onSubmit={submit}>
        {Object.keys(form).map(k => (
          <input key={k} placeholder={k} value={form[k]}
            onChange={e => setForm({ ...form, [k]: e.target.value })} required />
        ))}
        <button>Create</button>
      </form>

      <ul>
        {experts.map(e => (
          <li key={e.id}>{e.name} — {e.domain}</li>
        ))}
      </ul>
    </>
  );
}