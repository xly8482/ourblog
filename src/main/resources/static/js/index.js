var layer = layui.layer;
var imgMax = 3;
var strArr = [];
var currPage = 0;
var editor, editorUp, editorDetail;
function imgErr(obj){
    var imgIndex = parseInt(Math.random()*imgMax+1);
    obj.src="img/default/" + imgIndex + ".jpg";
    obj.onerror=null;
}

layui.use('laypage', function(){
    var laypage = layui.laypage;
    
    var searchPage = function(curr){
        // 查询博文列表
        var url = 'api/article/list';
        var data = {};
        
        $.ajax({
            type : "get",
            url : url,
            data : data,
            dataType : 'json',
            success : function(data) {
                // 执行一个laypage实例
                laypage.render({
                  elem: 'articlePage' 
                  ,curr: curr
                  ,count: data.length
                  ,limit: 6
                  ,theme: '#006dcc'
                  ,jump: function(obj, first){
                      // obj包含了当前分页的所有参数，比如：
                      // console.log(obj.curr); // 得到当前页，以便向服务端请求对应页的数据。
                      // console.log(obj.limit); // 得到每页显示的条数
                      strArr = [];
                      
                      var htm = '';
                      currPage = obj.curr;
                      var curNo = (obj.curr-1)*obj.limit;
                      for(var i=curNo; i<data.length; i++){
                          if(i >= obj.curr*obj.limit){
                              break;
                          }
                          
                          var objIndex = i - curNo;
                          
                          if(i%3 == 0){
                              htm += '<li class="span4" style="margin-left: 0;">';
                          }
                          else
                          {
                              htm += '<li class="span4">';
                          }
                          
                          htm += '<div class="thumbnail">';
                          htm += '<img alt="image not found" src="' + data[i].titleImgUrl + '" onerror="imgErr(this)" />';
                          htm += '<div class="caption">';
                          htm += '<h3>' + data[i].title + '</h3>';
                          htm += '<p>' + data[i].context + '</p>';
                          htm += '</div><div><a class="btn btn-primary" href="#" data-toggle="modal" data-target="#myModal" data-objid="'+data[i].id+'" data-objindex='+objIndex+'>浏览</a>';
                          htm += '<a class="btn btn-warning" href="#" data-toggle="modal" data-target="#myUpModal" data-objid="'+data[i].id+'" data-objindex='+objIndex+'>修改</a>';
                          htm += '<a class="btn btn-danger" href="#" data-objindex='+objIndex+' onclick="delArticle(\''+data[i].id+'\');">删除</a></div>';
                          htm += '</div>';
                          htm += '</li>';
                          
                          strArr.push(data[i]);
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
    }
    
    searchPage(currPage);
    Simditor.locale = 'zh-CN';//设置中文
    editor = new Simditor({
        textarea: $('#editor'),
        //optional options
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', 'ol',         
            'ul', 'blockquote', 'code', 'table', 'link', 'image', 'hr', 'indent', 'outdent', 'alignment'],
        codeLanguages: [{ name: 'Bash', value: 'bash' }, { name: 'C++', value: 'c++' }, { name: 'C#', value: 'cs' }, { name: 'CSS', value: 'css' },
             { name: 'Erlang', value: 'erlang' }, { name: 'Less', value: 'less' }, { name: 'Sass', value: 'sass' },  { name: 'Diff', value: 'diff' },
             { name: 'CoffeeScript', value: 'coffeescript' }, { name: 'HTML,XML', value: 'html' }, { name: 'JSON', value: 'json' },
             { name: 'Java', value: 'java' }, { name: 'JavaScript', value: 'js' }, { name: 'Markdown', value: 'markdown' },
             { name: 'Objective C', value: 'oc' }, { name: 'PHP', value: 'php' }, { name: 'Perl', value: 'parl' },
             { name: 'Python', value: 'python' }, { name: 'Ruby', value: 'ruby' }, { name: 'SQL', value: 'sql'}]
    });
    
    editorUp = new Simditor({
        textarea: $('#editorUp'),
        //optional options
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', 'ol',         
            'ul', 'blockquote', 'code', 'table', 'link', 'image', 'hr', 'indent', 'outdent', 'alignment'],
        codeLanguages: [{ name: 'Bash', value: 'bash' }, { name: 'C++', value: 'c++' }, { name: 'C#', value: 'cs' }, { name: 'CSS', value: 'css' },
             { name: 'Erlang', value: 'erlang' }, { name: 'Less', value: 'less' }, { name: 'Sass', value: 'sass' },  { name: 'Diff', value: 'diff' },
             { name: 'CoffeeScript', value: 'coffeescript' }, { name: 'HTML,XML', value: 'html' }, { name: 'JSON', value: 'json' },
             { name: 'Java', value: 'java' }, { name: 'JavaScript', value: 'js' }, { name: 'Markdown', value: 'markdown' },
             { name: 'Objective C', value: 'oc' }, { name: 'PHP', value: 'php' }, { name: 'Perl', value: 'parl' },
             { name: 'Python', value: 'python' }, { name: 'Ruby', value: 'ruby' }, { name: 'SQL', value: 'sql'}]
    });
    
    editorDetail = new Simditor({
        textarea: $('#editorDetail'),
        codeLanguages: [{ name: 'Bash', value: 'bash' }, { name: 'C++', value: 'c++' }, { name: 'C#', value: 'cs' }, { name: 'CSS', value: 'css' },
             { name: 'Erlang', value: 'erlang' }, { name: 'Less', value: 'less' }, { name: 'Sass', value: 'sass' },  { name: 'Diff', value: 'diff' },
             { name: 'CoffeeScript', value: 'coffeescript' }, { name: 'HTML,XML', value: 'html' }, { name: 'JSON', value: 'json' },
             { name: 'Java', value: 'java' }, { name: 'JavaScript', value: 'js' }, { name: 'Markdown', value: 'markdown' },
             { name: 'Objective C', value: 'oc' }, { name: 'PHP', value: 'php' }, { name: 'Perl', value: 'parl' },
             { name: 'Python', value: 'python' }, { name: 'Ruby', value: 'ruby' }, { name: 'SQL', value: 'sql'}]
    });
    
    $("#btnSubmit").on('click', function(){
        var url = 'api/article/add';
        var data = {
            "title": $("#title").val(),
            "context": editor.getValue()
        };
        
        $.ajax({
            type : "post",
            url : url,
            data : data,
            dataType : 'json',
            success : function(data) {
                layer.msg("发表成功！");
                setTimeout(reloadWindow, 1000);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg(XMLHttpRequest.responseText);
            }
        });
    });
    
    function reloadWindow(){
        window.location.reload();
    }
    
    $("#btnSubmitUp").on('click', function(){
        var url = 'api/article/update';
        var data = {
            "id": $("#objidUp").val(),
            "title": $("#titleUp").val(),
            "context": editorUp.getValue()
        };
        
        $.ajax({
            type : "put",
            url : url,
            data : data,
            dataType : 'json',
            success : function(data) {
                layer.msg("修改成功！");
                setTimeout(upSearch, 1000);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg(XMLHttpRequest.responseText);
            }
        });
    });
    
    function upSearch(){
        searchPage(currPage);
        $('#myUpModal').modal('hide')
    }
    
    // 删除文章
    window.delArticle = function (objId){
        layer.confirm('是否删除?', function(index){
            var url = "api/article/delete/" + objId;
            $.ajax({
                type : "delete",
                url : url,
                dataType : 'json',
                success : function(data) {
                    if(data == 0){
                        layer.msg("删除成功！");
                        setTimeout(upSearch, 1000);
                    } else {
                        layer.msg("删除失败！");
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg(XMLHttpRequest.responseText);
                }
            });
            
            layer.close(index);
        });  
    }
});

$('#myModal').on('show.bs.modal', function (e) {
    // 这里的btn就是触发元素，即你点击的删除按钮
    var btn = $(e.relatedTarget),
        index = btn.data("objindex"); 

    var data = strArr[index];
    
    $('#myModal .modal-body #titleDetail').html(data.title);
    editorDetail.setValue(data.context);
    $('#myModal .simditor').find(".simditor-toolbar").hide();
    $('#myModal .simditor').find(".simditor-body").attr("contenteditable", "false");
})

$('#myUpModal').on('show.bs.modal', function (e) {
    // 这里的btn就是触发元素，即你点击的删除按钮
    var btn = $(e.relatedTarget),
        index = btn.data("objindex"); 

    var data = strArr[index];
    
    $('#myUpModal .modal-body #objidUp').val(data.id);
    $('#myUpModal .modal-body #titleUp').val(data.title);
    editorUp.setValue(data.context);
})

