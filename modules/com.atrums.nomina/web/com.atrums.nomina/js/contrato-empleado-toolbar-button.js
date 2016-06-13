(function () {
  var buttonProps = {
      action: function(){
          var i, callback, view = this.view, selectedRecords = view.viewGrid.getSelectedRecords(),
          lstContratos=[];

          for(i=0; i < selectedRecords.length; i++){
          	lstContratos.push(selectedRecords[i].id);
          	
          };

          // define the callback function which shows the result to the user
          callback = function(rpcResponse, data, rpcRequest) {
            if (data.success) {
                isc.say(OB.I18N.getLabel('NO_ConfirmCheckoutCont', [data.message]));
            } else {
                isc.say("El proceso se ha ejecutado correctamente");
            }
            
          }
          
          // ask for confirmation
          isc.ask(OB.I18N.getLabel('NO_ConfirmCheckoutCont'), function(ok) {
              if (ok) {
                  // and call the server
                  OB.RemoteCallManager.call('com.atrums.nomina.ad_actionButton.CompletarContratoHandler', {lstContratos: lstContratos}, {}, callback);
              }
          });
        },
      buttonType: 'no_liqu',
      prompt: OB.I18N.getLabel('NO_CheckContratoTip'),
      updateState: function(){
          var view = this.view, form = view.viewForm, grid = view.viewGrid, selectedRecords = grid.getSelectedRecords();
          if (view.isShowingForm && form.isNew) {
            this.setDisabled(true);
          } else if (view.isEditingGrid && grid.getEditForm().isNew) {
            this.setDisabled(true);
          } else {
            this.setDisabled(selectedRecords.length === 0);
          } 
      }
    };
  
  // register the button for the sales order tab
  // the first parameter is a unique identification so that one button can not be registered multiple times.
  OB.ToolbarRegistry.registerButton(buttonProps.buttonType, isc.OBToolbarIconButton, buttonProps, 150, '0C51ECCEBC5F448FA60FF1A7DE775ED9');
  
}());