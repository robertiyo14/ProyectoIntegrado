<%-- 
    Document   : index
    Created on : 18-mar-2015, 20:01:55
    Author     : rober
--%>

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

    <title>Media Markt</title>

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
          <a class="navbar-brand" href="controlWeb?target=producto&op=select&action=view"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Insertar <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Categorías</a></li>
                <li><a href="#">Productos</a></li>
              </ul>
            </li>
            <li><a href="controlWeb?target=producto&op=select&action=view">Productos</a></li>
            <li><a href="#contact">Pedidos</a></li>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="../navbar/">Usuarios</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<!-- CONTENIDO -->
    <div class="container">
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Navbar example</h1>
          <a class="btn btn-lg btn-primary" href="" role="button">Ver productos</a>
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

