<%-- 
    Document   : productos
    Created on : 16-mar-2015, 11:31:29
    Author     : rober
--%>

<%@page import="modelos.ModeloCategoria"%>
<%@page import="hibernate.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="hibernate.Producto"%>
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
            <li class="active"><a href="controlWeb?target=producto&op=select&action=view">Productos</a></li>
            <li><a href="controlWeb?target=pedido&op=select&action=view">Pedidos</a></li>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="">Usuarios</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
        <!-- CONTENIDO -->
        <div class="container">

            <!-- Main component for a primary marketing message or call to action -->
            <div class="panel panel-default">
                <div class="panel-heading">Productos</div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Imagen</th>
                            <th>Título</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Añadir stock</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Producto> listaProductos = (List<Producto>) request.getAttribute("datos");
                            for (Producto p : listaProductos) {
                                Categoria c = ModeloCategoria.get(p.getCategoria().getId() + "");
                        %>
                        <tr>
                            <td><%= p.getId()%></td>
                            <td><img src="images/<%= p.getImagen()%>"/></td>
                            <td><%= p.getTitulo()%></td>
                            <td><%= p.getDescripcion()%></td>
                            <td><%= p.getPrecio()%></td>
                            <td><%= p.getStock()%></td>
                            <td>
                                <form action="controlWeb" method="POST">
                                    <input type="text" name="stockNuevo" required class="anadirStock"/>
                                    <input type="hidden" name="target" value="producto" />
                                    <input type="hidden" name="op" value="edit" />
                                    <input type="hidden" name="action" value="op" />
                                    <input type="hidden" name="id" value="<%= p.getId()%>" />
                                    <input type="hidden" name="imagen" value="<%= p.getImagen()%>" />
                                    <input type="hidden" name="categoria" value="<%= c.getId()%>" />
                                    <input type="hidden" name="precio" value="<%= p.getPrecio()%>" />
                                    <input type="hidden" name="titulo" value="<%= p.getTitulo()%>" />
                                    <input type="hidden" name="descripcion" value="<%= p.getDescripcion()%>" />
                                    <input type="hidden" name="stock" value="<%= p.getStock()%>" />
                                    <button type="submit">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <%
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

