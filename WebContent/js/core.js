/**
 * 
 */
Ext.require(['*']);
Ext.onReady(function() {
    
    var store = Ext.create('Ext.data.TreeStore', {
    	proxy : {
    		type : 'ajax',
    		url : 'connect!connectList.action',
    		reader :  {
    			type : 'json',
    			root : 'connects'
    		}
    	},
		root : {
			text :  'connects'
		}
	});
	
	var tree = Ext.create('Ext.tree.Panel', {
	    title: 'Connect List',
        region: 'west',
        width : 200,
        collapsible: true,
        split: true,
	    store: store,
	    rootVisible: false,
	    listeners: {
	        itemdblclick: function(view, record, item, index, e, eOpts){
	        	viewDomain(record, tabs);
	        }
	    },
        tbar : [{
        	xtype : 'button',
        	text : 'New',
        	handler : function(){
        		var form = Ext.create('Ext.form.Panel',{
        			region : 'center',
        			url : 'connect!edit.action',
        			items : [{
        				xtype : 'combo',
        				store : new Ext.data.SimpleStore({
        					fields : ['text', 'value'],
        					data : [[
        						'QEMU/KVM', 'qemu'
        					]]
        				}),
        				displayField : 'text',
        				valueField : 'value',
        				editable : false,
        				fieldLabel : 'Hypervisor',
        				name : 'hypervisor'
        			}, {
        				xtype : 'checkboxfield',
        				fieldLabel : 'Connect to remote host',
        				name : 'remote'
        			}, {
        				xtype : 'combo',
        				store : new Ext.data.SimpleStore({
        					fields : ['text', 'value'],
        					data : [[
        						'SSH', 'SSH'
        					]]
        				}),
        				fieldLabel : 'Method',
        				name : 'method',
        				editable : false,
        				disabled : true
        			}, {
        				xtype : 'textfield',
        				fieldLabel : 'Username',
        				name : 'username',
        				disabled : true
        			}, {
        				xtype : 'textfield',
        				fieldLabel : 'HostName',
        				name : 'hostname',
        				disabled : true
        			}, {
        				xtype : 'checkboxfield',
        				fieldLabel : 'Autoconnect',
        				name : 'auto'
        			}, {
        				xtype : 'textfield',
        				fieldLabel : 'Generated URI',
        				value : 'qemu:///system',
        				editable : false,
        				name : 'connect'
        			}],
        			buttons : [{
        				text : 'Save',
        				handler : function(){
        					var form = this.up('form').getForm();
        					
        					form.findField('connect').setValue(form.findField('hypervisor').value + ':///system');
        					
        					form.submit({
        						success : function(form, action){
        							tree.getStore().load();
        							win.close();
        						},
        						failure: function(form, action) {
        						}
        					});
        				}
        			}, {
        				text : 'Cancel',
        				handler : function(){win.close();}
        			}]
        		});
        		
        		var win = Ext.create('Ext.Window',{
        			title : 'New Connect',
        			height : 300,
        			width : 400,
        			closable : true,
        			autoShow : true,
        			layout : {type : 'border'},
        			items : [form]
        		});
        	}
		}, {
        	xtype : 'button',
        	text : 'Edit'
		}, {
        	xtype : 'button',
        	text : 'Del',
        	handler : function(){
        		var modes = tree.getSelectionModel().getSelection();
        		
        		if(modes && modes.length > 0){
        			Ext.Ajax.request({
        				url : 'connect!edit.action',
        				params : {
        					id : modes[0].get('id')
        				},
        				success : function(data){
        					Ext.Msg.alert('warning', 'success');
        					tree.getStore().load();
        				}
        			});
        		}
        		else{
        			Ext.Msg.alert('Warning', 'You must choose one.');
        		}
        	}
		}]
	});
	
	var tabs = Ext.createWidget('tabpanel', {
		activeTab : 0,
        region: 'center',
		items : [{
			title : 'Summary'
		}]
	});
    
    Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 5
        },
        defaults: {
            split: true
        },
        items: [{
            region: 'north',
            collapsible: true,
            title: 'North',
            split: true,
            height: 100,
            minHeight: 60,
            html: 'Virtual Machine Manager'
        },tree,
        tabs]
    });
});

function control(grid ,state){

	if(!grid)return;
	
	var records = grid.getSelectionModel().getSelection();
	
	if(!records || records.length == 0){
		Ext.Msg.alert('Warning', 'At least need one.');
		return;
	}
	
	var url = records[0].get('url');
	var name = records[0].get('name');
	
	Ext.Ajax.request({
		url : 'virt!control.action',
		params : {
			url : url,
			name : name,
			state : state
		},
		success : function(data){
			var result = Ext.JSON.decode(data.responseText);
	        var state = result.state;
	        
	        if(state == 0){
				Ext.Msg.alert('Warning', result.msg);
	        }else if(state == -1){
				Ext.Msg.alert('Warning', result.msg);
	        }
	        
			grid.getStore().load();
		}
	});
}

/**
 * 查看某个连接下的虚拟机
 */
function viewDomain(record, tabs){
	var node = record.data;
	var url = node.text;
	
	Ext.Ajax.request({
		url : 'virt.action',
		params : {
			url : node.text
		},
		success : function(data){
			var host = Ext.JSON.decode(data.responseText).host;
			
			var store = Ext.create('Ext.data.Store', {
			    fields:['id', 'name', 'autoStart', 'maxMemory', 'osType', 'uuidString', 'state', 'url'],
			    proxy: {
			        type: 'ajax',
			        url : 'virt!domains.action',
	        		params : {
	        			url : url
	        		},
			        reader: {
			            type: 'json',
			            root: 'domains'
			        }
			    },
			    autoLoad : true
			});
			
			var grid = Ext.create('Ext.grid.Panel', {
				store : store,
				tbar : [{
					xtype : 'button',
					text : 'New',
					handler : function(){
						new_virtual.create(url, grid.getStore().load());
					}
				}, {
					xtype : 'button',
					text : 'Edit',
					handler : function(){
					}
				}, {
					xtype : 'button',
					text : 'Del',
					handler : function(){control(grid, 6);}
				}, {
					xtype : 'button',
					text : 'Start',
					handler : function(){control(grid, 1);}
				}, {
					xtype : 'button',
					text : 'Pause',
					handler : function(){control(grid, 5);}
				}, {
					xtype : 'button',
					text : 'Resume',
					handler : function(){control(grid, 4);}
				}, {
					xtype : 'button',
					text : 'Stop',
					handler : function(){control(grid, 2);}
				}, {
					xtype : 'button',
					text : 'Rstart'
				}, {
					xtype : 'button',
					text : 'Status',
					handler : function(){
						var records = grid.getSelectionModel().getSelection();
						
						if(!records || records.length == 0){
							Ext.Msg.alert('Warning', 'At least need one.');
							return;
						}
						
						var url = records[0].get('url');
						var name = records[0].get('name');
						
						status_core.show({
							name : name,
							url : url
						});
					}
				}, {
					xtype : 'button',
					text : 'Reload',
					handler : function(){
						store.load();
					}
				}],
				columns : [{
					header : 'id', dataIndex : 'id', hidden : true
				}, {
					header : 'uuid', dataIndex : 'uuidString', width : 250
				}, {
					header : 'name', dataIndex : 'name'
				}, {
					header : 'autoStart', dataIndex : 'autoStart'
				}, {
					header : 'maxMemory', dataIndex : 'maxMemory'
				}, {
					header : 'osType', dataIndex : 'osType'
				}, {
					header : 'state', dataIndex : 'state'
				}, {
					header : 'url', dataIndex : 'url'
				}]
			});

        	var form = Ext.create('Ext.form.Panel', {
        		title : host.name + "-" + node.text,
        		defaultType : 'textfield',
        		closable : true,
        		items : [{
        			fieldLabel : 'name',
        			value : host.name
        		},{
        			fieldLabel : 'type',
        			value : host.type
        		},{
        			fieldLabel : 'cpus',
        			value : host.cpus
        		},{
        			fieldLabel : 'mhz',
        			value : host.mhz
        		},{
        			fieldLabel : 'model',
        			value : host.model
        		},{
        			fieldLabel : 'memory(kb)',
        			value : host.memory
        		}, grid]
        	});
        	
        	var items = tabs.items;
        	
        	if(items){
        		for(var i = 0; i < items.getCount(); i++){
        			if(items.getAt(i).title == form.title){
	        			tabs.setActiveTab(items.getAt(i));
        				return;
        			}
        		}
        		
	        	tabs.add(form);
	        	tabs.setActiveTab(form);
        	}
		},
		failure : function(){
			Ext.Msg.alert('Warning', 'Inter error');
		}
	});
}