layui.use(['form', 'layer'], function () {
    var form = layui.form,
        $ = layui.$,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //添加验证规则
    form.verify({

        // nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
        //     if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        //         return '用户名不能有特殊字符';
        //     }
        //     if(/(^\_)|(\__)|(\_+$)/.test(value)){
        //         return '用户名首尾不能出现下划线\'_\'';
        //     }
        //     if(/^\d+\d+\d$/.test(value)){
        //         return '用户名不能全为数字';
        //     }
        // },

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        newPassword: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ],

        //确认密码
        rePassword: function (value) {
            if (value !== $('.newPassword').val()) {
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    //修改密码
    form.on("submit(setPassword)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //修改密码
        $.ajax({
            type: "POST",
            url: '/ClassAttendance/login_updatePassword.action',//数据接口
            data: {
                loginPassword: data.field.oldPassword,
                newPassword: data.field.rePassword,
                userId: window.sessionStorage.getItem("userId")
            },
            success: function (data) {
                if (data.code===2) {
                    layer.close(index);
                    top.layer.msg("原密码错误，请重新输入！");
                } else if (data.code===0) {
                    setTimeout(function () {
                        layer.close(index);
                        layer.msg("修改成功,请重新登入!");
                    }, 1000);
                    window.sessionStorage.clear();
                    window.localStorage.clear();
                    parent.location.href = "http://127.0.0.1:8080/ClassAttendance/index.html";
                }
            },
            error: function () {
                layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});