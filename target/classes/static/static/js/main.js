/**
 * 管理员首页js
 */

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

//还书
function returnBook(str){
	layer.confirm("确定还书吗？", { 
		btn:['确定', '取消']
	},function(){
		$.post("book/huanshu", {id: str}, function(date){
			layer.confirm("还书成功！", { 
				btn:['确定']
			},function(index){
				var obj = $("#tt");
				var tab = obj.tabs('getTab', '还书');
				tab.panel('refresh', 'records/returnBook');
				layer.close(index);
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
	$("p[src]").click(function(){   
		var src = $(this).attr('src');
		var obj = $("#tt");
		var title = $(this).html();
		if(obj.tabs('exists', title)){
			var tab = obj.tabs('getTab', title);
			tab.panel('refresh', src);    //刷新页面
			obj.tabs('select', title); 
		}else{
			obj.tabs('add',{
				title: title,
				closable: true,
				href: src,
				tools: [{
					iconCls: 'icon-mini-refresh',
					handler: function(){
						var li = $(this).parent().parent();
						if(li.length > 0){
							var tab = $('#tt').tabs('getTab', li.eq(0).index());
							tab.panel('refresh');
						}
					}
				}]			
			});
		}
	});
	
	$("#navigation .easyui-tree").tree({
		onClick: function(node){
			if(node.text !=undefined){
				showTab('#tt', node.text, node.href, node.id);
			}
		}
	});
	
	$("#studnet_tree").tree({
		url: 'dept/dept_list'
		
	});
	
});

function showTab(container, title, src, id){
	var obj = $(container);
	if(obj.tabs('exists', title)){
		var tab = obj.tabs('getTab', title);
		tab.panel('refresh', src);    //刷新页面
		obj.tabs('select', title); 
	}else{
		if(src == "" || src == undefined){
			$.post("dept/node", {id: id}, function(data){
				if(data){
					obj.tabs('add',{
						title: title,
						closable: true,
						href: 'student/studentAll?id='+id,
						tools: [{
							iconCls: 'icon-mini-refresh',
							handler: function(){
								var li = $(this).parent().parent();
								if(li.length > 0){
									var tab = $('#tt').tabs('getTab', li.eq(0).index());
									tab.panel('refresh');
								}
							}
						}]			
					});
				}
			})
		}else{
			obj.tabs('add',{
				title: title,
				closable: true,
				href: src,
				tools: [{
					iconCls: 'icon-mini-refresh',
					handler: function(){
						var li = $(this).parent().parent();
						if(li.length > 0){
							var tab = $('#tt').tabs('getTab', li.eq(0).index());
							tab.panel('refresh');
						}
					}
				}]			
			});
		}
	}
}
