var arreglo_prueba =  [3,2,1];
imprimir(arreglo_prueba[0]);
arreglo_prueba.descendente();
imprimir(arreglo_prueba[0]);

var arreglo_prueba =  [1,2,3];
imprimir(arreglo_prueba[0]);
arreglo_prueba.ascendente();
imprimir(arreglo_prueba[0]);

var arreglo_prueba =  [1,2,3];
imprimir(arreglo_prueba[0]);
arreglo_prueba.invertir();
imprimir(arreglo_prueba[0]);

var arreglo_prueba =  [1,2,3];
imprimir(arreglo_prueba.maximo());


funcion verificaredad(var edad)
{
    si(edad >= 18)
    {
        retornar edad;
    }    
}

var arreglo_prueba =  [32,300,33,10,16,40];
var arreglo_aux = arreglo_prueba.filtrar(verificaredad);
imprimir(arreglo_aux[5]);