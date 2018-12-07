<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" href="css/bootstrap.min.css">
<link rel="Stylesheet" href="css/style.css">
</head>
<body id="idBody">
<form action="ConexionServlet" method="GET">
<div class="container-fluid">
        <div class="row">
            <div id="izquierda" class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                
            </div>

            <div id="mitad" class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                <div class="row pt-4">
                    <div class="column col-lg-12">
                        <%for(int i=0;i<10;i++)
                        	{
                        		%><div class="card col-lg-12 mb-4"> <!--meterlo en el for-->
                            <header><h3>Fecha</h3></header>
                            <p>Diagnostico</p>
                            <footer><p>algo mas</p></footer>
                        </div><%
                        	}
                        	%>
                    </div>
                </div>
            </div>

            <div id="derecha" class="col-xs-2 col-sm-2 col-md-2 col-lg-2">

            </div>
        </div>
    </div>
    <input type="submit" value="presiona perro">
    </form>

    <script src="scripts/jquery-3.3.1.min.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
</body>
</html>