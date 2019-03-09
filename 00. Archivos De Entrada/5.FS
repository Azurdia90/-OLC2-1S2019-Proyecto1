var ventana_nueva = CrearVentana("#FFFFFF",700,700,"hola");
var contenedor_nuevo = ventana_nueva.crearcontenedor(100,100,"#CCCCFF",verdadero,10,100);
contenedor_nuevo.crearTexto("Arial",10,"#000000",10,10,verdadero,verdadero,"HOLIS!");
contenedor_nuevo.crearCajaTexto(50,50,"Arial",10,"#000000",20,20,verdadero,verdadero,"ESCRIBAN ACÁ!","cajatexto1");
contenedor_nuevo.crearAreaTexto(50,50,"Arial",10,"#000000",40,40,verdadero,verdadero,"NO!!!ESCRIBAN ACÁ!","areatexto1");
var boton_nuevo = contenedor_nuevo.crearBoton("Arial",10,"#FFFFFF",50,100,"boton1","aceptar",50,100);
boton_nuevo.AlClic(metodo1());
ventana_nueva.alcargar();

funcion metodo1()
{
    imprimir("hola si funciona el boton :)");
    retornar nulo;
}
