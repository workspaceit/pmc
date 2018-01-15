/**
 * Created by mi_rafi on 1/15/18.
 */
function isScriptLoadedAtPage(uri) {
    var url = window.location.origin+BASEURL+"resources"+uri;
    var scripts = document.getElementsByTagName('script');
    for (var i =0;i< scripts.length; i++) {
        if (scripts[i].src == url) return true;
    }
    return false;
}