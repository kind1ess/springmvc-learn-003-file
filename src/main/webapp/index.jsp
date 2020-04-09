<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/4/9
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--传统方式--%>
    <h3>文件上传1</h3>
    <form action="/user/fileUpload1" method="post" enctype="multipart/form-data">
        选择文件<input type="file" name="upload"> <br>
                <input type="submit" value="上传">
    </form>
<%--springmvc方式--%>
    <h3>文件上传2</h3>
    <form action="/user/fileUpload2" method="post" enctype="multipart/form-data">
        选择文件<input type="file" name="upload"> <br>
                <input type="submit" value="上传">
    </form>

</body>
</html>
