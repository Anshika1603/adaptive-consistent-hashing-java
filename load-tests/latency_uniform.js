import http from "k6/http";
import { sleep } from "k6";

export let options = {
  vus: 50,
  duration: "2m",
  thresholds: {
    http_req_duration: [
      "p(95) < 300",
    ],
    http_req_failed: [
      "rate < 0.01",
    ],
  },
};

export default function () {
  const key = Math.random().toString(36).substring(2, 12);
  http.get(`http://localhost:8080/get/${key}`);
  sleep(0.1);
}
