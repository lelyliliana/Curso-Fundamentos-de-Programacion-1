# Taller de unidad 1 · Reserva de materiales

[Índice de unidad](README.md) · [Unidad siguiente](../unidad2/README.md)

## Problema

Un centro comunitario presta kits para actividades. Cada kit cuesta 12000 pesos por día de uso. La persona escribe su nombre, cantidad de kits y cantidad de días. La cantidad de kits debe estar entre 1 y 10; los días entre 1 y 7. Si el subtotal es mayor o igual a 100000, recibe un descuento del 10 %. Trabaja con pesos enteros y documenta el descarte de fracciones del descuento.

## Trabajo por etapas

1. Define entradas, rangos, salida y supuestos. Aclara que no se cobra una multa sino el uso contratado.
2. Escribe pseudocódigo. Separa recepción de datos, validación, cálculo y presentación.
3. Implementa métodos para subtotal y descuento. No copies la misma fórmula en varias ramas.
4. Recibe una línea por dato. En esta unidad puedes declarar que el formato debe ser entero; registra el fallo conocido ante texto no numérico. La unidad 2 permitirá resolverlo.
5. Muestra subtotal, descuento y total, con sus unidades.
6. Predice y ejecuta casos de frontera. Conserva un commit con el programa original y otro con una mejora explicada.

## Casos para comprobar

| Kits | Días | Subtotal | Descuento | Total |
|---|---|---|---|---|
| 1 | 1 | 12000 | 0 | 12000 |
| 2 | 4 | 96000 | 0 | 96000 |
| 3 | 3 | 108000 | 10800 | 97200 |
| 10 | 7 | 840000 | 84000 | 756000 |
| 0 | 1 | Rechazo | No calcular | No calcular |
| 1 | 8 | Rechazo | No calcular | No calcular |

Para comprobar directamente el umbral del método de descuento, prueba subtotales 99999, 100000 y 100001. No todos los subtotales se producen con las combinaciones de kits y días, pero el método debe conservar su contrato.

## Entrega y criterios

Conserva un README con el problema, instrucciones, tabla de casos y resultados. Añade el código y una explicación breve de una decisión. Si usas video, muestra una ejecución válida y una inválida en 3 a 5 minutos. Comparte enlaces.

| Criterio | Peso | Evidencia de logro |
|---|---|---|
| Análisis | 25 % | Entradas, rangos y regla de descuento explícitos |
| Implementación | 30 % | Métodos coherentes y cálculo correcto |
| Verificación | 30 % | Fronteras y rechazos contrastados con resultados |
| Documentación | 15 % | Ejecución reproducible y decisiones explicadas |

## Orientación de solución

El subtotal usa `kits * dias * 12000L`. Después de validar rangos, el descuento es `subtotal >= 100000 ? subtotal * 10 / 100 : 0`; el total es subtotal menos descuento. Usa long para evitar que el cálculo dependa del rango de int. Para estos límites no hay desbordamiento de long. El umbral directo produce descuentos 0, 10000 y 10000 (con descarte de fracción para el último).

## Autoevaluación

¿Puedes justificar el tipo del dinero? ¿Tu solución rechaza datos antes de calcular? ¿Las pruebas distinguen `>` de `>=`? ¿Tus métodos devuelven valores que puedes comprobar? ¿El historial explica una modificación real?
