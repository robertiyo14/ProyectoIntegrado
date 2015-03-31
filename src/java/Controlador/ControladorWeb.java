/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import hibernate.Categoria;
import hibernate.Login;
import hibernate.Producto;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloCategoria;
import modelos.ModeloLogin;
import modelos.ModeloProducto;

@WebServlet(name = "Controlador", urlPatterns = {"/controlWeb"})
public class ControladorWeb extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = "index.html";
        boolean forward = false;
        String target, op, action;
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        
        System.out.println("target = "+target +"\n op = "+op+"\n action = "+action);
        
        if(target==null && op == null && action == null){
            forward = true;
            destino = "WEB-INF/index.jsp";
        }
        
        //Ir a página donde muestra todos los productos.
        else if (target.equals("producto")
                && op.equals("select")
                && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/productos.jsp";
            request.setAttribute("datos", ModeloProducto.get());
        }
        //Añadir stock a los productos
        else if(target.equals("producto")
                && op.equals("edit")
                && action.equals("op")){
            forward = false;
            destino = "controlWeb?target=producto&op=select&action=view";
            Producto p = new Producto();
            int stock = Integer.parseInt(request.getParameter("stock"))+Integer.parseInt(request.getParameter("stockNuevo"));
            p.setId(Integer.parseInt(request.getParameter("id")));
            p.setCategoria(ModeloCategoria.get(request.getParameter("categoria")));
            p.setTitulo(request.getParameter("titulo"));
            p.setDescripcion(request.getParameter("descripcion"));
            p.setPrecio(BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio"))));
            p.setStock(stock);
            p.setImagen(request.getParameter("imagen"));
            ModeloProducto.edit(p);
        }
        //Logear
        else if(target.equals("login")
                && op.equals("select")
                && action.equals("logear")){
            boolean logeado = false;
            String user = request.getParameter("user");
            String clave = request.getParameter("pass");
            List<Login> usuarios = ModeloLogin.get();
            for(Login l : usuarios){
                if(l.getUsuario().equals(user) && l.getClave().equals(clave)){
                    logeado = true;
                    break;
                }
            }
            if(logeado){
                destino = "WEB-INF/index.jsp";
            }else{
                destino = "index.html";
            }
            forward = true;
        }
        //Ver las categorías y el formulario de insertar.
        else if(target.equals("categoria")
                && op.equals("insert")
                && action.equals("view")){
            request.setAttribute("datos", ModeloCategoria.get());
            forward = true;
            destino = "WEB-INF/categorias.jsp";
        }
        //Añadir categoría nueva.
        else if(target.equals("categoria")
                && op.equals("insert")
                && action.equals("op")){
            String nombre = request.getParameter("nombre");
            Categoria c = new Categoria();
            c.setNombre(nombre);
            ModeloCategoria.insert(c);
            forward = false;
            destino = "controlWeb?target=categoria&op=insert&action=view";
        }
        //Ver el formulario de insertar productos.
        else if(target.equals("producto")
                && op.equals("insert")
                && action.equals("view")){
            request.setAttribute("datos", ModeloCategoria.get());
            forward = true;
            destino = "WEB-INF/addProductos.jsp";
        }
        //Insertar productos
        else if(target.equals("producto")
                && op.equals("insert")
                && action.equals("op")){
            forward = false;
            destino = "controlWeb?target=producto&op=select&action=view";
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio")));
            int stock = Integer.parseInt(request.getParameter("stock"));
            Categoria c = ModeloCategoria.get().get(0);
            Producto p = new Producto();
            p.setTitulo(titulo);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
            p.setCategoria(c);
            ModeloProducto.insert(p);
        }
        //Eliminar productos
        else if(target.equals("producto")
                && op.equals("delete")
                && action.equals("op")){
            forward = false;
            destino = "controlWeb?target=producto&op=select&action=view";
            ModeloProducto.delete(request.getParameter("id"));
        }
        
        
        
        //Despues de todo
        if (forward) {
            request.getRequestDispatcher(destino).forward(request, response);
        } else {
            response.sendRedirect(destino);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
