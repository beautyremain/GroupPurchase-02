var tabData="";
$.ajax({
	async:false,
	url:'http://localhost:8021/seller/head',
	data:{
		"identity":"seller"
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
function new_pwd(btn)
{
	var data={};
	data['npwd']=$('input[type=text][name=pwd]').val();
	$.get("http://localhost:8021/seller/change",data,function(e){
		alert("back:"+e);
		if(e=="success")
		{
			setTimeout("location.reload()",2000);
		}
	});
	
}