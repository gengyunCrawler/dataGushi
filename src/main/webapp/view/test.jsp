<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/24
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="http://localhost:8080/DataStory/view/plugin/jquery-2.1.1.min.js"></script>
</head>
<body>
<div id="content"></div>
</body>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"http://localhost:8080/DataStory/wx/detail/get",
            type:'get',
            async:false,
            success:function (data) {
             $("#content").text(JSON.stringify(data))
            }
        })
    })
</script>
</html>
