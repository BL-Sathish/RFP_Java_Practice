let n = 8;
let i = 0;
let power = 1;

while (i <= n && power <= 256) {
    console.log(`2^${i} = ${power}`);
    power = power * 2;
    i++;
}