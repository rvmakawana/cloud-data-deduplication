<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>form1</title>
</head>
<body>
<h1>Upload your image</h1>
<form action="/upload"  method="post" enctype="multipart/form-data">
    <input type="file" name="file" /><br><br>
    <input type="submit" />
    </form> 
</body>
</html>