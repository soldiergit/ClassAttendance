layui.use(['form','layer','upload','laydate','util'],function(){
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        util = layui.util,
        laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({elem: '#teacherBirth',trigger: 'click', type: 'date', done: function (value, date, endDate) {}});
    laydate.render({elem: '#entryTime',trigger: 'click', type: 'date', done: function (value, date, endDate) {}});

    //渲染复选框数据
    $.post("/ClassAttendance/biz/dept_findAllParent.action", function (data) {//--所属部门
        $.each(data.data, function (index, item) {
            $('#deptId').append(new Option(item.deptName, item.deptId));
        });
        $('#deptId').val($('.deptHide').val()); //部门单选框选中
        //重新渲染select
        form.render('select');
    });
    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/ClassAttendance/biz/dept_findSubordinate.action?deptId="+deptId, function (data) {//--所属教研室
        $.each(data.data, function (index, item) {
            $('#unitIds').append('<input type="checkbox" name="unitIds" value="'+item.deptId+'" lay-skin="primary" title="'+item.deptName+'"/>');
        });
        var arr = $('.unitHide').val().split(',');  //教研室复选框选中
        if (arr != "" && arr.length != 0) {
            for ( var i = 0; i <arr.length; i++){
                $(".unitIds input[value="+arr[i]+"]").prop("checked",true);
            }
        }
        //重新渲染checkbox
        form.render('checkbox');
    });
    // $.post("/ClassAttendance/role_findByOtherRole.action", function (data) {
    //     $.each(data.data, function (index, item) {
    //         $('#roleSet').append('<input type="checkbox" name="userTypes" value="'+item.userType+'" lay-skin="primary" title="'+item.roleName+'"/>');
    //     });
    //     var arr = $('.roleHide').val().split(',');  //角色复选框选中

    //     if (arr != "" && arr.length != 0) {
    //          for ( var i = 0; i <arr.length; i++){
    //              $(".roleSet input[value="+arr[i]+"]").prop("checked",true);
    //          }
    //     }
    //     //重新渲染checkbox
    //     form.render('checkbox');
    // });

    //信息列表
    var teacherId = window.sessionStorage.getItem("teacherId");
    $.ajax({
        type: "POST",
        url: '/ClassAttendance/biz/teacher_findById.action',//数据接口
        data: {teacherId: teacherId},
        success: function (data) {
            if (data.code === 0) {
                //infoList 即 class="layui-form" 所在元素对应的 lay-filter="" 对应的值
                form.val("userInfo", {//表单回显
                    "teacherId": data.data.teacherId,  //"name": "value",就是表单元素的name
                    "teacherCode": data.data.teacherCode,
                    "teacherName": data.data.teacherName,
                    // "deptId": data.data.deptId+"",  //所属部门下拉框选中:https://blog.csdn.net/zengqifeng1997/article/details/84996017
                    "teacherSex": data.data.teacherSex+"",  //性别单选框选中
                    "teacherBirth": util.toDateString(data.data.teacherBirth, "yyyy-MM-dd"),
                    "entryTime": util.toDateString(data.data.entryTime, "yyyy-MM-dd"),
                    "teacherImg": data.data.teacherImg,
                    "highEdu": data.data.highEdu,
                    "firstEdu": data.data.firstEdu,
                    "technicalPost": data.data.technicalPost,
                    "administPost": data.data.administPost,
                    "teacherResume": data.data.teacherResume,
                    "deptHide": data.data.deptId,  //中间变量--防止用户重复刷新
                    "unitHide": data.data.unitIds,  //中间变量--防止用户重复刷新
                    "userType": data.data.userType,
                    "roleName": data.data.roleName,
                    "other": data.data.other
                });
                $('#imgPreview').attr('src', data.data.teacherImg); //图片显示
                $('.teacherCode').prop("disabled", true);
                $('.teacherName').prop("disabled", true);
                $('.roleName').prop("disabled", true);
                $('#deptId').prop("disabled", true);

                if (data.data.unitIds != null) {
                    var arr = data.data.unitIds.split(',');  //教研室复选框选中
                    if (arr != "" && arr.length != 0) {
                        for ( var i = 0; i <arr.length; i++){
                            $(".unitIds input[value="+arr[i]+"]").prop("checked",true);
                        }
                    }
                    //重新渲染checkbox
                    form.render('checkbox');
                }
                form.render();
            } else {
                layer.msg("未知错误，请联系管理员！" + data.msg);
            }
        },
        error: function () {
            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
        }

    });

    //上传头像
    var uploadInst = upload.render({
        elem: '#imgUpload',
        // url: '/ClassAttendance/upload_uploadTeacherImg.action',
        url: '/ClassAttendance/upload_uploadImage.action',
        size: 100,
        acceptMime: 'image/*',  //只显示图片文件
        auto: false,
        bindAction: '#startUpload',
        //文件提交上传前的回调
        before: function(obj){
            layer.load(); //上传loading
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                // console.log(file);//里面的名称是选中时文件的名称
                $('#imgPreview').attr('src', result); //图片链接（base64）
            });
        },
        //执行上传请求后的回调。
        done: function(res){
            if (res.code===0){
                layer.msg('上传成功!');
                $('#imgPath').val(res.src);
            }else {
                layer.msg('上传失败!');
            }
            layer.closeAll('loading'); //关闭loading
        },
        //执行上传请求出现异常的回调
        error: function(){
            //演示失败状态，并实现重传
            var reUpload = $('#reUpload');
            reUpload.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            reUpload.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    form.on("submit(addUser)",function(data){
        layer.confirm('确定要修改吗？', {icon: 3, title: '提示信息'}, function (index) {
            // var ids = [];
            // var names = [];
            // $('input[name="userTypes"]:checked').each(function(){
            //     ids.push($(this).val());//将选中的值添加到数组chk_value中
            //     names.push(this.title);//将选中的值添加到数组chk_value中
            // });
            //所属教研室
            var unitId = [];
            $('input[name="unitIds"]:checked').each(function(){
                unitId.push($(this).val());//将选中的值添加到数组chk_value中
            });
            //弹出loading
            var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            // 实际使用时的提交信息
            $.post("/ClassAttendance/biz/teacher_update.action",{

                teacherId : $(".teacherId").val(),//id
                teacherCode : $(".teacherCode").val(),  //登录名
                teacherName : $(".teacherName").val(),
                deptId : $(".deptId").val(),//部门
                unitIds : unitId.join(','),//教研室
                // userTypes : ids.join(','),//角色
                userType : $(".userType").val(),//角色
                roleName : $(".roleName").val(),//角色名称
                teacherSex : data.field.teacherSex,  //性别
                theTeacherBirth : $(".teacherBirth").val(),
                theTeacherEntryTime : $(".entryTime").val(),
                teacherImg : $(".teacherImg").val(),  //头像
                highEdu : $(".highEdu").val(),
                firstEdu : $(".firstEdu").val(),
                technicalPost : $(".technicalPost").val(),
                administPost : $(".administPost").val(),
                teacherResume : $(".teacherResume").val(),
                other : $(".other").val(),
            },function(res){
                if (res.code === 0){
                    top.layer.msg("修改成功!");
                }else {
                    top.layer.msg("操作失败！");
                }
                layer.close(index);
                form.render();
            });
            return false;
        })
    });
});