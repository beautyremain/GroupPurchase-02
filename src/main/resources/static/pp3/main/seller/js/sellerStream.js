var tabData="";
var id_info=[];
function getTime(date) {
	return date.getFullYear()+"-"+(date.getMonth()+1<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+date.getDate()
}
$.ajax({
	async:false,
	url:'http://localhost:8021/seller/streaminfo',
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
	var data={flag:1};
	var tr=btn.parentElement.parentElement;
	var id=tr.getAttribute('i');
	data[title.children[2].textContent]=tr.children[2].textContent;
	data['id']=id;
	if(confirm("确认该用户已支付？"))
	{
		$.post("http://localhost:8021/seller/payCheck",data,function(d){
			alert(d);
			if(d=="已确认付款，请及时送货")
			{
				location.reload();
			}
		});
	}
}

function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>您未出售任何商品</p>"
		$('#warndiv').html(str);
		console.log("in");
		return;
	}
    for(var i in arr){
        str+='<tr i='+arr[i]["id"]+'>';
        for(var j in arr[i]){
        	if(j=="flag"){
				str+=`<td>`+arr[i]["cargo_count"]*arr[i]["price"]+`</td>`;
				if(arr[i][j]=="0")
					str+=`<td>`+'<button class="purchase-btn" onclick="pbtnEvent(this)" >确认付款</button>'+`</td>`;
				if(arr[i][j]=="1")
					str+=`<td>`+'已支付'+`</td>`;
			}
        	else if(j=="date"){
				str+=`<td>`+getTime(new Date(arr[i][j]))+`</td>`;
			}
        	else if(j=="id"){
        		continue;
			}
            else {
				str+=`<td>`+arr[i][j]+`</td>`;
            }
        }
//      str+=`<td>
//      
//      </td>`;
        str+=`</tr>`;
    }
    $('#tab').html($oldhtml+str);
	//var btns=$('#tab .purchase-btn');
    //for(var i=0;i<btns.length;i++)
    //{
    //	this.onclick=pbtnEvent(this);
    //}
}