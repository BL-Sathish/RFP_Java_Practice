let diceMap = new Map();

// initialize map with 1–6
for (let i = 1; i <= 6; i++) {
    diceMap.set(i, 0);
}

let reachedTen = false;

while (!reachedTen) {
    let roll = Math.floor(Math.random() * 6) + 1;
    diceMap.set(roll, diceMap.get(roll) + 1);

    if (diceMap.get(roll) === 10) {
        reachedTen = true;
    }
}

let maxKey, minKey;
let maxValue = -Infinity;
let minValue = Infinity;

for (let [key, value] of diceMap) {
    if (value > maxValue) {
        maxValue = value;
        maxKey = key;
    }
    if (value < minValue) {
        minValue = value;
        minKey = key;
    }
}

console.log("Dice Roll Counts:", diceMap);
console.log("Maximum rolled number:", maxKey);
console.log("Minimum rolled number:", minKey);