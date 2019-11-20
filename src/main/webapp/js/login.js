layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;
    var show_num = [];

    $(".loginBody .seraph").click(function(){
        layer.msg("暂未支持",{
            time:5000
        });
    });

    /**
     * 是否已经登录过
     */
    function isLogin() {
        var userId = window.sessionStorage.getItem("userId");
        return !$tool.isBlank(userId);
    }
    //登录按钮
    form.on('submit(login)', function(data) {

        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");

        var val = $("#code").val().toLowerCase();//输入的验证码

        var num = show_num.join("");

        if (val == num){
            $.post("./login_userLogin.action",{
                loginNumber : $("#loginNumber").val(),
                loginPassword : $("#loginPassword").val()
            },function(data){
                if (data.data==undefined ||JSON.stringify(data.data) == "{}"){
                    layer.msg(data.msg);
                    $("#loginPassword").val('');//置为空
                    return false;
                }
                if (data.data.userStatus!==1) {
                    layer.msg("此用户未授权登入,请联系超管");
                    return false;
                }
                if (data.code === 0 && data.data.userStatus===1){
                    //保存用户信息到session中
                    window.sessionStorage.setItem("userId",data.data.id);
                    window.sessionStorage.setItem("userCode",data.data.loginNumber);
                    window.sessionStorage.setItem("userName",data.data.userName);
                    window.sessionStorage.setItem("userType",data.data.userType);
                    window.location.href = "/ClassAttendance/page/common/index.html";
                }
            })
        }else {
            layer.msg("验证码输入错误");
        }

        $(this).text("登录").attr("disabled",false).removeClass("layui-disabled");
        $("#code").val('');//清空输入框
        draw(show_num);//从新绘制验证码

        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    });

    // Test Code
    $(function(){
        draw(show_num);
        $("#canvas").on('click',function(){
            draw(show_num);
        })
    });

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    //退出登入
    $(".signOut").click(function(){
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('退出登入中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("退出登入成功！");
        },1000);
        window.location.href = "/ClassAttendance/index.html";
    })

})
