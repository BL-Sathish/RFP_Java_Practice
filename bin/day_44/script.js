// EmployeePayrollData class
class EmployeePayrollData {
  constructor(name, salary, department, startDate) {
    this.name = name;
    this.salary = salary;
    this.department = department;
    this.startDate = startDate;
  }
}

// UC 2 – On Document Load Set Event Listeners
window.addEventListener('DOMContentLoaded', (event) => {

  // ── Name validation listener ──────────────────────────────
  const name = document.querySelector('#name');
  const textError = document.querySelector('.text-error');

  name.addEventListener('input', function () {
    if (name.value.length == 0) {
      textError.textContent = "";
      setInvalidUI(name, false);
      return;
    }
    try {
      (new EmployeePayrollData()).name = name.value;;
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
      ? "Selected salary: ₹" + Number(salary.value).toLocaleString('en-IN') + " / month"
      : "";
  });

});

// ── Helper: set invalid / valid UI on a field ─────────────────
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

// ── UC 3 & UC 4 – save(): validate, create object, save to Local Storage ──
function save() {
  event.preventDefault();

  let isValid = true;

  const nameEl = document.querySelector('#name');
  const salaryEl = document.querySelector('#salary');
  const departmentEl = document.querySelector('#department');
  const startDateEl = document.querySelector('#startDate');
  const nameError = document.querySelectorAll('.text-error')[0];
  const dateError = document.querySelectorAll('.text-error')[1];

  const name = nameEl.value.trim();
  const salary = salaryEl.value;
  const department = departmentEl.value;
  const startDate = startDateEl.value;

  // Validate Name
  if (!/^[A-Z][a-zA-Z ]{2,}$/.test(name)) {
    nameError.textContent = "Name must start with a capital letter and min 3 characters.";
    setInvalidUI(nameEl, true);
    isValid = false;
  } else {
    nameError.textContent = "";
    setValidUI(nameEl);
  }

  // Validate Date
  if (!startDate) {
    dateError.textContent = "Start date is required.";
    setInvalidUI(startDateEl, true);
    isValid = false;
  } else {
    const chosen = new Date(startDate);
    const today = new Date();
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

  // UC 3 – Create EmployeePayrollData object
  const empData = new EmployeePayrollData(name, salary, department, startDate);
  console.log("Employee Payroll Data: ", empData);

  // UC 4 – Save object to Local Storage
  localStorage.setItem('employeePayroll', JSON.stringify(empData));
  console.log("Saved to Local Storage: ", localStorage.getItem('employeePayroll'));

  alert(
    "Employee record saved to Local Storage!\n\n" +
    "Name       : " + empData.name + "\n" +
    "Salary     : ₹" + Number(empData.salary).toLocaleString('en-IN') + " / month\n" +
    "Department : " + empData.department + "\n" +
    "Start Date : " + empData.startDate
  );
}

// ── UC 5 – resetForm(): reset form on clicking reset ─────────
function resetForm() {
  // Clear all error messages
  document.querySelectorAll('.text-error')
    .forEach(el => el.textContent = "");

  // Clear salary output display
  document.querySelector('.salary-output').textContent = "";

  // Remove all valid / invalid UI classes from inputs
  document.querySelectorAll('.input-invalid, .input-valid')
    .forEach(el => el.classList.remove('input-invalid', 'input-valid'));

  // Reset all select dropdowns to default
  document.querySelectorAll('select')
    .forEach(el => el.selectedIndex = 0);

  // Reset all text / date inputs
  document.querySelectorAll('input[type="text"], input[type="date"]')
    .forEach(el => el.value = "");

  // Uncheck all radio buttons
  document.querySelectorAll('input[type="radio"]')
    .forEach(el => el.checked = false);

  console.log("Form Reset");
}