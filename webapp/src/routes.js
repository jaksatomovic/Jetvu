import Dashboard from "views/Dashboard.jsx";
import Airport from "./views/Airport";

var routes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    icon: "tim-icons icon-chart-pie-36",
    component: Dashboard,
    layout: "/admin"
  },
  {
    path: "/user-profile",
    name: "Airports",
    icon: "tim-icons icon-send",
    component: Airport,
    layout: "/admin"
  }
];
export default routes;
