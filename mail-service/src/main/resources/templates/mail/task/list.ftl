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
        <form class="layui-form layui-form-pane" action="">
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
                        <ul id="emailList">
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
                            <tbody id="mailList">
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

        </form>

    </div>
</div>

<script src="../common/common.js"></script>
<script src="../lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    // 页面加载时请求后台邮箱列表数据
    window.onload = function () {
        let authorization = localStorage.getItem("authorization");

        fetch(contextPath + "/task/list", {
            headers: {
                'Authorization': authorization
            }
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                // 解析返回的数据，提取邮箱列表
                if (data.code === 0) {
                    let emailList = data.data;
                    // 动态渲染邮箱列表到HTML页面中
                    let emailListContainer = document.getElementById('emailList');
                    emailListContainer.innerHTML = ''; // 清空原有内容

                    emailList.forEach(function (email) {
                        let li = document.createElement('li');
                        li.className = 'layui-nav-item';
                        li.style.height = '30px';

                        let a = document.createElement('a');
                        a.href = 'javascript:;';
                        a.innerText = email.email;
                        a.onclick = function () {
                            loadMailList(email.id);
                        };

                        li.appendChild(a);
                        emailListContainer.appendChild(li);
                    });
                } else {
                    layer.msg(data.msg);
                }
            })
            .catch(function (error) {
                console.log('请求错误：', error);
            });
    };

    // 加载邮件列表
    function loadMailList(id) {
        let authorization = localStorage.getItem("authorization");
        const params = {
            id: id
        };

        const queryString = new URLSearchParams(params).toString();
        const url = contextPath + '/task/mail/list?' + queryString;
        // 发送请求获取邮件列表数据
        fetch(url, {
            headers: {
                'Authorization': authorization
            },
            // 可根据需要设置请求参数，如邮箱地址
            // body: JSON.stringify({ email: email })
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                // 解析返回的数据，提取邮件列表
                if (data.code === 0) {
                    let mailList = data.data;

                    // 动态渲染邮件列表到HTML页面中
                    let mailListContainer = document.getElementById('mailList');
                    mailListContainer.innerHTML = ''; // 清空原有内容

                    mailList.forEach(function (mail) {
                        let tr = document.createElement('tr');

                        let td = document.createElement('td');
                        td.innerText = mail.title;

                        tr.appendChild(td);
                        mailListContainer.appendChild(tr);
                    });
                } else {
                    layer.msg(data.msg);
                }
            })
            .catch(function (error) {
                console.log('请求错误：', error);
            });
    }

    // 添加任务
    function addTask() {
        layui.use('layer', function () {
            let index = layer.open({
                title: '添加任务',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['680px', '480px'],
                content:  contextPath + '/page/task/add'

            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        });
    }

</script>
</body>
</html>