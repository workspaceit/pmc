/**
 * Created by mi_rafi on 1/4/18.
 */
function TempFileTokenStorage () {
    this.storageTypeStatics ={ls:"localStorage",hhi:"htmlHiddenInput"};

    this.storageType = this.storageTypeStatics.hhi;//(typeof(Storage) !== "undefined")?this.storageTypeStatics.ls:this.storageTypeStatics.hhi;
    this.occupy = function(key) {
        switch(this.storageType){
            case this.storageTypeStatics.hhi:
                this._occupyHtmlElement(key);
                break;
        }
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
        localStorage.setItem(key, val);
    };
    this._storeInHtmlElement=function(key,val) {
        if($("#"+key).length==0){
            this.occupy(key);
        }
        $("#"+key).val(val);
    };
    this.getToken=function(key){
        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._getFromWebStorage(key);
            case this.storageTypeStatics.hhi:
                return this._getFromHtmlInputField(key);
        }
    };
    this.emptyToken=function(key){
        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._emptyWebStorage(key);
            case this.storageTypeStatics.hhi:
                return this.emptyHtmlStorage(key);
        }
    };
    this._emptyWebStorage=function(key){
        localStorage.setItem(key,"");
    };
    this.emptyHtmlStorage=function(key){
        $("#"+key).val("");
    };
    this.setToken=function(key,token){
        switch(this.storageType){
            case this.storageTypeStatics.ls:
                return this._storeInLocalStorage(key,token);
            case this.storageTypeStatics.hhi:
                return this._storeInHtmlElement(key,token);
        }
    };
    this._getFromWebStorage=function(key){
        return  localStorage.getItem(key);
    };
    this._getFromHtmlInputField=function(key){
        return $("#"+key).val().trim();
    };

}



function injectHiddenTokenFields(keys){
    for(var i=0;i<keys.length;i++){

        var keyElem = $("<input>", {type:"hidden",id: keys[i], "value": ""});
        $("body").append(keyElem);
    }
}
function getToken(elemId){
    var tokens=[];
    try{
        var tokenStr =  $("#"+elemId).val().trim();
        tokens = JSON.parse(tokenStr==""?"[]":tokenStr);

    }catch(ex) {
        console.log(ex);
        tokens = [];
    }
    return tokens;
}
function emptyToken(elemId){
    $("#"+elemId).val("");
}

function storeToken(elemId,token){
    var tokens = getToken(elemId);
    if(tokens.indexOf(token)<0){
        tokens.push(token);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}
function removeToken(elemId,token){
    var tokens=getToken(elemId);
    var index = tokens.indexOf(token);
    if(index>=0){
        tokens.splice(index,1);
    }
    $("#"+elemId).val(JSON.stringify(tokens));
}
function removeFileByToken(token, fn){
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
            fn(data);
        }
    });
}