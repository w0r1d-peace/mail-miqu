<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>menu</title>
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <link rel="stylesheet" href="../css/mail/task/list.css" media="all">
    <!-- 其他代码... -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.infinitescroll/3.0.5/jquery.infinitescroll.min.js"></script>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-row">
                <!-- 管理邮箱列表区域 -->
                <div class="layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            <span>邮箱账户</span>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" style="float:right; margin-top: 3px;" onclick="addTask()">
                                <i class="layui-icon layui-icon-add-1"></i>
                            </button>
                        </div>
                        <div class="layui-card-body">
                            <ul id="emailList" class="email-list scroll-pagination">
                                <!-- 邮箱列表项 -->
                                <!-- 更多邮箱列表项... -->
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- 邮件列表区域 -->
                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">邮件列表</div>
                        <div class="layui-card-body">
                            <div id="mailList" class="scroll-pagination">
                                <!-- 邮件列表项 -->
                                <!-- 更多邮件列表项... -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 邮件内容区域 -->
                <div class="layui-col-md5">
                    <div class="layui-card">
                        <div class="layui-card-header">邮件内容</div>
                        <div class="layui-card-body">
                            <div id="mailContent" class="mail-content">
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-infinitescroll/3.0.5/infinite-scroll.pkgd.js"></script>
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

        let page = 1;
        let isLoading = false;

        if (isLoading) return;
        isLoading = true;

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

                    let mailListContainer = $('#mailList');

                    mailListContainer.html("");
                    mailList.forEach(function (mail) {
                        let mailItem = $('<div class="mail-item"><span>' + mail.title + '</span></div>');
                        mailListContainer.append(mailItem);
                    });

                    page++;
                    isLoading = false;
                } else {
                    layer.msg(data.msg);
                }
            })
            .catch(function (error) {
                console.log('请求错误：', error);
                isLoading = false;
            });
    }

    /**
     * 查看邮件详情
     * @param mailId
     */
    function viewMail(mailId) {
        fetch('获取邮件内容的接口URL?mailId=' + mailId, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                var mail = data.mail;
                document.getElementById('mailSubject').innerHTML = mail.title;
                document.getElementById('mailSender').innerHTML = '发件人：' + mail.sender;
                document.getElementById('mailTime').innerHTML = '收件时间：' + mail.time;
                document.getElementById('mailBody').innerHTML = mail.body;
            })
            .catch(function (error) {
                console.log('请求错误：', error);
            });
    }

/*    // 使用Infinite Scroll库来实现无限滚动效果
    $('#mailList').infinitescroll({
        navSelector: '#paginationLoader',
        nextSelector: '.pagination a',
        itemSelector: '.mail-item',
        loading: {
            finishedMsg: '没有更多邮件了',
            msgText: '加载中...',
            img: 'https://i.imgur.com/6RMhx.gif',
            speed: 'slow'
        },
        behavior: 'local',
        maxPage: 5, // 最大加载页数
        debug: false,
        errorCallback: function () {
            console.log('请求错误');
        },
        path: function () {
            return '后台获取邮件列表数据的接口URL?page=' + page;
        }
    });*/

    // 添加任务
    function addTask() {
        layui.use('layer', function () {
            let index = layer.open({
                title: '添加任务',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['680px', '550px'],
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