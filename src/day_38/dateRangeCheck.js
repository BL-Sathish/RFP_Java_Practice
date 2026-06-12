let day = 15;
let month = "April";
let isTrue = false;

if ((month === "March" && day >= 20 ) || (month === "April") || (month === "May" ) || (month === "June" && day <= 20)){
    isTrue = true;
}
console.log(isTrue);