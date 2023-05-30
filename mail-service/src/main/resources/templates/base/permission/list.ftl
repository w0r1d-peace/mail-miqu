<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">权限名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">key</label>
                            <div class="layui-input-inline">
                                <input type="text" name="key" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../common/common.js"></script>
<script src="../lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        let $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        let authorization = localStorage.getItem("authorization");
        table.render({
            elem: '#currentTableId',
            url: contextPath + '/permission/page',
            toolbar: '#toolbarDemo',
            headers : {'Authorization' : authorization},
            parseData: function(res) { //res 即为原始返回的数据
                let resData = res.data;
                return {
                    "code": 0,
                    "msg": res.msg,
                    "count": resData.totalCount,//解析数据总条数
                    "data": resData.list //解析数据列表
                }
            },
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'parentName', width: 150, title: '父级权限名称'},
                {field: 'name', width: 120, title: '权限名称', sort: true},
                {field: 'key', width: 150, title: '唯一标识'},
                {field: 'url', width: 200, title: '路径', sort: true},
                {field: 'creator', width: 200, title: '创建人', sort: true},
                {field: 'createTime', width: 200, title: '创建时间', sort: true},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            let result = data.field;
            let username = result.username;
            let phone = result.phone;
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    username: username,
                    phone: phone
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                let index = layer.open({
                    title: '添加权限',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content:  contextPath + '/page/permission/add'

                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                let checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                let count = 0;
                let ids = new Array() ;
                for (let i in data) {
                    let obj = data[i];
                    ids[i] = obj.id;
                    ++count;
                }
                if (count == 0) {
                    layer.alert("请选择要删除的数据");
                }

                let result = JSON.parse('{"ids":['+ids+']}');
                ajaxPostAsyncData(contextPath + '/permission/delete', result, function(d){
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {

        });

        table.on('tool(currentTableFilter)', function (obj) {
            let data = obj.data;
            if (obj.event === 'edit') {
                let index = layer.open({
                    title: '编辑权限',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: contextPath + '/page/permission/edit',
                    success: function (layero, index) {
                        let body = layui.layer.getChildFrame('body', index);
                        body.find("input[name='id']").val(data.id);
                        body.find("input[name='name']").val(data.name);
                        body.find("input[name='phone']").val(data.phone);
                        body.find("textarea[name='description']").val(data.description);
                        form.render();
                        setTimeout(function () {
                            layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500)
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('您确定要删除该权限吗？', function (index) {
                    obj.del();
                    layer.close(index);
                });
                let result = JSON.parse('{"ids":['+data.id+']}');
                ajaxPostAsyncData(contextPath + '/permission/delete', result, function(d){

                });
            }
        });

    });
</script>

</body>
</html>