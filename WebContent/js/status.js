/**
 * Jan 5, 2012
 * 显示系统状态
 */
var status_core = {
		count : 0,
		data : [{cpus : 0, num : 0}]
};

status_core.store = Ext.create('Ext.data.Store', {
	fields : ['cpus', 'cpus'],
	proxy : {
		type : 'ajax',
		url : 'status!cpuInfo.action',
		reader : {
			type : 'json'
		}
	}
});

status_core.store1 = Ext.create('Ext.data.JsonStore', {
	fields : ['cpus', 'num']
});

status_core.win = Ext.create('Ext.Window', {
	title : 'CPU information',
	height : 400,
	width : 500,
	closeAction : 'hide',
	layout : {
		type : 'border'
	},
	items : [{
		region : 'center',
		xtype : 'chart',
        animate: true,
		store: status_core.store,
        flex: 1,
        axes: [{
            type: 'gauge',
            position: 'gauge',
            minimum: 0,
            maximum: 100,
            steps: 10,
            margin: 7
        }],
        series: [{
            type: 'gauge',
            field: 'cpus',
            donut: 0,
            colorSet: ['#F49D10', '#ddd']
        }]
	}, {
		region : 'south',
		xtype : 'chart',
		id : 'surenchart',
		store: status_core.store1,
		animate : true,
		axes : [{
            type: 'Numeric',
            grid: true,
            minimum: 0,
            maximum: 100,
            position: 'left',
            fields: ['cpus']
		}, {
			type : 'Numeric',
			grid : true,
            minimum: 0,
            maximum: 20,
			fields : 'num',
			constrain: true,
			position : 'bottom'
		}],
		series : [{
			type : 'line',
			axis : ['bottom', 'left'],
			xField : 'num',
			yField : 'cpus'
		}]
	}],
	listeners : {
		activate : function(){
			status_core.interval = window.setInterval(function(){
				status_core.store.load({
					params : {
						name : status_core.param.name,
						url : status_core.param.url
					},
					callback : function(records){
						var count = status_core.count++;
						var data = status_core.data;
						var chart = Ext.getCmp('surenchart');
						var max = chart.axes.get(1).maximum;
						
						if(data.length >= max){
							data.shift();
						}
						
						data.push({
							cpus : records[0].data.cpus,
							num : count
						});
						
						if(count >= max){
							chart.axes.get(1).minimum = data[0].num;
							chart.axes.get(1).maximum = data[data.length - 1].num;
							chart.redraw();
						}
						
						status_core.store1.loadData(data);
					}
				});
			}, 1000);
		},
		beforehide : function(){
			window.clearInterval(status_core.interval);
			status_core.count = 0;
			status_core.data = [{cpus : 0, num : 0}];
		}
	}
});

status_core.show = function(param, callback){
	status_core.win.show();
	
	status_core.param = param;
	status_core.callback = callback;
};

status_core.hide = function(){
	status_core.win.hide();
};