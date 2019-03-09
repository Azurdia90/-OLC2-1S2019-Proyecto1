var objetoprueba = {curso:"compi", oportunidad : 3, aprobado : verdadero};
imprimir(objetoprueba.curso);
imprimir(objetoprueba.oportunidad);
imprimir(objetoprueba.aprobado);

objetoprueba.aprobado = falso;

probarobjetos(objetoprueba);

funcion probarobjetos(var objetoparametro)
{
    imprimir(objetoparametro.curso);
    imprimir(objetoparametro.oportunidad);
    imprimir(objetoparametro.aprobado);
}
