<%--
  Created by IntelliJ IDEA.
  User: lauearo
  Date: 28/04/2017
  Description: async page
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>servlet async support</title>
</head>
<body>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.min.js" />"></script>
<script type="text/javascript">
    deferred();
    function deferred() {
        $.get('defer', function (data) {
            console.info(data);
            deferred();
        })
    }
</script>
</body>
</html>
