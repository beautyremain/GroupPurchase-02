var tabData="";
$.ajax({
	async:false,
	url:'http://localhost:8021/buyer/bookinfo',
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
function pbtnEvent(btn)
{
	var table=$('#tab');
	var title=table[0].children[1].children[0];
	var data={};
	var time=new Date();
	data["date"]=time.toLocaleDateString();
	var count=btn.nextElementSibling.nextElementSibling.textContent;
	var tr=btn.parentElement.parentElement;
	for(var i=0;i<title.childElementCount-1;i++)
	{
		data[title.children[i].textContent]=tr.children[i].textContent;

	}
	data['count']=count;
	if(tr.children[2].textContent-count<0)
	{
		alert("购买量超出剩余值");
		return;
	}
	if(confirm("确认要购买"+data['物资名']+data['count']+"斤吗？"))
	{

		for(key in data)
		{
			console.log(key+":"+data[key]);
		}
		$.post("http://localhost:8021/buyer/book_request",data,function(d){//这个后端接收端口要做
			alert(d);
			if(d=="购买请求提交成功！请尽快联系商家付款")
			{
				tr.children[2].textContent=tr.children[2].textContent-count;
			}
		});
	}
}
function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>目前没有任何商品在售</p>"
		$('#warndiv').html(str);
		console.log("in");
		return;
	}
    for(var i in arr){
        str+=`<tr>`;
        for(var j in arr[i]){
        	if(j=="id")
        		continue;
            str+=`<td>`+arr[i][j]+`</td>`;
        }
        str+=`<td>
        <button class="purchase-btn" onclick="pbtnEvent(this)" >购买</button>
        <button class="minus" onclick="minus(this)">-</button>
        <p class="count" style="disable:true;">1</p>
        <button class="plus" onclick="plus(this)">+</button>
        </td>`;
        str+=`</tr>`;
    }
    $('#tab').html($oldhtml+str);

}