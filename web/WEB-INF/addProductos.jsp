<%-- 
    Document   : addProductos
    Created on : 21-mar-2015, 14:21:10
    Author     : rober
--%>

<%@page import="hibernate.Categoria"%>
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
                        <li class="dropdown, active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Insertar <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="controlWeb?target=categoria&op=insert&action=view">Categorías</a></li>
                                <li><a href="controlWeb?target=producto&op=insert&action=view">Productos</a></li>
                            </ul>
                        </li>
                        <li><a href="controlWeb?target=producto&op=select&action=view">Productos</a></li>
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
            <div class="jumbotron"> 
                <h3>Insertar nuevo producto</h3>
                <form action="controlWeb" method="post" enctype="multipart/form-data">
                    <div class="input-group">
                        <span class="input-group-addon">Título</span>
                        <input class="form-control" type="text" name="titulo" required/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Descripción</span>
                        <textarea class="form-control" name="descripcion" required></textarea>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Precio</span>
                        <input class="form-control bfh-number" type="number" name="precio" required/>
                    </div>
                    <div class="input-group">                        
                        <span class="input-group-addon">Stock</span>
                        <input class="form-control" type="number" name="stock" required/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Imagen</span>
                        <input class="form-control" type="file" name="foto" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Categoría</span>
                        <select class="form-control" name="categoria">
                        <% 
                            List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("datos");
                            for (Categoria c : listaCategorias) {
                        %>
                        <option class="form-control"><%= c.getNombre() %></option>
                        <%
                            }
                        %>
                        </select>
                    </div>
                    <button type="submit">Insertar</button>
                    <input type="hidden" name="target" value="producto" />
                    <input type="hidden" name="op" value="insert" />
                    <input type="hidden" name="action" value="op" />
                </form>
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
