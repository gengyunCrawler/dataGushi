<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="<%=basePath%>view/plugin/images/favicon.png" type="image/png">

  <title>贵州地区微信排行榜 </title>

  <link href="<%=basePath%>view/plugin/css/style.default.css" rel="stylesheet">
  <link href="<%=basePath%>view/plugin/css/jquery.datatables.css" rel="stylesheet">
  <link href="<%=basePath%>view/plugin/phone.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="<%=basePath%>view/plugin/js/html5shiv.js"></script>
  <script src="<%=basePath%>view/plugin/js/respond.min.js"></script>
  <![endif]-->
</head>

<body style="background-color: #F0EFF4;">

<section>

  <div style="height: 100%;margin-bottom: 80px">

    <div class="contentpanel">

      <div class="row">
          <div class="col-sm-12">
              <div id="skip-left" class="skip-left"><span class="skipword">微信排行榜</span></div>
              <div id="skiptowb" class="skip"><span class="skipword">去微博排行榜</span></div>
            <span style="color: #2845a0;font-size: 16px;font-family: 微软雅黑;">出榜日期 :  </span>
            <div class="btn-group mr5">
              <button type="button" class="btn btn-xs btn-primary but-font" id="publish">8月1日</button>
              <button type="button" class="btn btn-xs btn-primary dropdown-toggle" data-toggle="dropdown" style="width: 30px;background-color: #0258D8">
                <span class="fa fa-chevron-down"></span>
                <span class="sr-only">Toggle Dropdown</span>
              </button>
              <ul class="dropdown-menu ul-font" role="menu">
                  <div class="point_mobile" ></div>
                  <li><span class="a-font" onclick="changeDate(2016,7)" style="cursor:pointer;font-size: 14px; margin-top:9px; margin-bottom: 9px; background-color: #3091ff;">7月1日</span></li>
                  <li><span   class="a-font" onclick="changeDate(2016,8)" style="cursor:pointer;font-size: 14px; margin-bottom: 6px;background-color: #3091ff;">8月1日</span></li>
              </ul>
            </div>
        </div>
        <div class="col-sm-12">
          <span style="color: #2845a0;font-size: 16px;font-family: 微软雅黑;" id="monitorRangeDate">监测时间范围 : 7月1日-7月31日</span>
        </div>
      </div>

      <div class="row" style="background-color: #F3F6F8;margin-left: 0;margin-right: 0;padding: 10px 0 10px 0;margin-top: 20px">
        <div class="col-sm-12" style="text-align: center;">
          <img src="<%=basePath%>view/plugin/1.png" style="width: 35%">
          <span style="color: #706ad3;font-size: 16px;font-family: 微软雅黑;padding: 0 10px 0 10px;">省市发布</span>
          <img src="<%=basePath%>view/plugin/2.png" style="width: 35%">
        </div>
      </div>

      <div class="row" style="margin-top: 20px">
        <div class="col-md-12">
          <table class="table table-striped table-bordered" style="font-size: 10px">
            <thead>
            <tr>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px;">序号</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px;width: 60px">昵称</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>文章数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>阅读数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>点赞数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>质量指数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>影响力</span></th>
            </tr>
            </thead>
            <tbody style="color: #2845a0" id="provence">

            </tbody>
          </table>
        </div><!-- col-md-6 -->
      </div><!-- row -->

        <div class="row" style="background-color: #F3F6F8;margin-left: 0;margin-right: 0;padding: 10px 0 10px 0;margin-top: 20px">
            <div class="col-sm-12" style="text-align: center;">
                <img src="<%=basePath%>view/plugin/1.png" style="width: 35%">
                <span style="color: #706ad3;font-size: 16px;font-family: 微软雅黑;padding: 0 10px 0 10px;">文化传媒</span>
                <img src="<%=basePath%>view/plugin/2.png" style="width: 35%">
            </div>
        </div>

      <div class="row" style="margin-top: 20px; padding-top: 0px">
        <div class="col-md-12">
          <table class="table table-striped table-bordered" style="font-size: 10px">
            <thead>
            <tr>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px">序号</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px; width: 60px">昵称</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>文章数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>阅读数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>点赞数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>质量指数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>影响力</span></th>
            </tr>
            </thead>
            <tbody style="color: #2845a0" id="media">

            </tbody>
          </table>
        </div><!-- col-md-6 -->
      </div><!-- row -->


      <div class="row" style="background-color: #F3F6F8;margin-left: 0;margin-right: 0;padding: 10px 0 10px 0;margin-top: 20px">
        <div class="col-sm-12" style="text-align: center;">
          <img src="<%=basePath%>view/plugin/1.png" style="width: 37%">
          <span style="color: #706ad3;font-size: 16px;font-family: 微软雅黑;padding: 0 20px 0 20px;">旅游</span>
          <img src="<%=basePath%>view/plugin/2.png" style="width: 37%">
        </div>
      </div>

      <div class="row" style="margin-top: 20px">
        <div class="col-md-12">
          <table class="table table-striped table-bordered" style="font-size: 10px">
            <thead>
            <tr>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px">序号</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px;width: 60px">昵称</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>文章数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>阅读数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>点赞数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>质量指数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>影响力</span></th>
            </tr>
            </thead>
            <tbody style="color: #2845a0" id="trip">

            </tbody>
          </table>
        </div><!-- col-md-6 -->
      </div><!-- row -->


        <div class="row" style="background-color: #F3F6F8;margin-left: 0;margin-right: 0;padding: 10px 0 10px 0;margin-top: 20px">
            <div class="col-sm-12" style="text-align: center;">
                <img src="<%=basePath%>view/plugin/1.png" style="width: 35%">
                <span style="color: #706ad3;font-size: 16px;font-family: 微软雅黑;padding: 0 10px 0 10px;">生活资讯</span>
                <img src="<%=basePath%>view/plugin/2.png" style="width: 35%">
            </div>
        </div>

      <div class="row" style="margin-top: 20px">
        <div class="col-md-12">
          <table class="table table-striped table-bordered" style="font-size: 10px">
            <thead>
            <tr>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px">序号</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px;width: 60px">昵称</th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>文章数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>阅读数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>点赞数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>质量指数</span></th>
                <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>影响力</span></th>
            </tr>
            </thead>
            <tbody style="color: #2845a0" id="life">

            </tbody>
          </table>
        </div><!-- col-md-6 -->
      </div><!-- row -->

    </div><!-- contentpanel -->
    </div>
</section>

<div style="position:fixed;width: 100%;background: #F6F8FD;bottom: 0;text-align: center;padding: 10px 0 10px 0">
    <div style="color: #94c4af;font-size: 12px;font-family: 微软雅黑;padding: 0 20px 0 20px;">指导单位 : 贵州省互联网信息办公室</div>
    <div style="color: #94c4af;font-size: 12px;font-family: 微软雅黑;padding: 0 20px 0 20px;">发榜单位 : 贵州省互联网发展协会</div>
    <div style="color: #94c4af;font-size: 12px;font-family: 微软雅黑;padding: 0 20px 0 20px;">指数编制单位 : 贵州大数据公共实验室</div>
    <div style="color: #94c4af;font-size: 12px;font-family: 微软雅黑;padding: 0 20px 0 20px;">数据提供单位 : 贵州耕云科技有限公司</div>
</div>


<script src="<%=basePath%>view/plugin/js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>view/plugin/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=basePath%>view/plugin/js/bootstrap.min.js"></script>
<script src="<%=basePath%>view/plugin/js/modernizr.min.js"></script>
<script src="<%=basePath%>view/plugin/js/jquery.sparkline.min.js"></script>
<script src="<%=basePath%>view/plugin/js/toggles.min.js"></script>
<script src="<%=basePath%>view/plugin/js/retina.min.js"></script>
<script src="<%=basePath%>view/plugin/js/jquery.cookies.js"></script>


<script src="<%=basePath%>view/plugin/js/jquery.datatables.min.js"></script>
<script src="<%=basePath%>view/plugin/js/select2.min.js"></script>

<script src="<%=basePath%>view/plugin/js/custom.js"></script>
<script src="<%=basePath%>view/js/mobilewx.js"></script>
</body>
</html>
