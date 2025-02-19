
function actua(){
    boton.style.display="none";
};
function redirigir(){
    window.location.href="/register";
}
function redirigirRuleta(){
    window.location.href="rule2/rule2.html"
}
window.onload=function(){
    if(localStorage.getItem("boolean") === "true"){
        document.getElementById("boton-reg").style.display="none";
        document.getElementById("boton-ini").style.display="none";
        document.getElementById("welcome").style.display="block";
    }
}