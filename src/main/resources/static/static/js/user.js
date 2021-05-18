/**
 * 用户页面js
 */

layui.use('element', function() {
	var element = layui.element;
});
layui.use('layer', function(){
  	var layer = layui.layer;
  	
  	$("#out").click(function(){
  		layer.confirm("确定退出", { 
			btn:['确定', '取消']
		},function(){
			window.location.href="logout1";
		});
  	});
});
function borrow(str){
	layer.confirm("确定借阅此书", { 
		btn:['确定', '取消']
	},function(){
		$.post("book/borrow", {id: str}, function(date){
			layer.confirm("已提交审核!", { 
				btn:['确定']
			},function(){
				parent.location.reload();
			});
		});
	});
}

layui.use('form', function(){
  	var form = layui.form;
  
	form.on('submit(formDemo)', function(data){
	    return false;
	});
	
	var obj;
	
	//修改密码验证
	form.verify({
		pwd: function(value){
			$.post('pwd', {password: value}, function(data){
				if(!data){
					layer.msg('原密码输入错误', {icon: 2});
				}
			});
		},
		pwd1: function(value){
			obj = value;
		},
		pwd2: function(value){
			if(obj != value){
				layer.msg('两次密码不一致', {icon: 2});
			}else{
				layer.confirm("修改成功！", {
					btn:['确定']
				}, function(){
					window.location.href="xg?pwd="+value;							 
				});
			}
		}
	});
});

$(function(){
	$("#book_searchbtn").click(function(){
		var str = $("#keyword").val();
		$("#home_book").load("keyword?str="+str);
	});
	
	$("#book_clearbtn").click(function(){
		$("#keyword").val("");
		$("#home_book").load("keyword?str="+"");
	})
});
