# U1_11 · Recursión y condición de terminación

[Anterior](../../unidad1/10-metodos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/11-arreglos-y-cadenas/README.md)

## Objetivo

Reconocer un caso base y un problema que disminuye en cada llamada.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Calcularemos el factorial de un número pequeño mediante dos versiones: una recursiva y otra iterativa. La comparación sirve para estudiar las llamadas; no implica que la versión recursiva sea siempre la mejor elección.

## Conceptos y decisiones

Una función recursiva se llama a sí misma. Necesita un caso base que devuelva un resultado sin otra llamada y un paso que acerque el problema a ese caso. Para factorial, 0! = 1 y n! = n × (n - 1)! cuando n es positivo.

Con 4, las llamadas bajan a 3, 2, 1 y 0. Al volver, los resultados se multiplican en orden inverso. Cada llamada conserva su propio parámetro y espacio en la pila de ejecución. Esa pila no es la colección ArrayDeque que programarás después: la administra la JVM para las llamadas.

La versión recursiva realiza O(n) operaciones y ocupa O(n) marcos de llamada. La iterativa también realiza O(n) multiplicaciones, pero usa espacio adicional constante. Una recursión demasiado profunda puede producir StackOverflowError; Java no garantiza eliminar las llamadas de cola.

Limitamos el rango a 0..20 porque 20! cabe en long y 21! no. Un algoritmo puede tener su fórmula correcta y aun así desbordar su representación numérica. Para números mayores necesitarías BigInteger y revisar el costo. El rango declarado permite verificar el contrato sin resultados silenciosamente incorrectos.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    static void validar(int n) {
        if (n < 0 || n > 20) throw new IllegalArgumentException("Use 0..20");
    }
    static long factorialRecursivo(int n) {
        validar(n);
        if (n == 0) return 1;
        return n * factorialRecursivo(n - 1);
    }
    static long factorialIterativo(int n) {
        validar(n);
        long resultado = 1;
        for (int i = 2; i <= n; i++) resultado *= i;
        return resultado;
    }
    public static void main(String[] args) {
        System.out.println("Recursivo: " + factorialRecursivo(4));
        System.out.println("Iterativo: " + factorialIterativo(4));
        System.out.println("Caso base: " + factorialRecursivo(0));
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/10b-recursion
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Recursivo: 24
Iterativo: 24
Caso base: 1
```

## Seguir la ejecución

1. 4 llama a 3; 3 a 2; 2 a 1; 1 a 0.
2. La llamada con 0 devuelve 1 sin volver a llamar.
3. Al retornar, 1 × 1 produce 1 y 2 × 1 produce 2.
4. 3 × 2 produce 6 y 4 × 6 produce 24.
5. La versión iterativa llega al mismo resultado con un acumulador.

## Errores frecuentes y cómo interpretarlos

Omitir el caso base impide terminar. Llamar otra vez con n en lugar de n - 1 no reduce el problema. Probar solo 4 no verifica el caso 0 ni el límite de representación.

## Práctica guiada

Compara ambas versiones para 0, 1, 5 y 20. Comprueba que -1 y 21 se rechacen. Dibuja en una tabla las llamadas y retornos para 3.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Los resultados son 1, 1, 120 y 2432902008176640000. Los dos valores fuera del rango lanzan IllegalArgumentException. Para 3: llamadas 3, 2, 1, 0 y retornos 1, 1, 2, 6.

</details>

## Preguntas para explicar con tus palabras

¿Qué garantiza que la llamada se acerque al caso base? ¿Por qué conocer el tipo numérico forma parte del algoritmo?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/10-metodos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/11-arreglos-y-cadenas/README.md)
