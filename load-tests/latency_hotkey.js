import http from "k6/http";

export let options = {
  vus: 100,
  duration: "2m",
  thresholds: { http_req_duration: ["p(95)<400"] }
};

export default function () {
  http.get("http://localhost:8080/get/HOT_KEY");
}
