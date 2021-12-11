<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" lay-reqtext="姓名不能为空" placeholder="请输入姓名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">手机</label>
        <div class="layui-input-block">
            <input type="number" name="phone" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">所属角色</label>
        <div class="layui-input-block">
            <select id="role" name="role">
                <option value=""></option>
                <option value="1">北京</option>
                <option value="2">上海</option>
                <option value="3">天津</option>
                <option value="4">重庆</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea name="description" class="layui-input" type="text/plain" style="resize: none; height: 100px;"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../common/common.js"></script>
<script src="../../lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    $(document).ready(function(){
        $.ajax({
            url: contextPath + "/role/findAll",
            data: JSON.stringify(data),
            type:"get",
            success:function(data){
                let resultCode = data.resultCode;
                let resultMsg = data.resultMsg;
                if (resultCode == 1000) {
                    resultCode.data;
                    $("#role").append("<option selected='selected' value='"+new_year+"'>"+new_year+"年</option>");
                } else {
                    layer.msg(resultMsg);
                }
            },
            error:function(data){
                console.log(data);
                layer.msg('操作失败');
            }
        });
    });

    layui.use(['form'], function () {
        let form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            data = data.field;
            $.ajax({
                url: contextPath + "/user/save",
                data: JSON.stringify(data),
                type:"post",
                dataType:"json",
                headers : {'Content-Type' : 'application/json;charset=utf-8'}, //接口json格式
                success:function(data){
                    let resultCode = data.resultCode;
                    let resultMsg = data.resultMsg;
                    if (resultCode == 1000) {
                        location.href = contextPath + "/page/index";
                    } else {
                        layer.msg(resultMsg);
                    }
                },
                error:function(data){
                    console.log(data);
                    layer.msg('操作失败');
                }
            });

            return false;
        });

    });
</script>
</body>
</html>