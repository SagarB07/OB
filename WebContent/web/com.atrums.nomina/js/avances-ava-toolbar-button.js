/*
// put within a function to hide local vars etc.
(function () {
  var buttonProps = {
      action: function(){
        alert('You clicked me!');
      },
      buttonType: 'no_process',
      prompt: OB.I18N.getLabel('NO_Procesar'),
      updateState: function(){
          var view = this.view, form = view.viewForm, grid = view.viewGrid, selectedRecords = grid.getSelectedRecords();
          if (view.isShowingForm && form.isNew) {
            this.setDisabled(true);
          } else if (view.isEditingGrid && grid.getEditForm().isNew) {
            this.setDisabled(true);
          } else {
            this.setDisabled(selectedRecords.length == 0);
          }
      }
    };
  
  // register the button for the sales order tab
  // the first parameter is a unique identification so that one button can not be registered multiple times.
  OB.ToolbarRegistry.registerButton(buttonProps.buttonType, isc.OBToolbarIconButton, buttonProps, 100, "'55D81228F99B47F8AAC41D1DE0175DFC'");
}());

*/


// put within a function to hide local vars etc.
(function () {
  var buttonProps = {
      action: function(){
        var i, callback, view = this.view, selectedRecords = view.viewGrid.getSelectedRecords()
        avances = [];

        for(i=0; i < selectedRecords.length; i++){
        	
        	avances.push(selectedRecords[i].id);
        	
        };
        

        // define the callback function which shows the result to the user
        callback = function(rpcResponse, data, rpcRequest) {
          if (data.success) {
              isc.say(OB.I18N.getLabel('NO_CheckoutResultAva', [data.total]));
          } else {
              isc.say(data.message);
          }
          
        }
        
        // ask for confirmation
        isc.ask(OB.I18N.getLabel('NO_ConfirmCheckoutAva'), function(ok) {
            if (ok) {
                // and call the server
                OB.RemoteCallManager.call('com.atrums.nomina.ad_actionButton.AvanceCheckOutActionHandler', {avances: avances}, {}, callback);
            }
        });
      },
      buttonType: 'no_ava',
      prompt: OB.I18N.getLabel('NO_CheckAvanTip'),
      updateState: function(){
      }
    };
  
  // register the button for the Guest tab of the Guest/Stay window (look up the ID of the tab inside the AD_TAB database table!)
  OB.ToolbarRegistry.registerButton(buttonProps.buttonType, isc.OBToolbarIconButton, buttonProps, 100, 'C018CCAD1E4F435EB18BF1CCA45604CC');
}());