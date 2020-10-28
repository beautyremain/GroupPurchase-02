var tabData="";//定义一个空变量用来存放数据
//ajax获取数据
$.ajax({
	async:false,
	url:'http://localhost:8021/buyer/changeInfo',
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
})

function new_info(btn,type)
{
	var data={};
	data[type]=$("input[type=text][name="+type+"]").val();
	console.log(data);
	if(confirm("确认修改"+type+"为"+data[type]+"?"))
	{
		$.get("http://localhost:8021/buyer/changeInfo",data,function(e){
			alert(e);
			if(e=="success")
			{
				setTimeout("location.reload()",2000);
			}
		});
	}
	
}