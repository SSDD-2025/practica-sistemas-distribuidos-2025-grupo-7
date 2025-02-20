const dice = document.getElementById('dice');

let currentX = 0;
let currentY = 0;

function getRandomInt(min, max) {
return Math.floor(Math.random() * (max - min + 1)) + min;
}

dice.addEventListener('click', () => {
const randomX = getRandomInt(1, 4) * 90;
const randomY = getRandomInt(1, 4) * 90;

currentX += randomX;
currentY += randomY;

dice.style.transform = `rotateX(${currentX}deg) rotateY(${currentY}deg)`;
});