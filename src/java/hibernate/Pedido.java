package hibernate;
// Generated 17-mar-2015 10:55:46 by Hibernate Tools 4.3.1


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * Pedido generated by hbm2java
 */
public class Pedido  implements java.io.Serializable {


     private Integer id;
     private Date fecha;
     private String nombre;
     private String apellidos;
     private String direccion;
     private String telefono;
     private Integer estado;
     private Set lineapedidos = new HashSet(0);

    public Pedido() {
    }

	
    public Pedido(Date fecha) {
        this.fecha = fecha;
    }
    public Pedido(Date fecha, String nombre, String apellidos, String direccion, String telefono, Integer estado, Set lineapedidos) {
       this.fecha = fecha;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.direccion = direccion;
       this.telefono = telefono;
       this.estado = estado;
       this.lineapedidos = lineapedidos;
    }
    
    public Pedido(JSONObject object){
       this.nombre = object.getString("nombre");
       this.apellidos = object.getString("apellidos");
       this.direccion = object.getString("direccion");
       this.telefono = object.getString("telefono");
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public Set getLineapedidos() {
        return this.lineapedidos;
    }
    
    public void setLineapedidos(Set lineapedidos) {
        this.lineapedidos = lineapedidos;
    }




}


