var url = window.location.href;

//获取数据
var order="qualityNum"
var date=new Date()
var year=date.getFullYear()
var month=date.getMonth()+1
function getPair(k,v) {
    var obj = new Object()
    obj.key=k
    obj.value=v
    return obj;
}
var arr = new Array()
arr.push(getPair("时政新闻","5664"))
arr.push(getPair("非时政新闻","5663"))
arr.push(getPair("教育培训","5589"))
arr.push(getPair("旅游","5586"))
arr.push(getPair("本地生活","5583"))
arr.push(getPair("餐饮","5591"))
arr.push(getPair("楼市地产","5587"))
arr.push(getPair("商业/贸易","5581"))
arr.push(getPair("财富金融","5592"))
arr.push(getPair("文化艺术","5579"))
arr.push(getPair("娱乐","5578"))
arr.push(getPair("生活百科","5580"))
arr.push(getPair("其他","5582"))



function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'/DataStory/wx/data/'+currentPage+'/'+pageSize+'/'+categoryId+"/"+year+"/"+month+"/"+order,
        type:"get",
        async:false,
        dataType:"json",
        success:function(data){
            wxData=data;
        }

    })

    return wxData;
}

//数据绑定
function bindWxData(body,data){

    var html="";

    body.empty();
    var sequence=1
    $.each(data.datas,function(index,item){
        var readNumStr;
        var readNum=parseFloat(item.totalReadNum)/10000

        readNum=Math.floor(readNum)
        if(readNum>0){
            readNumStr=readNum+"w+"

        }else{
            readNumStr=item.totalReadNum
        }

        var sequenceHtml
        if(sequence<4){
            sequenceHtml='<tr>'+
                '<td><span class="xh">'+sequence+'</span></td>'
        }else{
            sequenceHtml='<tr>'+
                '<td ><span class="xh" style="background-color: #9c98f0">'+sequence+'</span></td>'
        }
        sequenceHtml+=

            '<td class="nickname"><div style="margin-top: 10px"><div class="weixin_hao" >'+item.wxName+'</div><div class="weixin_hao" >'+item.wxid+'</div></div>'+'</td>'+
            '<td>'+item.articlesNum+'</td>'+

            '<td>'+readNumStr+'</td>'+
            '<td>'+item.totalLikeNum+'</td>'+


            '<td>'+item.avgReadNum+'</td>'+
            '<td>'+item.qualityNum+'</td>'+
            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    body.append(html)
}

function bindTbody(data){

    var html="";

    
    var sequence=1
    $.each(data.datas,function(index,item){
        var readNumStr;
        var readNum=parseFloat(item.totalReadNum)/10000

        readNum=Math.floor(readNum)
        if(readNum>0){
            readNumStr=readNum+"w+"

        }else{
            readNumStr=item.totalReadNum
        }

        var sequenceHtml
        if(sequence<4){
            sequenceHtml='<tr>'+
                '<td><span class="xh">'+sequence+'</span></td>'
        }else{
            sequenceHtml='<tr>'+
                '<td ><span class="xh" style="background-color: #9c98f0">'+sequence+'</span></td>'
        }
        sequenceHtml+=

            '<td class="nickname"><div style="margin-top: 10px"><div class="weixin_hao" >'+item.wxName+'</div><div class="weixin_hao" >'+item.wxid+'</div></div>'+'</td>'+
            '<td>'+item.articlesNum+'</td>'+

            '<td>'+readNumStr+'</td>'+
            '<td>'+item.totalLikeNum+'</td>'+


            '<td>'+item.avgLikeNum+'</td>'+
            '<td>'+item.qualityNum+'</td>'+
            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    return html
}

function prepareSingleHtml(typePair) {
    var data=loadData(1,10,typePair.value)
    var tbodyHtml = bindTbody(data)   
    var html = '<div class="row" style="background-color: #F3F6F8;margin-left: 0;margin-right: 0;padding: 10px 0 10px 0;margin-top: 70px">'+
        ' <div class="col-sm-12" style="text-align: center;">'+
        ' <img src="/DataStory/view/plugin/1.png" style="width: 35%">'+
        ' <span style="color: #706ad3;font-size: 16px;font-family: 微软雅黑;paddi+ng: 0 10px 0 10px;">'+typePair.key+'</span>'+
        ' <img src="/DataStory/view/plugin/2.png" style="width: 35%">'+
        ' </div>'+
        ' </div>'+
        ' <div class="row" style="margin-top: 20px">'+
        ' <div class="col-md-12">'+
        ' <table class="table table-striped table-bordered" style="font-size: 10px;margin-left: 10px">'+
        ' <thead>'+
        ' <tr>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px;">序号</th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px;width: 60px">昵称</th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>文章数</span></th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>阅读数</span></th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>点赞数</span></th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>平均阅读数</span></th>'+
        ' <th style="color: #0057d3;font-weight: bold;margin-top: 0px"><span>综合指数</span></th>'+
        ' </tr>'+
        ' </thead>'+
        ' <tbody style="color: #2845a0" id="provence">'
        +tbodyHtml+
        ' </tbody>'+
        ' </table>'+
        ' </div>'+
        ' </div>'
    return html
}
function bindWhole() {
    var html = ''
    $.each(arr,function (index,item) {
        html+=prepareSingleHtml(item)

    })
    // $('#content').empty()
    $('#content').append(html)
}
function changeDate(year1,month1){

    year=year1
    month=month1
    // $.each(typeArr,function(index,item){
    //     var data=loadData(1,10,item.value)
    //
    //     bindWxData($("#"+item.key+""),data);
    // })
    bindWhole()
    changeDateForLable()
}

function changeDateForLable(){
    var start=month-1
    var startHtml=start+"月1日"
    var publishDate=month+"月1日"
    $("#monitorRangeDate").html(startHtml+"-"+publishDate)
    $("#publish").html(publishDate)
}

//下拉自动添加新月份
function addNewMonth() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var recentMonthString = $("#dropDate li:last-child>span").text();
    var recentMonth = parseInt(recentMonthString.substring(0,recentMonthString.indexOf("月")));
    if(recentMonth != 12)   {
        var range = (month+1)-recentMonth;
        var loading_month;
        var i;
        if(month+1 > recentMonth) {
            for(i=0; i<range; i++)  {
                loading_month = recentMonth+1+i;
                $("#dropDate").append('<li><span   class="a-font" onclick="changeDate('+ year +','+ loading_month+')" style="cursor:pointer;font-size: 14px; margin-bottom: 5px;background-color: #3091ff;">'+loading_month+'月1日</span></li>');
            }
        }
    }else if (recentMonth === 12) {
        $("#dropDate li").remove();
        $("#dropDate").append('<li><span   class="a-font" onclick="changeDate('+ (year+1) +',1)" style="cursor:pointer;font-size: 14px; margin-bottom: 5px;background-color: #3091ff;">1月1日</span></li>');

    }
}

$(function(){

    changeDateForLable()
    changeDate(year,month)

    $("#skiptowb").click(function () {
        $(this).removeClass('skip').addClass('skip-clicked');
        window.location.href=url.replace("mobilewx","mobilewb");
    });

    addNewMonth();
    $("#dropDate").niceScroll(
        {
            cursorcolor: "#8096a8",
            horizrailenabled: true,
            cursorborderradius:'0px',
            background:'#c3cfd6',
            cursorminheight:50,
            cursorwidth:'6px'
        }
    );
    
})
