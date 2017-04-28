<%--
  Created by IntelliJ IDEA.
  User: lauearo
  Date: 28/04/2017
  Description: sse page
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.min.js" />"></script>
<script type="text/javascript">
    if (!!window.EventSource) {   //EventSource object is the client of SSE.
        var source = new EventSource('push');
        s = '';
        source.addEventListener("message", function (e) {
            s += e.data + "<br/>";
            $("#msgFrompPush").html(s);
        });

        source.addEventListener("open", function (e) {
            console.info("connection open.");
        }, false);

        source.addEventListener("error", function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.info("connection close.");
            } else {
                console.info("ready state is: " + e.readyState);
            }
        }, false)
    } else {
        console.info("browser not support SSE");
    }
</script>
</body>
</html>
