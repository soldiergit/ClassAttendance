layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/ClassAttendance/biz/attendance_update.action", {
            id: $(".Id").val(),//id
            courseId: $("#courseHide").val(),
            courseName: $(".courseName").val(),
            studentCode: $("#studentCodeHide").val(),
            studentName: $(".studentName").val(),
            attendanceTime: $(".attendanceTime").val(),
            teacherCode: $("#teacherCodeHide").val(),

            attendanceType: $("#attendanceType").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg("修改考勤成功！");
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