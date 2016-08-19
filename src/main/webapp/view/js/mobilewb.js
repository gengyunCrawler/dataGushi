//获取数据
var date=new Date()
var year=date.getFullYear();
var month=date.getMonth()+1
var arr=new Array();
var type1=new Object()
type1.key="provence";
type1.value="229"
arr.push(type1)

var type1=new Object()
type1.key="media";
type1.value="230"
arr.push(type1)

var type1=new Object()
type1.key="trip";
type1.value="231"
arr.push(type1)

var type1=new Object()
type1.key="life";
type1.value="233"
arr.push(type1)

function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'/DataStory/weibo/data/'+currentPage+'/'+pageSize+'/'+categoryId+"/"+year+"/"+month,
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
        var flollerStr;
        var newFollerStr;
        var followCount=parseFloat(item.followCount)/10000
        var  newfollower=Math.floor(parseFloat(item.newFllowCount)/10000)

        followCount=Math.floor(followCount)

        if(newfollower>0){
            newFollerStr=newfollower+"w+"
        }else{
            newFollerStr=item.newFllowCount
        }
        if(followCount>0){
            flollerStr=followCount+"w+"

        }else{
            flollerStr=item.followCount
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

            '<td class="nickname">'+item.nickName+'</td>'+
            '<td>'+flollerStr+'</td>'+

            '<td>'+newFollerStr+'</td>'+
            '<td>'+item.rateActivity+'</td>'+
            '<td>'+item.interactNum+'</td>'+


            '</tr>'
        html+=sequenceHtml
        sequence++;
    })
    body.append(html)
}

function changeDate(year1,month1){

    year=year1
    month=month1
    $.each(arr,function(index,item){
        var data=loadData(1,10,item.value)

        bindWxData($("#"+item.key+""),data);
    })
}

$(function(){


    changeDate(year,month)




})