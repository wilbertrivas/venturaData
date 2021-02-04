/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Correos;

import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
    private String tipoConexion;
    public mail(String tipoConexion) {
        this.tipoConexion=tipoConexion;
    }
    
   public  void RegistroSolicitudEquipos (Usuario userRemitente, SolicitudEquipo solicitudEquipo) throws SQLException {
        //Añadimos los destinatarios al correo, Nota: Se agregan los que tienen permisos para asignar equipos en el sistema.
        ArrayList<Usuario> listadoUsuarioAsignador=new ControlDB_Usuario(tipoConexion).buscarUsuario_Permiso_AsignarEquipos();
        
        if(listadoUsuarioAsignador != null){
            String remitente = "venturadatavg";  //Para la dirección nomcuenta@gmail.com
            String clave = "VG#V3ntur4D4t4!#";  //Para la dirección nomcuenta@gmail.com

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", clave);    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587");//El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(remitente));
                for(Usuario user : listadoUsuarioAsignador){
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getCorreo()));   //Se podrían añadir varios de la misma manera
                }
                String EquipoSOlicitados="LISTADO DE EQUIPOS\n";
                int contador =1;
                for(SolicitudListadoEquipo list : solicitudEquipo.getListadoSolicitudesEquipos()){
                    EquipoSOlicitados += "\n ITEM # "+contador;
                    EquipoSOlicitados += "\n TIPO EQUIPO: "+list.getTipoEquipo().getDescripcion();
                    EquipoSOlicitados += "\n MARCA: "+list.getMarcaEquipo();
                    EquipoSOlicitados += "\n MODELO: "+list.getModeloEquipo();
                    EquipoSOlicitados += "\n CANTIDAD: "+list.getCantidad();
                    EquipoSOlicitados += "\n ACTIVIDAD A REALIZAR: "+list.getLaborRealizada().getDescripcion();
                    EquipoSOlicitados += " \n";
                    contador++;
                }
                
                
                message.setSubject("Solicitud de Equipos");
                String cuerpo="Se ha registrado una nueva solicitud de equipo en el sistema,\n"
                        + "\nCENTRO OPERACIÓN: "+solicitudEquipo.getCentroOperacion().getDescripcion()
                        + "\nESTADO SOLICITUD: "+solicitudEquipo.getEstadoSolicitudEquipo().getDescripcion()
                        + "\nUSUARIO: "+userRemitente.getNombres()+" "+userRemitente.getApellidos()+"\n\n"+EquipoSOlicitados;
                message.setText(cuerpo);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", remitente, clave);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
            catch (MessagingException me) {
                me.printStackTrace();   //Si se produce un error
            }
        }   
     }
   public  void AsignacionSolicitudEquipos (Usuario usuarioOrigen, Usuario UsuarioDestino, ArrayList<AsignacionEquipo> listadoAsignacionEquipo, String estadoSolicitud) throws SQLException {
        //Añadimos los destinatarios al correo, Nota: Se agregan los que tienen permisos para asignar equipos en el sistema.
       
            String remitente = "venturadatavg";  //Para la dirección nomcuenta@gmail.com
            String clave = "VG#V3ntur4D4t4!#";  //Para la dirección nomcuenta@gmail.com

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", clave);    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587");//El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            if(estadoSolicitud.equals("APROBADA") || estadoSolicitud.equals("APROBADA CON MODIFICACIÓN")){
                try {
                    message.setFrom(new InternetAddress(remitente));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(UsuarioDestino.getCorreo()));   //Se podrían añadir varios de la misma manera

                    String EquipoAsignados="LISTADO DE EQUIPOS ASIGNADOS\n";
                    int contador =1;
                    for(AsignacionEquipo list : listadoAsignacionEquipo){
                        EquipoAsignados += "\n ITEM # "+contador+" ";
                        EquipoAsignados += "\n TIPO EQUIPO: "+list.getEquipo().getTipoEquipo().getDescripcion()+" ";
                        EquipoAsignados += "\n MARCA: "+list.getEquipo().getMarca()+" ";
                        EquipoAsignados += "\n DESCRIPCIÓN: "+list.getEquipo().getDescripcion()+ " "+list.getEquipo().getModelo()+" ";
                        EquipoAsignados += "\n ACTIVIDAD A REALIZAR: "+list.getSolicitudListadoEquipo().getLaborRealizada().getDescripcion()+" ";
                        EquipoAsignados += " \n";
                        contador++;
                    }


                    message.setSubject("Asignación de equipos "+estadoSolicitud);
                    String cuerpo="Se le informá que el usuario "+usuarioOrigen.getNombres()+" "+usuarioOrigen.getApellidos()+
                            " ha revisado su solicitud de equipo y pasó a estado de "+ estadoSolicitud+", en el cual se le fueron asignado los siguientes equipos:\n"
                            + EquipoAsignados;
                    message.setText(cuerpo);
                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }
                catch (MessagingException me) {
                    me.printStackTrace();   //Si se produce un error
                }
            }else{
                //La solicitud fue cancelada
                try {
                    message.setFrom(new InternetAddress(remitente));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(UsuarioDestino.getCorreo()));   //Se podrían añadir varios de la misma manera
                    message.setSubject("Asignación de equipos CANCELADA");
                    String cuerpo="Se le informá que el usuario "+usuarioOrigen.getNombres()+" "+usuarioOrigen.getApellidos()+
                            " ha revisado su solicitud de equipo y pasó a estado de "+ estadoSolicitud+", por tal motivo no presenta asignación de equipos.\n";
                    message.setText(cuerpo);
                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }
                catch (MessagingException me) {
                    me.printStackTrace();   //Si se produce un error
                }
            
            }
            
        
     }
   public  void ConfirmaciónAsignacionEquipos (Usuario usuarioOrigen,SolicitudEquipo solicitudEquipo, ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos) throws SQLException {
        //Añadimos los destinatarios al correo, Nota: Se agregan los que tienen permisos para asignar equipos en el sistema.
       ArrayList<Usuario> listadoUsuarioAsignador=new ControlDB_Usuario(tipoConexion).buscarUsuario_Permiso_AsignarEquipos();
        
        if(listadoUsuarioAsignador != null){
            String remitente = "venturadatavg";  //Para la dirección nomcuenta@gmail.com
            String clave = "VG#V3ntur4D4t4!#";  //Para la dirección nomcuenta@gmail.com

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", clave);    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587");//El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(remitente));
                 for(Usuario user : listadoUsuarioAsignador){
                    if(user.getCorreo() !=null){
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getCorreo()));   //Se podrían añadir varios de la misma manera
                    }
                }
                int contador =1;
                String estadoF="";    
                if(confirmacionSolicitudEquipos.getDescripcion().equals("ACEPTAR")){
                    estadoF="aceptada";
                }else{
                    if(confirmacionSolicitudEquipos.getDescripcion().equals("RECHAZAR")){
                        estadoF="Rechazada";
                    }
                }
                
                message.setSubject("Confirmación de asignación de equipos para la solicitud # "+solicitudEquipo.getCodigo());
                String cuerpo="Se le informá que el usuario "+usuarioOrigen.getNombres()+" "+usuarioOrigen.getApellidos()+
                        " revisó su asignación y esta fue "+ estadoF;
                message.setText(cuerpo);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", remitente, clave);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }
            catch (MessagingException me) {
                me.printStackTrace();   //Si se produce un error
            }
        }
            
        
     }
}


