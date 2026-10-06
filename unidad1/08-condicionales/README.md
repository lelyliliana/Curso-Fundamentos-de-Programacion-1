# U1_08 · Decisiones con if y switch

[Anterior](../../unidad1/07-entrada-y-salida/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/09-ciclos/README.md)

## Objetivo

Expresar decisiones excluyentes y probar los límites de una regla.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Un taller admite asistentes desde los 14 años. Si cumplen la edad, paga una tarifa diferente quien tiene beca. Otro dato representa el tipo de sesión. Son decisiones distintas y conviene no mezclarlas en una condición gigantesca.

## Conceptos y decisiones

`if` ejecuta un bloque cuando una condición es verdadera. `else` representa el caso contrario; una cadena `if / else if / else` selecciona un camino. Varios `if` independientes pueden ejecutar varios bloques en una misma evaluación. La diferencia afecta reglas excluyentes.

Una expresión `switch` permite obtener un valor según una opción discreta. Usamos las flechas de Java moderno para evitar caídas accidentales entre casos. `default` atiende opciones no previstas. No uses `switch` para reemplazar arbitrariamente comparaciones de rangos donde `if` sería más claro.

Antes de escribir código, enumera casos: edad 13, edad 14 con beca, edad 14 sin beca y edad negativa. Un valor negativo merece una validación previa; no debería informarse como si fuera solamente una edad insuficiente. El ejemplo separa validación, admisión y descripción de la sesión.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        int edad = 14;
        boolean tieneBeca = true;
        String modalidad = "virtual";
        if (edad < 0) {
            System.out.println("Edad inválida");
            return;
        }
        if (edad < 14) {
            System.out.println("Aún no cumple la edad mínima");
        } else if (tieneBeca) {
            System.out.println("Admitido. Tarifa: 0");
        } else {
            System.out.println("Admitido. Tarifa: 15000");
        }
        String sesion = switch (modalidad) {
            case "virtual" -> "Sesión por videollamada";
            case "presencial" -> "Sesión en laboratorio";
            default -> "Modalidad desconocida";
        };
        System.out.println(sesion);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/08-condicionales
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Admitido. Tarifa: 0
Sesión por videollamada
```

## Seguir la ejecución

1. La edad no es negativa, por lo que la ejecución continúa.
2. `edad < 14` es falso cuando la edad es exactamente 14.
3. La beca es verdadera y selecciona la tarifa cero.
4. `switch` produce el texto asociado a `virtual`.
5. Las dos decisiones dejan dos mensajes independientes.

## Errores frecuentes y cómo interpretarlos

Cambiar `< 14` por `<= 14` excluye el caso de frontera permitido. Un punto y coma inmediatamente después de `if (...)` crea una instrucción vacía y puede romper la intención. Omitir la validación confunde datos inválidos con casos válidos rechazados.

## Práctica guiada

Agrega una modalidad `hibrida`. Prueba edades 13, 14 y -1, con y sin beca. Escribe una tabla de decisiones antes de ejecutar.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La modalidad híbrida puede producir `Sesión combinada`. A los 13 no se admite; a los 14 se admite con tarifa 0 o 15000 según beca; -1 detiene la ejecución con `Edad inválida`. La beca no modifica la edad mínima.

</details>

## Preguntas para explicar con tus palabras

¿Cuándo usarías dos `if` independientes? ¿Qué caso detecta un error entre `<` y `<=`?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad1/07-entrada-y-salida/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/09-ciclos/README.md)
