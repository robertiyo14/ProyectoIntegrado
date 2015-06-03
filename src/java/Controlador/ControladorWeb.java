/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import hibernate.Categoria;
import hibernate.Login;
import hibernate.Pedido;
import hibernate.Producto;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelos.ModeloCategoria;
import modelos.ModeloLineaPedido;
import modelos.ModeloLogin;
import modelos.ModeloPedido;
import modelos.ModeloProducto;

@WebServlet(name = "Controlador", urlPatterns = {"/controlWeb"})
@MultipartConfig
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
            String categoria = request.getParameter("categoria");
            System.out.println("Categoria: " + categoria + "\n");
            String titulo = request.getParameter("titulo");
            System.out.println("Titulo:" + titulo + "\n");
            String descripcion = request.getParameter("descripcion");
            System.out.println("Desc: " + descripcion + "\n");
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio")));
            System.out.println("Precio: " + precio + "\n");
            int stock = Integer.parseInt(request.getParameter("stock"));
            System.out.println("Stock: " + stock + "\n");
            List <Categoria> categorias = ModeloCategoria.get();
            System.out.println("Numero de categorias: " + categorias.size() + "\n");
            Producto p = new Producto();
            p.setTitulo(titulo);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setStock(stock);
            for(Categoria cat : categorias){
                System.out.println("Categoria: " + cat.getNombre() + "\n");
                if(cat.getNombre().equals(categoria)){
                    p.setCategoria(cat);
                    System.out.println("PONGO LA CATEGORIA: " + cat.getNombre() + "\n");
                    break;
                }
            }
            
            //FOTO
            Part filePart = request.getPart("foto"); 
            InputStream fileContent = filePart.getInputStream();
            String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = fecha+".jpg";
            response.setContentType("application/json;charset=UTF-8");
            try {
                System.out.println("aaaaaaaaaaaa estoy en el try");
                FileOutputStream fos = new FileOutputStream( getServletContext().getRealPath("/") +"images/"+ fileName);
                System.out.println("aaaaaaaaaaaa " + fileName);
                byte[] array = new byte[1000]; // buffer temporal de lectura.
                int leido = fileContent.read(array);
                while (leido > 0) {
                    fos.write(array, 0, leido);
                    leido = fileContent.read(array);
                }
                // cierre de conexion y fichero.
                fileContent.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("AAAAAAAAAAAAAAAAAA: "+e.toString());
            }
            p.setImagen(fileName);
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
        //Ver pedidos
        else if (target.equals("pedido")
                && op.equals("select")
                && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/pedidos.jsp";
            request.setAttribute("datos", ModeloPedido.get());
        }
        //Entregar pedidos
        else if (target.equals("pedido")
                && op.equals("edit")
                && action.equals("op")) {
            forward = false;
            destino = "controlWeb?target=pedido&op=select&action=view";
            Pedido p = new Pedido();
            p.setId(Integer.parseInt(request.getParameter("id")));            
            Date date=null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(request.getParameter("fecha"));
            } catch (ParseException ex) {
                Logger.getLogger(ControladorWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.setFecha(date);
            System.out.println("FECHA: "+date.toString());
            //System.out.println(request.getParameter("fecha")+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            p.setNombre(request.getParameter("nombre"));
            p.setDireccion(request.getParameter("direccion"));
            p.setApellidos(request.getParameter("apellido"));
            p.setTelefono(request.getParameter("telefono"));
            p.setEstado(1);
            ModeloPedido.edit(p);
        }
        //Ver lineas pedido
        else if (target.equals("linea")
                && op.equals("select")
                && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/detallePedido.jsp";
            String id = request.getParameter("id");
            System.out.println(id);
            request.setAttribute("datos", id);
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
