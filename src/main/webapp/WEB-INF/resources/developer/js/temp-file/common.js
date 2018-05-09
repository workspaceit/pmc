/**
 * Created by mi_rafi on 1/4/18.
 * Store tokens and id
 */
function TokenStorage () {
    this.keyPrefix = "PMC_TOKEN_STORAGE";
    this.storageTypeStatics ={ls:"localStorage",hhi:"htmlHiddenInput"};
        /**
         * Using html hidden field
         * Local storage keeps value until browser closed
         * need some storage which keep value until page refresh
        * */
    this.storageType = this.storageTypeStatics.hhi; //(typeof(Storage) !== "undefined")?this.storageTypeStatics.ls:this.storageTypeStatics.hhi;
    this.occupy = function(key) {
        switch(this.storageType){
            case this.storageTypeStatics.hhi:
                this._occupyHtmlElement(key);
                break;
        }
    };
    this._getKey= function(key){
        return this.keyPrefix+key;
    };
    this._occupyHtmlElement=function(key) {
        if($("#"+key).length>0){
            console.log("Key already taken");
            return;
        }
        var keyElem = $("<input>", {type:"hidden",id: key, "value": ""});
        $("body").append(keyElem);
    };
    this._storeInLocalStorage=function(key,val) {
        sessionStorage.setItem(key, val);
    };
    this._storeInHtmlElement=function(key,val) {
        if($("#"+key).length==0){
            this.occupy(key);
        }
        $("#"+key).val(val);
    };

    this.emptyToken=function(key){
        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._emptyWebStorage(this._getKey(key));
            case this.storageTypeStatics.hhi:
                return this.emptyHtmlStorage(this._getKey(key));
        }
    };
    this._emptyWebStorage=function(key){
        sessionStorage.setItem(key,"");
    };
    this.emptyHtmlStorage=function(key){
        $("#"+key).val("");
    };
    this.setToken=function(key,token){

        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._storeInLocalStorage(this._getKey(key),token);
            case this.storageTypeStatics.hhi:
                return this._storeInHtmlElement(this._getKey(key),token);
        }
    };
    this.getToken=function(key){
        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._getFromWebStorage(this._getKey(key));
            case this.storageTypeStatics.hhi:
                return this._getFromHtmlInputField(this._getKey(key));
        }
    };
    this._getFromWebStorage=function(key){
        return  sessionStorage.getItem(key);
    };
    this._getFromHtmlInputField=function(key){
        var token = "";
        try{
            if($("#"+key).length>0)
                token = $("#"+key).val().trim();
        }catch(ex) {
            console.log(ex);
            token="";
        }
        return token;

    };

}



function injectHiddenTokenFields(keys){
    for(var i=0;i<keys.length;i++){

        var keyElem = $("<input>", {type:"hidden",id: keys[i], "value": ""});
        $("body").append(keyElem);
    }
}
function getToken(key){
    var tokens=[];
    try{
        var ls = new TokenStorage();
        var tokenStr =  ls.getToken(key);
        tokens = JSON.parse( (tokenStr==null || tokenStr=="")?"[]":tokenStr);

    }catch(ex) {
        console.log(ex);
        tokens = [];
    }
    return tokens;
}
function emptyToken(key){
    var ts = new TokenStorage();
    ts.emptyToken(key)
}

function storeToken(key,token){
    if((key ==null || key=="") || (token==null||token=="") ){
        throw "Key or token missing";
    }
    var tokens = getToken(key);
    if(tokens.indexOf(token)<0){
        tokens.push(token);
    }

    var ls = new TokenStorage();
    ls.setToken(key,JSON.stringify(tokens))
}
function removeToken(key,token){
    var tokens=getToken(key);
    var index = tokens.indexOf(token);
    if(index>=0){
        tokens.splice(index,1);
    }
    var ls = new TokenStorage();
    ls.setToken(key,JSON.stringify(tokens))
}
function removeFileByToken(token, fn,file){
    if(token == undefined){
        return;
    }
    $.ajax({
        url: BASEURL+'file/remove',
        data:{"token":token},
        type: 'POST',
        statusCode: {
            401: function (response) {
                console.log(response);
            },
            422: function (response) {
                console.log(response);
            }
        },success: function(data){
            console.log(data);
            fn(data,file);
        }
    });
}

function commonDropZoneConfig(data){
    var elementId = data.elementId;
    var param=data.param;
    var maxFile=data.maxFile;
    var maxFileSize=data.maxFileSize;
    var fnSuccess=data.success;
    var afterServerFileRemove=data.afterServerFileRemove;
    var addFileFn=data.afterAddFile;
    var paramName = (data.paramName!=undefined)?data.paramName:"profileImg";

    var dropZoneConfig = new Dropzone("#"+elementId,
        {
            url: BASEURL+"file/upload/"+param,
            method:"post",
            paramName:paramName,
            maxFilesize: maxFileSize,
            maxFiles:maxFile,
            addRemoveLinks: true,
            previewTemplate:$("#dropZonePreview").html(),
            init:function(){

                /** If function is overridden */
                if(addFileFn != undefined && typeof addFileFn == "function"){
                    addFileFn(this);
                }else{
                    /** Default behaviour */

                    this.on("maxfilesexceeded", function(file) {

                    });
                    this.on("addedfile", function(file) {
                        file._removeLink.addEventListener("click", function() {
                            console.log(file);
                            removeFileByToken(file.token,afterServerFileRemove,file);
                            advImgDropZone.removeFile(file);
                        });

                        if(this.files.length > maxFile){
                            if(maxFile==1){
                                console.log(this.files);
                                this.files[0]._removeLink.click();
                                console.log(this.files);
                            }else{
                                showServerErrorMessage("Maximum file limit is "+maxFile,"tc",4000);
                                this.files[advImgDropZone.files-1]._removeLink.click();
                                this.removeFile(file);
                            }
                        }


                    });
                }
            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#"+elementId).find(".dz-error-message span").html(msg).addClass("text-danger");
            },
            success:function(file,response){

                file.token = response.token;
                fnSuccess(response,file);
            }
        }
    );
    return dropZoneConfig;
}