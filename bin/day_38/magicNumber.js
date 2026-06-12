let low = 1;
let high = 100;
let magicNumber = 73; 
let mid;

while (low <= high) {
    mid = Math.floor((low + high) / 2);

    if (mid === magicNumber) {
        console.log("Magic Number Found:", mid);
        break;
    } else if (mid > magicNumber) {
        high = mid - 1;
    } else {
        low = mid + 1;
    }
}