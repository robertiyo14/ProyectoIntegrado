package modelos;

import hibernate.HibernateUtil;
import hibernate.Producto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class ModeloProducto {
    public static List<Producto> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Producto";
        Query q = session.createQuery(hql);
        List<Producto> productos = q.list();
        session.getTransaction().commit();
        session.close();
        return productos;
    }
    
    public static Producto get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Producto p = (Producto) session.get(Producto.class, Integer.parseInt(id));

        session.getTransaction().commit();
        
        session.close();
        
        return p;
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Producto p = (Producto) session.load(Producto.class, Integer.parseInt(id));
        session.delete(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Producto p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Producto p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(p);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
