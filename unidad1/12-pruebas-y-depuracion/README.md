# U1_13 · Pruebas de escritorio y depuración

[Anterior](../../unidad1/11-arreglos-y-cadenas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/01-clases-y-objetos/README.md)

## Objetivo

Detectar errores lógicos con casos normales, de frontera e inválidos.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Una calculadora de multas puede compilar y estar equivocada. Convertiremos los casos de la primera lección en comprobaciones ejecutables. No usaremos librerías externas: el ejemplo lanza `AssertionError` si una comparación falla.

## Conceptos y decisiones

Un error de compilación impide producir un programa válido; un error de ejecución ocurre durante su funcionamiento; un error lógico produce un resultado incorrecto aunque el programa termine. Las pruebas deben relacionarse con requisitos, no limitarse a confirmar que no aparece una excepción.

| Días | Resultado requerido | Razón |
|---|---|---|
| 0 | 0 | Mínimo válido |
| 7 | 0 | Último día sin multa |
| 8 | 1000 | Primer día con multa |
| 10 | 3000 | Caso habitual |
| -1 | Rechazo | Entrada inválida |

Una prueba de escritorio registra valores intermedios antes de ejecutar. Para depurar en un IDE, coloca un breakpoint en el cálculo, ejecuta en modo depuración y observa `dias`, `dias - 7` y el resultado. Avanza instrucción por instrucción para contrastar con tu tabla. Un breakpoint no corrige por sí solo: debes explicar la causa.

Las comprobaciones siguientes están siempre activas; no dependen de habilitar las sentencias `assert` de Java. Cada fallo informa esperado y obtenido. Es una base para comprender después herramientas de pruebas como JUnit.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    static long calcularMulta(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Días negativos");
        }
        return Math.max(0, dias - 7) * 1000L;
    }

    static void comprobar(long esperado, long obtenido) {
        if (esperado != obtenido) {
            throw new AssertionError("Esperado " + esperado + ", obtenido " + obtenido);
        }
    }

    public static void main(String[] args) {
        comprobar(0, calcularMulta(0));
        comprobar(0, calcularMulta(7));
        comprobar(1000, calcularMulta(8));
        comprobar(3000, calcularMulta(10));
        boolean rechazado = false;
        try {
            calcularMulta(-1);
        } catch (IllegalArgumentException e) {
            rechazado = true;
        }
        if (!rechazado) {
            throw new AssertionError("Se aceptaron días negativos");
        }
        System.out.println("5 casos comprobados");
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/12-pruebas-y-depuracion
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
5 casos comprobados
```

## Seguir la ejecución

1. Cada llamada compara una salida con el requisito.
2. Una discrepancia lanza un error y detiene la ejecución.
3. El caso inválido debe lanzar una excepción específica.
4. El booleano registra si ocurrió el rechazo esperado.
5. El mensaje final solo se imprime cuando pasan todos los casos.

## Errores frecuentes y cómo interpretarlos

Modificar el esperado para que coincida con un resultado incorrecto oculta el defecto. Probar únicamente 10 no detecta todos los errores de frontera. Capturar cualquier excepción puede dar por correcto un fallo diferente del que querías comprobar.

## Práctica guiada

Cambia temporalmente la regla a `Math.max(0, dias - 8) * 1000L`. Ejecuta y localiza la primera prueba que falla. Corrige la regla y añade un caso con 20 días.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Falla el caso 8: se esperaban 1000 y se obtuvieron 0. El defecto está en el plazo, no en la prueba. Con la regla correcta, 20 días producen 13000. Guarda la corrección y la nueva prueba en un commit explicativo.

</details>

## Preguntas para explicar con tus palabras

¿Por qué una prueba exitosa no demuestra ausencia de todos los errores? ¿Qué aporta comprobar un rechazo explícito?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/11-arreglos-y-cadenas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/01-clases-y-objetos/README.md)
