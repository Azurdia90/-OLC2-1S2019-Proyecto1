package FS_ANALIZADORES;

import	java_cup.runtime.Symbol; 
import UI.ObjetoEntrada;

%%

%cupsym Tabla_Simbolos_FS_CUP
%class Lexico_FS
%ignorecase
%unicode
%public
%column
%char
%line   
%cup                            
       
letra         = [a-zA-ZnÑ]
identificador = ({letra}|("_"{letra}))({letra}|"_"|{digito})*

digito  = [0-9]    
entero  = {digito}+
decimal = {entero}"."{entero}

cadena   = "\"" [^\"]* "\""                      
caracter = "'" [^"'"] "'"
booleano = "verdadero"| "falso"

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

s_ternario = "?"

s_dobledot = ":"
s_dot      = "."
s_coma     = ","
s_dotcoma  = ";"

r_nulo     = "nulo"
r_var      = "var"
r_imprimir = "imprimir"

r_importar    = "importar"
r_detener     = "detener"
r_retornar    = "retornar"
r_si          = "si"
r_sino        = "sino"
r_selecciona  = "selecciona"
r_caso        = "caso"
r_defecto     = "defecto"
r_funcion     = "funcion"

r_descendente = "descendente"
r_ascendente  = "ascendente"
r_invertir    = "invertir"
r_maximo      = "maximo"
r_minimo      = "minimo"
r_filtrar     = "filtrar"
r_buscar      = "buscar"
r_map         = "map"
r_reduce      = "reduce"
r_todos       = "todos"
r_alguno      = "alguno"
r_creararray  = "creararraydesdearchivo"

r_leergxml             = "leergxml"
r_obteneretiqueta      = "obtenerporetiqueta"
r_obtenerid            = "obtenerporid"
r_obtenernombre        = "obtenerpornombre"
r_crearventana         = "crearventana"
r_crearcontenedor      = "crearcontenedor"
r_creartexto           = "creartexto"
r_crearcajatexto       = "crearcajatexto"
r_crearareatexto       = "crearareatexto"
r_crearcontrolnumerico = "crearcontrolnumerico"
r_creardesplegable     = "creardesplegable"
r_crearboton           = "crearboton"
r_crearimagen          = "crearimagen"
r_crearreproductor     = "crearreproductor"
r_crearvideo           = "crearvideo"

r_clic   = "alclic"
r_cargar = "alcargar"
r_cerrar = "alcerrar"

comentario_single = [/][/] [^\n]* [\n]
comentario_multi = [/]"*" [^*]* "*"[/]

%{
//codigo que se utilizara en el analizador lexico

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
    error_encontrado.setIdentificador("Análisis Léxico FuncionScript");
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
{s_par_open}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_par_open, yycolumn,yyline, new String(yytext()));}
{s_par_close}                       {return new Symbol(Tabla_Simbolos_FS_CUP.s_par_close, yycolumn,yyline, new String(yytext()));}
{s_cor_open}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_cor_open, yycolumn,yyline, new String(yytext()));}
{s_cor_close}                       {return new Symbol(Tabla_Simbolos_FS_CUP.s_cor_close, yycolumn,yyline, new String(yytext()));}
{s_plus}                            {return new Symbol(Tabla_Simbolos_FS_CUP.s_plus, yycolumn,yyline, new String(yytext()));}
{s_minus}                           {return new Symbol(Tabla_Simbolos_FS_CUP.s_minus, yycolumn,yyline, new String(yytext()));}
{s_mul}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_mul, yycolumn,yyline, new String(yytext()));}
{s_div}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_div, yycolumn,yyline, new String(yytext()));}
{s_to}                              {return new Symbol(Tabla_Simbolos_FS_CUP.s_to, yycolumn,yyline, new String(yytext()));}
{s_mod}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_mod, yycolumn,yyline, new String(yytext()));}
{s_increment}                       {return new Symbol(Tabla_Simbolos_FS_CUP.s_increment, yycolumn,yyline, new String(yytext()));}
{s_decrement}                       {return new Symbol(Tabla_Simbolos_FS_CUP.s_decrement, yycolumn,yyline, new String(yytext()));}
{s_compare}                         {return new Symbol(Tabla_Simbolos_FS_CUP.s_compare, yycolumn,yyline, new String(yytext()));}
{s_diferent}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_diferent, yycolumn,yyline, new String(yytext()));}
{s_less}                            {return new Symbol(Tabla_Simbolos_FS_CUP.s_less, yycolumn,yyline, new String(yytext()));}
{s_greather}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_greather, yycolumn,yyline, new String(yytext()));}
{s_less_equal}                      {return new Symbol(Tabla_Simbolos_FS_CUP.s_less_equal, yycolumn,yyline, new String(yytext()));}
{s_greather_equal}                  {return new Symbol(Tabla_Simbolos_FS_CUP.s_greather_equal, yycolumn,yyline, new String(yytext()));}
{s_or}                              {return new Symbol(Tabla_Simbolos_FS_CUP.s_or, yycolumn,yyline, new String(yytext()));}
{s_and}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_and, yycolumn,yyline, new String(yytext()));}
{s_not}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_not, yycolumn,yyline, new String(yytext()));}
{s_equal}                           {return new Symbol(Tabla_Simbolos_FS_CUP.s_equal, yycolumn,yyline, new String(yytext()));}

//simbolos del lenguaje
{s_key_open}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_key_open, yycolumn,yyline, new String(yytext()));}
{s_key_close}                       {return new Symbol(Tabla_Simbolos_FS_CUP.s_key_close, yycolumn,yyline, new String(yytext()));}
{s_ternario}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_ternario, yycolumn,yyline, new String(yytext()));}
{s_dobledot}                        {return new Symbol(Tabla_Simbolos_FS_CUP.s_dobledot, yycolumn,yyline, new String(yytext()));}
{s_dot}                             {return new Symbol(Tabla_Simbolos_FS_CUP.s_dot, yycolumn,yyline, new String(yytext()));}
{s_coma}                            {return new Symbol(Tabla_Simbolos_FS_CUP.s_coma, yycolumn,yyline, new String(yytext()));}
{s_dotcoma}                         {return new Symbol(Tabla_Simbolos_FS_CUP.s_dotcoma, yycolumn,yyline, new String(yytext()));}

//palabras reservadas del lenguaje
{r_nulo}                            {return new Symbol(Tabla_Simbolos_FS_CUP.r_nulo, yycolumn,yyline, new String(yytext()));}
{r_var}                             {return new Symbol(Tabla_Simbolos_FS_CUP.r_var, yycolumn,yyline, new String(yytext()));}
{r_imprimir}                        {return new Symbol(Tabla_Simbolos_FS_CUP.r_imprimir, yycolumn,yyline, new String(yytext()));}
{r_importar}                        {return new Symbol(Tabla_Simbolos_FS_CUP.r_importar, yycolumn,yyline, new String(yytext()));}
{r_detener}                         {return new Symbol(Tabla_Simbolos_FS_CUP.r_detener, yycolumn,yyline, new String(yytext()));}
{r_retornar}                        {return new Symbol(Tabla_Simbolos_FS_CUP.r_retornar, yycolumn,yyline, new String(yytext()));}
{r_si}                              {return new Symbol(Tabla_Simbolos_FS_CUP.r_si, yycolumn,yyline, new String(yytext()));}
{r_sino}                            {return new Symbol(Tabla_Simbolos_FS_CUP.r_sino, yycolumn,yyline, new String(yytext()));}
{r_selecciona}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_selecciona, yycolumn,yyline, new String(yytext()));}
{r_caso}                            {return new Symbol(Tabla_Simbolos_FS_CUP.r_caso, yycolumn,yyline, new String(yytext()));}
{r_defecto}                         {return new Symbol(Tabla_Simbolos_FS_CUP.r_defecto, yycolumn,yyline, new String(yytext()));}
{r_funcion}                         {return new Symbol(Tabla_Simbolos_FS_CUP.r_funcion, yycolumn,yyline, new String(yytext()));}

//funciones nativas de arreglos
{r_descendente}                     {return new Symbol(Tabla_Simbolos_FS_CUP.r_descendente, yycolumn,yyline, new String(yytext()));}
{r_ascendente}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_ascendente, yycolumn,yyline, new String(yytext()));}
{r_invertir}                        {return new Symbol(Tabla_Simbolos_FS_CUP.r_invertir, yycolumn,yyline, new String(yytext()));}
{r_maximo}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_maximo, yycolumn,yyline, new String(yytext()));}
{r_minimo}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_minimo, yycolumn,yyline, new String(yytext()));}
{r_filtrar}                         {return new Symbol(Tabla_Simbolos_FS_CUP.r_filtrar, yycolumn,yyline, new String(yytext()));}
{r_buscar}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_buscar, yycolumn,yyline, new String(yytext()));}
{r_map}                             {return new Symbol(Tabla_Simbolos_FS_CUP.r_map, yycolumn,yyline, new String(yytext()));}
{r_reduce}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_reduce, yycolumn,yyline, new String(yytext()));}
{r_todos}                           {return new Symbol(Tabla_Simbolos_FS_CUP.r_todos, yycolumn,yyline, new String(yytext()));}
{r_alguno}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_alguno, yycolumn,yyline, new String(yytext()));}
{r_creararray}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_creararray, yycolumn,yyline, new String(yytext()));}

//funciones nativas de interfaz
{r_leergxml}                        {return new Symbol(Tabla_Simbolos_FS_CUP.r_leergxml, yycolumn,yyline, new String(yytext()));}
{r_obteneretiqueta}                 {return new Symbol(Tabla_Simbolos_FS_CUP.r_obteneretiqueta, yycolumn,yyline, new String(yytext()));}
{r_obtenerid}                       {return new Symbol(Tabla_Simbolos_FS_CUP.r_obtenerid, yycolumn,yyline, new String(yytext()));}
{r_obtenernombre}                   {return new Symbol(Tabla_Simbolos_FS_CUP.r_obtenernombre, yycolumn,yyline, new String(yytext()));}
{r_crearventana}                    {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearventana, yycolumn,yyline, new String(yytext()));}
{r_crearcontenedor}                 {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearcontenedor, yycolumn,yyline, new String(yytext()));}
{r_creartexto}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_creartexto, yycolumn,yyline, new String(yytext()));}
{r_crearcajatexto}                  {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearcajatexto, yycolumn,yyline, new String(yytext()));}
{r_crearareatexto}                  {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearareatexto, yycolumn,yyline, new String(yytext()));}
{r_crearcontrolnumerico}            {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearcontrolnumerico, yycolumn,yyline, new String(yytext()));}
{r_creardesplegable}                {return new Symbol(Tabla_Simbolos_FS_CUP.r_creardesplegable, yycolumn,yyline, new String(yytext()));}
{r_crearboton}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearboton, yycolumn,yyline, new String(yytext()));}
{r_crearimagen}                     {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearimagen, yycolumn,yyline, new String(yytext()));}
{r_crearreproductor}                {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearreproductor, yycolumn,yyline, new String(yytext()));}
{r_crearvideo}                      {return new Symbol(Tabla_Simbolos_FS_CUP.r_crearvideo, yycolumn,yyline, new String(yytext()));}

//eventos
{r_clic}                            {return new Symbol(Tabla_Simbolos_FS_CUP.r_clic, yycolumn,yyline, new String(yytext()));}
{r_cargar}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_cargar, yycolumn,yyline, new String(yytext()));}
{r_cerrar}                          {return new Symbol(Tabla_Simbolos_FS_CUP.r_cerrar, yycolumn,yyline, new String(yytext()));}

//expresiones regulares tipo datos
{booleano}                          {return new Symbol(Tabla_Simbolos_FS_CUP.booleano, yycolumn,yyline, new String(yytext()));}
{entero}                            {return new Symbol(Tabla_Simbolos_FS_CUP.entero, yycolumn,yyline, new String(yytext()));}
{decimal}                           {return new Symbol(Tabla_Simbolos_FS_CUP.decimal, yycolumn,yyline, new String(yytext()));}
{caracter}                          {return new Symbol(Tabla_Simbolos_FS_CUP.caracter, yycolumn,yyline, new String(yytext()));}
{cadena}                            {return new Symbol(Tabla_Simbolos_FS_CUP.cadena, yycolumn,yyline, new String(yytext()));}
{identificador}                     {return new Symbol(Tabla_Simbolos_FS_CUP.identificador, yycolumn,yyline, new String(yytext()));}


//tokens omitidos
[ \t\r\f\n]+            {/* Se ignoran */}  
{comentario_single}     {/* Se ignoran */}
{comentario_multi}      {/* Se ignoran */}

/* CUAQUIER OTRO */ 
.         		{_error( new String (yytext()),yycolumn,yyline);}