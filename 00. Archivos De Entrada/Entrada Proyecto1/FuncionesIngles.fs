funcion ackermann(var m, var n) {
    si (m == 0) {
        retornar (n + 1);
    } sino si (m > 0 && n == 0) {
        retornar ackermann(m - 1, 1);
    } sino {
        retornar ackermann(m - 1, ackermann(m, n - 1));
    }
}

funcion Pregunta(var tipo){
	Si(tipo == "Tipo"){
		imprimir("Se habla sobre Ackermann");
	}sino si(tipo == "Resultado"){
		imprimir("el resultado de ackerman 3,11 es " + ackermann(3,11));
	}
}