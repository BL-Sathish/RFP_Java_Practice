class EmployeePayroll {

    // Default constructor (no required params)
    constructor() {
        this._id = null;
        this._name = "";
        this._salary = 0;
        this._gender = "";
        this._department = "";
        this._startDate = null;
        this._notes = "";
        this._profilePic = "";
    }

    // ── Getters ────────────────────────────────
    get id() { return this._id; }
    get name() { return this._name; }
    get salary() { return this._salary; }
    get gender() { return this._gender; }
    get department() { return this._department; }
    get startDate() { return this._startDate; }
    get notes() { return this._notes; }
    get profilePic() { return this._profilePic; }

    // ── Setters ────────────────────────────────
    set id(value) { this._id = value; }
    set name(value) {
        if (!value || value.trim() === "")
            throw new Error("Name cannot be empty");
        this._name = value.trim();
    }
    set salary(value) {
        if (value < 0)
            throw new Error("Salary cannot be negative");
        this._salary = value;
    }
    set gender(value) { this._gender = value; }
    set department(value) { this._department = value; }
    set startDate(value) { this._startDate = value; }
    set notes(value) { this._notes = value; }
    set profilePic(value) { this._profilePic = value; }

    // ── Utility ────────────────────────────────
    toString() {
        return `EmployeePayroll {
  id         : ${this._id}
  name       : ${this._name}
  salary     : ₹${this._salary.toLocaleString()}
  gender     : ${this._gender}
  department : ${this._department}
  startDate  : ${this._startDate}
  notes      : ${this._notes}
  profilePic : ${this._profilePic || "(none)"}
}`;
    }
}