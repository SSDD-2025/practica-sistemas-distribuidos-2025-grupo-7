const dice = document.getElementById('dice');

let currentX = 0;
let currentY = 0;
let currentFace = 1;

// Función para obtener un número aleatorio entre min y max
function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Función para determinar la cara superior del dado basada en los ángulos de rotación
function getDiceFace(x, y) {
    // Normalizar los ángulos a valores entre 0 y 3 (porque cada 360° vuelve al inicio)
    const modX = ((x / 90) % 4 + 4) % 4;
    const modY = ((y / 90) % 4 + 4) % 4;

    // Casos de la cara superior basados en la combinación de rotaciones
    if (modX === 0 && modY === 0) return 1;
    if (modX === 0 && modY === 1) return 5;
    if (modX === 0 && modY === 2) return 6;
    if (modX === 0 && modY === 3) return 2;
    if (modX === 1 && modY === 0) return 410;
    if (modX === 1 && modY === 1) return 411;
    if (modX === 1 && modY === 2) return 412;
    if (modX === 1 && modY === 3) return 413;
    if (modX === 2 && modY === 0) return 6;
    if (modX === 2 && modY === 1) return 221;
    if (modX === 2 && modY === 2) return 122;
    if (modX === 2 && modY === 3) return 523;
    if (modX === 3 && modY === 0) return 3;
    if (modX === 3 && modY === 1) return 331;
    if (modX === 3 && modY === 2) return 332;
    if (modX === 3 && modY === 3) return 333;
    else return 0;

}

// Evento al hacer clic en el dado
/*dice.addEventListener('click', () => {

    const randomX = getRandomInt(1, 4) * 90;
    const randomY = getRandomInt(1, 4) * 90;

    //const randomX = 1*90;
    //const randomY = 2*90;


    currentX += randomX;
    currentY += randomY;

    dice.style.transform = `rotateX(${currentX}deg) rotateY(${currentY}deg)`;

    // Calcular la cara superior correcta
    currentFace = getDiceFace(currentX, currentY);

    // Mostrar el resultado en pantalla
    document.getElementById("currentFace").innerHTML = currentFace;
});*/

