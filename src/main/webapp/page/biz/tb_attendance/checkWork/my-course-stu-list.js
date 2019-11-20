layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$,
        laytpl = layui.laytpl,
        table = layui.table;

    //渲染下拉数据
    var teacherCode = window.sessionStorage.getItem("userCode");
    $.post("/ClassAttendance/biz/course_findAllByTeacher.action?teacherCode="+teacherCode, function (data) {
        $('#courseId').append(new Option('请选择课程', '########'));
        $.each(data.data, function (index, item) {
            $('#courseId').append(new Option(item.courseName, item.id));
        });
        //重新渲染select
        form.render('select');
    });

    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/ClassAttendance/biz/student_findByCourse.action',//数据接口
        height: "full-125",//容器高度
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {field: 'studentCode', title: '学号', minWidth: 100, align: 'center'},
            {field: 'studentName', title: '姓名', minWidth: 100, align: 'center'},
            {field: 'className', title: '所属班级', minWidth: 100, align: 'center', templet:function(d){
                    return d.tbClassEntity.className;
                }},
            {field: 'studentSex', title: '性别', minWidth: 20, align: 'center', templet:function(d){
                    return d.studentSex === 1 ? '男' : '女';
                }},
            {field: 'studentPhone', title: '电话', minWidth: 75, align: 'center'},
            {field: 'checkWorkType', title: '目前出勤状态', minWidth: 20, align: 'center'},
            {field: 'attendanceType',title: '出勤状态', minWidth: 100, templet: '#infoListBar', unresize: true, align: 'center'}
        ]],done:function (res) {
            //由于layui 设置了超出隐藏，所以这里改变下，以兼容操作按钮的下拉菜单
            // $(".layui-table-body, .layui-table-box, .layui-table-cell").css('overflow', 'visible');
            layer.close(tableLoad);    //返回数据关闭loading
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                // 条件
                where: {key: dataReload.val(), courseId: $('#courseId').val()}
            }, 'data');
        }
    };
    $('.reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    var checkWorkStu = [];

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'attendance') {    //出勤
            obj.update({
                checkWorkType: '出勤'
            });
            addStu(data.id, data.studentName, 1);
        } else if (layEvent === 'leave') {  //请假
            obj.update({
                checkWorkType: '请假'
            });
            addStu(data.id, data.studentName, 2);
        } else if (layEvent === 'late') {   //迟到
            obj.update({
                checkWorkType: '迟到'
            });
            addStu(data.id, data.studentName, 3);
        } else if (layEvent === 'truancy') {//旷课
            obj.update({
                checkWorkType: '旷课'
            });
            addStu(data.id, data.studentName, 4);
        }
    });

    /**
     * addStu:      添加考勤学生
     * deleteStu:   删除已存在的
     */
    function addStu(studentId, studentName, attendanceType) {
        var check = {};
        deleteStu(studentId);           //先删除
        check.studentId = studentId;
        check.studentName = studentName;
        check.attendanceType = attendanceType;
        checkWorkStu.push(check);       //再添加
    }
    function deleteStu(studentId) {
        for(var i=0;i<checkWorkStu.length;i++){
            if (checkWorkStu[i].studentId == studentId){
                checkWorkStu.splice(i,1)
            }
        }
    }

    $('#checkWork').on('click', function(){
        var courseId = $('#courseId').val();
        if (courseId === '########' || checkWorkStu.length == 0) {
            layer.msg("请选择学生进行考勤！", { icon: 5, time: 2000, shade: [0.6, '#000', true] });
            return false;
        }
        layer.confirm('确定保存考勤记录？', {icon: 3, title: '提示信息'}, function (index) {
            $.post("/ClassAttendance/biz/attendance_checkWork.action", {
                courseId: $('#courseId').val(),
                courseName: $('#courseId option:checked').text(),
                teacherCode: teacherCode,
                checkWorkStu: JSON.stringify(checkWorkStu)  //考勤情况
            }, function (data) {
                if (data.code === 0) {
                    layer.msg("考勤成功");
                } else {
                    layer.msg("考勤失败");
                }
                tableIns.reload();
                layer.close(index);
            })
        });
    });
});