let start = 10;
let end = 50;

for (let num = start; num <= end; num++) {
    let isPrime = num > 1;

    for (let i = 2; i < num; i++) {
        if (num % i === 0) {
            isPrime = false;
            break;
        }
    }

    if (isPrime) {
        console.log(num);
    }
}