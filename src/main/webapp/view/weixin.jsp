<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title>微信排行</title>
  <script type="text/javascript" src="plugin/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="js/index.js"></script>
  <link rel="stylesheet" type="text/css" href="css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <link rel="stylesheet" type="text/css" href="css/iconfont.css" />

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
          <span>7月10日-8月08日</span>
        </div>
        <div class="monitor-result">
          <span>出榜日期</span>
          <span>8月10日</span>
          <span><i class="iconfont icon-xiasanjiao"></i></span>
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
          <li id="provence" class="hand" value="5051">省市发布</li>
          <li id="media" class="hand" value="5052">文化传媒</li>
          <li id="trip" class="hand" value="5053">旅游</li>
          <li id="life" class="hand" value="5054">生活资讯</li>
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
              <th>原创数<span id="origin" class="questionmark" title="带原创标识的文章数">[?]</span></th>
              <th>阅读数<span id="readNo" class="questionmark" title="30天所有文章阅读总数">[?]</span></th>
              <th>点赞数<span id="likeNo" class="questionmark" title="30天所有文章点赞总数">[?]</span></th>
              <th>平均阅读数<span id="aveReadNo" class="questionmark" title="30天的平均阅读数">[?]</span></th>
              <th>平均点赞数<span id="aveLikeNo" class="questionmark" title="30天的平均点赞数">[?]</span></th>
              <th>平均头条阅读数<span id="aveTopReadNo" class="questionmark" title="30天的平均头条阅读数">[?]</span></th>
              <th>质量指数<span id="quaVector" class="questionmark" title="平均阅读数、平均点赞数和点赞率的加权">[?]</span></th>
              <th>影响力<span id="influence" class="questionmark" title="总阅读数和总点赞数的加权">[?]</span></th>
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
