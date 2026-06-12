function celsiusToFahrenheit(degC) {
    return (degC * 9 / 5) + 32;
}

function fahrenheitToCelsius(degF) {
    return (degF - 32) * 5 / 9;
}

let choice = 1; // 1: C to F, 2: F to C
let value = 25;

switch (choice) {
    case 1:
        if (value >= 0 && value <= 100) {
            console.log("Fahrenheit:", celsiusToFahrenheit(value));
        } else {
            console.log("Invalid Celsius input");
        }
        break;

    case 2:
        if (value >= 32 && value <= 212) {
            console.log("Celsius:", fahrenheitToCelsius(value));
        } else {
            console.log("Invalid Fahrenheit input");
        }
        break;

    default:
        console.log("Invalid choice");
}