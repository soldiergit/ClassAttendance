layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
    //渲染下拉数据
    $.post("/ClassAttendance/biz/teacher_findAll.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#teacherId').append(new Option(item.teacherName, item.id));
        });
        if (updateFlag === '1') $("#teacherId").val($(".teacherHide").val());//如果是更新，使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/ClassAttendance/biz/course_save.action" : "/ClassAttendance/biz/course_update.action", {
            id: updateFlag === '0' ? null : $(".Id").val(),//id
            courseName: $(".courseName").val(),
            courseYear: $(".courseYear").val(),
            courseTerm: $(".courseTerm").val(),
            courseHour: $(".courseHour").val(),
            courseCapacity: $(".courseCapacity").val(),//课程最大容量
            courseResidual: updateFlag === '0' ? $(".courseCapacity").val() : $(".courseResidualHide").val(),//剩余容量
            teacherId: $("#teacherId").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加课程成功！" : "修改修改成功！");
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