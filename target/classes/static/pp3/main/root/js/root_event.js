function search()
{
	console.log("in");
	var input=$('#condition').val();
	var data={};
	data['condition']=input;
	data['user_name']="root";
	data['identity']="root";
	console.log("js:"+input);
	$.get("http://localhost:8021/root/search",data,function(data){
		console.log(data);
		newHtml(data.info);
	});
	
}
function newHtml(arr){
	console.log("arr.len="+arr.length)
	if(arr.length==0)
	{
		alert("查找无此结果");
		return;
	}
	var str="";
	console.log(arr[0]);
	for(var i in arr){
		str+=`<tr>`;
		for(var j in arr[i]){
			if(j=="flag"){
				if(arr[i][j]=="0")
					str+=`<td style="color:red;">`+'未支付'+`</td>`;
				if(arr[i][j]=="1")
					str+=`<td style="color:darkgreen;">`+'已支付'+`</td>`;
			}
			else{
				str+=`<td>`+arr[i][j]+`</td>`;
			}
		}
		str+=`</tr>`;
	}
    $('tbody').html(str);
}