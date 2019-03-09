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

// filtrar
funcion verificaredad(var edad)
{
    retornar edad>=18;  
}

var arreglo_prueba =  [32,300,33,10,16,40];
var arreglo_aux = arreglo_prueba.filtrar(verificaredad);
imprimir(arreglo_aux[5]);

//buscar
funcion buscaredad(var edad)
{
    retornar edad == 18;  
}

var arreglo_prueba =  [32,300,18,10,16,40];
var resultado_aux = arreglo_prueba.buscar(buscaredad);
imprimir(resultado_aux);

//map
funcion sumarvalores(var precios)
{
    retornar precios * 2;  
}

var arreglo_prueba =  [1,2,3,4,5,6];
var resultado_aux = arreglo_prueba.map(sumarvalores);
imprimir(resultado_aux[0]);

//reduce
funcion preciototal(var total, var precio)
{
    retornar total + precio;  
}

var arreglo_prueba =  [32,300,33,100,160,40];
var resultado_aux = arreglo_prueba.reduce(preciototal);
imprimir(resultado_aux);


//todos
funcion verificaredad(var edad)
{
    retornar edad>=18;  
}

var arreglo_prueba =  [32,300,33,100,160,40];
var resultado_aux = arreglo_prueba.todos(verificaredad);
imprimir(resultado_aux);

//alguno
funcion verificaredad(var edad)
{
    retornar edad>=18;  
}

var arreglo_prueba =  [32,300,33,100,160,40];
var resultado_aux = arreglo_prueba.alguno(verificaredad);
imprimir(resultado_aux);