layui.config({
    base: '/ClassAttendance/layui/layui_exts/'
}).extend({
    excel: 'excel'
}).use(['form', 'layer', 'table', 'laytpl','util','excel'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$,
        laytpl = layui.laytpl,
        util = layui.util,
        excel = layui.excel,
        table = layui.table;

    //信息列表
    var tableLoad = layer.load(1);
    var dataLen = 0;//判断是否有记录，用于导出时校验
    var teacherCode = window.sessionStorage.getItem("userCode");
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/ClassAttendance/biz/attendance_findByTea.action?teacherCode='+teacherCode,//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'studentName', title: '被考勤学生', minWidth: 100, align: 'center'},
            {field: 'courseName', title: '考勤课程', minWidth: 100, align: 'center'},
            {field: 'attendanceType', title: '考勤情况', minWidth: 100, align: 'center', templet:function(d){
                    dataLen = 1;
                    if (d.attendanceType === 1) return '出勤';
                    if (d.attendanceType === 2) return '请假';
                    if (d.attendanceType === 3) return '迟到';
                    if (d.attendanceType === 4) return '旷课';
                }},
            {field: 'attendanceTime', title: '考勤时间', minWidth: 100, align: 'center'},
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
                $.post("/ClassAttendance/biz/attendance_deleteBatch.action", {
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
                $.get("/ClassAttendance/biz/attendance_delete.action", {
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
            title: "修改",
            type: 2,
            content: "attendance-add.html",
            maxmin: true,
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.id);
                    body.find("#courseHide").val(edit.courseId);            //隐藏
                    body.find(".courseName").val(edit.courseName).prop("disabled", true);
                    body.find("#studentCodeHide").val(edit.studentCode);    //隐藏
                    body.find(".studentName").val(edit.studentName).prop("disabled", true);
                    body.find(".attendanceTime").val(edit.attendanceTime).prop("disabled", true);
                    body.find("#teacherCodeHide").val(edit.teacherCode);    //隐藏
                    body.find("#attendanceType").val(edit.attendanceType);
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
            content: "attendance-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find(".studentName").val(data.studentName);
                body.find(".courseName").val(data.courseName);
                if (data.attendanceType === 1) body.find(".attendanceType").val('出勤');
                if (data.attendanceType === 2) body.find(".attendanceType").val('请假');
                if (data.attendanceType === 3) body.find(".attendanceType").val('迟到');
                if (data.attendanceType === 4) body.find(".attendanceType").val('旷课');
                body.find(".attendanceTime").val(data.attendanceTime);
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

    /**
     *  1-管理员导出全部， 2-老师导出全部， 3-学生导出全部， 4-根据id批量导出
     */
    //批量导出
    $(".exportBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].id);
            }
            $.ajax({
                url: '/ClassAttendance/biz/attendance_findByExport.action',
                data: {ids : newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        var dataIndex = 0;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            id: function (value, line, data) {
                                dataIndex += 1;
                                return dataIndex;
                            },
                            courseName: 'courseName',
                            studentName: 'studentName',
                            attendanceTime: 'attendanceTime',
                            attendanceType: function(value, line, data) {
                                if (value === 1) value='出勤';
                                if (value === 2) value='请假';
                                if (value === 3) value='迟到';
                                if (value === 4) value='旷课';
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            }
                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({id: '编号', courseName: '课程名词', studentName: '学生姓名', attendanceTime: '考勤时间', attendanceType: '考勤情况'});

                        excel.exportExcel({
                            sheet1: data
                        }, '学生考勤记录.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });
    //全部导出
    $(".exportAllBtn").click(function(){
        if (dataLen === 0) {
            layer.msg("无考勤记录！");
            return false;
        } else {
            $.ajax({
                url: '/ClassAttendance/biz/attendance_findByExport.action',
                data: {teacherCode: teacherCode, exportCode: 2},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        var dataIndex = 0;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            id: function (value, line, data) {
                                dataIndex += 1;
                                return dataIndex;
                            },
                            courseName: 'courseName',
                            studentName: 'studentName',
                            attendanceTime: 'attendanceTime',
                            attendanceType: function(value, line, data) {
                                if (value === 1) value='出勤';
                                if (value === 2) value='请假';
                                if (value === 3) value='迟到';
                                if (value === 4) value='旷课';
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            }
                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({id: '编号', courseName: '课程名词', studentName: '学生姓名', attendanceTime: '考勤时间', attendanceType: '考勤情况'});

                        excel.exportExcel({
                            sheet1: data
                        }, '学生考勤记录.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }
    });
});