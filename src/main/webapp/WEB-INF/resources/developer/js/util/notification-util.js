function showSuccessMessage(title,massage){
    $.growl.notice({ title: title, message: massage });
}
function showServerErrorMessage(massage,location,duration){
    location=(location==undefined)?"tr":location;
    duration=(duration==undefined)?3200:duration;
    $.growl.error({ message: massage ,location:location,duration:duration});
}

