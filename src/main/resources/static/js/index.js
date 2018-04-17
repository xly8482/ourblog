var layer = layui.layer;
var imgMax = 3;
function imgErr(obj){
    var imgIndex = parseInt(Math.random()*imgMax+1);
    obj.src="img/default/" + imgIndex + ".jpg";
    obj.onerror=null;
}

layui.use('laypage', function(){
    var laypage = layui.laypage;
    
    // 查询博文列表
    var url = 'api/article/list';
    var data = {};
    $.ajax({
        type : "post",
        url : url,
        data : data,
        dataType : 'json',
        success : function(data) {
            // 执行一个laypage实例
            laypage.render({
              elem: 'articlePage' // 注意，这里的 test1 是 ID，不用加 # 号
              ,count: data.length // 数据总数，从服务端得到
              ,limit: 6
              ,theme: '#006dcc'
              ,jump: function(obj, first){
                  // obj包含了当前分页的所有参数，比如：
                  // console.log(obj.curr); // 得到当前页，以便向服务端请求对应页的数据。
                  // console.log(obj.limit); // 得到每页显示的条数
                  
                  var htm = '';
                  var curNo = (obj.curr-1)*obj.limit;
                  for(var i=curNo; i<data.length; i++){
                      if(i >= obj.curr*obj.limit){
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
                      htm += '<img alt="image not found" src="' + data.titleImgUrl + '" onerror="imgErr(this)" />';
                      htm += '<div class="caption">';
                      htm += '<h3>' + data[i].title + '</h3>';
                      htm += '<p>' + data[i].context + '</p>';
                      htm += '<p><a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a></p>';
                      htm += '</div>';
                      htm += '</div>';
                      htm += '</li>';
                  }
                  
                  $("#articleList").html(htm);
                  
                  // 首次不执行
                  if(!first){
                    
                  }
              }
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(XMLHttpRequest.responseText);
        }
    });
});

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
	
});

