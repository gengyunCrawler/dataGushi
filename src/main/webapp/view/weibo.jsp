<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <link rel="shortcut icon" href="<%=basePath%>view/plugin/images/favicon.png" type="image/png">
  <title>微博排行</title>
  <script type="text/javascript" src="plugin/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="js/deviceJudger.js"></script>
  <script type="text/javascript" src="js/weibo.js"></script>
  <link rel="stylesheet" type="text/css" href="css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <link rel="stylesheet" type="text/css" href="css/iconfont.css" />
  <script type="text/javascript" src="plugin/bootstrap/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="plugin/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<div class="main-container">
  <div class="header">
    <div class="logo">
      <i class="iconfont icon-weibo"></i>
    </div>
    <div class="logo-title">
      <span>微博</span>
      <span>排行榜</span>
    </div>
    <div class="title-right">
      <div class="monitor">
        <i class="iconfont icon-img-time"></i>
      </div>
      <div class="monitor-date">
        <span>监测范围</span>
        <span>7月10日-8月08日</span>
      </div>
      <div class="monitor-result">
        <span>出榜日期</span>
        <span>8月10日</span>
        <span><i id="selectDate" class="iconfont icon-xiasanjiao"></i></span>
      </div>
    </div>
  </div>
  <div class="main">
    <div class="category">
      <%--<span class="active hand" id="all">全部</span>--%>
      <%--<span class="line"></span>--%>
      <ul id="type">
        <li class="active hand" id="all" value="all">全部</li>
          <li class="hand" style="width: 2px"> <span class="line" style="margin-top: 8px;padding: 0px;margin-left: 4px"></span></li>

        <li id="provence" class="hand" value="229">省市发布</li>
        <li id="media" class="hand" value="230">文化传媒</li>
        <li id="trip" class="hand" value="231">旅游</li>
        <li id="life" class="hand" value="233">生活资讯</li>
      </ul>
      <span class="arrow"><i></i></span>
    </div>
    <div class="table-wrap">
      <table class="table-responsive">
        <thead>
        <tr>
          <th>序号</th>
          <th>头像</th>
          <th>昵称</th>
          <th>粉丝数</th>
          <th>新增粉</th>
          <th>活跃度</th>
          <th>互动数</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
      </table>
    </div>
    <div class="pagination">
      <ul id="pagination">
        <li class="hand" id="first">首页</li>
        <li flag="1" class="hand1 active" name="num">1</li>
        <li flag="2" class="hand1" name="num">2</li>
        <li flag="3" class="hand1" name="num">3</li>
        <li flag="4" class="hand1" name="num">4</li>
        <li flag="5">...</li>
        <li class="hand" id="next">后页</li>
      </ul>
    </div>
  </div>
  <div class="footer">
    <ul>
      <li>指导单位：贵州省互联网信息办公室</li>
      <li>发榜单位：贵州省互联网发展协会</li>
      <li>指数编制单位：贵州大数据公共实验室</li>
      <li>数据提供单位：贵州耕云科技有限公司</li>
    </ul>
  </div>
</div>
</body>
</html>
