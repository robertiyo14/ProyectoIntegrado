/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import hibernate.Categoria;
import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rober
 */
public class ModeloCategoria {
    public static List<Categoria> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Categoria";
        Query q = session.createQuery(hql);
        List<Categoria> categorias = q.list();
        session.getTransaction().commit();
        session.close();
        return categorias;
    }
    
    public static Categoria get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Categoria c = (Categoria) session.get(Categoria.class, Integer.parseInt(id));

        session.getTransaction().commit();
        
        session.close();
        
        return c;
    }
    
    public static Categoria getPorNombre(String nombre){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Categoria c = (Categoria) session.get(Categoria.class, nombre);

        session.getTransaction().commit();
        
        session.close();
        
        return c;
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Categoria c = (Categoria) session.load(Categoria.class, Integer.parseInt(id));
        session.delete(c);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Categoria c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(c);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Categoria c){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(c);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
