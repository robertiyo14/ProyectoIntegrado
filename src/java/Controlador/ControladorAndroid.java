/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import hibernate.Categoria;
import hibernate.Lineapedido;
import hibernate.Pedido;
import hibernate.Producto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ModeloCategoria;
import modelos.ModeloLineaPedido;
import modelos.ModeloPedido;
import modelos.ModeloProducto;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "ControladorAndroid", urlPatterns = {"/controlandroid"})
public class ControladorAndroid extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        response.setContentType("application/json;charset=UTF-8");
        JSONArray lista = new JSONArray();
        
        //ACCIONES
        if (action.equals("productos")) {
            List<Producto> productos = ModeloProducto.get();
            for(Producto p: productos){
                lista.put(p.getJSON());
            }
        } else if(action.equals("categorias")){
            List<Categoria> categorias = ModeloCategoria.get();
            for(Categoria c: categorias){
                lista.put(c.getJSON());
            }
        } else if(action.equals("pedido")){
            
            String json = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = in.readLine();
            while (line != null) {
                json += line;
                line = in.readLine();
            }
            JSONObject object = new JSONObject(json);
            Pedido pedido = new Pedido(object);
            pedido.setEstado(0);
            
            ModeloPedido.insert(pedido);
            List<Pedido> pedidos = ModeloPedido.get();
            int id = pedidos.get(pedidos.size()-1).getId();
            JSONObject objetoJson = new JSONObject();
            objetoJson.put("id", id);
            lista.put(objetoJson);
        } else if(action.equals("lineapedido")){
            
            String json = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = in.readLine();
            while (line != null) {
                json += line;
                line = in.readLine();
            }
            JSONObject object = new JSONObject(json);

            Lineapedido pedido = new Lineapedido(object);
            ModeloLineaPedido.insert(pedido);
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.print(lista.toString());
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
