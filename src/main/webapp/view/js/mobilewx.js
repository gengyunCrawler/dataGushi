var url = window.location.href;

//获取数据
var date=new Date()
var year=date.getFullYear()
var month=date.getMonth()+1

var arr=new Array();
var type1=new Object()
type1.key="provence";
type1.value="5051"
arr.push(type1)

var type1=new Object()
type1.key="media";
type1.value="5052"
arr.push(type1)

var type1=new Object()
type1.key="trip";
type1.value="5053"
arr.push(type1)

var type1=new Object()
type1.key="life";
type1.value="5054"
arr.push(type1)
typeArr=arr
// alert(year+":"+month)
function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'/DataStory/wx/data/'+currentPage+'/'+pageSize+'/'+categoryId+"/"+year+"/"+month,
        async:false,
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


            '<td>'+item.qualityNum+'</td>'+
            '<td>'+item.influenceNum+'</td>'+
            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    body.append(html)
}


function changeDate(year1,month1){

    year=year1
    month=month1
    $.each(typeArr,function(index,item){
        var data=loadData(1,10,item.value)

        bindWxData($("#"+item.key+""),data);
    })
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
