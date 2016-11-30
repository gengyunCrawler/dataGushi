
//判断设备是PC还是移动
var url = window.location.href;
redirectURL =url.replace('weixin','mobilewx');
deviceJudge(redirectURL);

var date=new Date()
var year=date.getFullYear();
var month=date.getMonth()+1

//获取数据
function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'../wx/data/'+currentPage+'/'+pageSize+'/'+categoryId+"/"+year+"/"+month,
        type:"get",
        async:false,
        dataType:"json",
        success:function(data){
            wxData=data;
        }

    })
    return wxData;
}
//如果当前出榜日期是八月，那么month1传入为7
function changeDate(year1,month1){
    year=year1
    month=month1
    changeDateForLable()
    dealTypeClick($("#all"))
}

function changeDateForLable(){
    var start=month-1
    var startHtml=start+"月1日"
    var publishDate=month+"月1日"
    $("#monitorRangeDate").html(startHtml+"-"+publishDate)
    $("#publishDate").html(publishDate)
}
var categoryId="all";
var totalPage;
function bindWx(data,pageNo) {
    var html="";

    $('tbody').empty();
    $.each(data,function(index,item){

        var sequence=(pageNo-1)*10+index+1
        var sequenceHtml
        if(sequence<4){
            sequenceHtml='<tr>'+
                '<td class="font-3"><span>'+sequence+'</span></td>'
        }else{
            sequenceHtml='<tr>'+
                '<td class="behind-7"><span>'+sequence+'</span></td>'
        }
        sequenceHtml+=
            "<td class='avator'><img src='"+item.headPicture+"'></td>"+
            '<td class="nickname"><div style="margin-top: 10px"><div class="weixin_hao" >'+item.wxName+'</div><div class="weixin_hao" >'+item.wxid+'</div></div>'+'</td>'+
            '<td>'+item.articlesNum+'</td>'+
            '<td>'+item.totalOriginalNum+'</td>'+
            '<td>'+item.totalReadNum+'</td>'+
            '<td>'+item.totalLikeNum+'</td>'+
            '<td>'+item.avgReadNum+'</td>'+
            '<td>'+item.avgLikeNum+'</td>'+
            '<td>'+item.avgHeadlineNum+'</td>'+
            '<td>'+item.qualityNum+'</td>'+
            '<td>'+item.influenceNum+'</td>'+
            '<td><a href=\'../wx/detail/'+year+'/'+month+'/'+item.wxBiz+'\'>查看详情</a></td>'+
            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    $('table').append(html)
}function pageselectCallback(page_index, jq) {
   var data = loadData(page_index+1,10,categoryId)
    bindWx(data.datas,data.currentPage)
}
//数据绑定
function bindWxData(data,categoryId){

   var data = loadData(1,10,categoryId)
    bindWx(data.datas,data.currentPage)
    $("#pagination").pagination(data.totalPage, {
        num_edge_entries: 0,
        num_display_entries: 4,
        callback: pageselectCallback,
        items_per_page:1,
        prev_text:'上一页',
        next_text:'下一页'

    });
}

function dealTypeClick(obj){
    $("li[flag='5']").show()
    firstHandle()
    removeTypeClass()
    //$("#all").css('color','#FFFFFF')
    categoryId=obj.val();
    if(categoryId=='0'){
        categoryId="all";
    }
    obj.addClass('active')
    bindWxData('1',categoryId);
    for(var i=0;i<=5;i++){
        var num=i
        $("li[flag='"+num+"']").show()
    }
    if(totalPage<5){
        //if(totalPage==4){
        //    $("li[flag='5']").hide()
        //    return
        //}
        var i=totalPage
        for(i;i<=5;i++){
            var num=i+1
            $("li[flag='"+num+"']").hide()
        }
        return

    }

}
//移除所有翻页样式
function removeCursor(){
    $.each($("#pagination li"),function(index,item){
        $(this).removeClass("active")
    });
}

//移除类型样式
function removeTypeClass(){
    $.each($("#type li"),function(index,item){
        $(this).removeClass('active')
    })
}

//下拉自动添加新月份
function addNewMonth() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var recentMonthString = $("#dropDate span:last-child").text();
    var recentMonth = parseInt(recentMonthString.substring(0,recentMonthString.indexOf("月")));
    var appendCss = {
        "margin": 0,
        position: 'absolute',
        display: 'block',
        left: '9px',
        height: '25px',
        'line-height': '28px',
        'text-align': 'center',
        padding: '0 5px',
        'background-color': '#3091ff',
        width: '72px',
        'margin-bottom':'10px'
    }
    if(recentMonth != 12)   {
        var range = (month+1)-recentMonth;
        var loading_month;
        var i;
        var top;
        var top_value;
        top = $(".dropDate span#s1").css('top');
        if(month+1 > recentMonth) {
            var loading_top;
            for(i=0; i<range; i++)  {
                top_value = parseInt(top.substring(0,top.indexOf("px")));
                loading_top = top_value+30;
                loading_month = recentMonth+1+i;
                $("#dropDate").append('<span onclick="changeDate('+ year +','+ loading_month+')">'+loading_month+'月1日</span>');

                $("#dropDate span:last-child").css(appendCss).css('top',loading_top+"px");
                top = loading_top+'px';
            }
        }
    }else if (recentMonth === 12) {
        $("#dropDate span").remove();
        $("#dropDate").append('<span id="s1" onclick="changeDate('+ (year+1) +',1)">1月1日</span>');

    }
}

function firstHandle(){
    removeCursor()
    bindWxData(1,categoryId);
    $.each($("li[name='num']"),function(index,item){
        if(index==0){
            $(this).addClass('active')
        }
        $(this).html(index+1)
    });
}


$(function(){
    $("#point").hide();
    $("#dropDate").hide();
    bindWxData(1,"all");
    changeDateForLable()


    $("#type li").click(function(){
        $("#dropDate").hide();
        dealTypeClick($(this))
    })



    $("#first").click(function(){
        $("#dropDate").hide();
        firstHandle()

    })




    $.each($("li[name='num']"),function(index,item){

        $(this).click(function(){
            $("#dropDate").hide();
            removeCursor();
            $(this).addClass("active")
            bindWxData($(this).text(),categoryId)
        })
    });

    $("#origin").tipsy({gravity:'s'});
    $("#readNo").tipsy({gravity:'s'});
    $("#likeNo").tipsy({gravity:'s'});
    $("#aveReadNo").tipsy({gravity:'s'});
    $("#aveLikeNo").tipsy({gravity:'s'});
    $("#aveTopReadNo").tipsy({gravity:'s'});
    $("#quaVector").tipsy({gravity:'s'});
    $("#influence").tipsy({gravity:'s'});


    $("#selectDate").mouseenter(function () {
        $("#point").show();
        $("#dropDate").show();
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
    });

    $("#dropDate").mouseleave(function () {
        $("#point").hide();
        $("#dropDate").hide();
    });



    $("#skiptowb").click(function () {
        $(this).addClass('hand').removeClass('skipTag').addClass('skipTagClicked');
        window.location.href=url.replace("weixin","weibo");
    });

    $("#skiptowb").hover(function () {
        $(this).addClass('hand');
    });

    addNewMonth();

})

