layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //新增,更新
        var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/ClassAttendance/biz/college_save.action" : "/ClassAttendance/biz/college_update.action", {
            id: updateFlag === '0' ? null : $(".Id").val(),//id
            collegeName: $(".collegeName").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加学院成功！" : "修改学院成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                top.layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

});