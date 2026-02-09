import http from "k6/http";

export let options = {
  stages: [
    { duration: "1m", target: 50 },
    { duration: "2m", target: 200 },
    { duration: "1m", target: 50 }
  ]
};

export default function () {
  http.get("http://localhost:8080/get/" + Math.random());
}
