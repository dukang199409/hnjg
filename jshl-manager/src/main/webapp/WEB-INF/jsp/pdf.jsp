<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${pdfname}在线预览</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
</head>
<body>
<iframe src="<c:url value="/generic/web/viewer.html" />?file=/displayPDF/${pdfName}"
        width="100%" height="800"></iframe>
</body>
</html>