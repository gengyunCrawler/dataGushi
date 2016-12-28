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
  <title>微信排行</title>
  <script type="text/javascript" src="plugin/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="js/deviceJudger.js"></script>
    <script type="text/javascript" src="plugin/js/jquery.nicescroll.js"></script>
    <script type="text/javascript" src="js/weixin.js"></script>
  <script type="text/javascript" src="plugin/jpagination/jquery.pagination.js"></script>
  <link rel="stylesheet" type="text/css" href="css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <link rel="stylesheet" type="text/css" href="css/iconfont.css" />
  <link rel="stylesheet" type="text/css" href="plugin/jpagination/pagination.css">

  <link rel="stylesheet" type="text/css" href="plugin/css/tipsy.css" />
  <script type="text/javascript" src="plugin/js/jquery.tipsy.js"></script>
</head>
<body>
  <div class="main-container">
    <div class="header">
      <div class="logo">
        <i class="iconfont icon-weixin"></i>
      </div>
      <div class="logo-title hand" id="weixin">
        <span>微信</span>
        <span>排行榜</span>
      </div>
      <div class="logo-title hand" id="article">
        <span>文章</span>
        <span>排行榜</span>
      </div>
      <div class="title-right">
        <div class="monitor">
          <i class="iconfont icon-img-time"></i>
        </div>
        <div class="monitor-date">
          <span>监测范围</span>
          <span id="monitorRangeDate">7月10日-8月08日</span>
        </div>
        <div class="monitor-result">
          <span>出榜日期</span>
          <span id="publishDate">8月10日</span>
          <%--<div id="skiptowb" class="skipTag"><span>去微博排行榜</span></div>--%>
          <span><i id="selectDate" class="iconfont icon-xiasanjiao">
              <div class="date-wrapper">
                <div class="point" id="point"></div>
                <div class="dropDate" id="dropDate">
                      <span id="s1" onclick="changeDate(2016,11)">8月1日</span>
                </div>
              </div>
          </i></span>
      </div>
    </div>
    </div>
    <div class="main">
      <div class="category">


        <ul id="type">
          <li class="active hand" id="all" value="all">全部</li>
          <li class="hand" style="width: 2px"> <span class="line" style="margin-top: 8px;padding: 0px;margin-left: 4px"></span></li>
          <li class="hand" value="5664">时政新闻</li>
          <li class="hand" value="5663">非时政新闻</li>
          <li class="hand" value="5589">教育培训</li>
          <li class="hand" value="5586">旅游</li>
          <li class="hand" value="5583">本地生活</li>
          <li class="hand" value="5591">餐饮</li>
          <li class="hand" value="5587">楼市地产</li>
          <li id="business" class="hand" value="5581">商业/贸易</li>
          <li class="hand" value="5592">财富金融</li>
          <li id="media" class="hand" value="5579">文化艺术</li>
          <li id="provence" class="hand" value="5578">娱乐</li>
          <li id="trip" class="hand" value="5580">生活百科</li>
          <li class="hand" value="5582">其他</li>
        </ul>
        <span class="arrow"><i></i></span>

      </div>
      <div class="table-wrap">
        <table>
          <thead>

          </thead>
          <tbody>
          </tbody>
        </table>
      </div>


    </div>
      <div  id="pagination" class="newPage">

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
