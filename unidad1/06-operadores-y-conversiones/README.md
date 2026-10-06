# U1_06 · Operadores, conversiones y precisión

[Anterior](../../unidad1/05-estructura-tipos-y-variables/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/07-entrada-y-salida/README.md)

## Objetivo

Evaluar expresiones y reconocer división entera, precedencia y conversiones.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Tres mediciones suman 10 unidades. La media no es 3 exacto, pero una división entre enteros puede perder la fracción antes de guardarse. También compararemos un cálculo decimal aproximado con uno decimal exacto.

## Conceptos y decisiones

Los operadores aritméticos incluyen `+`, `-`, `*`, `/` y `%`. El residuo `%` sirve, por ejemplo, para reconocer pares: `numero % 2 == 0`. Multiplicación y división tienen precedencia sobre suma y resta; los paréntesis hacen explícita la agrupación.

Si ambos operandos de `/` son enteros, la división descarta la fracción. Guardar después ese resultado en `double` no la recupera. Convertir un operando antes de dividir cambia la operación a decimal: `(double) suma / cantidad`. Una conversión que reduce rango o precisión necesita una decisión consciente.

`==`, `!=`, `<`, `<=`, `>` y `>=` producen booleanos; `&&`, `||` y `!` combinan o niegan condiciones. `&&` y `||` evalúan con cortocircuito. `=` asigna y `==` compara.

`double` usa representación binaria y muchos decimales no son exactos. Para cantidades monetarias fraccionarias, `BigDecimal` construido desde texto evita introducir el error de una representación binaria previa. Al dividir con `BigDecimal`, una fracción no terminante necesita escala y modo de redondeo.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        int suma = 10;
        int cantidad = 3;
        System.out.println("División entera: " + suma / cantidad);
        System.out.println("Media decimal: " + (double) suma / cantidad);
        System.out.println("Residuo: " + suma % cantidad);
        System.out.println("Agrupación: " + (2 + 3) * 4);
        System.out.println("Aproximado: " + (0.1 + 0.2));
        BigDecimal exacto = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("Decimal exacto: " + exacto);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/06-operadores-y-conversiones
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
División entera: 3
Media decimal: 3.3333333333333335
Residuo: 1
Agrupación: 20
Aproximado: 0.30000000000000004
Decimal exacto: 0.3
```

## Seguir la ejecución

1. `10 / 3` produce 3 porque ambos operandos son enteros.
2. La conversión de `suma` se realiza antes de dividir y conserva la fracción aproximada.
3. `10 % 3` produce 1: queda una unidad después de tres grupos de tres.
4. Los paréntesis fuerzan sumar antes de multiplicar.
5. Los dos cálculos finales hacen visible la diferencia de representación.

## Errores frecuentes y cómo interpretarlos

Escribir `(double) (suma / cantidad)` convierte un resultado ya truncado. Crear `new BigDecimal(0.1)` incorpora la aproximación del `double`. Dividir por cero necesita una regla explícita; cambiar de tipo no resuelve el problema del requisito.

## Práctica guiada

Calcula la media de 17 unidades entre 4 mediciones. Compara `2 + 3 * 4` con `(2 + 3) * 4`. Calcula `new BigDecimal("10").divide(new BigDecimal("3"), 2, java.math.RoundingMode.HALF_UP)`.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La media decimal es 4.25 y la división entera produce 4. Las expresiones producen 14 y 20. La división decimal con escala 2 y ese redondeo produce 3.33; debes declarar esa política al usarla para dinero.

</details>

## Preguntas para explicar con tus palabras

¿En qué momento se pierde la fracción? ¿Por qué no conviene comprobar cualquier cálculo decimal con igualdad exacta?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/05-estructura-tipos-y-variables/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/07-entrada-y-salida/README.md)
