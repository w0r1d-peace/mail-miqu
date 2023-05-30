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
        <label class="layui-form-label required">父级</label>
        <div class="layui-input-block">
            <select name="parentId" lay-filter="aihao">
                <option value=""></option>
                <option value="0">写作</option>
                <option value="1" selected="">阅读</option>
                <option value="2">游戏</option>
                <option value="3">音乐</option>
                <option value="4">旅行</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">权限名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" lay-reqtext="名称不能为空" placeholder="请输入名称" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">唯一标识</label>
        <div class="layui-input-block">
            <input type="text" name="key" lay-verify="required" lay-reqtext="唯一标识不能为空" placeholder="请输入唯一标识" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">路径</label>
        <div class="layui-input-block">
            <input type="text" name="url" lay-verify="required" lay-reqtext="路径不能为空" placeholder="请输入路径" value="" class="layui-input">
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
    layui.use(['form'], function () {
        let form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        let authorization = localStorage.getItem("authorization");
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            data = data.field;
            $.ajax({
                url: contextPath + "/user/save",
                data: JSON.stringify(data),
                type:"post",
                dataType:"json",
                headers : {'Content-Type' : 'application/json;charset=utf-8', 'Authorization': authorization}, //接口json格式
                success:function(data){
                    let resultCode = data.code;
                    let resultMsg = data.msg;
                    if (resultCode == 0) {
                        location.href = contextPath + "/page/permission";
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