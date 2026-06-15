let birthMonthMap = new Map();

for (let i = 1; i <= 12; i++) {
    birthMonthMap.set(i, []);
}

for (let i = 1; i <= 50; i++) {
    let month = Math.floor(Math.random() * 12) + 1;
    birthMonthMap.get(month).push("Person" + i);
}

for (let [month, people] of birthMonthMap) {
    if (people.length > 0) {
        console.log(`Month ${month}:`, people);
    }
}