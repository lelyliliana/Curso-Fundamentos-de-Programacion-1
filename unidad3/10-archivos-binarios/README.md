# U3_11 · Archivos binarios y cierre de recursos

[Anterior](../../unidad3/09-formato-y-validacion-de-archivos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/11-proyecto-integrador/README.md)

## Objetivo

Escribir y leer valores tipados respetando el mismo orden y formato.

Tema de la unidad: **Manipulación de archivos**.

## Situación que resolveremos

Guardaremos el código de un taller y su número de cupos en un archivo binario. No será legible como texto plano, pero tendrá una estructura definida. El lector debe conocerla y verificar una marca de versión.

## Conceptos y decisiones

Un archivo binario contiene bytes que la aplicación interpreta según un formato. Texto también se almacena en bytes, pero añade una codificación de caracteres. La diferencia útil es cómo acordamos interpretar el contenido, no que el texto esté «fuera de los bytes».

DataOutputStream escribe tipos primitivos y cadenas con operaciones específicas; DataInputStream lee sus equivalentes. El orden es parte del contrato: una marca int, un código writeUTF y cupos int. `writeUTF` usa UTF modificado de Java, no un archivo de texto UTF-8 ni una cadena de longitud ilimitada.

`try-with-resources` cierra el recurso al salir, incluso cuando ocurre una excepción. Los decoradores se construyen sobre Files.newOutputStream y Files.newInputStream; cerrar el decorador cierra el flujo que envuelve. Separamos escritura y lectura en dos bloques para completar el archivo antes de abrirlo para leer.

Este ejemplo no serializa objetos con ObjectInputStream. Para persistencia educativa preferimos un formato explícito y validable, que no dependa de la estructura interna de una clase. La serialización nativa tiene consideraciones de compatibilidad y confianza que deben evaluarse antes de usarla. El acceso aleatorio es otra decisión: un formato binario puede leerse secuencialmente, como hacemos aquí.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path archivo = Path.of("salida", "taller.bin");
        Files.createDirectories(archivo.getParent());
        try (DataOutputStream salida = new DataOutputStream(Files.newOutputStream(archivo))) {
            salida.writeInt(1);
            salida.writeUTF("T01");
            salida.writeInt(3);
        }
        try (DataInputStream entrada = new DataInputStream(Files.newInputStream(archivo))) {
            int version = entrada.readInt();
            if (version != 1) throw new IOException("Versión no admitida");
            String codigo = entrada.readUTF();
            int cupos = entrada.readInt();
            if (cupos < 0) throw new IOException("Cupos negativos");
            System.out.println("Código: " + codigo);
            System.out.println("Cupos: " + cupos);
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/10-archivos-binarios
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Código: T01
Cupos: 3
```

## Seguir la ejecución

1. Se abre el archivo de salida y se sobrescribe para la demostración.
2. Se escriben versión, código y cupos en ese orden.
3. El primer bloque termina y cierra la salida.
4. La lectura verifica versión y consume los mismos tipos en el mismo orden.
5. La segunda salida se produce solo después de validar los cupos.

## Errores frecuentes y cómo interpretarlos

Leer con un orden diferente interpreta bytes como otros tipos y puede generar valores inválidos o excepciones. Abrir el binario con readAllLines no recupera este formato. Olvidar el cierre puede dejar recursos abiertos o datos incompletos.

## Práctica guiada

Agrega después de cupos un precio con writeLong y recupéralo con readLong. Cambia la versión a 2 tanto al escribir como al validar. Explica cómo atenderías archivos antiguos.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

El precio se añade al final y el lector de versión 2 lo consume después de cupos. Para archivos antiguos necesitas una rama por versión o una migración explícita; no debes leer un campo nuevo que el archivo de versión 1 nunca escribió.

</details>

## Preguntas para explicar con tus palabras

¿Por qué el orden es parte del contrato? ¿Puede un archivo binario procesarse secuencialmente?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad3/09-formato-y-validacion-de-archivos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/11-proyecto-integrador/README.md)
