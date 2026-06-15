let numbers = [];

for (let i = 0; i < 10; i++){
    numbers.push(Math.floor(Math.random() * 900) + 100);
}

let largest = -Infinity;
let secondLargest = -Infinity;
let smallest = Infinity;
let secondSmallest = -Infinity;

for (let num of numbers) {
    if (num > largest) {
        secondLargest = largest;
        largest = num;
    } else if (num > secondLargest && num !== largest) {
        secondLargest = num;
    }
    if (num < smallest) {
        secondSmallest = smallest;
        smallest = num;
    } else if (num < secondSmallest && num !== smallest) {
        secondSmallest = num;
    }
}

console.log("Array: ", numbers);
console.log("Second largest: ", secondLargest);
console.log("Second smallest: ", secondSmallest);

