var tabData="";
var id_info=[];
$.ajax({
        async:false,
        url:'./ca_seller_ta.php',
        data:{},
        type:'get',
        dataType:'text',
        success:function(data){
        tabData=data;
        }
});
$(function(){
    initVar();
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
		var name=tabData.split(":");
		
		var str="<h1>Dear<span> "+name[0]+" </span>,welcome back</h1><a href='../../log/log_out.php'>注销登录</a>";
		$("#head_left").html(str);
		//请求数据，并执行方法将字符串转换成数组
    	StrToArr(name[1]);
    }
})
function initVar(){
    tabDom=$('#tab');
}
function StrToArr(str){
    var arr=str.split('^');
    var brr=[];
    for(var i in arr){
        brr.push(arr[i].split(','));
    }
    console.log(brr[0]);
    initHtml(brr);
}
function initHtml(arr){
	var $oldhtml=$('#tab').html();
	var str="";
	if(arr.length==1&&arr[0]=="")
	{
		str+="<p>您没有任何商品在售</p>"
		$('#warndiv').html(str);
		console.log("in");
		return;
	}
    for(var i in arr){
        str+='<tr>';
        for(var j=0;j<arr[i].length;j++){
        	if(j==0)
            	str+=`<td><div></div>`+arr[i][j]+`</td>`;
            else if(arr[i][j]!="")
            {
            	str+=`<td><div hidden="" class="edit_div"><input placeholder="请输入新的内容..." type="text"/><button onclick="edit1(this)">确认</button><button onclick="cancel1(this)">取消</button></div>`+arr[i][j]+`<button class="modi" onclick="show_edit(this)">修改</button></td>`;           	
            }
            else if(arr[i][j]=="")
            {
            	str+=`<td><div hidden="" class="edit_div"><input placeholder="请输入新的内容..." type="text"/><button onclick="edit1(this)">确认</button><button onclick="cancel1(this)">取消</button></div>无<button class="modi" onclick="show_edit(this)">修改</button></td>`;           	
            }
        }
        str+=`<td>
        <button class="confirm" onclick="confirmEvent(this)">上传更改</button>
        </td>`;
        str+=`</tr>`;
    }
    $('#tab').html($oldhtml+str);
	//var btns=$('#tab .purchase-btn');
    //for(var i=0;i<btns.length;i++)
    //{
    //	this.onclick=pbtnEvent(this);
    //}
}