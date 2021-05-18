/**
 * datagrid封装js
 */

$.extend({
	'crudgrid':{
		'getGrid' :function(gridBtn){
			//获取当前表格
			return $(gridBtn).parent().closest('div.datagrid-wrap').children('div.datagrid-view').children('table.datagrid-f');
		},
		//定义表格上方按钮
		'buttons':{
			'create':{
				text: '新增' ,
				iconCls : 'icon-add',
				handler: function(){   //点击事件
					//获取表格
					var $table=$.crudgrid.getGrid(this);
					//创建一个div层
					var $dialog=$('<div id=dialog />');
					//打开div层
					$dialog.appendTo($table.parent());
					//options返回属性对象
					var $options = $table.datagrid('options');
					var $param={
						title: '新增',
						modal: true,
						width: 300,
						href: 'edit',
						grid: $table,   //把表格和弹出层绑定在一起
						onClose: function(){  //面板关闭之后触发。
							//返回外部对话框窗口对象。摧毁
							$dialog.dialog('destroy');
						},
						//定义弹框下面的三个按钮，关闭，清空，保存
						buttons: [$.dialog.buttons.save, $.dialog.buttons.reset, $.dialog.buttons.close]
					};
					
					if($options.dialog){   //是否有这个属性值,有就替换
						$param = $.extend($param, $options.dialog.create);
					}
					$dialog.dialog($param);
				}
			},
			'update':{
				text: '编辑',
				iconCls: 'icon-edit',
				handler: function(){
					//获取表格
					var $table = $.crudgrid.getGrid(this);
					//获取当前行
					var row = $table.datagrid('getSelected');
					//判断当前行是否选中
					if(row == null){
						$.messager.alert({
							title: '提示',
							msg: '请选择一行，再进行编辑操作',
							icon: 'info'
						});
						return ;
					}
					//创建一个div层
					var $dialog = $('<div id = dialog />');
					//打开创建的div层
					$dialog.appendTo($table.parent());
					//options返回属性对象
					var $options = $table.datagrid('options');
					var $param={
							title: '编辑',
							width: 300,
							href: 'update',
							modal: true,
							grid: $table,
							//提交时传递参数
							queryParams: {id: row.id},  //当前行的id
							onClose: function(){    //在面板关闭之后触发该函数
								$dialog.remove();   //删除
							},
							//定义下面三个按钮
							buttons: [$.dialog.buttons.save, $.dialog.buttons.reset, $.dialog.buttons.close]
					};
					
					if($options.dialog){   //是否有这个属性值,有就替换
						$param = $.extend($param, $options.dialog.update);
					}
					$dialog.dialog($param);
				}
			},
			'delete':{
				text: '删除',
				iconCls: 'icon-remove',
				handler: function(){
					//获取当前表格
					var $table = $(this).parent().closest('.panel').find('.datagrid-f');
					//获取当前选中的行
					var row = $table.datagrid('getSelected');
					if(row == null){
						$.messager.alert({
							title: '提示' ,
							msg: '请先选中一行，再进行删除操作！' ,
							icon : 'info'
						});
						return ;
					}
					//获取表格
					var $table = $.crudgrid.getGrid(this);
					//options返回属性对象
					var $options = $table.datagrid('options');
					//获取删除路径
					var $delete = $options.dialog.delete.url;
					//确认框
					$.messager.confirm('确认对话框', '您想要删除选中的数据吗？', function(r){
						if(r){
							//发送到后台
							$.post($delete!=undefined?$delete:'delete', {id: row.id}, function(result){
								if(result.success){
									$.messager.show({
										title: '提示',
										msg: '操作成功',
										icon: 'info' 
									});
									//刷新表格
									if($table.data('treegrid')){
										$table.treegrid('reload');
										$("#studnet_tree").tree('reload');
										$table.treegrid('clearSelections');
									}else{
										$table.datagrid('reload');
										$table.datagrid('clearSelections');
									}
								}else{
									$.messager.alert({
										title:'提示',
										msg: '删除失败',
										icon: 'error'
									});
								}
							})
						}
					});
				}
			},
			'reset':{
				text: '重置密码',
				iconCls: 'icon-save',
				handler: function(){
					//获取当前表格
					var $table = $(this).parent().closest('.panel').find('.datagrid-f');
					//获取当前选中的行
					var row = $table.datagrid('getSelected');
					if(row == null){
						$.messager.alert({
							title: '提示' ,
							msg: '请先选中一行，再进行重置密码操作！' ,
							icon : 'info'
						});
						return ;
					}
					//获取表格
					var $table = $.crudgrid.getGrid(this);
					//options返回属性对象
					var $options = $table.datagrid('options');
					//获取删除路径
					var $reset = $options.dialog.reset.url;
					//确认框
					$.messager.confirm('确认对话框', '您确定重置选中用户的密码吗？', function(r){
						if(r){
							//发送到后台
							$.post($reset!=undefined?$reset:'reset', {id: row.id}, function(result){
								if(result.success){
									$.messager.show({
										title: '提示',
										msg: '操作成功',
										icon: 'info' 
									});
									//刷新表格
									$table.datagrid('reload');
								}else{
									$.messager.alert({
										title:'提示',
										msg: '删除失败',
										icon: 'error'
									});
								}
							})
						}
					});
				}
			},
			'reload':{
				text:'刷新' ,
				iconCls: 'icon-reload',
				handler: function(){
					var $grid=$.crudgrid.getGrid(this);
					if($grid.data('treegrid')){
						$grid.treegrid('unselectAll');
						$grid.treegrid('reload');
					}else{
						$grid.datagrid('unselectAll');
						$grid.datagrid('reload');
					}
				}
			}
		}
	}
});

$.extend({
	'dialog': {   //定义对话框按钮
		'me': function(dialogBtn){
			//选中弹出的对话框中的文本框
			return $(dialogBtn).closest('.window').find('div.window-body');
		},
		'getForm': function(dialogBtn){
			//判断文本框值是否为空
			return $(dialogBtn == undefined ? this: dialogBtn).parent().closest('.window').find('form');
		},
		'buttons':{
			'reset': {
				text: '重置',
				iconCls: 'icon-reload',
				handler: function(){
					//获取form表单
					var $form = $.dialog.getForm(this);
					//重置文本框
					$form.form('reset');
				}
			},
			'save': {
				text: '保存',
				iconCls: 'icon-ok',
				handler: function(){
					$this=$(this);
					//按钮禁用
					$this.linkbutton('disable');
					//打开的层
					var $dialog=$.dialog.me(this);
					//获取form表单
					var $form=$.dialog.getForm(this);
					//获取表格
					var $table=$.dialog.me(this).dialog('options').grid;
					//获取保存的值
					var $action = $dialog.dialog('options').action;
					//获取form表单中所有文本框的值
					var fd = new FormData($form[0]);
					if($form.form('validate')){
						$.ajax({
							url:$action?$action:'save',
							cache: false , 
							type : 'post',
							data : fd ,
							dataType : 'json' ,
							processData: false,   // 不处理数据
							contentType: false,   // 不设置内容类型
							success : function(result){
								if(result.success){
									$.messager.show({
										title: '提示'  ,
										msg : '操作成功' ,
										icon : 'info' 
									});
									//关闭窗口
									$dialog.dialog('close');
									//刷新表格
									if($table.data('treegrid')){
										$table.treegrid('reload');
										$("#studnet_tree").tree('reload');
									}else{
										$table.datagrid('reload');
									}
								}else{
									//取消按钮禁用，不能点击
									$this.linkbutton('enable');
									$.messager.alert({
										title: '提示'  ,
										msg : '保存失败' ,
										icon : 'error'
									});
								}
							}
						});
					}else{
						$.messager.alert({
							title:'提示',
							msg: '验证没通过',
							icon: 'error'
						});
						$this.linkbutton('enable');
					}
				}
			},
			'close':{
				text: '关闭',
				iconCls: 'icon-cancel',
				handler: function(){
					//关闭
					$.dialog.me(this).dialog('close');
				}
			}
		}
	}
});

//js方法，序列化表单
function serializeForm(form){
	var obj = {};
	$.each(form.serializeArray(), function(index){
		if(obj[this['name']]){
			obj[this['name']] = obj[this['name']] + ',' + this['value'];
		}else{
			obj[this['name']] = this['value'];
		}
	});
	return obj;
}
