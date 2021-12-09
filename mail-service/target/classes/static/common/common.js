let contextPath = 'http://localhost:8080/mail';

function getContextPath(){
    return contextPath;
}

/**
 * 获取URL参数
 */
function getQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

/**
 * 获取应用的上下文环境
 */
function getUrl() {
    let href = window.location.href;
    let strContext = href.substring(0, href.indexOf("/",
        (href.indexOf("//") + 2)) + 1);
    return strContext;
}

/**
 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),"state": 1} successfn 成功回调函数
 * 后端异步提交方法
 */
function ajaxPost(url, data, successfn) {
    data = (data == null || data == "" || typeof (data) == "undefined") ? {
        "date" : new Date().getTime()
    } : data;
    $.ajax( {
        type : "post",
        data : data,
        url : url,
        dataType : "json",
        contentType : "application/json;charset=UTF-8",
        beforeSend : function(XMLHttpRequest) {
            jzts();
        },
        success : function(d) {
            if (d.code == "40002") {
                $.jBox.tip('数据解析错误!', 'error');
                hangge();
                return;
            }
            successfn(d);
        },
        complete : function(XMLHttpRequest, textStatus) {
            hangge();
        },
        error : function(e) {
            hangge();
        }
    });
};

/**
 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),"state": 1} successfn 成功回调函数
 * 前端异步提交方法
 */
function ajaxWeb(url, data, successfn) {
    data = (data == null || data == "" || typeof (data) == "undefined") ? {
        "date" : new Date().getTime()
    } : data;
    $.ajax( {
        type : "post",
        data : data,
        url : url,
        dataType : "json",
        contentType : "application/json;charset=UTF-8",
        beforeSend : function(XMLHttpRequest) {
        },
        success : function(d) {
            if (d.code == "40002") {
                $.jBox.tip('数据解析错误!', 'error');
                return;
            }
            successfn(d);
        },
        complete : function(XMLHttpRequest, textStatus) {
        },
        error : function(e) {
        }
    });
};


/*
 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(),"state": 1} successfn 成功回调函数
 * ajax前端同步方法
 * 没tip等方法
 */
function ajaxPostAsyncData(url, data, successfn) {
    data = (data == null || data == "" || typeof (data) == "undefined") ? {
        "date" : new Date().getTime()
    } : data;
    $.ajax( {
        type : "post",
        data : data,
        async: false,
        url : url,
        dataType : "json",
        contentType : "application/json;charset=UTF-8",
        beforeSend : function(XMLHttpRequest) {
        },
        success : function(d) {
            if (d.code == "40002") {
                return;
            }
            successfn(d);
        },
        complete : function(XMLHttpRequest, textStatus) {
        },
        error : function(e) {
        }
    });
};

/**
 * 给页面显示内容赋值
 *
 * @param json
 * @return
 */
function setShowData(res) {
    for ( let key in res) {
        let name = key;
        if (res[key] != null && res[key] != "") {
            $('#' + name).html(res[key]);
        }
    }
}

/**
 * 给页面input内容赋值
 *
 * @param json
 * @return
 */
function setInputData(res) {
    for ( let key in res) {
        let name = key;
//        alert("1=="+name+"&"+res[key]);
        let value = res[key];
        $('#' + name).val(value);
    }
}

/**
 * 给页面的tree赋值
 *
 * @param json
 * @return
 */
function setTreeValue(treeName,treeValue){
    $('#' + treeName+"_tree").attr("value",treeValue);
}

/**
 * 给页面img图片赋值
 *
 * @param key为要展示的span标签id
 * @return
 */
function setImgData(key,value) {
    let item = '<img style="margin:5px 0px 0px 180px" width="200px" src="' + value + '" />';
    $("#"+key).html(item);// 赋值img数据
}


/**
 * 返回上一页
 *
 * @return
 */
function backPage(){
    history.go(-1);
}

/**
 * 弹出层不传c_type  跳转传c_type
 */
function goBack(){
    if(getQueryString("c_type")){
        setTimeout('backPage();',500);
    }else{
        //列表刷新
        queryData();
        //关闭弹出窗口
        $("#endBut").click();
    }
}

//刷新列表样式
function refreshListStyle(){
    $("#mytable").find("tr:even").addClass("oushu");
    $("#mytable").find("tr:odd").addClass("jishu");
    $("#mytable").find("tr:odd td:first-child").addClass("jishu");
}

// 给第一列高度
function firstTrHeight() {
    for(let i = $(".mytable tr").length - 1; i >= 0; i--) {
        let trht = $(".mytable tr")[i].offsetHeight
        let domtr = $(".mytable tr")[i];
        // 有30px的边距
        $(domtr).children('td').height(trht - 30);
        $(domtr).children('.firsttd').css('line-height', trht - 30 + 'px');
    };
}


//清除加载进度
function hangge(){
    $("#jzts").hide();
}

//显示加载进度
function jzts(){
    $("#jzts").show();
}
//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
function curWwwPath(){
    let curWwwPath=window.document.location.href;
    return curWwwPath;
}

//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
function pathName(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    let curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    let pathName=window.document.location.pathname;
    return pathName;
}

//获取主机地址，如： http://localhost:8083
function localhostPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    let curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    let pathName=window.document.location.pathname;
    let pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    let localhostPaht=curWwwPath.substring(0,pos);
    return localhostPaht;
}

//获取带"/"的项目名，如：/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    let curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    let pathName=window.document.location.pathname;
    let pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    let localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    let projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
