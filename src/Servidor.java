import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12346);
            System.out.println("Servidor UDP esperando datagramas...");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(
                        paquete.getData(), 0, paquete.getLength());

                int numero = Integer.parseInt(mensaje);

                String resultado = calcularPrimos(numero);

                byte[] respuesta = resultado.getBytes();

                DatagramPacket paqueteRespuesta =
                        new DatagramPacket(
                                respuesta,
                                respuesta.length,
                                paquete.getAddress(),
                                paquete.getPort());

                socket.send(paqueteRespuesta);
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor" + e.getMessage());
        }
    }

    private static String calcularPrimos(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= n; i++) {
            boolean esPrimo = true;

            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    esPrimo = false;
                    break;
                }
            }

            if (esPrimo) {
                sb.append(i).append(",");
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
