var x = 4;
var y = 10;

si(x<y)
{
    var x = 11;
    imprimir("PEPA PIG " + x );
}
sino si (x==4)
{
    imprimir("entro al sino si");
}
sino 
{
    var y = "AMIGUITOS";
    imprimir("HOLA" + " " +  y);
}

selecciona(y/2)
{
    caso 1:
    {
        imprimir(1);
    }
    caso 2:
    {
        imprimir(2);
    }    
    caso 3:
    {
        imprimir(3);        
    }
    caso 4:
    {
        imprimir(4);
    }
    caso 5:
    {
        imprimir(5);
        si(y==10)
        {
            imprimir("este es el valor de y " + y );
        }
    }
    defecto:
    {
        imprimir("defecto");
    }
}

funcion prueba()
{
    imprimir("entrando al metodo prueba");
}