let addressBook = [];

// Regex patterns
const namePattern = /^[A-Z][a-zA-Z]{2,}$/;
const textPattern = /^[a-zA-Z\s]{4,}$/;
const zipPattern = /^[0-9]{6}$/;
const phonePattern = /^[0-9]{10}$/;
const emailPattern = /^[a-zA-Z0-9]+([._+-][a-zA-Z0-9]+)*@[a-zA-Z0-9]+\.[a-zA-Z]{2,}$/;

// UC1: Create Contact + UC2: Validate Contact + UC3: Add to Address Book
function addContact(firstName, lastName, address, city, state, zip, phone, email) {

    if (isDuplicateContact(firstName, lastName)) {
        console.log("Duplicate Contact Found");
        return;
    }


    let contact = {
        firstName: firstName,
        lastName: lastName,
        address: address,
        city: city,
        state: state,
        zip: zip,
        phone: phone,
        email: email
    };
    validateContact(contact); // UC2 Validation
    addressBook.push(contact);
    console.log("Contact Added Successfully");
}

function validateContact(contact) {
    if (!namePattern.test(contact.firstName)) {
        throw new Error("Invalid First name")
    }
    if (!namePattern.test(contact.lastName)) {
        throw new Error("Invalid Last Name");
    }

    if (!textPattern.test(contact.address)) {
        throw new Error("Invalid Address");
    }

    if (!textPattern.test(contact.city)) {
        throw new Error("Invalid City");
    }

    if (!textPattern.test(contact.state)) {
        throw new Error("Invalid State");
    }

    if (!zipPattern.test(contact.zip)) {
        throw new Error("Invalid Zip");
    }

    if (!phonePattern.test(contact.phone)) {
        throw new Error("Invalid Phone Number");
    }

    if (!emailPattern.test(contact.email)) {
        throw new Error("Invalid Email");
    }
}

// UC4: Find existing contact and edit it
function editContact(firstName, updatedDetails) {
    let contact = addressBook.find(function (person) {
        return person.firstName === firstName;
    });

    if (!contact) {
        console.log("Contact not found");
        return;
    }

    contact.lastName = updatedDetails.lastName || contact.lastName;
    contact.address = updatedDetails.address || contact.address;
    contact.city = updatedDetails.city || contact.city;
    contact.state = updatedDetails.state || contact.state;
    contact.zip = updatedDetails.zip || contact.zip;
    contact.phone = updatedDetails.phone || contact.phone;
    contact.email = updatedDetails.email || contact.email;

    validateContact(contact);
}

function deleteContact(firstName) {

    let index = addressBook.findIndex(function (person) {
        return person.firstName === firstName;
    });

    if (index === -1) {
        console.log("Contact not found ");
        return;
    }

    addressBook.splice(index, 1);
    console.log("Contact Deleted Successfully");
}

function getContactCount() {
    let count = addressBook.length;
    console.log("Total Contacts :", count);
    return count;
}

function isDuplicateContact(firstName, lastName) {

    let duplicate = addressBook.filter(function (person) {
        return person.firstName === firstName &&
            person.lastName === lastName;
    });

    return duplicate.length > 0;
}

function searchByCity(city) {
    let result = addressBook.filter(function (person) {
        return person.city === city;
    });

    if (result.length === 0) {
        console.log("No contacts found in city :", city);
    } else {
        console.log("Contacts in city", city, ":", result);
    }
    return result;
}
function searchByState(state) {

    let result = addressBook.filter(function (person) {
        return person.state === state;
    });

    if (result.length === 0) {
        console.log("No contacts found in state :", state);
    } else {
        console.log("Contacts in state", state, ":", result);
    }

    return result;
}
function viewPersonsByCity(city) {

    let persons = addressBook
        .filter(function (person) {
            return person.city === city;
        })
        .map(function (person) {
            return person.firstName + " " + person.lastName;
        });

    if (persons.length === 0) {
        console.log("No persons found in city :", city);
    } else {
        console.log("Persons in city", city, ":", persons);
    }

    return persons;
}

function viewPersonsByState(state) {

    let persons = addressBook
        .filter(function (person) {
            return person.state === state;
        })
        .map(function (person) {
            return person.firstName + " " + person.lastName;
        });

    if (persons.length === 0) {
        console.log("No persons found in state :", state);
    } else {
        console.log("Persons in state", state, ":", persons);
    }

    return persons;
}

function countByCity(city) {

    let count = addressBook.filter(function (person) {
        return person.city === city;
    }).length;

    console.log("Number of contacts in city", city, ":", count);
    return count;
}

function countByState(state) {

    let count = addressBook.filter(function (person) {
        return person.state === state;
    }).length;

    console.log("Number of contacts in state", state, ":", count);
    return count;
}

function sortContactsByName() {

    addressBook.sort(function (person1, person2) {
        let name1 = person1.firstName.toLowerCase();
        let name2 = person2.firstName.toLowerCase();

        if (name1 > name2) return 1;
        if (name1 < name2) return -1;
        return 0;
    });

    console.log("Contacts sorted alphabetically by name:");
    addressBook.forEach(function (person) {
        console.log(contactToString(person));
    });
}

function contactToString(contact) {
    return contact.firstName + " " + contact.lastName +
        ", " + contact.city + ", " + contact.state +
        ", " + contact.phone;
}

function sortByCity() {

    addressBook.sort(function (person1, person2) {
        let city1 = person1.city.toLowerCase();
        let city2 = person2.city.toLowerCase();

        if (city1 > city2) return 1;
        if (city1 < city2) return -1;
        return 0;
    });

    console.log("Contacts sorted by City:");
    addressBook.forEach(function (person) {
        console.log(person);
    });
}

function sortByState() {

    addressBook.sort(function (person1, person2) {
        let state1 = person1.state.toLowerCase();
        let state2 = person2.state.toLowerCase();

        if (state1 > state2) return 1;
        if (state1 < state2) return -1;
        return 0;
    });

    console.log("Contacts sorted by State:");
    addressBook.forEach(function (person) {
        console.log(person);
    });
}

function sortByZip() {

    addressBook.sort(function (person1, person2) {
        return person1.zip - person2.zip;
    });

    console.log("Contacts sorted by Zip:");
    addressBook.forEach(function (person) {
        console.log(person);
    });
}


try {
    addContact("John",
        "Doe",
        "Main Street",
        "Chennai",
        "Tamilnadu",
        "600001",
        "9876543210",
        "john.doe@email.com"
    );

    addContact(
        "Alice",
        "Smith",
        "Park Avenue",
        "Bangalore",
        "Karnataka",
        "560001",
        "9123456780",
        "alice.smith@email.com"
    );

    console.log(addressBook);
    editContact("John", {
        city: "Bangalore",
        phone: "9999999999"
    });
    console.log(addressBook);
    deleteContact("John");
    console.log(addressBook);
    getContactCount();
    addContact(
        "Alice",
        "Smith",
        "Park Avenue",
        "Bangalore",
        "Karnataka",
        "560001",
        "9123456780",
        "alice.smith@email.com"
    );
    addContact(
        "Alice",
        "Smith",
        "Park Avenue",
        "Bangalore",
        "Karnataka",
        "560001",
        "9123456780",
        "alice.smith@email.com"
    );
    console.log(addressBook);

    searchByCity("Chennai");
    searchByState("Karnataka");
    viewPersonsByCity("Chennai");
    viewPersonsByState("Karnataka");
    countByCity("Chennai");
    countByState("Karnataka");
    sortContactsByName();
    console.log(addressBook);
    sortByCity();
    sortByState();
    sortByZip();


} catch (error) {
    console.log("Error :", error.message);
}
