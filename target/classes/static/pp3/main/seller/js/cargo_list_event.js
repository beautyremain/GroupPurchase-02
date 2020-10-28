function show(){
	$("#new_cargo").removeAttr("hidden");
}
function show_edit(btn){
	btn.previousElementSibling.removeAttribute("hidden");	
}
function edit1(btn)
{
	var new_text=btn.previousElementSibling.value;
	btn.parentElement.nextSibling.nodeValue=new_text;
	btn.parentElement.parentElement.style.color="red";
	btn.parentElement.setAttribute("hidden","true");
}
function cancel1(btn)
{
	btn.previousElementSibling.previousElementSibling.value="";
	btn.parentElement.setAttribute("hidden","true");
}
function cancel2(btn)
{
	btn.parentElement.setAttribute('hidden','true');
	var form=btn.previousElementSibling;
	for(var each of form.children)
	{
		if(each.value!="提交")
			each.value="";
	}
}
function give_up()
{
	location.reload();
}
function confirmEvent(btn)
{
	var table=$('#tab');
	var title=table[0].children[1].children[0];
	var data={};
	var tr=btn.parentElement.parentElement;
	var time=new Date();
	data["date"]=time.toLocaleDateString();
	for(var i=0;i<title.childElementCount-1;i++)
	{
		if(tr.children[i].childNodes[1].nodeValue!="无")
			data[title.children[i].textContent]=tr.children[i].childNodes[1].nodeValue;
		else
		{
			data[title.children[i].textContent]=null;
		}

	}
//						if(tr.children[2].textContent-count<0)
//						{
//							alert("购买量超出剩余值");
//							return;
//						}
	if(confirm("确认要修改该商品信息吗？"))
	{

		for(key in data)
		{
			console.log(key+":"+data[key]);
		}
	$.post("./ca_seller_edit.php",data,function(d){
		alert(d);
		location.reload();
	});
	}
}
function review()
{
	var form_div=$("#new_cargo");
	var f=form_div[0].children[0];
	var str=["商品名：","数量：","价格：","备注："];
	var s="";
	for(var i=0;i<4;i++)
	{
	    s+=str[i]+f.children[i].value+",";
	}

    return confirm("确定提交 "+s+"?");
}
