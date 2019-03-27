funcion Hanoi(var discos, var origen, var auxiliar, var destino) {
    si (discos == 1) {
        imprimir("mover disco de " + origen + " a " + destino);
    } sino {
        Hanoi(discos - 1, origen, destino, auxiliar);
        imprimir("mover disco de " + origen + " a " + destino);
        Hanoi(discos - 1, auxiliar, origen, destino);
    }
}

funcion hofstaderFemenina(var n) {
    si (n < 0) {
        retornar 0;
    } sino {
        retornar (n == 0) ? 1 : n - hofstaderFemenina(n - 1);
    }
}

funcion hofstaderMasculino(var n) {
    si (n < 0) {
        retornar 0;
    } sino {
        retornar n == 0 ? 0 : n - hofstaderMasculino(n - 1);
    }
}

funcion par(var nump) {
    si (nump == 0) {
        retornar 1;
    }
    retornar impar(nump - 1);
}

funcion impar(var numi) {
    si (numi == 0) {
        retornar 0;
    }
    retornar par(numi - 1);
}

funcion VerFemenina(){
    hofstaderFemenina(10);
}

funcion VerMasculino(){
    hofstaderMasculino(10);
}

funcion VerPar(var a){
    imprimir("El numero " + a + " es: " + (par(a) == 1 ? "Par" : "Impar"));
}

funcion VerImpar(var b){
    imprimir("El numero " + b + " es: " + (impar(b) == 0 ? "Par" : "Impar"));
}

        