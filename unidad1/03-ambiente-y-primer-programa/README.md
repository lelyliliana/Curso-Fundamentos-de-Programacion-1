# U1_03 · Ambiente de trabajo y primer programa

[Anterior](../../unidad1/02-paradigmas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/04-control-de-versiones/README.md)

## Objetivo

Distinguir JDK, compilador y JVM; ejecutar un archivo Java sin depender de un IDE.

Tema de la unidad: **Configuración del ambiente de trabajo**.

## Situación que resolveremos

Prepararemos un ambiente reproducible. El curso tiene como base Java 21 y usa características estables sin opciones experimentales. Puedes editar con VS Code, IntelliJ IDEA, Eclipse u otro editor; la terminal permite verificar si el problema pertenece al código o a la configuración.

## Conceptos y decisiones

El JDK contiene herramientas de desarrollo como `javac` y el ejecutor `java`. La JVM ejecuta bytecode; el archivo fuente `.java` se convierte en `.class` al compilar. Instalar únicamente un entorno de ejecución puede dejarte sin compilador. Usa un JDK para la arquitectura de tu equipo.

1. Sigue la [preparación del ambiente](../../docs/ambiente-y-herramientas.md) para tu sistema: Windows, Ubuntu o macOS. Instala un **JDK 21**, no solamente un entorno de ejecución.
2. Abre PowerShell en Windows o Terminal en Ubuntu/macOS. Ejecuta `java -version`.
3. Ejecuta `javac -version`. Ambas herramientas deben indicar la versión principal 21.
4. Si un comando no existe, revisa instalación y `PATH` siguiendo la guía de tu sistema.
5. Guarda el siguiente código como `Main.java` y ejecútalo desde su carpeta.

La primera ejecución de este curso usa `java Main.java`, que compila el archivo fuente en memoria y lo ejecuta. Para separar etapas, usa `javac -encoding UTF-8 -d out Main.java` y después `java -cp out Main`. Crea previamente `out` con `mkdir out` si fuera necesario. En Windows puedes usar PowerShell para los mismos comandos de Java. No escribas el símbolo del prompt como parte del comando.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hola, estoy aprendiendo Java");
        System.out.println("Mi programa se ejecuta desde la terminal");
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/03-ambiente-y-primer-programa
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Hola, estoy aprendiendo Java
Mi programa se ejecuta desde la terminal
```

## Seguir la ejecución

1. `public class Main` define la clase que contiene el programa.
2. `main` es el punto de entrada usado en estos ejemplos.
3. Cada `println` imprime un mensaje y termina la línea.
4. Las llaves delimitan el cuerpo del método y de la clase.
5. La JVM termina cuando `main` finaliza.

## Errores frecuentes y cómo interpretarlos

El archivo `main.java` puede fallar donde se distingue entre mayúsculas y minúsculas. `java Main.class` no es la forma de ejecutar bytecode: usa `java Main` con el classpath adecuado. Ejecutar desde otra carpeta produce un error de archivo inexistente.

## Práctica guiada

Agrega una tercera línea con una meta personal. Luego elimina temporalmente un punto y coma y vuelve a ejecutar. Lee el mensaje completo y restaura el código.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La tercera salida coincide con el texto que agregaste. La omisión del punto y coma genera un error de compilación: el programa no llega a imprimir. El número de línea te orienta, pero conviene revisar también la línea anterior.

</details>

## Preguntas para explicar con tus palabras

¿Qué herramienta comprueba la sintaxis? ¿Qué diferencia hay entre ejecutar el fuente y ejecutar un `.class`?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/02-paradigmas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/04-control-de-versiones/README.md)
