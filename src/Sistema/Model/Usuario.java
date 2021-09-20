package Sistema.Model;
  
public class Usuario {
    private String codigo;
    private String clave;
    private String nombres;
    private String apellidos;
    private Perfil perfilUsuario;
    private String correo;
    private String estado;
    private String claveAnterior1;
    private String claveAnterior2;
    private String fechaCambioClave;
    private String sesionBloqueada;
    private int cantidadIntento;
    private String cambiarClave;

    public Usuario() {
    }

    public Usuario(String codigo) {
        this.codigo = codigo;
    }

    public Usuario(String codigo, String clave, String nombres, String apellidos, Perfil perfilUsuario, String correo, String estado) {
        this.codigo = codigo;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.perfilUsuario = perfilUsuario;
        this.correo = correo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClaveAnterior1() {
        return claveAnterior1;
    }

    public void setClaveAnterior1(String claveAnterior1) {
        this.claveAnterior1 = claveAnterior1;
    }

    public String getClaveAnterior2() {
        return claveAnterior2;
    }

    public void setClaveAnterior2(String claveAnterior2) {
        this.claveAnterior2 = claveAnterior2;
    }

    public String getFechaCambioClave() {
        return fechaCambioClave;
    }

    public void setFechaCambioClave(String fechaCambioClave) {
        this.fechaCambioClave = fechaCambioClave;
    }

    public String getSesionBloqueada() {
        return sesionBloqueada;
    }

    public void setSesionBloqueada(String sesionBloqueada) {
        this.sesionBloqueada = sesionBloqueada;
    }

    public int getCantidadIntento() {
        return cantidadIntento;
    }

    public void setCantidadIntento(int cantidadIntento) {
        this.cantidadIntento = cantidadIntento;
    }

    public String getCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(String cambiarClave) {
        this.cambiarClave = cambiarClave;
    }

   

    
}
