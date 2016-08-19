//判断设备是PC还是移动
var url = window.location.href;
redirectURL =url.replace('weibo','mobilewb');
deviceJudge(redirectURL);
var date=new Date()
var year=date.getFullYear();
var month=date.getMonth()+1
//获取数据
function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'../weibo/data/'+currentPage+'/'+pageSize+'/'+categoryId+"/"+year+"/"+month,
        async:false,
        success:function(data){
            wxData=data;
        }
    })
    return wxData;
}


var categoryId="all";
var totalPage;

//数据绑定
function bindWxData(currentPage,categoryId){
    var data =loadData(currentPage,10,categoryId);
    var html="";

    totalPage=data.totalPage
    var pageNo=data.currentPage
    $('tbody').empty();
    $.each(data.datas,function(index,item){

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
            "<td class='avator'><img src='"+item.head+"'></td>"+
            '<td class="nickname">'+item.nickName+'</td>'+
            '<td>'+item.followCount+'</td>'+
            '<td>'+item.newFllowCount+'</td>'+
            '<td>'+item.rateActivity+'</td>'+
            '<td>'+item.interactNum+'</td>'+
            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    $('table').append(html)
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
//解决出现数字比页数多的情况
function dealPageSizeWith(){

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

function changeDate(year1,month1){

    year=year1
    month=month1
    $("#dropDate").hide();
    //document.body.removeChild(document.getElementById("dropDate"))
    dealTypeClick($("#all"))
}
$(function(){
    $("#dropDate").hide();
    bindWxData('1',"all");

    //$("#all").click(function(){
    //    firstHandle()
    //    removeTypeClass()
    //    $(this).addClass('active')
    //    categoryId="all";
    //    bindWxData('1',categoryId)
    //})

    $("#type li").click(function(){
        $("#dropDate").hide();
       dealTypeClick($(this))

    })

    $("#first").click(function(){
        $("#dropDate").hide();
        $("li[flag='5']").show()
        firstHandle()

    })



    $("#next").click(function(){
        $("#dropDate").hide();
        var lastNum=parseInt($("#pagination > li:nth-child(5)").html());
        var curentHtml=$("[class='hand1 active']");
        var currentNum=parseInt(curentHtml.html())
        var flagNum=parseInt($("[class='hand1 active']").attr("flag"))
        if(totalPage<5){

        }

        if(totalPage-currentNum<=4-flagNum){
            $("li[flag='5']").hide()
            if(currentNum==totalPage){
                alert("当前已是最后页")
              return
            }
            curentHtml.next().addClass("active")
            curentHtml.removeClass("active")
            var page=parseInt($("[class='hand1 active']").html())
            bindWxData(page,categoryId);
            return

        }

        if(currentNum<totalPage){
            $.each($("li[name='num']"),function(index,item){
                var  value=$(this).text();
                value= parseInt(value)
                $(this).html(parseInt(value)+1)
                var page=parseInt($("[class='hand1 active']").html())
                bindWxData(page,categoryId);
            });

        }else{
            alert("当前已是最后页")
            return
        }



    })


    $.each($("li[name='num']"),function(index,item){
        $(this).click(function(){
            $("#dropDate").hide();
            removeCursor();
            $(this).addClass("active")
            bindWxData($(this).text(),categoryId)
        })
    });

    $("#selectDate").mouseenter(function () {
        $("#dropDate").show();
    });

    $("#dropDate").mouseleave(function () {
        $("#dropDate").hide();
    });

//获取总页数
//加载分页数
//添加换页点击事件
})