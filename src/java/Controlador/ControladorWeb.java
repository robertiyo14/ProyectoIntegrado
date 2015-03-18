/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
        
        //Ir a página donde muestra todos los productos.
        if (target.equals("producto")
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
