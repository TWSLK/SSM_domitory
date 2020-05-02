function ImgUpload(node,width,height,linHeight){
	var _this = this;
	this._node = node;
	this.width = width + 'px';
	this.height = height + 'px';
	this.linHeight = linHeight + 'px';
	this.setCss();
	this.createFile();
}

ImgUpload.prototype.createFile = function(){
	$(this._node).append('<input type="file"/>')
}

ImgUpload.prototype.setCss = function(){
    $(this._node).css({
    	"width":this.width,
    	"height":this.height,
    	"line-height":this.linHeight,
    })
}

ImgUpload.prototype.setSpan = function(_this){
	$(_this).siblings().css("opacity",0);
}