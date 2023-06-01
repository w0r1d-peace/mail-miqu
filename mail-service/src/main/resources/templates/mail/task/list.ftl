<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>menu</title>
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <style>
        .layui-btn:not(.layui-btn-lg ):not(.layui-btn-sm):not(.layui-btn-xs) {
            height: 34px;
            line-height: 34px;
            padding: 0 8px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-row">
            <!-- 管理邮箱列表区域 -->
            <div class="layui-col-md2">
                <div class="layui-card">
                    <div class="layui-card-header">
                        <span>邮箱账户</span>
                        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" style="float:right; margin-top: 3px;" onclick="addTask()">
                            <i class="layui-icon layui-icon-add-1"></i>
                        </button>
                    </div>
                    <div class="layui-card-body">
                        <ul>
                            <!-- 邮箱列表项 -->
                            <li class="layui-nav-item" style="height: 30px;">
                                <a href="javascript:;">example1@example.com</a>
                            </li>
                            <li class="layui-nav-item" style="height: 30px;">
                                <a href="javascript:;">example2@example.com</a>
                            </li>
                            <!-- 更多邮箱列表项... -->
                        </ul>
                    </div>
                </div>
            </div>

            <!-- 邮件列表区域 -->
            <div class="layui-col-md3">
                <div class="layui-card">
                    <div class="layui-card-header">邮件列表</div>
                    <div class="layui-card-body">
                        <table class="layui-table">
                            <colgroup>
                                <col width="300">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>主题</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- 邮件列表项 -->
                            <tr>
                                <td>邮件主题1</td>
                            </tr>
                            <tr>
                                <td>邮件主题2</td>
                            </tr>
                            <!-- 更多邮件列表项... -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- 邮件内容区域 -->
            <div class="layui-col-md7">
                <div class="layui-card">
                    <div class="layui-card-header">邮件内容</div>
                    <div class="layui-card-body">
                        <div class="email-content">
                            <h3>邮件主题</h3>
                            <p>发件人：example@example.com</p>
                            <p>收件时间：2023-05-31 10:00:00</p>
                            <hr>
                            <!-- 邮件正文内容 -->
                            <p>亲爱的用户，</p>
                            <p>这是一封示例邮件...</p>
                            <p>此致，</p>
                            <p>示例团队</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

    </div>
</div>

<script src="../common/common.js"></script>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    function addTask() {
        layui.use('layer', function () {
            layer.open({
                type: 2,
                area: ['680px', '480px'],
                content: contextPath + '/page/task/add',
                title: '新建帐号',
                fixed: false, // 不固定
                maxmin: true,
                shadeClose: true,
                btn: ['创建', '取消'],
                btnAlign: 'c',
                yes: function(index, layero) {
                    console.log(111);
                    let email = $('input[name="email"]').val();
                    console.log(email);
                }
            })
        });
    }

</script>
</body>
</html>