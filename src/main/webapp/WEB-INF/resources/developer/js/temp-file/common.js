/**
 * Created by mi_rafi on 1/4/18.
 */

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