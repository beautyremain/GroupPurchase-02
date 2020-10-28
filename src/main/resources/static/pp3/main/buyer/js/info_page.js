
function getTime(date) {
	return date.getFullYear()+"-"+(date.getMonth()+1<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+date.getDate()
}
var tabData="";//定义一个空变量用来存放数据
var user="";
//ajax获取数据
$.ajax({
        async:false,
        url:'http://localhost:8021/buyer/selfinfo',
        data:{
        	"identity":"buyer"
		},
        type:'get',
        success:function(data){
        	console.log(data)
			if(typeof (data.info)=="undefined"){
				let d=JSON.parse(data);
				console.log(d)
				if(d.state=="fail") {
					alert("请先登录");
					window.location.href = d.content
				}
			}
        	tabData=data.info;
        	user=data.user;
        }
});
//页面加载完成后执行
$(function(){
//判断是否加载失败：
		var str="<h1>Dear<span> "+user+" </span>,welcome back</h1><a href='http://localhost:8021/log/logout'>注销登录</a>";
		$("#head_left").html(str);
		//请求数据，并执行方法将字符串转换成数组
    	fillTable(tabData);
})
function fillTable(info){

	console.log("info:"+info);
//调用方法：打印表格
    initHtml(info);
}
//方法：打印表格
function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	console.log(arr[0]);
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>您还未购买任何商品</p>"
		$('#warndiv').html(str);
		console.log("in");
		return;
	}
    for(let column of arr){
        str+='<tr>';

        for(let i in column){
			if(i=="flag")
			{	
				if(column[i]=="0")
					str+=`<td style="color: red">`+'未支付'+`</td>`;
				if(column[i]=="1")
					str+=`<td style="color: green">`+'已支付'+`</td>`;
			}
			else if(i=="date"){
				str+=`<td>`+getTime(new Date(column[i]))+`</td>`;
			}
			else
			{
				str+=`<td>`+column[i]+`</td>`;
			}

        }
        str+=`</tr>`;
    }
    $('#tab').html($oldhtml+str);
}