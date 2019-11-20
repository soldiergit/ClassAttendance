layui.use(['form', 'layer','util' , 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$,
        util = layui.util,
        laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({elem: '.attendanceTime',trigger: 'click', type: 'date', done: function (value, date, endDate) {}});

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/ClassAttendance/biz/attendance_update.action", {
            id: $(".Id").val(),//id
            attendanceTime: util.toDateString($(".attendanceTime").val(), "yyyy-MM-dd"),
            attendanceType: $("#attendanceType").val(),
            teacherCode: $("#teacherCodeHide").val(),
            studentId: $("#studentHide").val(),
            courseId: $("#courseHide").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加考勤成功！" : "修改考勤成功！");
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