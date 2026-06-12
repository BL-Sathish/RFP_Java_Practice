function isPalindrome(num) {
    let original = num;
    let reverse = 0;

    while (num > 0) {
        reverse = reverse * 10 + (num % 10);
        num = Math.floor(num / 10);
    }

    return original === reverse;
}

let num1 = 121;
let num2 = 123;

console.log("Num1 Palindrome:", isPalindrome(num1));
console.log("Num2 Palindrome:", isPalindrome(num2));