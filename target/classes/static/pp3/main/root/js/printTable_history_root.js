function getTime(date) {
	return date.getFullYear()+"-"+(date.getMonth()+1<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+date.getDate()
}
var tabData="";
$.ajax({
	async:false,
	url:'http://localhost:8021/root/history_stream',
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

function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>目前没有任何数据流</p>"
		$('#warndiv').html(str);
		console.log("in");
		return;
	}
	for(var i in arr){
		str+=`<tr>`;
		for(var j in arr[i]){
			if(j=="c_flag"){
				if(arr[i][j]=="1")
					str+=`<td style="color: green;">`+'已通过'+`</td>`;
				else if(arr[i][j]=="2")
					str+=`<td style="color: red;">`+'已拒绝'+`</td>`;
				else if(arr[i][j]=="3")
					str+=`<td class="tlast"><button id="test" onclick="check(this,1,0)">通过</button><button style="margin-left: 20px;" id="test2" onclick="check(this,2,0)">拒绝</button></td>`;
			}
			else if(j=="date"){
				str+=`<td>`+getTime(new Date(arr[i][j]))+`</td>`;
			}
			else if(j=="id"){
				continue
			}
			else{
				str+=`<td>`+arr[i][j]+`</td>`;
			}
		}
		str+=`</tr>`;
	}
	$('#tab').html($oldhtml+str);

}