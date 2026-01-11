package herencia;

public class Notificacion {
    private String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() { return mensaje; }

    public void enviar() {
        System.out.println("Enviando notificación genérica: " + mensaje);
    }
}


