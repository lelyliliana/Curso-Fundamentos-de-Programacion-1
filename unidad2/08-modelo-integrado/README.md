# U2_08 · Modelo integrado con reglas y verificación

[Anterior](../../unidad2/07-excepciones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/01-tipos-abstractos-de-datos/README.md)

## Objetivo

Combinar encapsulación, interfaz y pruebas en un caso completo de inscripción.

Tema de la unidad: **Excepciones**.

## Situación que resolveremos

El cierre de unidad reúne un taller con cupos, un servicio que inscribe y un notificador sustituible. Verificaremos tanto el estado del taller como las confirmaciones para evitar un mensaje exitoso cuando la inscripción fue rechazada.

## Conceptos y decisiones

Una solución orientada a objetos distribuye responsabilidades sin perder las reglas del problema. Taller administra cupos; Servicio coordina el flujo; Notificador atiende una capacidad externa al dominio. El constructor del servicio recibe sus colaboradores y verifica que existan.

La secuencia es validar nombre, intentar la inscripción y notificar solo si hubo éxito. Si el cupo se agotó, devuelve false y no envía confirmación. Si el nombre es inválido, lanza una excepción antes de consumir el cupo. Estos órdenes son parte del comportamiento, no solo preferencias de estilo.

El notificador de memoria permite verificar cuántas veces fue invocado. Es un doble de prueba sencillo, sin bibliotecas. La prueba comprueba: una aceptación con un cupo, una segunda solicitud rechazada, cupos restantes cero y una única confirmación. Un modelo válido debe mantener estado y efectos coherentes.

Este ejemplo no implementa transacciones. Si un notificador real fallara después de consumir un cupo, necesitarías decidir cómo recuperar o reintentar. El contrato del proyecto final se limita a un notificador de consola; no presupongas garantías de entrega que el código no proporciona.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Objects;

public class Main {
    static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }
    public static void main(String[] args) {
        Taller taller = new Taller(1);
        NotificadorMemoria canal = new NotificadorMemoria();
        Servicio servicio = new Servicio(taller, canal);
        comprobar(servicio.inscribir("Ana"), "Primera inscripción");
        comprobar(!servicio.inscribir("Luis"), "No debe sobreasignar");
        comprobar(taller.disponibles() == 0, "Cupos no negativos");
        comprobar(canal.envios() == 1, "Solo confirma aceptaciones");
        System.out.println("Inscritos: 1");
        System.out.println("Disponibles: " + taller.disponibles());
        System.out.println("Confirmaciones: " + canal.envios());
    }
}

class Taller {
    private int cupos;
    Taller(int cupos) {
        if (cupos < 0) throw new IllegalArgumentException("Cupos negativos");
        this.cupos = cupos;
    }
    boolean inscribir() {
        if (cupos == 0) return false;
        cupos--;
        return true;
    }
    int disponibles() { return cupos; }
}

interface Notificador { void enviar(String mensaje); }

class NotificadorMemoria implements Notificador {
    private int cantidad;
    @Override
    public void enviar(String mensaje) { cantidad++; }
    int envios() { return cantidad; }
}

class Servicio {
    private final Taller taller;
    private final Notificador canal;
    Servicio(Taller taller, Notificador canal) {
        this.taller = Objects.requireNonNull(taller);
        this.canal = Objects.requireNonNull(canal);
    }
    boolean inscribir(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        if (!taller.inscribir()) return false;
        canal.enviar("Inscripción de " + nombre);
        return true;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/08-modelo-integrado
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Inscritos: 1
Disponibles: 0
Confirmaciones: 1
```

## Seguir la ejecución

1. Se crea un taller de un cupo y un canal de prueba.
2. Ana consume el único cupo y genera una confirmación.
3. Luis recibe false y no se notifica.
4. Las comprobaciones verifican estado y número de efectos.
5. Solo después de pasar las verificaciones se imprime el resumen.

## Errores frecuentes y cómo interpretarlos

Notificar antes de validar crea confirmaciones falsas. Descontar cupos desde el servicio duplica la responsabilidad de Taller. Comprobar únicamente el booleano puede dejar sin detectar un cupo negativo o un envío extra.

## Práctica guiada

Añade una prueba de nombre vacío sobre un taller con un cupo. Comprueba que conserve ese cupo y que el notificador siga en cero después del rechazo.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Crea un escenario separado, captura IllegalArgumentException y comprueba disponibles igual a 1 y envíos igual a 0. La validación debe ocurrir antes de llamar a `taller.inscribir()`. No cambies el estado para adaptar la prueba.

</details>

## Preguntas para explicar con tus palabras

¿Qué objeto protege los cupos? ¿Qué garantiza este flujo si el canal real puede fallar?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad2/07-excepciones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/01-tipos-abstractos-de-datos/README.md)
