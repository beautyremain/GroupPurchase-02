function getTime(date) {
    return date.getFullYear()+"-"+(date.getMonth()+1<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+date.getDate()
}
var tabData="";
$.ajax({
    async:false,
    url:'http://localhost:8021/seller/seller_cargo',
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
        str+=`<tr>`;
        for(var j in arr[i]){
            if(j=="name")
                str+=`<td><div></div>`+arr[i][j]+`</td>`;
            else if(j=="seller"||j=="id"){

            }
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

}