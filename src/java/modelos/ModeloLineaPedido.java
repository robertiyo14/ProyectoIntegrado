package modelos;

import hibernate.Lineapedido;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloLineaPedido {
    public static List<Lineapedido> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Lineapedido";
        Query q = session.createQuery(hql);
        List<Lineapedido> lineas = q.list();
        session.getTransaction().commit();
        session.close();
        return lineas;
    }
    
    public static Lineapedido get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Lineapedido lp = (Lineapedido) session.get(Lineapedido.class, Integer.parseInt(id));

        session.getTransaction().commit();
        
        session.close();
        
        return lp;
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Lineapedido lp = (Lineapedido) session.load(Lineapedido.class, Integer.parseInt(id));
        session.delete(lp);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Lineapedido lp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(lp);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Lineapedido lp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(lp);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
