(function () {
  var buttonProps = {
      action: function(){
        alert('You clicked me!');
      },
      buttonType: 'correo',
      prompt: OB.I18N.getLabel('E-mail'),
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
  OB.ToolbarRegistry.registerButton(buttonProps.buttonType, isc.OBToolbarIconButton, buttonProps, 200, '0C51ECCEBC5F448FA60FF1A7DE775ED9');
  
}());