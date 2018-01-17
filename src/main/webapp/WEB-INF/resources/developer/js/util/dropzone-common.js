/**
 * temp-file/common.js required
* */

function configDropZone(elementId,param,maxFile,maxFileSize,fnSuccess,fnError){
    var advImgDropZone = new Dropzone("#"+elementId,
        {
            url: BASEURL+"file/upload/adv/"+param,
            method:"post",
            paramName:"advImg",
            maxFilesize: maxFileSize,
            maxFiles:maxFile,
            addRemoveLinks: true,
            previewTemplate:$("#dropZonePreview").html(),
            init:function(){

                this.on("maxfilesexceeded", function(file) {
                    this.removeAllFiles();
                    this.addFile(file);
                });
                this.on("addedfile", function(file) {
                    file._removeLink.addEventListener("click", function() {
                        console.log(file);
                        removeFileByToken(file.token,fnError);
                        advImgDropZone.removeFile(file);
                    });
                });

            },
            error:function(file,response){
                var msg = (typeof response == "object")?((response.length>0)?response[0].msg:""):response;
                $("#"+elementId).find(".dz-error-message span").html(msg);
            },
            success:function(file,response){

                file.token = response.token;
                console.log(file);
                fnSuccess(response);
            }
        }
    );
    return advImgDropZone;
}