把right的样式固定，table丢到一个div里去，然后给div加scroll。
思考一下数据库里的存储形式，调用有点复杂。
如果按照现在的写法 需要多少个页面？
	！！要求name+seller唯一！！
	1.买家历史信息页面。
	date name count seller tips flag
	2.买家购买页面。
	seller remain price tips <confirm>


	在checking表中，flag=0为提交新货物，flag=1为修改货物。
		
	4.卖家提交新货物信息。
	name remain price tips seller(由后台按照session提交)

	5.卖家审查所有订单。
	此处应当有两个table：
	table1：
	有关此卖家的交易流：
	date buyer name address (dont forget this address!) price tips 确认付款按钮。

	table2：
	此卖家上传的所有商品：
	date name remain price tips 所以说这里还缺一个flag 判断是否停止出售 卖完了自动判否；  
	应当有三个按钮 停止出售（已停止后改为继续出售） 修改（物价\剩余数量\备注）和 修改后提交。

	

	7.管理员审查信息流。
	date buyer seller name count
	8.管理员审核修改/提交信息。
	seller name price remain tips 确认按钮
	

	要求卖家注册必须经过审核。名字为3个字+联系方式
	后期还可以加上卖家的联系方式。
	
	#3.15任务：
	加上修改cargo_list 的代码 
	然后把session加进去 把提交的名字改成session_name，info查询改成select from where name=name；
	
	#晚上来把各个页面的css稍微调整一下。然后就是没有购买物资的时候，个人订单页面的表格要设置一下。
	

	注意：应当添加历史纪录页面，把已付款，已完成的订单流放在一起。
	
	seller_cargo功能：每一个均可修改 物资数量、价格和备注，。有修改按钮（可与文本在同个td），有提交键；td旁有删除该货物；列表下有提交新商品按钮，弹出信息填写框。
	
	checking_cargo
	正在checking的列表，正在checking 下面接着历史checking的记录 3：正在处理 2：已拒绝 1：已通过。
	
	#把检查字符串的php写到函数里去。
	
	要在session里加入用户种类的认证。
	#check出现问题！！！


可以加个买家看地址的地方，不然有点蠢
	