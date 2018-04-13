var layer = layui.layer;

$(document).ready(function() {
	$('#summernote').summernote({
		height: 500
	});
	
	
	$("#btnSubmit").on('click', function(){
		var url = 'api/article/add';
		var data = {
	        "title": $("#title").val(),
	        "context": $("#summernote").val()
		};
		
		$.ajax({
	        type : "post",
	        url : url,
	        data : data,
	        dataType : 'json',
	        success : function(data) {
	            console.log(data);
	            layer.msg("发表成功！");
	            setTimeout(() => {
	                window.location.reload();
                }, 1000);
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
	            layer.msg(XMLHttpRequest.responseText);
            }
	    });
	});
	
	// 查询博文列表
	var url = 'api/article/list';
	var data = {};
    $.ajax({
        type : "post",
        url : url,
        data : data,
        dataType : 'json',
        success : function(data) {
            var htm = '';
            for(var i=0; i<data.length; i++){
                if(i >= 6){
                    break;
                }
                
                if(i%3 == 0){
                    htm += '<li class="span4" style="margin-left: 0;">';
                }
                else
                {
                    htm += '<li class="span4">';
                }
                
                htm += '<div class="thumbnail">';
                htm += '<img alt="image not found" src="' + data.titleImgUrl + '" onerror="this.src=\'img/sports.jpg;this.onerror=null\'" />';
                htm += '<div class="caption">';
                htm += '<h3>' + data[i].title + '</h3>';
                htm += '<p>' + data[i].context + '</p>';
                htm += '<p><a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a></p>';
                htm += '</div>';
                htm += '</div>';
                htm += '</li>';
            }
            
            $("#articleList").html(htm);
            
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(XMLHttpRequest.responseText);
        }
    });
});

