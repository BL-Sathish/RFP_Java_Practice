let heads = 0;
let tails = 0;

while (heads < 11 && tails < 11) {
    let flip = Math.floor(Math.random() * 2);

    if (flip === 0) {
        heads++;
    } else {
        tails++;
    }
}

console.log("Heads:", heads);
console.log("Tails:", tails);