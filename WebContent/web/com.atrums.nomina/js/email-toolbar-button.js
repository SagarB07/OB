OB.NO = OB.NO || {};

OB.NO.Process = {
	execute: function (params, view) {
		var i, selection = params.button.contextView.viewGrid.getSelectedRecords(),
		rolpago = [], messageBar = view.getView(params.adTabId).messageBar, 
		callback;
		
		callback = function (rpcResponse, data, rpcRequest) {
			/*isc.say(OB.I18N.getLabel('Atacv_Updated', [data.updated]));*/
			//params.button.contextView.viewGrid.refreshGrid();
			var status = rpcResponse.status,
			view = rpcRequest.clientContext.view.getView(params.adTabId);
			view.messageBar.setMessage(data.message.severity, null, data.message.text);
			
			params.button.closeProcessPopup();
		};
		
		for (i = 0; i < selection.length; i++) {
		    rolpago.push(selection[i].id);
		};
		
		OB.RemoteCallManager.call('com.atrums.nomina.ad_process.RolEnvioMailHandler', {
		    rolpago: rolpago,
		    action: params.action
		}, {}, callback, {
      view: view
    });
	},
	
	email: function (params, view) {
		params.action = 'PROCESS';
		params.adTabId = '9627836015B94CF6ACD14D0E16F4627B';		
		OB.NO.Process.execute(params, view);
	}
};