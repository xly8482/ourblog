var stepSize = 5;
    var bodyMaxWidth = 600;
    var bodyMaxHeight = 400;
    var canvas;
    var context;
    var img;
    var direction=0;
    
    var shooting;
    var buCanvas;
    var buContext;
    var bulletImg;
    var buDirection=0;
    var ifShoot=false;
    
    function shoot()
    {
        var bfLeft = $("#bullet1").offset().left;
        var bfTop = $("#bullet1").offset().top;
        
        if(buDirection == 90)
        {
            if(bfLeft+stepSize < bodyMaxWidth)
            {
                $("#bullet1").offset({top:bfTop,left:bfLeft+stepSize});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                ifShoot=false;
                clearInterval(shooting);
            }
        }
        else if(buDirection == 0 || buDirection == 360)
        {
            if(bfTop-stepSize > 0)
            {
                $("#bullet1").offset({top:bfTop-stepSize,left:bfLeft});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                ifShoot=false;
                clearInterval(shooting);
            }
        }
        else if(buDirection == 180)
        {
            if(bfTop+stepSize < bodyMaxHeight)
            {
                $("#bullet1").offset({top:bfTop+stepSize,left:bfLeft});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                ifShoot=false;
                clearInterval(shooting);
            }
        }
        else if(buDirection == 270)
        {
            if(bfLeft-stepSize > 0)
            {
                $("#bullet1").offset({top:bfTop,left:bfLeft-stepSize});
            }
            else
            {
                buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
                ifShoot=false;
                clearInterval(shooting);
            }
        }
        else
        {
            buContext.clearRect(0, 0, buCanvas.width, buCanvas.height);
            ifShoot=false;
            clearInterval(shooting);
        }
    }

    $(document).keydown(function(event){
        var lfLeft = $("#player1").offset().left;
        var lfTop = $("#player1").offset().top;
        
        //发射子弹
        if(event.keyCode == 65 || event.keyCode == 32)
        {
            if(!ifShoot)
            {
                ifShoot = true;
                //产生子弹
                $("#bullet1").offset({top:lfTop,left:lfLeft});
                buCanvas = document.getElementById("bullet1");
                buContext = buCanvas.getContext("2d");
                bulletImg = new Image();
                bulletImg.src = "img/play/bullet.png";
                
                buContext.drawImage(bulletImg, 25, 25, 10, 10);
                buDirection = direction;

                shooting = setInterval(shoot, 20);
            }
        }

        //左
        if (event.keyCode == 37) {
            rotatePlayer(270 - direction);
            direction = 270;
            
            if (lfLeft - stepSize > 0) {
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

            if (lfTop - stepSize > 0) {
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

            if (lfLeft + stepSize < bodyMaxWidth) {
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
            
            if (lfTop + stepSize < bodyMaxHeight) {
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

    $(document).ready(function() {
        canvas = document.getElementById("player1");
        context = canvas.getContext("2d");
        img = new Image();
        img.src = "img/play/player.jpg";
        context.drawImage(img, 0, 0, 60, 60);

        $("#palyArea").css({
            width : bodyMaxWidth + 50,
            height : bodyMaxHeight + 50
        });
    });