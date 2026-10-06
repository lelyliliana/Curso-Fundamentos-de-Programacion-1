# Ambiente y herramientas

[Inicio](../README.md) · [Lección de configuración](../unidad1/03-ambiente-y-primer-programa/README.md)

## Preparación

Instala un **JDK 21** compatible con tu sistema y arquitectura, usando un proveedor oficial. [Temurin](https://adoptium.net/temurin/releases/?version=21) ofrece instaladores y paquetes para distintos sistemas. En Ubuntu puedes instalar el paquete `openjdk-21-jdk` si está disponible en tus repositorios. Reinicia la terminal después de cambiar las variables de entorno.

Verifica primero el ejecutor:

```bash
java -version
```

Después el compilador:

```bash
javac -version
```

Ambos deben indicar la misma versión principal, 21. `JAVA_HOME` apunta al directorio del JDK cuando una herramienta lo requiere; `PATH` debe permitir localizar su carpeta bin. No establezcas CLASSPATH global para este curso ni copies rutas de otro equipo. Si tienes varios JDK, revisa cuál usa la terminal y cuál seleccionó el IDE.

## Dos maneras de ejecutar

Cada ejemplo es independiente y tiene Main.java. Desde su propia carpeta:

```bash
java Main.java
```

El lanzamiento del fuente compila en memoria y ejecuta. Para observar las etapas separadas, crea primero una carpeta out:

```bash
mkdir out
```

Compila el archivo (incluidas sus clases de apoyo):

```bash
javac -encoding UTF-8 -d out Main.java
```

Ejecuta el bytecode:

```bash
java -cp out Main
```

Si out ya existe, no hace falta volver a crearla. Después de editar el fuente debes recompilar para que la ejecución del bytecode refleje el cambio. `java Main.java` realiza esa preparación cada vez.

## Editor e IDE

Puedes usar [VS Code con soporte de Java](https://code.visualstudio.com/docs/languages/java), [IntelliJ IDEA](https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html), [Eclipse](https://www.eclipse.org/downloads/) u otro editor. Abre una carpeta de ejemplo por vez: varios Main en el mismo paquete producirían clases duplicadas. El editor debe guardar los archivos en UTF-8.

## Diagnóstico por síntoma

| Síntoma | Qué revisar primero |
|---|---|
| `java` no se reconoce | JDK instalado, terminal nueva y PATH |
| `javac` no existe | Que instalaste JDK completo, no solo un runtime |
| Archivo no encontrado | Carpeta actual y nombre Main.java |
| Clase duplicada Main | Ejemplos distintos importados como un único módulo |
| Resultado antiguo | Fuente guardado y recompilación si ejecutas `.class` |
| Tildes alteradas | UTF-8 del fuente, archivo y consola |
| El programa espera | Está pidiendo datos: revisa la lección de entrada |
| Error después de una edición | Mensaje completo, línea indicada y línea anterior |

Empieza por reproducir en terminal. Si funciona allí y falla en el IDE, revisa JDK seleccionado, carpeta de trabajo y configuración de ejecución del IDE. Cambiar el algoritmo no corrige una herramienta mal configurada.
