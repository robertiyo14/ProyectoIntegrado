package modelos;

import hibernate.Pedido;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloPedido {
    public static List<Pedido> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Pedido";
        Query q = session.createQuery(hql);
        List<Pedido> pedidos = q.list();
        session.getTransaction().commit();
        session.close();
        return pedidos;
    }
    
    public static Pedido get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Pedido p = (Pedido) session.get(Pedido.class, Integer.parseInt(id));

        session.getTransaction().commit();
        
        session.close();
        
        return p;
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Pedido p = (Pedido) session.load(Pedido.class, Integer.parseInt(id));
        session.delete(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Pedido p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Pedido p){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(p);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
