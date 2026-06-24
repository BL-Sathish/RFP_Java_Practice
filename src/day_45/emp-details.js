// UC 6 – Retrieve Employee Payroll Data from Local Storage
// Note: attributes start with underscore (_name, _salary etc.)
// because EmployeePayrollData class uses getters and setters
// Scope variable declared outside DOMContentLoaded for global access

// UC 6 – Variable with scope across JS file
let employeeList = [];

window.addEventListener('DOMContentLoaded', (event) => {

  const container = document.querySelector('#deptContainer');
  const countEl   = document.querySelector('#empCount');

  // UC 6 – Retrieve from Local Storage and assign to scope variable
  const stored = localStorage.getItem('employeePayrollList');
  employeeList = stored ? JSON.parse(stored) : getDefaultData();

  // Display count in Header Text
  if (countEl) countEl.textContent = employeeList.length;

  if (employeeList.length === 0) {
    container.innerHTML = `
      <div class="empty-state show">
        <div class="empty-icon">👥</div>
        <p>No employee records found.</p>
        <a href="add-employee.html" class="btn">+ Add First Employee</a>
      </div>`;
    return;
  }

  // UC 6 – Get unique departments using for loop
  // Note: keys are _department (underscore) from getter/setter class
  const departments = [];
  for (let i = 0; i < employeeList.length; i++) {
    const dept = employeeList[i]._department || employeeList[i].department;
    if (!departments.includes(dept)) {
      departments.push(dept);
    }
  }

  let deptHTML = '';

  // UC 5 & 6 – for loop per department + Template Literals
  for (let d = 0; d < departments.length; d++) {
    const dept = departments[d];

    // Filter employees for this department
    const deptEmployees = [];
    for (let i = 0; i < employeeList.length; i++) {
      const empDept = employeeList[i]._department || employeeList[i].department;
      if (empDept === dept) {
        deptEmployees.push(employeeList[i]);
      }
    }

    // UC 5 & 6 – Build rows using for loop + Template Literals
    // Note: read _name, _salary, _startDate (underscore keys from JSON.stringify)
    let rowsHTML = '';
    for (let r = 0; r < deptEmployees.length; r++) {
      const emp = deptEmployees[r];

      // UC 6 – Read underscore attributes from Local Storage JSON
      const empName      = emp._name      || emp.name;
      const empSalary    = emp._salary    || emp.salary;
      const empStartDate = emp._startDate || emp.startDate;

      rowsHTML += `
        <tr style="background: ${r % 2 === 0 ? '#fff' : '#f8fafc'}">
          <td class="td-serial">${r + 1}</td>
          <td class="td-name">${empName}</td>
          <td class="td-date">${formatDate(empStartDate)}</td>
          <td><span class="salary-badge">₹${Number(empSalary).toLocaleString('en-IN')} / month</span></td>
        </tr>
      `;
    }

    // Template Literal for each Department section
    deptHTML += `
      <div class="dept-section">
        <div class="dept-heading">
          <span class="dept-badge dept-badge-lg">${dept}</span>
          <span class="dept-count">${deptEmployees.length} employee${deptEmployees.length > 1 ? 's' : ''}</span>
        </div>
        <table class="emp-table">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Start Date</th>
              <th>Salary / Month</th>
            </tr>
          </thead>
          <tbody>${rowsHTML}</tbody>
        </table>
      </div>
    `;
  }

  // UC 6 – Set innerHTML using document object
  container.innerHTML = deptHTML;
});

// Format date DD/MM/YYYY
function formatDate(dateStr) {
  if (!dateStr) return '-';
  const d = new Date(dateStr);
  return d.toLocaleDateString('en-IN', {
    day: '2-digit', month: '2-digit', year: 'numeric'
  });
}

// Fallback default data if Local Storage is empty
function getDefaultData() {
  return [
    { _name: "Amit Sharma",  _salary: 75000, _department: "Engineering", _startDate: "2024-01-15" },
    { _name: "Priya Nair",   _salary: 35000, _department: "HR",          _startDate: "2024-03-01" },
    { _name: "Rahul Verma",  _salary: 50000, _department: "Finance",     _startDate: "2023-11-10" },
    { _name: "Sneha Pillai", _salary: 90000, _department: "Engineering", _startDate: "2024-02-20" },
    { _name: "Karthik Rajan",_salary: 40000, _department: "Marketing",   _startDate: "2024-04-05" }
  ];
}