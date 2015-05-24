<%-- 
    Document   : pedidos
    Created on : 13-may-2015, 13:58:14
    Author     : rober
--%>

<%@page import="hibernate.Lineapedido"%>
<%@page import="modelos.ModeloLineaPedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.ModeloProducto"%>
<%@page import="hibernate.Producto"%>
<%@page import="hibernate.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Fixed Top Navbar Example for Bootstrap</title>

        <!-- Bootstrap core CSS 
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
        -->
        <!-- Custom styles for this template -->
        <link href="styles.css" rel="stylesheet">

    </head>

    <body>

        <!-- Fixed navbar -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="controlWeb"></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="controlWeb">Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Insertar <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="controlWeb?target=categoria&op=insert&action=view">Categorías</a></li>
                                <li><a href="controlWeb?target=producto&op=insert&action=view">Productos</a></li>
                            </ul>
                        </li>
                        <li><a href="controlWeb?target=producto&op=select&action=view">Productos</a></li>
                        <li class="active"><a href="controlWeb?target=pedido&op=select&action=view">Pedidos</a></li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="">Usuarios</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <!-- CONTENIDO -->
        <div class="container">
            <% 
                //List<Pedido> listaPedidos = (List<Pedido>) request.getAttribute("datos");
                Pedido p = (Pedido) request.getAttribute("datos");
                if(p==null){
                    System.out.print("NULO");
                }else{
                    System.out.print("id: "+p.getId());
                    System.out.print("nombre: "+p.getNombre());
                }
            %>
            <div class="jumbotron">
                <div class="row">
                    
                    <div class="col-md-4"><%= p.getNombre() %></div>
                    <div class="col-md-4"><%= p.getDireccion() %></div>
                    <div class="col-md-4"><%= p.getFecha() %></div>
                </div>
                <div class="row">
                    <div class="col-md-4"><%= p.getApellidos() %></div>
                    <div class="col-md-4"><%= p.getTelefono() %></div>
                    <div class="col-md-4"><% if(p.getEstado()==1){%>
                                              Entregado  
                                            <%}else{ %>
                                              Sin entregar
                                            <%} %>
                    </div>
                </div>
                
            </div>

            <!-- Main component for a primary marketing message or call to action -->
            <div class="panel panel-default">
                <div class="panel-heading">Artículos: </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Imagen</th>
                            <th>Título</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        ArrayList lineasPedido = new ArrayList();
                        Producto pro = new Producto();
                        for(Lineapedido lp : ModeloLineaPedido.get()){
                            if(lp.getPedido().getId() == p.getId()){ %>
                        <tr>
                            <% pro = lp.getProducto(); System.out.print("ES IGUAL"); %>
                            <td><%= pro.getId() %></td>
                            <td><img src="images/<%= pro.getImagen() %>"/></td>
                            <td><%= pro.getTitulo() %></td>
                            <td><%= pro.getDescripcion() %></td>
                            <td><%= pro.getPrecio() %></td>
                            <td><%= lp.getCantidad() %></td>
                        </tr>
                        <%
                            }else{
                                System.out.print("NO ES IGUAL: "+lp.getPedido().getId()+"--"+p.getId());
                            }
                        }
                        %>
                        
                    </tbody>
                </table>
            </div>

        </div> <!-- /container -->


        <!-- Bootstrap core JavaScript
   ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="bootstrap//js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>


