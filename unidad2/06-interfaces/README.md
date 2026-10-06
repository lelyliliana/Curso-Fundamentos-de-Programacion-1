# U2_06 · Interfaces y dependencia de contratos

[Anterior](../../unidad2/05-clases-abstractas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/07-excepciones/README.md)

## Objetivo

Separar un servicio de la implementación concreta que ejecuta una capacidad.

Tema de la unidad: **Clases abstractas e interfaces**.

## Situación que resolveremos

El sistema de inscripción debe enviar una confirmación. Hoy la mostramos por consola; otro día podría cambiar el canal. El servicio dependerá de una interfaz para no quedar ligado a cómo se entrega el mensaje.

## Conceptos y decisiones

Una interfaz declara un contrato. `implements` indica que una clase lo cumple. Los métodos abstractos de una interfaz son públicos; su implementación no puede reducir esa visibilidad. Una clase puede implementar varias interfaces, aunque solo pueda extender una clase.

`ServicioInscripcion` recibe un Notificador por constructor. Esta forma de entregar una dependencia permite cambiarla sin editar la lógica del servicio. No se necesita un framework para usarla. Tampoco hay envío de correo real en este ejemplo: la implementación escribe en consola.

Una clase abstracta es útil cuando necesitas compartir estado y comportamiento en una familia. Una interfaz es útil para una capacidad que implementaciones diferentes deben ofrecer. Las interfaces también pueden incluir métodos `default` o estáticos, pero no los necesitamos para este contrato pequeño.

Elegir un contrato mínimo facilita probarlo: un notificador de prueba puede registrar el mensaje recibido. Evita interfaces gigantes con operaciones que la mayoría de implementaciones no pueden atender. La independencia solo es real si el servicio no crea internamente una implementación fija.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Notificador canal = new NotificadorConsola();
        ServicioInscripcion servicio = new ServicioInscripcion(canal);
        servicio.confirmar("Ana");
    }
}

interface Notificador {
    void enviar(String mensaje);
}

class NotificadorConsola implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Confirmación: " + mensaje);
    }
}

class ServicioInscripcion {
    private final Notificador notificador;
    ServicioInscripcion(Notificador notificador) {
        this.notificador = Objects.requireNonNull(notificador);
    }
    void confirmar(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        notificador.enviar("Inscripción de " + nombre);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/06-interfaces
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Confirmación: Inscripción de Ana
```

## Seguir la ejecución

1. `main` elige el canal concreto.
2. Entrega ese canal al servicio mediante el contrato Notificador.
3. El servicio valida el nombre y construye la confirmación.
4. `enviar()` invoca la implementación de consola.
5. El servicio conserva su lógica aunque se cambie el canal en `main`.

## Errores frecuentes y cómo interpretarlos

Implementar `enviar` sin `public` falla porque reduce visibilidad. Instanciar Notificador como si fuera una clase concreta no cumple la intención. Crear NotificadorConsola dentro del servicio vuelve a fijar la dependencia.

## Práctica guiada

Crea `NotificadorMemoria` que guarde el último mensaje en un atributo. Confirma una inscripción y comprueba el texto almacenado sin imprimir desde esa implementación.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La clase implementa enviar y asigna su parámetro a `ultimoMensaje`. Una consulta permite verificar `Inscripción de Ana`. Entrega la instancia al mismo servicio; no cambies su código. Este doble sencillo prepara las pruebas con dependencias sustituibles.

</details>

## Preguntas para explicar con tus palabras

¿Qué parte elige la implementación? ¿Cómo distinguirías una capacidad de una familia de objetos?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad2/05-clases-abstractas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/07-excepciones/README.md)
