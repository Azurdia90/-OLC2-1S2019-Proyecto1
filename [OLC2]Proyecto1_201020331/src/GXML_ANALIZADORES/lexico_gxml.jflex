package GXML_ANALIZADORES;

import	java_cup.runtime.Symbol; 
import UI.ObjetoEntrada;

%%

%cupsym Tabla_Simbolos_GXML_CUP
%class Lexico_GXML
%ignorecase
%unicode
%public
%column
%char
%line   
%cup                            

%state CA

letra         = [a-zA-ZnÑ]
identificador = ({letra}|("_"{letra}))({letra}|"_"|{digito})*

digito  = [0-9]    
entero  = {digito}+
decimal = {entero}"."{entero}

cadena   = "\"" [^\"]* "\""                      
caracter = "'" [^"'"] "'"
booleano = "verdadero"|"falso"

texto_script =  ("{") ("\n"|[^"}"])* ("}")
contenido_tag= ([^"<"])+

comentario_single =   ("##"([^"\n"]))*
comentario_multi  =  ("#$"(" "|"\n"|[^*][^$])*"$#")



s_key_open  = "{"
s_key_close = "}"

s_cor_open  = "["
s_cor_close = "]"

s_par_open       = "("
s_par_close      = ")"
s_plus           = "+"
s_minus          = "-"
s_mul            = "*"
s_div            = "/"
s_to             = "^"
s_mod            = "%"
s_increment      = "++"
s_decrement      = "--"
s_compare        = "=="
s_diferent       = "!="
s_less           = "<"
s_greather       = ">"
s_less_equal     = "<="
s_greather_equal = ">="
s_or             = "||"
s_and            = "&&"
s_not            = "!"
s_equal          = "="

s_dobledot = ":"
s_dot      = "."
s_coma     = ","
s_dotcoma  = ";"

//etiquetas
r_importar       = "importar"
r_ventana        = "ventana"
r_contenedor     = "contenedor"
r_texto          = "texto"
r_controlador    = "controlador"
r_listadatos     = "listadatos"
r_dato           = "dato"  
r_control        = "control"
r_defecto        = "defecto" 
r_multimedia     = "multimedia"
r_boton          = "boton"
r_enviar         = "enviar" 

//parametros
r_id               = "id"
r_tipo             = "tipo"
r_principal        = "principal"
r_secundaria       = "secundaria"
r_color            = "color"
r_accioninicial    = "accioninicial"
r_accionfinal      = "accionfinal"
r_posx             = "x"
r_posy             = "y"
r_alto             = "alto"
r_ancho            = "ancho"
r_borde            = "borde"
r_nombre           = "nombre"
r_fuente           = "fuente"
r_tamano           = "tam"
r_negrita          = "negrita"         
r_cursiva          = "cursiva" 
r_numerico         = "numerico"
r_textoarea        = "textoarea"
r_desplegable      = "desplegable"
r_maximo           = "maximo"
r_minimo           = "minimo"
r_accion           = "accion"
r_referencia       = "referencia" 
r_musica           = "musica"
r_video            = "video"
r_imagen           = "imagen"
r_path             = "path" 
r_autoreproduccion = "autoreproduccion"


%{
//codigo que se utilizara en el analizador lexico

private String texto_contenido = "";
private ObjetoEntrada entrada;

public void setObjetoEntrada(ObjetoEntrada p_entrada)
{
    entrada = p_entrada;
}

public ObjetoEntrada getObjetoEntrada()
{
    return entrada;
}

private void _error(String erro_texto, int erro_column, int erro_line)
{
    ERRORES.Nodo_Error error_encontrado = new ERRORES.Nodo_Error();
    error_encontrado.setArchivo(entrada.getNombre_archivo());
    error_encontrado.setIdentificador("Análisis Léxico GXML");
    error_encontrado.setDescripcion("Caracter no reconocido: " + erro_texto);
    error_encontrado.setLinea(Integer.toString(erro_line));
    error_encontrado.setColumna(Integer.toString(erro_column));
    error_encontrado.setTipo("Lexico");
    ERRORES.Tabla_Errores.getInstance().add(error_encontrado);
    //System.out.println("Error Lexico: " + erro_texto + " columna: " + erro_column + " linea: " + erro_line);
}

%}

%%

//simbolos de operacion
{s_par_open}                        {return new Symbol(Tabla_Simbolos_GXML_CUP.s_par_open, yycolumn,yyline, new String(yytext()));}
{s_par_close}                       {return new Symbol(Tabla_Simbolos_GXML_CUP.s_par_close, yycolumn,yyline, new String(yytext()));}
{s_cor_open}                        {return new Symbol(Tabla_Simbolos_GXML_CUP.s_cor_open, yycolumn,yyline, new String(yytext()));}
{s_cor_close}                       {return new Symbol(Tabla_Simbolos_GXML_CUP.s_cor_close, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{s_equal}                {return new Symbol(Tabla_Simbolos_GXML_CUP.s_equal, yycolumn,yyline, new String(yytext()));}
{s_plus}                            {return new Symbol(Tabla_Simbolos_GXML_CUP.s_plus, yycolumn,yyline, new String(yytext()));}
{s_minus}                           {return new Symbol(Tabla_Simbolos_GXML_CUP.s_minus, yycolumn,yyline, new String(yytext()));}
{s_mul}                             {return new Symbol(Tabla_Simbolos_GXML_CUP.s_mul, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{s_div}                  {return new Symbol(Tabla_Simbolos_GXML_CUP.s_div, yycolumn,yyline, new String(yytext()));}
{s_to}                              {return new Symbol(Tabla_Simbolos_GXML_CUP.s_to, yycolumn,yyline, new String(yytext()));}
{s_mod}                             {return new Symbol(Tabla_Simbolos_GXML_CUP.s_mod, yycolumn,yyline, new String(yytext()));}
{s_increment}                       {return new Symbol(Tabla_Simbolos_GXML_CUP.s_increment, yycolumn,yyline, new String(yytext()));}
{s_decrement}                       {return new Symbol(Tabla_Simbolos_GXML_CUP.s_decrement, yycolumn,yyline, new String(yytext()));}
{s_compare}                         {return new Symbol(Tabla_Simbolos_GXML_CUP.s_compare, yycolumn,yyline, new String(yytext()));}
{s_diferent}                        {return new Symbol(Tabla_Simbolos_GXML_CUP.s_diferent, yycolumn,yyline, new String(yytext()));}

<YYINITIAL>{s_less}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.s_less, yycolumn,yyline, new String(yytext()));}
{s_less_equal}                      {return new Symbol(Tabla_Simbolos_GXML_CUP.s_less_equal, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{s_greather}             {texto_contenido = ""; yybegin(CA); return new Symbol(Tabla_Simbolos_GXML_CUP.s_greather, yycolumn,yyline, new String(yytext()));}
{s_greather_equal}                  {return new Symbol(Tabla_Simbolos_GXML_CUP.s_greather_equal, yycolumn,yyline, new String(yytext()));}
{s_or}                              {return new Symbol(Tabla_Simbolos_GXML_CUP.s_or, yycolumn,yyline, new String(yytext()));}
{s_and}                             {return new Symbol(Tabla_Simbolos_GXML_CUP.s_and, yycolumn,yyline, new String(yytext()));}
{s_not}                             {return new Symbol(Tabla_Simbolos_GXML_CUP.s_not, yycolumn,yyline, new String(yytext()));}

//simbolos del lenguaje
{s_key_open}                        {return new Symbol(Tabla_Simbolos_GXML_CUP.s_key_open, yycolumn,yyline, new String(yytext()));}
{s_key_close}                       {return new Symbol(Tabla_Simbolos_GXML_CUP.s_key_close, yycolumn,yyline, new String(yytext()));}
{s_dobledot}                        {return new Symbol(Tabla_Simbolos_GXML_CUP.s_dobledot, yycolumn,yyline, new String(yytext()));}
{s_dot}                             {return new Symbol(Tabla_Simbolos_GXML_CUP.s_dot, yycolumn,yyline, new String(yytext()));}
{s_coma}                            {return new Symbol(Tabla_Simbolos_GXML_CUP.s_coma, yycolumn,yyline, new String(yytext()));}
{s_dotcoma}                         {return new Symbol(Tabla_Simbolos_GXML_CUP.s_dotcoma, yycolumn,yyline, new String(yytext()));}

//palabras reservadas etiquetas
<YYINITIAL>{r_importar}             {return new Symbol(Tabla_Simbolos_GXML_CUP.r_importar, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_ventana}              {return new Symbol(Tabla_Simbolos_GXML_CUP.r_ventana, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_contenedor}           {return new Symbol(Tabla_Simbolos_GXML_CUP.r_contenedor, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_texto}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_texto, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_controlador}          {return new Symbol(Tabla_Simbolos_GXML_CUP.r_controlador, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_listadatos}           {return new Symbol(Tabla_Simbolos_GXML_CUP.r_listadatos, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_dato}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_dato, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_control}              {return new Symbol(Tabla_Simbolos_GXML_CUP.r_control, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_defecto}              {return new Symbol(Tabla_Simbolos_GXML_CUP.r_defecto, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_multimedia}           {return new Symbol(Tabla_Simbolos_GXML_CUP.r_multimedia, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_boton}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_boton, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_enviar}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_enviar, yycolumn,yyline, new String(yytext()));}

//palabras reservadas de gxml
<YYINITIAL>{r_id}                   {return new Symbol(Tabla_Simbolos_GXML_CUP.r_id, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_tipo}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_tipo, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_principal}            {return new Symbol(Tabla_Simbolos_GXML_CUP.r_principal, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_secundaria}           {return new Symbol(Tabla_Simbolos_GXML_CUP.r_secundaria, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_color}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_color, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_accioninicial}        {return new Symbol(Tabla_Simbolos_GXML_CUP.r_accioninicial, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_accionfinal}          {return new Symbol(Tabla_Simbolos_GXML_CUP.r_accionfinal, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_posx}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_posx, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_posy}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_posy, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_alto}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_alto, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_ancho}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_ancho, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_borde}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_borde, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_nombre}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_nombre, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_fuente}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_fuente, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_tamano}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_tamano, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_negrita}              {return new Symbol(Tabla_Simbolos_GXML_CUP.r_negrita, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_cursiva}              {return new Symbol(Tabla_Simbolos_GXML_CUP.r_cursiva, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_numerico}             {return new Symbol(Tabla_Simbolos_GXML_CUP.r_numerico, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_textoarea}            {return new Symbol(Tabla_Simbolos_GXML_CUP.r_textoarea, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_desplegable}          {return new Symbol(Tabla_Simbolos_GXML_CUP.r_desplegable, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_maximo}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_maximo, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_minimo}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_minimo, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_accion}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_accion, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_referencia}           {return new Symbol(Tabla_Simbolos_GXML_CUP.r_referencia, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_musica}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_musica, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_video}                {return new Symbol(Tabla_Simbolos_GXML_CUP.r_video, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_imagen}               {return new Symbol(Tabla_Simbolos_GXML_CUP.r_imagen, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_path}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.r_path, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{r_autoreproduccion}     {return new Symbol(Tabla_Simbolos_GXML_CUP.r_autoreproduccion, yycolumn,yyline, new String(yytext()));}

//expresiones regulares tipo datos
<YYINITIAL>{booleano}               {return new Symbol(Tabla_Simbolos_GXML_CUP.booleano, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{entero}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.entero, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{decimal}                {return new Symbol(Tabla_Simbolos_GXML_CUP.decimal, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{caracter}               {return new Symbol(Tabla_Simbolos_GXML_CUP.caracter, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{cadena}                 {return new Symbol(Tabla_Simbolos_GXML_CUP.cadena, yycolumn,yyline, new String(yytext()));}
<YYINITIAL>{identificador}          {return new Symbol(Tabla_Simbolos_GXML_CUP.identificador, yycolumn,yyline, new String(yytext()));}

<CA>       {contenido_tag}          { yybegin(YYINITIAL); texto_contenido+=yytext(); if(!(texto_contenido.replaceAll("\n","").trim().equals(""))) return new Symbol(Tabla_Simbolos_GXML_CUP.contenido_tag, yycolumn, yyline, texto_contenido);}

//tokens omitidos
[ \t\r\f\n]+            {/* Se ignoran */}  
{comentario_multi}      {/* Se ignoran */}
{comentario_single}     {/* Se ignoran */}

/* CUAQUIER OTRO */ 
.         		{_error( new String (yytext()),yycolumn,yyline);}