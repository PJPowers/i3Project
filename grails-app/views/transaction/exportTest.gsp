<!DOCTYPE html>
<%@ page import="i3project.Transaction" %>
<export:resource />

<html>
<head>
		
<div class="paginateButtons">
    <g:paginate total="${Transaction.count()}" />
</div>

</head>
<body>

<export:formats formats="['csv', 'excel', 'ods', 'pdf', 'rtf', 'xml']" controller="Transaction" action="exportTrans"> </export:formats>
      
</body>
</html>