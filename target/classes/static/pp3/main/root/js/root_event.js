function search()
{
	console.log("in");
	var input=$('#condition').val();
	var data={};
	data['str']=input;
	console.log("js:"+input);
	$.get("./search_ta.php",data,function(data){
		console.log(data);
			processData(data);
	});
	
}
function processData(str){
	if(str.length<3)
	{
		alert("查找无此结果");
		return;
	}
	if(str.indexOf("^")==-1)
	{
		alert(str);
		return;
	}
    var arr=str.split('^');
    var brr=[];
    for(var i in arr){
        brr.push(arr[i].split(','));
    }
    newHtml(brr);
}
function newHtml(arr){
	var str="";
	console.log(arr[0]);
    for(var i in arr){
        str+='<tr>';
        for(var j in arr[i]){
			if(j==arr[i].length-1)
			{	
				if(arr[i][j]=="0")
					str+=`<td style="color:red;">`+'未支付'+`</td>`;
				if(arr[i][j]=="1")
					str+=`<td style="color:darkgreen;">`+'已支付'+`</td>`;
			}
			else
			{
				str+=`<td>`+arr[i][j]+`</td>`;
			}
        }
        str+=`</tr>`;
    }
    $('tbody').html(str);
}