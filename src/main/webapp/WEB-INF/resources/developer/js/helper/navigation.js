/**
 * Created by mi_rafi on 1/10/18.
 */
function notifyUpdateStatus(fn){
    $("#successMsg").html("Success fully updated").fadeIn(500).delay( 1000 ).fadeIn(500,function(){
        if(fn==undefined){
            location.reload();
        }else{
            fn();
        }
    });

}