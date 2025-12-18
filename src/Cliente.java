import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getByName("54.82.79.217");

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce un número: ");
            int numero = sc.nextInt();

            byte[] mensaje = String.valueOf(numero).getBytes();

            DatagramPacket paquete =
                    new DatagramPacket(
                            mensaje,
                            mensaje.length,
                            ipServidor,
                            12346);

            socket.send(paquete);

            byte[] buffer = new byte[4096];
            DatagramPacket respuesta =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(respuesta);

            String resultado = new String(
                    respuesta.getData(), 0, respuesta.getLength());

            System.out.println("Primos: " + resultado);

            socket.close();

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
