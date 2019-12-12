<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="/static/plugins/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/admin/index.css"/>
    <script type="text/javascript" src="/static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <title>内容管理系统</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/admin">
        <img src="/static/image/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
        内容管理系统
    </a>
    <ul class="navbar-nav">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="user" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                admin
            </a>
            <div class="dropdown-menu" aria-labelledby="user">
                <a class="dropdown-item" href="#">个人信息</a>
                <a class="dropdown-item" href="#">注销</a>
            </div>
        </li>
    </ul>
</nav>
<script type="text/javascript" src="/static/js/admin/index.js"></script>
</body>
</html>