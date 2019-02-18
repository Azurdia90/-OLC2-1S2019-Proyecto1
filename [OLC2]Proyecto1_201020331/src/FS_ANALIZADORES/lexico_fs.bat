SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_202\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET JFLEX_HOME= C:\00.Analizadores\jflex-1.6.1
cd "C:\Users\Cristian Azurdia\OneDrive\Documentos\3. GitHub\-OLC2-1S2019-Proyecto1\[OLC2]Proyecto1_201020331\src\FS_ANALIZADORES"
java -jar %JFLEX_HOME%\lib\jflex-1.6.1.jar lexico_fs.jflex 
pause