
//生成运营趋势图标与文章发布时段图表

function createFrame(data){
    var frameChat = echarts.init(document.getElementById('frame'))
    var frameArr=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
    // for(var i = 0 ; i<data.items.publishtrend.articleNumPerHour.length;i++){
    //     frameArr[i] = i
    // }
    frameOption = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : frameArr,
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'发布文章数',
            type:'bar',
            barWidth: '50%',
            data:data
        }
    ]
};
frameChat.setOption(frameOption)

}



function createTendency(statistic){
    var tendencyChat = echarts.init(document.getElementById('tendency'))
    option = {
        legend: {
            data:['阅读数(千次)','点赞数(次)','文章数(篇)']
        },
        tooltip: {
            trigger: 'axis',
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        xAxis: {
            type: 'category',
            axisLine: {onZero: false},
            axisLabel: {
                formatter: '{value}'
            },
            boundaryGap: false,
            data: statistic.days
        },
        series: [
            {
                name: '阅读数(千次)',
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                },
                data:statistic.readNum
            },
            {
                name: '点赞数(次)',
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                },
                data:statistic.likeNum
            },
            {
                name: '文章数(篇)',
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                },
                data:statistic.articleNum
            }
        ]
    };
    tendencyChat.setOption(option)

}
/**
 * 请求文章详情页数据
 * @returns  详情页
 */
function ajaxDetailData() {
        var data1
        $.ajax({
            url:"/DataStory/wx/detail/get",
            type:'get',
            async:false,
            success:function (data) {
                data1 = data
            }
        })
        return data1
}

function bindOperationIndex(userInfo) {
    $("#articlesNum").html(userInfo.articlesNum)
    $("#totalReadNum").html(userInfo.totalReadNum)
    $("#avgReadNum").html(userInfo.avgReadNum)
    $("#totalLikeNum").html(userInfo.totalLikeNum)
    $("#avgLikeNum").html(userInfo.avgLikeNum)
    $("#avgHeadlineNum").html(userInfo.avgHeadlineNum)
    $("#activeNum").html(userInfo.activeNum)
    $("#qualityNum").html(userInfo.qualityNum)
    $("#influenceNum").html(userInfo.influenceNum)
    
}

function bindHead(userInfo) {
    $("#wxName").html(userInfo.wxName)
    $("#descrition").html(userInfo.descrition)
    $("#gongzhongimg").attr('src',userInfo.headPicture)
    var qrImg = 'http://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz='+userInfo.wxBiz
    $("#qrcode").attr('src',qrImg)
    //headPicture and two
}

function bindArticleList(articles,number) {
    var html = ''
    $.each(articles,function (index,item) {
        number = number+1
        var seriaHtml=''
        if (number<4){
            seriaHtml='<td><span class="serial">'+number+'</span></td> '
        }else {
            seriaHtml= '<td><span class="serial-common">'+number+'</span></td> '
        }
       html+=' <tr>'+
           seriaHtml +
           '<td>'+item.date+'</td>'+
           '<td><a target=\"_blank\" href=\"'+item.url+'\">'+item.title+'</a></td>'+
           '<td>'+item.readNum+'</td>'+
           '<td>'+item.likeNum+'</td>'+
           '</tr>'
    })
    $("#tbody-articles").empty()
    $("#tbody-articles").append(html)
    return number
    
}

function  bindStatistic(statistics) {
    var arr =new Array();
    $.each(statistics.readNum,function (index,item) {
        arr.push(item/1000)
    })
    statistics.readNum= arr
    createTendency(statistics)

}



function bindPageData(articles) {

    $('#pagination').pagination({
        dataSource: articles,
        pageSize: 20,
        pageNumber: 1,
        callback: function(data, pagination) {
       bindArticleList(data,(pagination.pageNumber-1)*pagination.pageSize)

    }
})

}
/**
 * when page loaded start to bind data
 */
$(function () {
    var obj = ajaxDetailData()
    bindOperationIndex(obj.userInfo)
    bindHead(obj.userInfo)
    createFrame(obj.articleNumPerHour)
    bindStatistic(obj.articleStatistics)
    bindPageData(obj.articles)
})


