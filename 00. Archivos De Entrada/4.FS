imprimir(fibonacci (1));

funcion fibonacci(var valor)
{
    si(valor > 1)
    {
        retornar fibonacci (valor - 1 ) + fibonacci (valor - 2);
    }
    sino si( valor == 1)
    {
        retornar 1;
    }
    sino si( valor == 0)
    {
        retornar 1;
    }
    sino
    {
        retornar -1;
    }
}

funcion factorial(var num)
{
    si(num == 0)
    {
        retornar 1;
    }
    sino 
    {
        retornar num * factorial(num -1);
    }
}

imprimir("El factorial de 5 es: "+ factorial(5));



imprimir( anckerman(1,2));

funcion anckerman(var valor1, var valor2)
{
    si(valor1==0)
    {
        retornar valor2 + 1;
    }
    sino si(valor1 > 0 && valor2 == 0)
    {
        retornar anckerman(valor1 - 1,1);
    }
    sino 
    {
        retornar anckerman(valor1 - 1,anckerman(valor1,valor2 - 1));
    }
}