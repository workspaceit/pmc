$(document).ready(function(){
    $("#allAdvertiserSelection").on("click",function(){
        showHideAdvertiserSelectBox();
    });

    showHideAdvertiserSelectBox();
});


function showHideAdvertiserSelectBox(){
    var isChecked = $("#allAdvertiserSelection:checked").length;
    if(isChecked==1){
        $("#advertiser-select2").parent().hide();
    }else{
        $("#advertiser-select2").parent().show();
    }
}