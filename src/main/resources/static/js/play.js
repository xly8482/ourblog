    var stepSize = 5;
    var bodyMaxWidth = $(window).width();
    var bodyMaxHeight = $(window).height();
    var canvas;
    var context;
    var img;
    var direction=0;
    
    var shooting = 0;
    var buContext;
    var bulletImg;
    
    var topBoundary = 0;
    var leftBoundary = 0;
    var bottomBoundary = bodyMaxHeight+topBoundary;
    var rightBoundary = bodyMaxWidth+leftBoundary;
    
    function shoot(bulletId, index, buCanvas, buDirection)
    {
        if($("#"+bulletId).length <= 0){
            clearInterval(index);
            return;
        }
        
        var bfLeft = $("#"+bulletId).offset().left;
        var bfTop = $("#"+bulletId).offset().top;
        
        if(buDirection == 90)
        {
            if(bfLeft+stepSize < rightBoundary)
            {
                $("#"+bulletId).offset({top:bfTop,left:bfLeft+stepSize});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                $("#bulletArea #"+bulletId).remove();
                clearInterval(index);
            }
        }
        else if(buDirection == 0 || buDirection == 360)
        {
            if(bfTop-stepSize > topBoundary)
            {
                $("#"+bulletId).offset({top:bfTop-stepSize, left:bfLeft});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                $("#bulletArea #"+bulletId).remove();
                clearInterval(index);
            }
        }
        else if(buDirection == 180)
        {
            if(bfTop+stepSize < bottomBoundary)
            {
                $("#"+bulletId).offset({top:bfTop+stepSize,left:bfLeft});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                $("#bulletArea #"+bulletId).remove();
                clearInterval(index);
            }
        }
        else if(buDirection == 270)
        {
            if(bfLeft-stepSize > leftBoundary)
            {
                $("#"+bulletId).offset({top:bfTop,left:bfLeft-stepSize});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                $("#bulletArea #"+bulletId).remove();
                clearInterval(index);
            }
        }
        else
        {
            buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
            $("#bulletArea #"+bulletId).remove();
            clearInterval(index);
        }
    }
    
    $(document).keypress(function(e) { 
        if (e.ctrlKey && e.which == 10) 
            playInit();
    });

    $(document).keydown(function(event){
        if($("#player1").length <= 0){
            return;
        }
        
        var lfLeft = $("#player1").offset().left;
        var lfTop = $("#player1").offset().top;
        
        //发射子弹
        if(event.keyCode == 65 || event.keyCode == 32)
        {
            if($("#bulletArea").children().length < 10)
            {
                //产生子弹
                shooting++
                var canId = "bullet" + shooting;
                $("#bulletArea").append('<canvas id="'+canId+'" style="width:10;height:10;"></canvas>');
                
                $("#"+canId).offset({top:lfTop,left:lfLeft});
                var buCanvas = document.getElementById(canId);
                buContext = buCanvas.getContext("2d");
                bulletImg = new Image();
                bulletImg.src = "img/play/bullet.png";
                
                buContext.drawImage(bulletImg, 25, 25, 10, 10);
                var buDirection = direction;

                var index = setInterval(function(){
                    shoot(canId, index, buCanvas, buDirection);
                }, 20);
            }
        }

        //左
        if (event.keyCode == 37) {
            rotatePlayer(270 - direction);
            direction = 270;
            
            if (lfLeft - stepSize > leftBoundary) {
                $("#player1").offset({
                    top : lfTop,
                    left : lfLeft - stepSize
                });
            }
        }

        //上
        if (event.keyCode == 38) {
            rotatePlayer(360 - direction);
            direction = 360;

            if (lfTop - stepSize > topBoundary) {
                $("#player1").offset({
                    top : lfTop - stepSize,
                    left : lfLeft
                });
            }
        }

        //右
        if (event.keyCode == 39) {
            rotatePlayer(90 - direction);
            direction = 90;

            if (lfLeft + stepSize < rightBoundary) {
                $("#player1").offset({
                    top : lfTop,
                    left : lfLeft + stepSize
                });
            }
        }

        //下
        if (event.keyCode == 40) {
            rotatePlayer(180 - direction);
            direction = 180;
            
            if (lfTop + stepSize < bottomBoundary) {
                $("#player1").offset({
                    top : lfTop + stepSize,
                    left : lfLeft
                });
            }
        }
    });

    // 旋转
    function rotatePlayer(jd) 
    {
        var translateSize = 30;

        context.clearRect(0, 0, canvas.width, canvas.height);
        context.translate(translateSize, translateSize);
        context.rotate(jd * Math.PI / 180);
        context.translate(-translateSize, -translateSize);
        context.drawImage(img, 0, 0, 60, 60);
    }

    function playInit()
    {
        if($('#player1').length <= 0) {
            $("body").append('<div id="palyArea"><canvas id="player1" style="width:30;height:30;position:fixed;z-index:99999;"></canvas><div id="bulletArea"></div></div>');
        }
        
        // 加载遍历页面所有的元素
        $(document).find("body").each(function(i, item){
            console.log(item);
        });
        
        canvas = document.getElementById("player1");
        context = canvas.getContext("2d");
        img = new Image();
        img.src = "img/play/player.jpg";
        context.drawImage(img, 0, 0, 60, 60);
        
        bulletImg = new Image();
        bulletImg.src = "img/play/bullet.png";
        
        $("#palyArea").css({
            overflow: 'hidden',
            position: 'fixed',
            top: '0',
            left: '0',
            zindex: '9999'
        });
    }