let min, max;

for (let i = 1; i <= 5; i++) {
    let num = Math.floor(Math.random() * 900) + 100;
    console.log("Number:", num);

    if (i == 1){
        min = max = num;
    }else {
        if (num < min) {
            min = num;
        }
        if (num > max){
            max = num;
        }
    }
}

console.log("Minimum: " + min);
console.log("Maximum: " + max);
