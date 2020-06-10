/******ajax request请求，依赖jquery*****/
var $yykf = window.$yykf || {};
$yykf.ajaxHttpReq = null;

(function(){
    var AjaxHttpReq = function() {
        //contentType 1、json 2、form 3、multipart ; params 必须满足json格式
    	var http = function(method,url,contentType,params,successCallBack,async) {
            $.ajax({
                type: method,
                url:url,
                async:(async==false?false:true),
                data:(contentType == 1?JSON.stringify(params):contentType == 2?$.param(params):params),
                contentType: (contentType == 1?"application/json; charset=utf-8":contentType == 2?"application/x-www-form-urlencoded;charset=utf-8":false),
                dataType:'json',
                processData: (contentType == 3?false:true),
                success: function (result,status,xhr) {
                    var accessState = xhr.getResponseHeader("Access-State");
                    if (accessState == "unlogin") {
                        if (!$yykf.dialog) {
                            openTxWin("/admin/ajaxLoginNew.jsp",
                                {title: '请重新登录',
                                    id:"ART_DIALOG_ReLogin",
                                    lock:true,
                                    width:"400px",
                                    height:"350px"
                                });
                        } else {
                            $yykf.dialog.open("/admin/ajaxLoginNew.jsp",
                                {title: '请重新登录',
                                    id:"ART_DIALOG_ReLogin",
                                    lock:true,
                                    width:"400px",
                                    height:"350px"
                                }
                            );
                        }
                    } else if (accessState == "unauthorized") {
                        if (!$yykf.dialog) {
                            alertMessage("没有权限进行此操作!!!");
                        } else {
                            $yykf.dialog.alert("没有权限进行此操作!!!");
                        }
                    } else {
                        if($.isFunction(successCallBack)){
                            successCallBack(result);
                        }
                    }
                },
                error:function (result) {
                    var status = result["status"];
                    var responseText = result["responseText"];
                    var statusText = result["statusText"];
                    var msg = "staus:" + status + ",responseText:" + responseText + ",statusText:" + statusText;
                    /*if (!$yykf.dialog) {
                        alertMessage("请求" + url + "发生错误，" + msg);
                    } else {
                        $yykf.dialog.alert("请求" + url + "发生错误，" + msg);
                    }*/
                }
            })
		}

        this.httpGet = function(url,params,successCallBack,async) {
            http('get',url,1,params,successCallBack,async);
        };

        this.httpPost = function(url,params,successCallBack,async) {
            http('post',url,1, params, successCallBack, async);
        }

        this.httpGetByForm = function(url,params,successCallBack,async) {
            http('get',url,2,params,successCallBack,async);
        };

        this.httpPostByForm = function(url,params,successCallBack,async) {
            http('post',url,2, params, successCallBack, async);
        }

        this.httpPostByMutiPart = function(url,params,successCallBack,async) {
            http('post',url,3, params, successCallBack, async);
        }
	}
	$yykf.ajaxHttpReq = new AjaxHttpReq();
})();