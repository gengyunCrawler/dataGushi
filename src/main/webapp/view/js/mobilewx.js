//获取数据
function loadData(currentPage,pageSize,categoryId){
    var wxData;
    $.ajax({
        url:'/DataStory/wx/data/'+currentPage+'/'+pageSize+'/'+categoryId,
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

            '<td class="nickname">'+item.wxName+'</td>'+
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
$(function(){

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
    $.each(arr,function(index,item){
        var data=loadData(1,10,item.value)

        bindWxData($("#"+item.key+""),data);
    })




})