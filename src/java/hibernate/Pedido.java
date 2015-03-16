package hibernate;
// Generated 16-mar-2015 11:20:10 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pedido generated by hbm2java
 */
public class Pedido  implements java.io.Serializable {


     private Integer id;
     private Date fecha;
     private Set lineapedidos = new HashSet(0);

    public Pedido() {
    }

	
    public Pedido(Date fecha) {
        this.fecha = fecha;
    }
    public Pedido(Date fecha, Set lineapedidos) {
       this.fecha = fecha;
       this.lineapedidos = lineapedidos;
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
    public Set getLineapedidos() {
        return this.lineapedidos;
    }
    
    public void setLineapedidos(Set lineapedidos) {
        this.lineapedidos = lineapedidos;
    }




}

