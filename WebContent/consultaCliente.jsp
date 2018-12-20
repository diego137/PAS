<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.util.*"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" href="css/bootstrap.min.css">
<link rel="Stylesheet" href="css/cards.css">
</head>
<body>
    <div id="bg">
        <div id="bg-color">
            <div class="content">
            	<div class="row">
		    		<div class="col-sm-10 col-md-10 col-lg-10"></div>
		    		<div class="col-sm-2 col-md-2 col-lg-2">
		    			<input id="btnCerrar" type="button" class="btn btn-danger btn-block" value="Cerrar Session"/>
		    		</div>    		
		    	</div>
                <div class="container-fluid">
                    <div class="row">
                        <div id="izquierda" class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                        
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
            </div>
        </div>
    </div>

    
    

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/consulta.js"></script>
    <script src="js/pAdmin.js"></script>
</body>
</html>