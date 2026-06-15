let numbers = [];

for (let i = 0; i < 10; i++) {
    numbers.push(Math.floor(Math.random() * 900) + 100);
}

numbers.sort((a, b) => a - b);

console.log("Arrays: ", numbers);
console.log("Second smallest number: ", numbers[0]);
console.log("Second largest number: ", numbers[numbers.length - 2]);

