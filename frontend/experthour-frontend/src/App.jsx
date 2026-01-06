import { Routes, Route, Link } from "react-router-dom";
import UserPage from "./pages/UserPage";
import ExpertPage from "./pages/ExpertPage";
import AvailabilityPage from "./pages/AvailabilityPage";
import BookingPage from "./pages/BookingPage";

export default function App() {
  return (
    <div className="container">
      <h1>ExpertHour</h1>

      <nav>
        <Link to="/users">Users</Link>
        <Link to="/experts">Experts</Link>
        <Link to="/availability">Availability</Link>
        <Link to="/bookings">Bookings</Link>
      </nav>

      <Routes>
        <Route path="/users" element={<UserPage />} />
        <Route path="/experts" element={<ExpertPage />} />
        <Route path="/availability" element={<AvailabilityPage />} />
        <Route path="/bookings" element={<BookingPage />} />
        <Route path="*" element={<UserPage />} />
      </Routes>
    </div>
  );
}