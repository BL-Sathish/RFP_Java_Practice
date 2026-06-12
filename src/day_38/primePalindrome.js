function isPrime(num) {
    if (num <= 1) return false;

    for (let i = 2; i * i <= num; i++) {
        if (num % i === 0) return false;
    }
    return true;
}

function getPalindrome(num) {
    let reverse = 0;
    let temp = num;

    while (temp > 0) {
        reverse = reverse * 10 + (temp % 10);
        temp = Math.floor(temp / 10);
    }
    return reverse;
}

let number = 131;

if (isPrime(number)) {
    console.log(number, "is Prime");

    let palindrome = getPalindrome(number);
    console.log("Palindrome:", palindrome);

    if (isPrime(palindrome)) {
        console.log("Palindrome is also Prime");
    } else {
        console.log("Palindrome is NOT Prime");
    }
} else {
    console.log(number, "is NOT Prime");
}