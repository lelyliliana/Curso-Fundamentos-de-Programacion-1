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
