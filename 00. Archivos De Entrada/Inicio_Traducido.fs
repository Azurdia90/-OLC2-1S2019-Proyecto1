Importar("C:\Users\Cristian Azurdia\OneDrive\Documentos\3. GitHub\-OLC2-1S2019-Proyecto1\00. Archivos De Entrada\Funciones.fs");
/////////////////////////////////////////////////////////////////////////////////
///////////////////         Traduccion Ventana Inicio         ///////////////////
/////////////////////////////////////////////////////////////////////////////////

var Ven_Inicio = CrearVentana("#ffffff",700,700,"hola");

/////////////////// Contenedor Cont1
var Cont1_Inicio = Ven_Inicio.CrearContenedor(200, 200, "#ffffff", falso, 10, 10);

////////////////// Valores de Nombre
cont1_inicio.CrearTexto("Arial", 14, "#000000", 10, 20, falso, falso, "Nombre");
cont1_inicio.CrearCajaTexto(10, 100, "Arial", 14, "#000000", 40, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre");

////////////////// Valores de Correo
cont1_inicio.CrearTexto("Arial", 14, "#000000", 10, 50, falso, falso, "Correo");
cont1_inicio.CrearCajaTexto(10, 100, "Arial", 14, "#000000", 40, 50, falso, falso, "Ingrese aqui su correo", "CTCorreo");

/////////////////// Contenedor ContBtn
var ContBtn_Inicio = Ven_Inicio.CrearContenedor(100, 200, "#ffffff", falso, 10, 220);

/////////////////// Boton enviar
Var btnIngresar_Inicio = ContBtn_Inicio.CrearBoton("Arial", 14, "#000000", 25, 30,"botoningresar","Ingresar", 70, 50);
btnIngresar_Inicio.AlClic(Guardar_Inicio());

/////////////////// Boton Registrar
Var btnRegistrar_Inicio = ContBtn_Inicio.CrearBoton("Arial", 14, "#000000", 75, 30, "botoninicio", "Registrar", 70, 50);

/////////////////////////////////////////////////////////////////////////////////
/////////////////         Fin Traduccion Ventana Inicio         /////////////////
/////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////
//////////////////         Traduccion Ventana Registro         //////////////////
/////////////////////////////////////////////////////////////////////////////////

var Ven_Registrar = CrearVentana("#ffffff",700,700,"hola");

/////////////////// Contenedor Cont1
var Cont1_Registrar = Ven_Registrar.CrearContenedor(300, 200, "#ffffff", falso, 10, 10);

////////////////// Valores de Nombre
Cont1_Registrar.CrearTexto("Arial", 14, "#000000", 10, 20, falso, falso, "Nombre");
Cont1_Registrar.CrearCajaTexto(10, 100, "Arial", 14, "#000000", 40, 20, falso, falso, "Ingrese aqui su nombre", "CTNombre");

////////////////// Valores de Edad
Cont1_Registrar.CrearTexto("Arial", 14, "#000000", 10, 50, falso, falso, "Edad");
//Cont1_Registrar.CrearControlNumerico(10, 50, nulo, 18, 40, 50, 18, "CEdad"); //Sin valor maximo que sea nulo

////////////////// Valores de Descripcion
Cont1_Registrar.CrearTexto("Arial", 14, "#000000", 10, 80, falso, falso, "Descripcion");
Cont1_Registrar.CrearAreaTexto(100, 100, "Arial", 14, "#000000", 40, 80, falso, falso, "Ingrese aqui la descripcion de \n su registro", "CADescripcion"); 

////////////////// Valores de Correo
Cont1_Registrar.CrearTexto("Arial", 14, "#000000", 10, 200, falso, falso, "Correo");
Cont1_Registrar.CrearCajaTexto(10, 100, "Arial", 14, "#000000", 40, 200, falso, falso, "Ingrese aqui su correo", "CTCorreo");

////////////////// Valores de Pais
Cont1_Registrar.CrearTexto("Arial", 14, "#000000", 10, 220, falso, falso, "Pais");
var lista_pais_cont1 = ["Guatemala", "Honduras", "Costa Rica", "Panama"];
//Cont1_Registrar.CrearDesplegable(40, 220, lista_pais_cont1, 10, 100, "Guatemala", "CDPais");

/////////////////// Contenedor ContBtn
var ContBtn_Registrar = Ven_Registrar.CrearContenedor(100, 200, "#ffffff", falso, 10, 320);

/////////////////// Boton enviar
Var btnEnviar_Registrar = ContBtn_Inicio.CrearBoton("Arial", 14, "#000000", 25, 30, "botoninicio3", "Ingresar", 70, 50);
btnEnviar_Registrar.AlClic(Guardar_Registrar());

Ven_Registrar.AlCargar();
Ven_Registrar.AlCerrar();

/////////////////////////////////////////////////////////////////////////////////
////////////////         Fin Traduccion Ventana Registro         ////////////////
/////////////////////////////////////////////////////////////////////////////////

funcion Guardar_Inicio(){
	imprimir("Se Esta guardando un arreglo desde archivo");
	//Ven_Inicio.crearArrayDesdeArchivo();
}

funcion Guardar_Registrar(){
	imprimir("Se Esta guardando un arreglo desde archivo");
	//Ven_Registrar.crearArrayDesdeArchivo();
}

funcion CargarVentana_Registrar(){
	ven_Registrar.AlCargar();
}

funcion CargarVentana_Inicio(){
	Ven_Inicio.AlCargar();
}

Ven_Inicio.AlCargar(); 
// Traduccion ventana principal