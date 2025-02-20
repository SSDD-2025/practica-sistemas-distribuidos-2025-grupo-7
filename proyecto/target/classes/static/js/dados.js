const dice = document.getElementById('dice');

let currentX = 0;
let currentY = 0;
let currentFace = 5; // Variable para almacenar el resultado del dado

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function getDiceFace(x, y) {
    const modX = (x / 90) % 4;
    const modY = (y / 90) % 4;

    // Mapeo de caras según la rotación
    if (modX === 0 && modY === 0) return 1;
    if (modX === 0 && modY === 2) return 5;
    if (modX === 1) return 6;
    if (modX === 3) return 2;
    if (modY === 1) return 3;
    if (modY === 3) return 4;
    
    return 1; // Valor por defecto
}

dice.addEventListener('click', () => {
    const randomX = getRandomInt(1, 4) * 90;
    const randomY = getRandomInt(1, 4) * 90;

    currentX += randomX;
    currentY += randomY;

    dice.style.transform = `rotateX(${currentX}deg) rotateY(${currentY}deg)`;

    // Obtener el número de la cara visible
    currentFace = getDiceFace(currentX, currentY);
    document.getElementById("currentFace").innerHTML = currentFace;
});
