var frameDefault="appFrame";function displayLogic(){return true;}function setgWaitingCallOut(state,frameName){if(frameName==null||frameName==""){frameName=frameDefault;}objFrame=eval("parent."+frameName);if(!objFrame){objFrame=parent;}objFrame.setGWaitingCallOut(state);}function returnResponse(arrResponse,calloutName,frameName,formName){if(frameName=="frameAplicacion"){frameName="appFrame";
}setgWaitingCallOut(false,frameName);if(arrResponse==null&&(calloutName==null||calloutName=="")){return false;}if(frameName==null||frameName==""){frameName=frameDefault;}objFrame=eval("parent."+frameName);if(!objFrame){objFrame=parent;if(!objFrame.fillElementsFromArray){return;}}if(objFrame){objFrame.fillElementsFromArray(arrResponse,calloutName,formName);
try{objFrame.displayLogic();}catch(ignored){}}}