layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$;

    //新增,更新
    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
    //渲染下拉数据
    $.post("/ClassAttendance/biz/class_findAll.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#classId').append(new Option(item.className, item.id));
        });
        if (updateFlag === '1') $("#classId").val($(".classHide").val());//如果是更新，使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/ClassAttendance/biz/student_save.action" : "/ClassAttendance/biz/student_update.action", {
            id: updateFlag === '0' ? null : $(".Id").val(),//id
            studentCode: $(".studentCode").val(),
            studentName: $(".studentName").val(),
            studentSex: data.field.studentSex,
            studentPhone: $(".studentPhone").val(),
            studentEmail: $(".studentEmail").val(),
            classId: $("#classId").val()
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加学生成功！" : "修改学生成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else if (res.code === 444) {
                layer.close(index);
                layer.msg(res.msg, { icon: 5, time: 2000, shade: [0.6, '#000', true] });
            } else {
                top.layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });
});