<!DOCTYPE html>
<html>
<head>
    <style type="text/css"  rel="stylesheet">
        body{
            background: #dcdcdc;
        }
        table{
            border-radius:5px;
        }

        #one{
            margin-left: 61.5%;
            font-size: 20px;
            font-family: "Comic Sans MS";
        }

        #two,tr{
            height: 28px;
        }

        #two{
            width: 1500px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            font-family: "Comic Sans MS";
            margin-top: 30px;
        }

        #two a:link {color: blue; text-decoration:none;}
        #two a:active{color: red;}

        input{border-radius: 5px;	font-size: 15px;font-weight: bold;font-family: "comic sans ms";}

        input[type=file]{height: 35px;width: 250px;margin-top: 25px;margin-left:30px;position: relative;box-shadow: 2px 2px 4px #226;}
        input[type=file]:hover{color: blue;transform: scale(1.1);transition: 0.3s;}
        input[type=file]:active{top: 3px;}
        input[type=submit]{height: 35px;width: 100px;margin-top: 25px;margin-left:30px;position: relative;box-shadow: 2px 2px 4px #226;}
        input[type=submit]:hover{color: blue;transform: scale(1.1);transition: 0.3s;}
        input[type=submit]:active{top: 3px;}

    </style>
</head>
<body>

<div>
    <#include "fragments/header.ftl"/>
</div>
<#if message??>
    <h2>${message}</h2>
</#if>
<div id="one">
    <form method="POST" enctype="multipart/form-data" action="/view/upload">
        <table>
            <tr>
                <td><input type="file" name="filename"/></td>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <td><input type="submit" value="一键上传"/></td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </form>
</div>

<div id="two">
    <table border="1px" bordercolor="darkblue"  cellpadding="0px" cellspacing="0px" bgcolor="#dcdcdc" >
        <thead>
        <tr>
            <td width="250px">Name(名称)</td>
            <td width="250px">ID（编号）</td>
            <td width="250px">contentType（内容类型）</td>
            <td width="200px">size（bytes）</td>
            <td width="250px">uploadDate（上传时间）</td>
            <td width="300px">md5（摘要信息）</td>
            <td width="150px">操作</td>
        </tr>
        </thead>
        <tbody>
            <#list files as file >
            <tr>
                <td><a href="/view/file?id=${file.id!''}&method=download">${file.name!''}</a></td>
                <td>${file.id!''}</td>
                <td>${file.contentType!''}</td>
                <td>${file.size!''}</td>
                <td>${file.uploadDate}</td>
                <td>${file.md5!''}</td>
                <td>
                    <a href="/view/file?id=${file.id!''}&method=show" target="_blank">查看</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/view/file?id=${file.id!''}&method=delete">删除</a>
                </td>
            </tr>
            <#else>
                <tr>
                    <td colspan="7">没有文件信息！！</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<div>
    <#include "fragments/footer.ftl"/>
</div>
</body>
</html>
