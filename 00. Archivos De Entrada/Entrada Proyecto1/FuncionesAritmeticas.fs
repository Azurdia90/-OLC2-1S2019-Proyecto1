
funcion potencia(var base, var exp) {
    retornar exp == 0 ? 1 : (base * potencia(base, exp - 1));
}

funcion factorial(var n) {
    retornar n == 0 ? 1 : n * factorial(n - 1);
}

funcion invertirNumero (var n){
    retornar n < 10 ? n : modulo(n, 10) + invertirNumero (n / 10) * 10;
}

funcion modulo(var n, var p){
    retornar n < p ? n : modulo( n - p, p);
}

funcion sacar_mcd(var a, var b) {
    retornar b == 0 ? a : sacar_mcd(b, modulo(a , b));
}

funcion fibonaci(var n){
    retornar (n==1 || n==2) ? 1 : fibonaci(n-1) + fibonaci(n-2);
} 

funcion VerPotencia(var num, var pot){
    Imprimir("La potencia de " + num + " a la " + pot + " es " + potencia(num, pot));
}

funcion VerFactorial(var num){
    Imprimir("El factorial de " + num + " es " + Factorial(num));
}

funcion VerInvertido(var num){
    Imprimir("El numero invertido de " + num + " es " + invertirNumero(num));
}

funcion VerMCD(var num, var p){
    Imprimir("El numero MCD de " + num + "a la " + p + " es " + sacar_mcd(num, p));
}

funcion VerFibonacci(var num){
    Imprimir("El Fibonacci de " + num + " es " + Fibonaci(num));
}