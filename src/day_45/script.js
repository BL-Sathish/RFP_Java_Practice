// UC 6 – EmployeePayrollData class with Getters and Setters
// Note: attributes start with underscore (_) due to getters/setters
// When JSON.stringify is called, keys will appear as _name, _salary etc.

class EmployeePayrollData {
  constructor(name, salary, department, startDate) {
    // UC 6 – setters are called here, which store values in underscore properties
    this.name       = name;
    this.salary     = salary;
    this.department = department;
    this.startDate  = startDate;
  }

  // UC 6 – Getter and Setter for name
  get name() {
    return this._name;
  }
  set name(name) {
    if (name === undefined || name.length === 0) {
      throw new Error("Name is empty");
    }
    if (!/^[A-Z][a-zA-Z ]{2,}$/.test(name)) {
      throw new Error("Name must start with capital letter and min 3 characters");
    }
    this._name = name;
  }

  // UC 6 – Getter and Setter for salary
  get salary() {
    return this._salary;
  }
  set salary(salary) {
    this._salary = salary;
  }

  // UC 6 – Getter and Setter for department
  get department() {
    return this._department;
  }
  set department(department) {
    this._department = department;
  }

  // UC 6 – Getter and Setter for startDate
  get startDate() {
    return this._startDate;
  }
  set startDate(startDate) {
    this._startDate = startDate;
  }

  // toString for console logging
  toString() {
    return `Name: ${this._name}, Salary: ${this._salary}, Department: ${this._department}, Start Date: ${this._startDate}`;
  }
}

// UC 2 – On Document Load Set Event Listeners
window.addEventListener('DOMContentLoaded', (event) => {

  // ── Name validation listener ──────────────────────────────
  const name      = document.querySelector('#name');
  const textError = document.querySelector('.text-error');

  name.addEventListener('input', function () {
    if (name.value.length == 0) {
      textError.textContent = "";
      setInvalidUI(name, false);
      return;
    }
    try {
      (new EmployeePayrollData(name.value, 0, '', ''));
      textError.textContent = "";
    } catch (e) {
      textError.textContent = e;
    }
  });

  // ── Salary range display listener ─────────────────────────
  const salary = document.querySelector('#salary');
  const output = document.querySelector('.salary-output');

  salary.addEventListener('input', function () {
    output.textContent = salary.value
      ? `Selected salary: ₹${Number(salary.value).toLocaleString('en-IN')} / month`
      : "";
  });

});

// ── Helper: set invalid / valid UI ───────────────────────────
function setInvalidUI(element, isInvalid) {
  if (isInvalid) {
    element.classList.add('input-invalid');
    element.classList.remove('input-valid');
  } else {
    element.classList.remove('input-invalid');
  }
}

function setValidUI(element) {
  element.classList.add('input-valid');
  element.classList.remove('input-invalid');
}

// ── UC 3 & 4 & 6 – save() ────────────────────────────────────
function save() {
  event.preventDefault();

  let isValid = true;

  const nameEl       = document.querySelector('#name');
  const salaryEl     = document.querySelector('#salary');
  const departmentEl = document.querySelector('#department');
  const startDateEl  = document.querySelector('#startDate');
  const nameError    = document.querySelectorAll('.text-error')[0];
  const dateError    = document.querySelectorAll('.text-error')[1];

  const name       = nameEl.value.trim();
  const salary     = salaryEl.value;
  const department = departmentEl.value;
  const startDate  = startDateEl.value;

  // Validate Name using setter
  try {
    new EmployeePayrollData(name, salary, department, startDate);
    nameError.textContent = "";
    setValidUI(nameEl);
  } catch (e) {
    nameError.textContent = e;
    setInvalidUI(nameEl, true);
    isValid = false;
  }

  // Validate Date
  if (!startDate) {
    dateError.textContent = "Start date is required.";
    setInvalidUI(startDateEl, true);
    isValid = false;
  } else {
    const chosen = new Date(startDate);
    const today  = new Date();
    today.setHours(0, 0, 0, 0);
    if (chosen < today) {
      dateError.textContent = "Start date cannot be in the past.";
      setInvalidUI(startDateEl, true);
      isValid = false;
    } else {
      dateError.textContent = "";
      setValidUI(startDateEl);
    }
  }

  if (!isValid) return;

  // UC 3 – Create EmployeePayrollData object (getters/setters used)
  const empData = new EmployeePayrollData(name, salary, department, startDate);
  console.log("Employee Payroll Data: ", empData.toString());

  // UC 4 & 6 – JSON.stringify stores keys as _name, _salary, etc. (underscore)
  const existing = localStorage.getItem('employeePayrollList');
  const records  = existing ? JSON.parse(existing) : [];
  records.push(empData);
  localStorage.setItem('employeePayrollList', JSON.stringify(records));
  console.log("Saved to Local Storage (underscore keys): ", JSON.stringify(empData));

  alert(
    `Employee record saved!\n\n` +
    `Name       : ${empData.name}\n` +
    `Salary     : ₹${Number(empData.salary).toLocaleString('en-IN')} / month\n` +
    `Department : ${empData.department}\n` +
    `Start Date : ${empData.startDate}`
  );
}

// ── UC 5 – resetForm() ───────────────────────────────────────
function resetForm() {
  document.querySelectorAll('.text-error')
          .forEach(el => el.textContent = "");
  document.querySelector('.salary-output').textContent = "";
  document.querySelectorAll('.input-invalid, .input-valid')
          .forEach(el => el.classList.remove('input-invalid', 'input-valid'));
  document.querySelectorAll('select')
          .forEach(el => el.selectedIndex = 0);
  document.querySelectorAll('input[type="text"], input[type="date"]')
          .forEach(el => el.value = "");
  document.querySelectorAll('input[type="radio"]')
          .forEach(el => el.checked = false);
  console.log("Form Reset");
}