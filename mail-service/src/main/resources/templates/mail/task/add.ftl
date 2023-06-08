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
        <label class="layui-form-label required">Email地址</label>
        <div class="layui-input-block">
            <input type="text" name="email" lay-verify="required" lay-reqtext="姓名不能为空" placeholder="请输入姓名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱类型</label>
        <div class="layui-input-block">
            <div class="layui-col-md6">
                <select name="protocolType" class="layui-input">
                    <option value="">请选择</option>
                    <option value="1">ICMP</option>
                    <option value="2">POP3</option>
                    <option value="3">Exchange</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">服务器</label>
        <div class="layui-input-block">
            <input type="text" name="host" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">SSL</label>
        <div class="layui-input-block">
            <input type="checkbox" name="hasSsl" lay-skin="switch" value="true" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">端口</label>
        <div class="layui-input-block">
            <input type="text" name="port" class="layui-input">
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
            console.log(data);
            $.ajax({
                url: contextPath + "/task/save",
                data: JSON.stringify(data),
                type:"post",
                dataType:"json",
                headers : {'Content-Type' : 'application/json;charset=utf-8', 'Authorization': authorization}, //接口json格式
                success:function(data){
                    let resultCode = data.code;
                    let resultMsg = data.msg;
                    if (resultCode == 0) {
                        location.href = contextPath + "/page/user";
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