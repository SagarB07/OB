DHTMLGoodies_menuModel=function(){var a;this.menuItems=new Array();};DHTMLGoodies_menuModel.prototype={addItem:function(f,a,c,b,e,d){this.menuItems[f]=new Array();this.menuItems[f]["id"]=f;this.menuItems[f]["itemText"]=a;this.menuItems[f]["itemIcon"]=c;this.menuItems[f]["url"]=b;this.menuItems[f]["parentId"]=e;
this.menuItems[f]["separator"]=false;this.menuItems[f]["jsFunction"]=d;},addSeparator:function(b,a){this.menuItems[b]=new Array();this.menuItems[b]["parentId"]=a;this.menuItems[b]["separator"]=true;},init:function(){this.__getDepths();},getItems:function(){return this.menuItems;},__getDepths:function(){for(var a in this.menuItems){this.menuItems[a]["depth"]=1;
if(this.menuItems[a]["parentId"]){this.menuItems[a]["depth"]=this.menuItems[this.menuItems[a]["parentId"]]["depth"]+1;}}},__hasSubs:function(b){for(var a in this.menuItems){if(this.menuItems[a]["parentId"]==b){return true;}}return false;}};var referenceToDHTMLSuiteContextMenu;DHTMLGoodies_contextMenu=function(){var c;
var a;var e;var f;var h;var b;var g;var i;var d;this.menuModels=new Array();this.menuObject=false;this.menuUls=new Array();this.width=100;this.srcElement=false;this.indexCurrentlyDisplayedMenuModel=false;this.imagePath="images/";};DHTMLGoodies_contextMenu.prototype={setWidth:function(a){this.width=a;
},setLayoutCss:function(a){this.layoutCSS=a;},attachToElement:function(b,a,c){window.refToThisContextMenu=this;if(!b&&a){b=document.getElementById(a);}if(!b.id){b.id="context_menu"+Math.random();b.id=b.id.replace(".","");}this.menuModels[b.id]=c;b.oncontextmenu=this.__displayContextMenu;document.documentElement.onclick=this.__hideContextMenu;
},__setReference:function(a){referenceToDHTMLSuiteContextMenu=a;},__displayContextMenu:function(b){if(document.all){b=event;}var a=referenceToDHTMLSuiteContextMenu;a.srcElement=a.getSrcElement(b);if(!a.indexCurrentlyDisplayedMenuModel||a.indexCurrentlyDisplayedMenuModel!=this.id){if(a.indexCurrentlyDisplayedMenuModel){a.menuObject.innerHTML="";
}else{a.__createDivs();}a.menuItems=a.menuModels[this.id].getItems();a.__createMenuItems();}a.indexCurrentlyDisplayedMenuModel=this.id;a.menuObject.style.left=(b.clientX+Math.max(document.body.scrollLeft,document.documentElement.scrollLeft))+"px";a.menuObject.style.top=(b.clientY+Math.max(document.body.scrollTop,document.documentElement.scrollTop))+"px";
a.menuObject.style.display="block";return false;},__hideContextMenu:function(){var a=referenceToDHTMLSuiteContextMenu;if(a.menuObject){a.menuObject.style.display="none";}},__createDivs:function(){this.menuObject=document.createElement("DIV");this.menuObject.className="DHTMLSuite_contextMenu";this.menuObject.style.backgroundImage="url('"+this.imagePath+"context-menu-gradient.gif"+"')";
this.menuObject.style.backgroundRepeat="repeat-y";if(this.width){this.menuObject.style.width=this.width+"px";}document.body.appendChild(this.menuObject);},__mouseOver:function(){this.className="DHTMLSuite_item_mouseover";if(!document.all){this.style.backgroundPosition="left center";}},__mouseOut:function(){this.className="";
if(!document.all){this.style.backgroundPosition="1px center";}},__evalUrl:function(){var js=this.getAttribute("jsFunction");if(!js){js=this.jsFunction;}if(js){eval(js);}},__createMenuItems:function(){window.refToContextMenu=this;this.menuUls=new Array();for(var d in this.menuItems){if(!this.menuUls[0]){this.menuUls[0]=document.createElement("UL");
this.menuObject.appendChild(this.menuUls[0]);}if(this.menuItems[d]["depth"]==1){if(this.menuItems[d]["separator"]){var a=document.createElement("DIV");a.className="DHTMLSuite_contextMenu_separator";}else{var a=document.createElement("LI");if(this.menuItems[d]["jsFunction"]){this.menuItems[d]["url"]=this.menuItems[d]["jsFunction"]+"(this,referenceToDHTMLSuiteContextMenu.srcElement)";
}if(this.menuItems[d]["itemIcon"]){a.style.backgroundImage="url('"+this.menuItems[d]["itemIcon"]+"')";if(!document.all){a.style.backgroundPosition="1px center";}}if(this.menuItems[d]["url"]){var b=this.menuItems[d]["url"]+"";var c=b+"";a.setAttribute("jsFunction",b);a.jsFunction=b;a.onclick=this.__evalUrl;
}a.innerHTML='<a href="#" onclick="return false">'+this.menuItems[d]["itemText"]+"</a>";a.onmouseover=this.__mouseOver;a.onmouseout=this.__mouseOut;}this.menuUls[0].appendChild(a);}}},getSrcElement:function(b){var a;if(b.target){a=b.target;}else{if(b.srcElement){a=b.srcElement;}}if(a.nodeType==3){a=a.parentNode;
}return a;}};