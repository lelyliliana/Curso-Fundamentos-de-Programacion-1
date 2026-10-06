# U1_09 · Repeticiones, contadores y acumuladores

[Anterior](../../unidad1/08-condicionales/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/10-metodos/README.md)

## Objetivo

Elegir for, while y do-while según la condición de repetición.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Sumaremos los números del 1 al 4, verificaremos tres turnos y simularemos un intento inicial obligatorio. Los ejemplos son pequeños para seguir los cambios de estado sin depender de entrada de teclado.

## Conceptos y decisiones

Un ciclo repite instrucciones mientras se cumple una condición. `for` reúne inicialización, condición y actualización; resulta adecuado cuando conoces el recorrido. `while` comprueba antes de entrar y puede ejecutar cero veces. `do-while` comprueba después y ejecuta al menos una vez.

Un contador registra cuántas veces ocurre algo; un acumulador reúne cantidades. No son intercambiables: contar cuatro mediciones no equivale a sumar sus valores. Antes de programar, declara el valor inicial, la condición de entrada, qué cambia y cuándo termina.

En la suma, después de cada iteración `total` contiene la suma de los números visitados. Esa propiedad ayuda a revisar el algoritmo. La condición `numero <= 4` incluye 4. Un `while` necesita actualizar el estado que controla su condición; olvidarlo puede generar un ciclo infinito. `break` sale del ciclo y `continue` pasa a la siguiente iteración, pero ninguno reemplaza una condición bien diseñada.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        int total = 0;
        for (int numero = 1; numero <= 4; numero++) {
            total += numero;
        }
        System.out.println("Suma: " + total);
        int turno = 1;
        while (turno <= 3) {
            System.out.println("Turno: " + turno);
            turno++;
        }
        int intentos = 0;
        do {
            intentos++;
        } while (intentos < 1);
        System.out.println("Intentos: " + intentos);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/09-ciclos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Suma: 10
Turno: 1
Turno: 2
Turno: 3
Intentos: 1
```

## Seguir la ejecución

1. `total` comienza en 0.
2. El `for` lo cambia a 1, 3, 6 y 10.
3. Al llegar a 5, la condición del `for` es falsa.
4. El `while` imprime 1, 2 y 3; luego `turno` vale 4.
5. El `do-while` incrementa antes de comprobar y termina con un intento.

## Errores frecuentes y cómo interpretarlos

Inicializar el acumulador dentro del ciclo borra la suma previa. Usar `< 4` deja fuera el último número. Un incremento ubicado solamente en una rama puede impedir terminar el ciclo.

## Práctica guiada

Suma del 1 al 10 y cuenta cuántos de esos números son pares. Usa dos variables distintas. Después modifica el inicio del while a 4 y compara con un do-while equivalente.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La suma es 55 y hay 5 pares. El contador aumenta solo cuando `numero % 2 == 0`. Con inicio 4 y condición `<= 3`, while no entra; do-while ejecuta una vez antes de comprobar.

</details>

## Preguntas para explicar con tus palabras

¿Qué conserva el acumulador al terminar cada iteración? ¿Cuál ciclo garantiza una ejecución inicial?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad1/08-condicionales/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/10-metodos/README.md)
