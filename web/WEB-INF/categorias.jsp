<%-- 
    Document   : categorias
    Created on : 21-mar-2015, 13:53:51
    Author     : rober
--%>

<%@page import="modelos.ModeloCategoria"%>
<%@page import="hibernate.Categoria"%>
<%@page import="hibernate.Producto"%>
<%@page import="hibernate.Producto"%>
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
                <h3>Insertar nueva categoría</h3>
                <form action="controlWeb" method="POST">
                    <div class="input-group">
                        <span class="input-group-addon">Nombre</span>
                        <input class="form-control" type="text" name="nombre" required/>
                        <span class="input-group-btn">
                            <button class="btn" type="submit">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            </button>
                        </span>
                    </div>
                        <input type="hidden" name="target" value="categoria" />
                        <input type="hidden" name="op" value="insert" />
                        <input type="hidden" name="action" value="op" />
                    </form>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Categrorías</div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Categoria> listaCategorias = (List<Categoria>) request.getAttribute("datos");
                            for (Categoria c : listaCategorias) {
                        %>
                        <tr>
                            <td><%= c.getId()%></td>
                            <td><%= c.getNombre()%></td>

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
