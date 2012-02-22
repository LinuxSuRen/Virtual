/**
 * Jan 4, 2012
 * 创建新虚拟机向导
 */

var new_virtual = {};

var virtual_step_1 = Ext.create('Ext.form.Panel', {
	items : [{
		xtype : 'fieldset',
		title : 'Enter your virtual machine details',
		items : [{
			fieldLabel : 'Name',
			name : 'name',
			xtype : 'textfield',
			allowBlank : false
		}, {
			fieldLabel : 'Connect',
			xtype : 'displayfield',
			value : 'localhost(QEMU/KVM)'
		}]
	}, {
		xtype : 'fieldset',
		title : 'Choose how you would like to install the operating system',
		items : [{
			xtype : 'fieldcontainer',
			defaultType : 'radiofield',
			items : [{
				boxLabel : 'Local install media(ISO image or CDROM)',
				name : 'image'
			}, {
				boxLabel : 'Network Install(HTTP,FTP,or NFS)',
				name : 'image'
			}, {
				boxLabel : 'Network Boot(PXE)',
				name : 'image'
			}, {
				boxLabel : 'Import existing disk image',
				name : 'image'
			}]
		}]
	}],
	buttons : [{
		text : 'Cancel',
		handler : function(){
			virtual_win.hide();
		}
	}, {
		text : 'Forward',
		handler : function(){
			if(virtual_step_1.getForm().isValid()){
				var f_1 = virtual_step_1.getForm();
				var f_5 = virtual_step_5.getForm();
				
				f_5.findField('domain.name').setValue(f_1.findField('name').getValue());
				
				virtual_win.getLayout().next();
			}
		}
	}],
	listeners : {
		activate : function(){
			new_virtual_setTitle(1);
		}
	}
});

var virtual_step_2 = Ext.create('Ext.form.Panel', {
	items : [{
		xtype : 'fieldset',
		title : 'Locate your install media',
		items : [{
			xtype : 'textfield',
			fieldLabel : 'Use ISO image',
			name : 'isofile',
			allowBlank : false
		}]
	}, {
		xtype : 'fieldset',
		title : 'Choose an operating system type and version',
		items : [{
			xtype : 'combo',
			store : new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [[
					'Generic', 'generic'
				]]
			}),
			displayField : 'text',
			valueField : 'value',
			editable : false,
			fieldLabel : 'OS type',
			name : 'ostype',
			allowBlank : false,
			listeners : {
				added : function(obj){
					obj.select(obj.getStore().first());
				}
			}
		}, {
			xtype : 'combo',
			queryMode : 'local',
			store : new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [[
					'Generic', 'generic'
				]]
			}),
			displayField : 'text',
			valueField : 'value',
			editable : false,
			fieldLabel : 'Version',
			name : 'version',
			allowBlank : false,
			listeners : {
				added : function(obj){
					obj.select(obj.getStore().first());
				}
			}
		}]
	}],
	buttons : [{
		text : 'Cancel',
		handler : function(){
			virtual_win.hide();
		}
	}, {
		text : 'Back',
		handler : function(){
			virtual_win.getLayout().prev();
		}
	}, {
		text : 'Forward',
		handler : function(){
			if(virtual_step_2.getForm().isValid()){
				var f_2 = virtual_step_2.getForm();
				var f_5 = virtual_step_5.getForm();
				
				f_5.findField('domain.isoFile').setValue(f_2.findField('isofile').getSubmitValue());
				
				virtual_win.getLayout().next();
			}
		}
	}],
	listeners : {
		activate : function(){
			new_virtual_setTitle(2);
		}
	}
});

var virtual_step_3 = Ext.create('Ext.form.Panel', {
	items : [{
		xtype : 'fieldset',
		title : 'Choose Memory and CPU settings',
		items : [{
			xtype : 'numberfield',
			fieldLabel : 'Memory(RAM)',
			name : 'ram',
			minValue : 100,
			maxValue : 1024,
			value : 256,
			allowBlank : false
		}, {
			xtype : 'numberfield',
			fieldLabel : 'CPUs',
			name : 'cpus',
			minValue : 1,
			maxValue : 2,
			value : 1,
			allowBlank : false
		}]
	}],
	buttons : [{
		text : 'Cancel',
		handler : function(){
			virtual_win.hide();
		}
	}, {
		text : 'Back',
		handler : function(){
			virtual_win.getLayout().prev();
		}
	}, {
		text : 'Forward',
		handler : function(){
			if(virtual_step_3.getForm().isValid()){
				var f_3 = virtual_step_3.getForm();
				var f_5 = virtual_step_5.getForm();
				
				f_5.findField('domain.curMem').setValue(f_3.findField('ram').getValue());
				f_5.findField('domain.cpus').setValue(f_3.findField('cpus').getValue());
				
				virtual_win.getLayout().next();	
				new_virtual_setTitle(4);
			}
		}
	}],
	listeners : {
		activate : function(){
			new_virtual_setTitle(3);
		}
	}
});

var virtual_step_4 = Ext.create('Ext.form.Panel', {
	items : [{
		xtype : 'fieldset',
		title : 'Enable storage for this virtual machine',
		items : [{
			xtype : 'numberfield',
			fieldLabel : 'Create disk image on the computer\'s hard drive',
			labelAlign : 'top',
			minValue : 4,
			maxValue : 500,
			value : 8,
			allowBlank : false
		}]
	}],
	buttons : [{
		text : 'Cancel',
		handler : function(){
			virtual_win.hide();
		}
	}, {
		text : 'Back',
		handler : function(){
			virtual_win.getLayout().prev();
		}
	}, {
		text : 'Forward',
		handler : function(){
			if(virtual_step_4.getForm().isValid()){
				virtual_win.getLayout().next();
				new_virtual_setTitle(5);
			}
		}
	}],
	listeners : {
		activate : function(){
			new_virtual_setTitle(4);
		}
	}
});

var virtual_step_5 = Ext.create('Ext.form.Panel', {
	url : 'domain.action',
	items : [{
		xtype : 'fieldset',
		title : 'Ready to begin installation of',
		items : [{
			xtype : 'textfield',
			readOnly : true,
			fieldLabel : 'OS',
			value : 'Generic'
		}, {
			xtype : 'textfield',
			readOnly : true,
			fieldLabel : 'Name',
			name : 'domain.name'
		}, {
			xtype : 'textfield',
			readOnly : true,
			fieldLabel : 'ISO image',
			name : 'domain.isoFile'
		}, {
			xtype : 'textfield',
			readOnly : true,
			fieldLabel : 'Memory(RAM)',
			name : 'domain.curMem'
		}, {
			xtype : 'textfield',
			readOnly : true,
			fieldLabel : 'CPUS',
			name : 'domain.cpus'
		}, {
			xtype : 'hiddenfield',
			name : 'url'
		}]
	}, {
		xtype : 'fieldset',
		title : 'Advanced options',
		collapsible : true,
		collapsed : true,
		items : [{
			xtype : 'combo',
			fieldLabel : 'Virt Type',
			store : new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [['Kvm', 'kvm']]
			}),
			listeners : {
				added : function(obj){
					obj.select(obj.getStore().first());
				}
			}
		}, {
			xtype : 'combo',
			fieldLabel : 'Architechture',
			store : new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [['i686', 'i686']]
			}),
			listeners : {
				added : function(obj){
					obj.select(obj.getStore().first());
				}
			}
		}]
	}],
	buttons : [{
		text : 'Cancel',
		handler : function(){
			virtual_win.hide();
		}
	}, {
		text : 'Back',
		handler : function(){
			virtual_win.getLayout().prev();
		}
	}, {
		text : 'Finish',
		handler : function(){
			var form = virtual_step_5.getForm();
			
			if(form.isValid()){
				form.submit({
					success : function(form, action){
						Ext.Msg.alert('Success', action.result.msg);
						virtual_step_5.getForm().reset();
						virtual_win.hide();
						
						if(new_virtual.callback && (typeof new_virtual.callback) == 'function'){
							new_virtual.callback();
						}
					},
					failure : function(form, action){
						Ext.Msg.alert('Failed', action.result.msg);
					}
				});
			}
		}
	}],
	listeners : {
		activate : function(){
			new_virtual_setTitle(5);
		}
	}
});

var virtual_win = Ext.create('Ext.Window',{
	height : 300,
	width : 400,
	closeAction : 'hide',
	layout : 'card',
	items : [virtual_step_1, virtual_step_2,
	         virtual_step_3, virtual_step_4,
	         virtual_step_5]
});

new_virtual.create = function(url, callback){
	virtual_step_5.getForm().findField('url').setValue(url);
	
	virtual_win.show();
	
	new_virtual.callback = callback;
};

function new_virtual_setTitle(num){
	virtual_win.setTitle('New Virtual Matchine Step ' + num + ' of 5');
}
