package herencia;

public class EmailNotificacion extends Notificacion {
    private String direccionEmail;

    public EmailNotificacion(String direccionEmail, String mensaje) {
        super(mensaje);
        this.direccionEmail = direccionEmail;
    }

    @Override
    public void enviar() {
        System.out.println("Enviando email a: " + direccionEmail + " con mensaje: " + getMensaje());
    }
}
