# U3_03 · Pilas y colas con ArrayDeque

[Anterior](../../unidad3/01b-matrices/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/03-genericos/README.md)

## Objetivo

Elegir LIFO o FIFO según el orden requerido por el problema.

Tema de la unidad: **Estructuras de datos abstractas (TAD)**.

## Situación que resolveremos

Deshacer ediciones exige recuperar la última acción primero. Atender turnos exige recuperar la primera solicitud primero. Aunque ambas tareas almacenan elementos, sus contratos de orden son diferentes.

## Conceptos y decisiones

Una pila sigue LIFO (último en entrar, primero en salir). Una cola sigue FIFO (primero en entrar, primero en salir). `Deque` permite operar por ambos extremos y ArrayDeque proporciona una implementación útil para estas tareas en un solo hilo.

| Necesidad | Insertar | Consultar | Extraer |
|---|---|---|---|
| Pila | `push` | `peek` | `pop` |
| Cola | `offer` | `peek` | `poll` |

`peek` no elimina. `poll` devuelve null cuando la cola está vacía; `pop` lanza una excepción si la pila está vacía. ArrayDeque no admite null, por lo que ese resultado de poll no se confunde con un elemento válido. Para pila puedes comprobar `isEmpty()` antes de pop.

Declarar la variable con una interfaz (`Deque` o `Queue`) comunica las operaciones necesarias. No se recomienda elegir la clase histórica Stack para este nuevo ejemplo. Las operaciones habituales de los extremos en ArrayDeque tienen costo amortizado O(1); buscar un elemento específico puede requerir recorrer la estructura. ArrayDeque no ofrece seguridad automática para acceso concurrente.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Deque<String> historial = new ArrayDeque<>();
        historial.push("Escribir título");
        historial.push("Cambiar color");
        System.out.println("Deshacer: " + historial.pop());
        System.out.println("Pendiente: " + historial.peek());
        Queue<String> turnos = new ArrayDeque<>();
        turnos.offer("Ana");
        turnos.offer("Luis");
        System.out.println("Atender: " + turnos.poll());
        System.out.println("Atender: " + turnos.poll());
        System.out.println("Vacía: " + turnos.poll());
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/02-pilas-y-colas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Deshacer: Cambiar color
Pendiente: Escribir título
Atender: Ana
Atender: Luis
Vacía: null
```

## Seguir la ejecución

1. La segunda acción queda en la cima de la pila.
2. `pop()` la retira y devuelve.
3. `peek()` consulta la acción restante sin quitarla.
4. La cola entrega Ana antes que Luis por orden de llegada.
5. Una tercera extracción de la cola vacía devuelve null.

## Errores frecuentes y cómo interpretarlos

Usar push para agregar turnos y pop para atenderlos cambia FIFO a LIFO. Confundir peek con extracción deja el mismo elemento pendiente. Agregar null a ArrayDeque produce una excepción, no un turno vacío.

## Práctica guiada

Agrega tres acciones y tres personas. Predice el orden de extracción. Crea un ciclo que atienda mientras la cola no esté vacía y verifica el tamaño final.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La pila entrega las acciones en orden inverso a su inserción. La cola conserva el orden de llegada. `while (!turnos.isEmpty())` seguido de poll consume todos los turnos y termina con tamaño 0.

</details>

## Preguntas para explicar con tus palabras

¿Qué problema real necesita FIFO? ¿Qué diferencia práctica hay entre poll y pop al faltar elementos?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/01b-matrices/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/03-genericos/README.md)
