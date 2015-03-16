package modelos;

import hibernate.Login;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloLogin {
    public static List<Login> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Login";
        Query q = session.createQuery(hql);
        List<Login> logins = q.list();
        session.getTransaction().commit();
        session.close();
        return logins;
    }
    
    public static Login get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Login l = (Login) session.get(Login.class, Integer.parseInt(id));

        session.getTransaction().commit();
        
        session.close();
        
        return l;
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Login l = (Login) session.load(Login.class, Integer.parseInt(id));
        session.delete(l);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Login l){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(l);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Login l){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(l);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
