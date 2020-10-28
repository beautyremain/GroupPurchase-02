var tabData="";//定义一个空变量用来存放数据
//ajax获取数据
$.ajax({
        async:false,
        url:'./getHead.php',
        data:{},
        type:'get',
        dataType:'text',
        success:function(data){
        tabData=data;
        }
});
//页面加载完成后执行
$(function(){
//初始化变量
    tabDom=$('#tab');
//判断是否加载失败：
	if(tabData=="forbidden")
	{
		var str="<h1>请先登录！</h1> <a href='../../log/seller_in.php'>登录</a>"
		$("#head_left").html(str);
	}
	else if(tabData=="wrong")
	{
		var str="<h1>请以卖家身份登录该界面</h1> <a href='../../log/seller_in.php'>登录</a>"
		$("#head_left").html(str);
	}
	else
	{
		//分离用户名
		var name=tabData;
		var str="<h1>Dear<span> "+name+" </span>,welcom back</h1><a href='../../log/log_out.php'>注销登录</a>";
		$("#head_left").html(str);
		//请求数据，并执行方法将字符串转换成数组
    }
})

function new_pwd(btn)
{
	var data={};
	data['npwd']=$('input[type=text][name=pwd]').val();
	$.get("../change_info.php",data,function(e){
		alert("php:"+e);
		if(e=="success")
		{
			setTimeout("location.reload()",2000);
		}
	});
	
}