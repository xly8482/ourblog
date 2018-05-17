layui.use('laypage', function(){
    var laypage = layui.laypage;
    
    $("#uploadFile").on('click', function(){
        var url = '/api/upload/resource';
//        var data = {
//            "file": $("#imageFile")[0].files
//        };
        var form = new FormData(document.getElementById("imageFile"));
        $.ajax({
            type : "post",
            url : url,
            data : form,
            processData:false,
            contentType:"multipart/form-data",
            success : function(data) {
                layer.msg("上传成功！");
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg(XMLHttpRequest.responseText);
            }
        });
    });
});  

