<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>HttpMessageConverter Demo</title>

</head>
<body>
<div id="resp"></div>
<input value="Request" type="button" onclick="req();"/>

<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript">
    function req() {
        $.ajax({
            url: '/convert',
            data: '2-testMesage',
            type: 'POST',
            contentType: 'application/x-lauearo',
            success: function (data) {
                $('#resp').html(data);
            }
        })
    }
</script>
</body>
</html>