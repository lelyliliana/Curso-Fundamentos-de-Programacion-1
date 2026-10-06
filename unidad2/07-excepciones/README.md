# U2_07 · Excepciones y validación de entrada

[Anterior](../../unidad2/06-interfaces/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/08-modelo-integrado/README.md)

## Objetivo

Diferenciar errores de formato y de dominio y capturarlos donde pueden atenderse.

Tema de la unidad: **Excepciones**.

## Situación que resolveremos

La entrada de cupos no siempre será numérica. Probaremos tres valores: texto, un entero fuera del rango y un entero permitido. El método de conversión comunicará fallos específicos y el llamador decidirá cómo continuar.

## Conceptos y decisiones

Una excepción interrumpe el camino normal y se propaga hasta un manejador compatible. `throw` lanza una excepción; `try / catch` atiende fallos. `throws` declara excepciones que un método puede propagar. Las excepciones comprobadas, como IOException, deben capturarse o declararse; las que derivan de RuntimeException no exigen esa declaración.

`NumberFormatException` indica que el texto no puede convertirse al entero esperado. Es una subclase de IllegalArgumentException. Por eso su catch debe ir antes del catch más general; de lo contrario quedaría inalcanzable.

Un dato `0` tiene formato correcto, pero viola el rango de 1 a 30. Ambas situaciones necesitan mensajes distintos. Capturamos en `main`, que puede informar y seguir con otro caso. El método de conversión no devuelve 0 como señal de error, porque ese valor ocultaría la causa y podría confundirse con un dato real.

Evita `catch (Exception e)` como respuesta universal. Maneja fallos previstos en el lugar que tiene capacidad de decidir, y deja visibles los defectos de programación. Para recursos como archivos, usa `try-with-resources` cuando tú seas responsable de cerrarlos.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    static int convertirCupos(String texto) {
        int cupos = Integer.parseInt(texto.strip());
        if (cupos < 1 || cupos > 30) {
            throw new IllegalArgumentException("Debe estar entre 1 y 30");
        }
        return cupos;
    }
    public static void main(String[] args) {
        String[] entradas = {"dos", "0", "3"};
        for (String entrada : entradas) {
            try {
                System.out.println("Aceptado: " + convertirCupos(entrada));
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido: " + entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Rango inválido: " + e.getMessage());
            }
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/07-excepciones
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Formato inválido: dos
Rango inválido: Debe estar entre 1 y 30
Aceptado: 3
```

## Seguir la ejecución

1. `dos` falla durante la conversión y entra al primer catch.
2. `0` se convierte, pero el método lanza una excepción de dominio.
3. El segundo catch muestra la regla de rango.
4. El ciclo continúa con la siguiente entrada.
5. `3` cumple formato y rango, de modo que produce una aceptación.

## Errores frecuentes y cómo interpretarlos

Capturar IllegalArgumentException antes de NumberFormatException hace inalcanzable el caso específico. Ignorar la excepción con un catch vacío deja al usuario sin explicación. Mostrar siempre una traza técnica en una interfaz final dificulta entender qué corregir.

## Práctica guiada

Prueba `30`, `31`, ` 5 ` y `2147483648`. Después adapta la entrada de consola de U1_07 para volver a pedir la cantidad cuando sea inválida y terminar si la entrada se agota.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

30 y 5 se aceptan; 31 viola el rango. 2147483648 no cabe en int y produce NumberFormatException. Para reintentar, usa un ciclo y comprueba `hasNextLine()` antes de leer; captura solo los fallos previstos y sal cuando recibas una cantidad válida.

</details>

## Preguntas para explicar con tus palabras

¿Puede un número válido tener significado inválido? ¿Quién tiene información suficiente para decidir un reintento?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad2/06-interfaces/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/08-modelo-integrado/README.md)
