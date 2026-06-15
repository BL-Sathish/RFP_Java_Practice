let repeated = [];

for (let i = 10; i <= 100; i++) {
    let tens = Math.floor(i / 10);
    let ones = i % 10;

    if (tens === ones) {
        repeated.push(i);
    }
}

console.log("Repeated Digits:", repeated);