let n = 84;

console.log("Prime Factors:");

for (let i = 2; i * i <= n; i++) {
    while (n % i === 0) {
        console.log(i);
        n = n / i;
    }
}

if (n > 1) {
    console.log(n);
}