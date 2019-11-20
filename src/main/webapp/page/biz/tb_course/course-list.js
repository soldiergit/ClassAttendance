layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$,
        laytpl = layui.laytpl,
        table = layui.table;

    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/ClassAttendance/biz/course_findByPage.action',//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'courseName', title: '课程名称', minWidth: 100, align: 'center'},
            {field: 'teacherName', title: '开课老师', minWidth: 100, align: 'center', templet:function(d){
                    return d.tbTeacherEntity.teacherName;
                }},
            {field: 'courseYear', title: '学年', minWidth: 100, align: 'center'},
            {field: 'courseTerm', title: '学期', minWidth: 100, align: 'center'},
            {field: 'courseHour', title: '学时', minWidth: 100, align: 'center'},
            {field: 'courseCapacity', title: '课程容量', minWidth: 100, align: 'center'},
            {field: 'courseResidual', title: '剩余容量', minWidth: 100, align: 'center'},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]],done:function (res) {
            layer.close(tableLoad);    //返回数据关闭loading
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                // 重新从第 1 页开始
                page: {curr: 1},
                // 条件
                where: {key: dataReload.val()}
            }, 'data');
        }
    };
    $('.reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function () {
        addUser();
    });

    //批量删除
    $(".delBtn").click(function () {
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].id);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/ClassAttendance/biz/course_deleteBatch.action", {
                    ids: newsId.join(',') //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的教师");
        }
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/ClassAttendance/biz/course_delete.action", {
                    id: data.id  //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'detail') {
            detailUser(data);
        }
    });

    //添加
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "course-add.html",
            maxmin: true,
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.id);
                    body.find(".courseName").val(edit.courseName);
                    body.find(".teacherHide").val(edit.tbTeacherEntity.id);//使用中间变量渲染下拉框
                    body.find(".courseYear").val(edit.courseYear);
                    body.find(".courseTerm").val(edit.courseTerm);
                    body.find(".courseHour").val(edit.courseHour);
                    body.find(".courseCapacity").val(edit.courseCapacity);
                    body.find(".courseResidualHide").val(edit.courseResidual);//使用中间变量记录剩余容量
                    body.find(".updateFlag").val(1);//更新标识
                    form.render();
                }
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //用户详情
    function detailUser(data) {
        var index = layui.layer.open({
            title: "详情",
            type: 2,
            content: "course-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find(".courseName").val(data.courseName);
                body.find(".teacherName").val(data.tbTeacherEntity.teacherName);
                body.find(".courseYear").val(data.courseYear);
                body.find(".courseTerm").val(data.courseTerm);
                body.find(".courseHour").val(data.courseHour);
                body.find(".courseCapacity").val(data.courseCapacity);
                body.find(".courseResidual").val(data.courseResidual);
                form.render();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});