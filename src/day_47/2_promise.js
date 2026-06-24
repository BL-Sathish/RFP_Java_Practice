// ============================================================
// Day 47 - Part 1: AJAX Server Calls to JSONServer using CALLBACK
// ============================================================
// Run: node 1_callback.js
// Requires json-server running: npx json-server --watch db.json --port 3000

const http = require("http");

// Helper: AJAX-style GET using http module + Callback
function ajaxGet(url, callback) {
  http.get(url, (res) => {
    let data = "";

    res.on("data", (chunk) => {
      data += chunk;
    });

    res.on("end", () => {
      try {
        const parsed = JSON.parse(data);
        callback(null, parsed); // null = no error
      } catch (err) {
        callback(new Error("Failed to parse JSON: " + err.message), null);
      }
    });
  }).on("error", (err) => {
    callback(err, null);
  });
}

// ---- Fetch all employees using Callback ----
console.log("=== Fetching ALL Employees (Callback) ===");
ajaxGet("http://localhost:3000/employees", (err, employees) => {
  if (err) {
    console.error("Error fetching employees:", err.message);
    return;
  }
  console.log("Employees List:");
  employees.forEach((emp) => {
    console.log(
      `  ID: ${emp.id} | Name: ${emp.name} | Dept: ${emp.department} | Salary: $${emp.salary}`
    );
  });
});

// ---- Fetch single employee by ID using Callback (nested callback) ----
console.log("\n=== Fetching Employee with ID=1 (Callback) ===");
ajaxGet("http://localhost:3000/employees/1", (err, employee) => {
  if (err) {
    console.error("Error:", err.message);
    return;
  }
  console.log("Employee Found:");
  console.log(`  Name: ${employee.name}`);
  console.log(`  Department: ${employee.department}`);
  console.log(`  Salary: $${employee.salary}`);
});