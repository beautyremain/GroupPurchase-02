var tabData="";//定义一个空变量用来存放数据
//ajax获取数据
$.ajax({
        async:false,
        url:'./check_ta.php',
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
    initVar();
//判断是否加载失败：
	if(tabData=="forbidden")
	{
		var str="<h1>请先登录！</h1> <a href='../../log/log_in.php'>登录</a>"
		$("#head_left").html(str);
	}
	else if(tabData=="wrong")
	{
		var str="<h1>该页面禁止访问，请重新登录</h1> <a href='../../log/log_in.php'>登录</a>"
		$("#head_left").html(str);
	}
	else
	{
		//分离用户名
		var name=tabData.split(":");
		var str="<h1>Dear<span> "+name[0]+" </span>,welcome back</h1><a href='../../log/log_out.php'>注销登录</a>";
		$("#head_left").html(str);
		//请求数据，并执行方法将字符串转换成数组
    	StrToArr(name[1]);
    }
})
//方法：初始化变量
function initVar(){
    tabDom=$('#tab');
}
//方法：字符串转数组
function StrToArr(str){
	console.log("php:"+str);
//将在php里加的“，”和“^”去掉，并转成数组
    var arr=str.split('^');
    var brr=[];
    for(var i in arr){
        brr.push(arr[i].split(','));
    }
    
//	console.log(brr[0]);
//调用方法：打印表格
    initHtml(brr);
}
//方法：打印表格
function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	console.log(arr[0]);
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>没有任何修改记录</p>"
		$('#warndiv').html(str);
		return;
	}
    for(var i in arr){
        str+='<tr>';
        for(var j in arr[i]){
			if(j==arr[i].length-1)
			{	
				if(arr[i][j]=="1")
					str+=`<td style="color: green;">`+'已通过'+`</td>`;
				else if(arr[i][j]=="2")
					str+=`<td style="color: red;">`+'已拒绝'+`</td>`;
				else if(arr[i][j]=="3")
					str+=`<td class="tlast"><button id="test" onclick="check(this,1,0)">通过</button><button style="margin-left: 20px;" id="test2" onclick="check(this,2,0)">拒绝</button></td>`;
			}
			else
			{
				str+=`<td>`+arr[i][j]+`</td>`;
			}
        }
        str+=`</tr>`;
    }
    $('#tab').html($oldhtml+str);
}