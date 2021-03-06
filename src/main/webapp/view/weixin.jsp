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
      <div class="logo-title">
        <span>微信</span>
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
          <div id="skiptowb" class="skipTag"><span>去微博排行榜</span></div>
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
        <%--<span class="active hand" id="all">全部</span>--%>
        <%--<span class="line"></span>--%>
        <ul id="type">
          <li class="active hand" id="all" value="all">全部</li>
          <li class="hand" style="width: 2px"> <span class="line" style="margin-top: 8px;padding: 0px;margin-left: 4px"></span></li>
          <li id="provence" class="hand" value="5578">娱乐</li>
          <li id="media" class="hand" value="5579">文化艺术</li>
          <li id="trip" class="hand" value="5580">生活百科</li>
          <li id="business" class="hand" value="5581">商业/贸易</li>
          <li class="hand" value="5583">民生服务</li>
          <li  class="hand" value="5585">媒体</li>
          <li  class="hand" value="5586">旅游</li>
          <li  class="hand" value="5587">楼市地产</li>
          <li  class="hand" value="5589">教育培训</li>
          <li  class="hand" value="5590">健康/医疗</li>
          <li  class="hand" value="5591">餐饮</li>
          <li  class="hand" value="5592">财富金融</li>
          <li  class="hand" value="5582">其他</li>
        </ul>
        <span class="arrow"><i></i></span>

      </div>
      <div class="table-wrap">
        <table>
          <thead>
            <tr>
              <th>序号</th>
              <th>头像</th>
              <th>昵称</th>
              <th>文章数</th>
              <th id="origin" original-title="带原创标识的文章数" >原创数<span class="questionmark">[?]</span></th>
              <th id="readNo" original-title="30天所有文章阅读总数">阅读数<span class="questionmark">[?]</span></th>
              <th id="likeNo"  original-title="30天所有文章点赞总数">点赞数<span class="questionmark" >[?]</span></th>
              <th id="aveReadNo"  original-title="30天的平均阅读数">平均阅读数<span  class="questionmark">[?]</span></th>
              <th id="aveLikeNo"  original-title="30天的平均点赞数">平均点赞数<span  class="questionmark">[?]</span></th>
              <th id="aveTopReadNo"  original-title="30天的平均头条阅读数">平均头条阅读数<span  class="questionmark">[?]</span></th>
              <th id="quaVector"  original-title="平均阅读数、平均点赞数和点赞率的加权">质量指数<span  class="questionmark">[?]</span></th>
              <th id="influence"  original-title="总阅读数和总点赞数的加权">影响力<span  class="questionmark">[?]</span></th>
              <th id="operation"  original-title="总阅读数和总点赞数的加权">操作<span  class="questionmark">[?]</span></th>
            </tr>
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
