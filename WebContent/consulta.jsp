<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" href="css/bootstrap.min.css">
<link rel="Stylesheet" href="css/cards.css">
</head>
<body id="idBody">

<div class="container-fluid">
        <div class="row">
            <div id="izquierda" class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
              
            <form action="ConexionServlet" method="GET">  <input type="submit" value="presiona perro"></form>
            </div>

            <div id="mitad" class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
                <div class="row pt-4">
                    <div class="column col-lg-12">
                        
                        <%if(request.getAttribute("card")==null){
                        	%>
                        		<%="<h1>Esta vacio</h1>" %>
                        	<%
                        }else{
                        	%>
                        		<%=request.getAttribute("card") %>
                        	<%
                        } %>
                        
                        
                    </div>
                </div>
            </div>

            <div id="derecha" class="col-xs-2 col-sm-2 col-md-2 col-lg-2">

            </div>
        </div>
    </div>
    
    

    <script src="scripts/jquery-3.3.1.min.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
</body>
</html>