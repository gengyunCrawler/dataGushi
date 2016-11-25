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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>view/css/detail.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>view/css/iconfont.css" />
    <script type="text/javascript" src="<%=basePath%>view/plugin/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>view/plugin/js/jquery.nicescroll.js"></script>
    <script type="text/javascript" src="<%=basePath%>view/plugin/echarts.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>view/plugin/css/pagination.css">


    <title>微信排行</title>
</head>
<body>
	<div class="container">
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
          <!-- <div id="skiptowb" class="skipTag"><span>去微博排行榜</span></div> -->
          <span><i id="selectDate" class="iconfont icon-xiasanjiao">
              <div class="date-wrapper">
                <div class="point" id="point"></div>
                <div class="dropDate" id="dropDate">
                      <!-- <span id="s1" onclick="changeDate(2016,8)">8月1日</span> -->
                </div>
              </div>
          </i></span>
      </div>
    </div>
    </div>
    <div class="main">
    	<div class="main-top">
    		<img src="img/header.jpeg" id="gongzhongimg">
    		<div class="gongzhong-xinxi">
    			<span class="gongzhong-title" id="wxName">公众号</span>
    			<span class="gongzhong-intro" id="descrition">公众号简介</span>
    		</div>
    		<img src="img/qrcode.jpg" id="qrcode">
    	</div>
    	<div class="main-time">
    		<span class="quantum">30天(10.01-10.31)</span> <span class="zidingyi">自定义</span> <span id="start-time">2016-10-01</span> - <span class="end-time">2016-10-31</span>
    	</div>
    	<div class="main-index">
    		<div class="column">运营指数</div>
    		<div class="index-detail">文章数 <br> <span class="number" id="articlesNum">64</span></div>
    		<div class="index-detail">累计阅读数 <br> <span class="number" id="totalReadNum">64</span></div>
    		<div class="index-detail">平均阅读数 <br> <span class="number" id="avgReadNum">64</span></div>
    		<div class="index-detail">累计点赞数 <br> <span class="number" id="totalLikeNum">64</span></div>
    		<div class="index-detail">平均点赞数 <br> <span class="number" id="avgLikeNum">64</span></div>
    		<div class="index-detail">平均头条阅读数 <br> <span class="number" id="avgHeadlineNum">64</span></div>
        <div class="index-detail">原创文章数 <br> <span class="number" id="activeNum">64</span></div>
        <div class="index-detail">质量指数 <br> <span class="number" id="qualityNum">64</span></div>
        <div class="index-detail">影响力 <br> <span class="number" id="influenceNum">64</span></div>
        <div class="index-detail">行业影响排名 <br> <span class="number"></span></div>
    	</div>

    	<div class="main-tendency">
    		<div class="column">运营趋势</div>
    		<div class="tendency-chart" id="tendency"></div>
    	</div>
    	<div class="main-frame">
    		<div class="column">文章发布时段</div>
    		<div class="frame-chart" id="frame"></div>
    	</div>
    	<div class="main-article">
    		<div class="column">文章(10.01-10.31)</div>
    		<div class="article-chart">
    			<table>
    				<thead>
    					<th style="width:50px;">排序</th>
    					<th style="width:165px;">发布时间</th>
    					<th>文章标题</th>
    					<th style="width: 65px;">阅读数</th>
    					<th style="width: 65px;">点赞数</th>
    				</thead>
    				<tbody id="tbody-articles">
    					<tr>
    						<td><span class="serial">1</span></td>
    						<td>2016-11-18 11:28:54</td>
    						<td><a href="#">黔西县召开森林防火工作会</a></td>
    						<td>15</td>
    						<td>95</td>
    					</tr>
    					<tr>
    						<td><span class="serial-common">1</span></td>
    						<td>2016-11-18 11:28:54</td>
    						<td><a href="#">黔西县召开森林防火工作会</a></td>
    						<td>15</td>
    						<td>95</td>

    					</tr>
    					<tr>
    						<td><span class="serial-common">12</span></td>
    						<td>2016-11-18 11:28:54</td>
    						<td><a href="#">黔西县召开森林防火工作会</a></td>
    						<td>15</td>
    						<td>95</td>

    					</tr>
    				</tbody>

    			</table>
                <div id="data"></div>
                <div id="pagination"></div>
    		</div>
    	</div>
    </div>
	</div>


  <script type="text/javascript" src="<%=basePath%>view/js/detail.js"></script>
<script type="text/javascript" src="<%=basePath%>view/plugin/pagination/pagination.min.js"></script>



</body>

</html>