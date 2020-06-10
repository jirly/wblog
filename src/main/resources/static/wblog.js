var zoneType = -1;
var userUnAttListCurPage = 1;
var userAttListCurPage = 1;
var userPageCount = 6;
var userSelFlag = 0;
var zoneTypeMap = {1:'交友',2:'交易',3:'租房',4:'宠物',5:'招聘',6:'感谢'};
var zoneUserMap = {};
var curUser = null;
$(document).ready(function () {
    $yykf.ajaxHttpReq.httpPost('/wblog/getBlogInfo.mvc',{},function (data) {
        if (data.status == 0) {
            var resultMap = data.data;
            $("#blogCount").text(resultMap.blogCount);
            $("#attCount").text(resultMap.attCount);
            $("#attedCount").text(resultMap.attedCount);
            $("#userImg").attr('src',resultMap.userPic);
            $("#blogUserName").text(resultMap.curUserName);
            $("#blogUserLoginName").text(resultMap.curUserLoginName);
            var zoneList = resultMap.zoneList;
            if (zoneList && zoneList.length > 0) {
                var _zoneHtml ="";
                for (var i=0; i<zoneList.length; i++) {
                    var zone = zoneList[i];
                    zoneUserMap[zone.id] = zone.editUsers;
                    _zoneHtml += '<td align="center"><a href="javascript:void(0);" onclick="chooseZone(this,'+zone.id+');" class="herfStype">'+zone.zoneName+'</a></td>';
                }
                $("#zoneTr").append(_zoneHtml);
            }
            curUser = ""+resultMap.curUser;
        } else {
            console.error(data.errorMsg);
        }
    },false);
    reLoadWblog();
    loadUnAttUserList();
});

function reLoadWblog() {
    loadTb(getParams());
}

function chooseZone(obj,zType) {
    $(".herfStype.current").removeClass('current');
    $(obj).addClass('current');
    zoneType = zType;
    reLoadWblog();
    if (zoneType > 0 && !checkEdit(zoneUserMap[zoneType])) {
        $("#publishBtn").hide();
    } else {
        $("#publishBtn").show();
    }
}

function checkEdit(editUsers) {
    if (!editUsers) {
        return true;
    }
    var array = editUsers.split(",");
    if ($.inArray(curUser, array) >= 0) {
        return true;
    } else {
        return false;
    }
}

function initBlogTb(items) {
    var _html = '';
    for (var i=0; i<items.length;i++) {
        item = items[i];
        _html += '<table width="90%" border="0" align="center" cellpadding="3" cellspacing="0" style="margin-top: 20px;">' +
            '                <tbody>' +
            '                <tr>' +
            '                    <td rowspan="3" align="center" valign="top">' +
            '                        <a href="javascript:void(0)"><img src="'+item.userPic+'" width="50" height="50"></a>' +
            '                    </td>' +
            '                    <td width="88%" class="content" style="word-break: break-all;"><a href="javascript:void(0)" style="text-decoration-line: none;color:#09C">'+item.createUserName+'</a><img src="../images/wblog/v.gif" width="11" height="10" align="middle"><br/>' +
            (zoneType == -1 && item.zoneType > 0?'<span style="color: #2b96e1">#'+item.zoneName+ "#</span>&nbsp;" +item.validContent:item.validContent)  +
            '                    </td>' +
            '                </tr>';
        if (item.picList && item.picList.length > 0) {
            _html += '        <tr> ' +
                '             <td class="viewer">';
            for (var j=0; j<item.picList.length; j++) {
                var pic = item.picList[j];
                if (isVedio(pic)) {
                    _html += '         <video src="'+item.picList[j]+'"  controls="controls" width="60%"   ondblclick="" onclick="">您的浏览器不支持</video>'
                } else {
                    _html += '         <img src="'+item.picList[j]+'" width="120"  ondblclick="" onclick="">'
                }

            }
            _html += '        </td>' +
                '             </tr>';
        }
        _html+='              <tr>' +
            '                    <td height="40">' +
            '                        <table width="100%" border="0" cellpadding="0" cellspacing="0" >' +
            '                            <tr>' +
            '                                <td><span style="color:#aaa;">'+item.createTimeStr+'</span></td>' +
            '                                <td align="right">';
        if (item.curUser == item.createUser) {
            _html+= '                            <a href="javascipt:void(0)" style="text-decoration: none;color:#09C"  onclick="delBlog('+item.id+')">删除&nbsp;|&nbsp;</a>';
        }
        _html+='                                 <a style="text-decoration:none;color:#09C" href="javascipt:void(0)" onclick="upvote('+item.id+')">点赞(<span id="upvoteCount'+item.id+'">'+item.upvoteCount+'</span>)&nbsp;|&nbsp;</a>' +
            '                                    <a href="javascipt:void(0)" style="text-decoration: none;color:#09C" onclick="remark('+item.id+')">评论(<span id="remarkCount'+item.id+'">'+item.remarkCount+'</span>)</a>' +
            '                                </td>' +
            '                            </tr>' +
            '                        </table>' +
            '                        <table>' +
            '                            <tr>' +
            '                                <div id="upvoteDiv'+item.id+'" style="background:#efefef; border-top: #d9d9d9 1px solid;  border-radius: 0px 0px 4px 4px; line-height:24px; text-align:right; padding:5px 10px;display: none" onclick="leaveUpvoteDiv(this)"></div>' +
            '                                <div id="remarkDiv'+item.id+'" style="display: none">' +
            '                                   <textarea style="width: 90%;height: 30px;border-radius: 5px;font-size:12px;line-height:17px;resize: none;display: block" name="remarkContent" id="remarkContent'+item.id+'"></textarea>' +
            '                                   <a href="javascipt:void(0)" style="text-decoration: none;color:#09C" onclick="submitRemark('+item.id+')">评论&nbsp;&nbsp;&nbsp;</a>' +
            '                                   <a href="javascipt:void(0)" style="text-decoration: none;color:#09C" onclick="remark('+item.id+')">取消</a>' +
            '                                </div>' +
            '                            </tr>' +
            '                            <tr>' +
            '                                <table id="remarkTb'+item.id+'" border="0" width="100%" align="left"></table>' +
            '                            </tr>' +
            '                        </table>' +
            '                 </td>' +
            '              </tr>' +
            '           </tbody>' +
            ' </table>'
        $("#blogTable").html(_html);
        $('.viewer').viewer();
    }
}
function checknum(obj){
   var curNum = $(obj).val().length;
   if(curNum > 140){
       $(obj).val($(obj).val().substring(0,140));
       return;
   }
   $("#remainNum").val(140-curNum);
}

function publish() {
    var wblogContent = $("#wblogContent").val().trim();
    if (wblogContent.length == 0) {
        console.error("微博内容不能为空!!!");
        return;
    }
    var formData = new FormData();
    formData.append("content",wblogContent);
    formData.append("zoneType",zoneType<0?-1:zoneType);
    var fileList = document.getElementById('uploadPic').files;
    var vedioNum = 0;
    for (var i=0; i<fileList.length; i++) {
        if (checkPicOrVedioValid(fileList[i]) == false) {
            return;
        }
        if (isVedio(fileList[i].name)) {
            vedioNum++;
        }
        formData.append("files",fileList[i]);
    }
    if (vedioNum > 1) {
        console.error("视频文件一次只能传一个!");
        return;
    }
    $("#publishBtn").hide();
    $.ajax({
        url: '/wblog/add.mvc',
        type: 'post',
        data: formData,
        dataType: 'json',
        cache: false,
        contentType: false,	// 必须，不然会报出‘非法调用’的错误，原因应该是不支持ajax文件上传
        processData: false,
        success: function(data) {
            if (data.status == 0) {
                $("#wblogContent").val('');
                $("#remainNum").val('140');
                document.getElementById('uploadPic').value = '';
                reLoadWblog();
            } else {
                console.error(data.errorMsg);
            }
            $("#publishBtn").show();
        }
    });
}

function delBlog(blogId) {
    $yykf.ajaxHttpReq.httpPost('/wblog/delBlog.mvc',blogId,function (data) {
        if (data.status == 0) {
            reLoadWblog();
        } else {
            console.error(data.errorMsg);
        }
    })
}
function loadTb(params) {
    $yykf.ajaxHttpReq.httpPost('/wblog/list.mvc',params,function (data) {
        if (data.status == 0) {
            if (data.data.length > 0) {
                initBlogTb(data.data)
                $("#totalPage").text(data.totalPage);
                $("#pageID").val(data.currentPage);
            } else {
                $("#blogTable").html('');
                $("#totalPage").text(0);
                $("#pageID").val(1);
            }
        } else {
            console.error(data.errorMsg);
        }
    })
}

function goPage(type) {
    if (type == 0) {//跳转
        $("#pageID").val($("#pageInput").val());
    } else if (type == 1) {//首页
        $("#pageID").val('1');
    } else if (type == 2) {//下一页
        var curPage = $("#pageID").val();
        $("#pageID").val(parseInt(curPage)+1);
    }
    reLoadWblog();
}

function getPageParam() {
    var params = {pageCount:20};
    params.pageID = $("#pageID").val();
    return params;
}

function getParams() {
    var params = {params:{}}
    params = $.extend(params,getPageParam());
    params.params.content = $("#searchCotent").val().trim();
    params.params.zoneType = zoneType;
    return params;
}

function remark(blogId) {
 var display =  $("#remarkDiv"+blogId).css('display');
 if (display == 'none') {
     $("#remarkDiv"+blogId).css('display','block');
     $("#remarkTb"+blogId).css('display','block');
     loadRemarkTb(blogId);
 } else {
     $("#remarkDiv"+blogId).css('display','none');
     $("#remarkTb"+blogId).css('display','none');
 }
}

function submitRemark(blogId) {
   var remark = {};
   remark.blogId = blogId;
   remark.remarkContent = $("#remarkContent"+blogId).val();
   if (remark.remarkContent.trim().length == 0) {
       console.error("评论不能为空!");
       return;
   }
    $yykf.ajaxHttpReq.httpPost('/wblog/remark.mvc',remark,function (data) {
        if (data.status == 0) {
            loadRemarkTb(blogId);
            $("#remarkContent"+blogId).val("");
        } else {
            console.error(data.errorMsg);
        }
    })
}

function loadRemarkTb(blogId) {
    $yykf.ajaxHttpReq.httpPost('/wblog/listRemarkByBlogId.mvc',blogId,function (data) {
        if (data.status == 0) {
            var remarkList = data.data;
            var _html = '';
            for (var i=0; i<remarkList.length; i++) {
                var item = remarkList[i];
               _html+='<tr>' +
                    '  <td  align="center" valign="top" height="55">' +
                    '    <a href="javascript:void(0)"><img src="'+item.remarkUserPicPath+'" width="50" height="50" title="'+item.remarkUserName+'"></a>' +
                    '  </td>' +
                    '  <td width="90%"  height="55">' +
                    '    <a href="javascript:void(0)" style="text-decoration-line: none;color:#09C;display: block">'+item.remarkUserName+'<img src="../images/wblog/v.gif" width="11" height="10" align="middle"></a>' +
                    '    <span style="color:#aaa;display: block">'+item.remarkTimeStr+'</span>';
            var reg = /\@.*?\:(.*)/;
            if (reg.test(item.remarkContent)) {
                var m = getRepalyUser(item.remarkContent);
                _html+= ' <span style="display: block"><span style="color: #3795ec;">'+m[1]+'</span>'+m[2]+'</span>';
            } else {
                _html+= ' <span style="display: block">'+item.remarkContent+'</span>';
            }
            if (item.curUser == item.remarkUser) {
                _html+= '<a href="javascript:void(0)" style="text-decoration-line: none;color:#09C;display: block;float: right" onclick="delRemark('+item.id+','+blogId+')">&nbsp;&nbsp;|&nbsp;&nbsp;删除</a>';
            }
            _html+= '    <a href="javascript:void(0)" style="text-decoration-line: none;color:#09C;display: block;float: right" onclick="replayRemark('+item.blogId+',\''+item.remarkUserName+'\')">回复TA</a>' +
                    '  </td>' +
                    '</tr>'
            }
            $("#remarkTb"+blogId).html(_html);
            $("#remarkCount"+blogId).text(remarkList.length);
        } else {
            console.error(data.errorMsg);
        }
    })
}

function replayRemark(blogId,remarkUserName) {
    $("#remarkContent"+blogId).val("@"+remarkUserName+":");
}

function delRemark(remarkId,blogId) {
    $yykf.ajaxHttpReq.httpPost('/wblog/delRemark.mvc',remarkId,function (data) {
        if (data.status == 0) {
            loadRemarkTb(blogId);
        } else {
            console.error(data.errorMsg);
        }
    })
}


function getRepalyUser(remark) {
    var reg = /(@.*?:)(.*)/
    return remark.match(reg);
}

function leaveUpvoteDiv(obj) {
    var display =  $(obj).css('display');
    if (display != 'none') {
        $(obj).css('display','none');
    }
}
function upvote(blogId) {
    var display =  $("#upvoteDiv"+blogId).css('display');
    if (display == 'none') {
        $("#upvoteDiv"+blogId).css('display','block');
        $yykf.ajaxHttpReq.httpPost('/wblog/upvote.mvc',{blogId:blogId},function (data) {
            if (data.status == 0) {
                loadUpvoteTb(blogId);
            } else {
                console.error(data.errorMsg);
            }
        });
    } else {
        $("#upvoteDiv"+blogId).css('display','none');
        $yykf.ajaxHttpReq.httpPost('/wblog/delUpvote.mvc',{blogId:blogId},function (data) {
            if (data.status == 0) {
                loadUpvoteTb(blogId);
            } else {
                console.error(data.errorMsg);
            }
        });
    }
}

function loadUpvoteTb(blogId) {
    $yykf.ajaxHttpReq.httpPost('/wblog/listUpvoteByBlogId.mvc',blogId,function (data) {
        if (data.status == 0) {
            var upvoteList = data.data;
            var _html = '<span style="color:#A00; font-size:16px;">❤</span>';
            for (var i=0; i<upvoteList.length; i++) {
                var upvote = upvoteList[i];
                if (i == upvoteList.length -1) {
                    _html += upvote.upvoteUserName;
                } else {
                    _html += upvote.upvoteUserName + ',';
                }
            }
            $("#upvoteDiv"+blogId).html(_html);
            $("#upvoteCount"+blogId).text(upvoteList.length);
        } else {
            console.error(data.errorMsg);
        }
    })
}

function selectUserType(type) {
    userSelFlag = type;
    if (userSelFlag == 0) {
        loadUnAttUserList();
    } else {
        loadAttUserList();
    }
}
function reloadUserList() {
    if (userSelFlag == 0) {
        userUnAttListCurPage++;
        loadUnAttUserList();
    } else {
        userAttListCurPage++;
        loadAttUserList();
    }
}
function loadUnAttUserList() {
    if (userUnAttListCurPage == 0) {
        userUnAttListCurPage = 1;
    }
    $yykf.ajaxHttpReq.httpPost('/wblog/listUnAttUserList.mvc',{pageID:userUnAttListCurPage,pageCount:userPageCount},function (data) {
        if (data.status == 0) {
            if (data.data.length < userPageCount) {
                userUnAttListCurPage = 0;
            }
            initUserList(data.data,0);
        } else {
            console.error(data.errorMsg);
        }
    });
}
function loadAttUserList() {
    if (userAttListCurPage == 0) {
        userAttListCurPage = 1;
    }
    $yykf.ajaxHttpReq.httpPost('/wblog/listAttUserList.mvc',{pageID:userAttListCurPage,pageCount:userPageCount},function (data) {
        if (data.status == 0) {
            if (data.data.length < userPageCount) {
                userAttListCurPage = 0;
            }
            initUserList(data.data,1);
        } else {
            console.error(data.errorMsg);
        }
    });
}
function initUserList(userList,type) {
    $("#userListTitleDiv").siblings().remove();
    var _html = '';
    for (var i=0; i<userList.length; i++) {
        var item = userList[i];
        _html += '<tr>' +
            '           <td colspan="2">' +
            '                 <table border="0" cellpadding="0" cellspacing="0" class="userdetail">' +
            '                       <tbody>' +
            '                           <tr>' +
            '                                <td width="26%"><a href="javascript:void(0)"><img src="'+item.userPic+'" width="50" height="50" border="0"></a></td>' +
            '                                <td width="74%">' +
            '                                    <a href="javascript:void(0)">'+item.name+'</a>' +
            '                                    <a href="javascript:void(0)" onclick="attentionUser('+item.id+','+type+')" class="btnguanzhu">'+(type==0?"+关注":"-取消关注")+'</a>' +
            '                                    <br>Tel：' + (item.phone==null?'':item.phone) +
            '                                    <br>email：' + (item.email==null?'':item.email) +
            '                                </td>' +
            '                            </tr>' +
            '                         </tbody>' +
            '                </table>' +
            '           </td>' +
            '    </tr>'
    }
    $("#userListTitleDiv").after(_html);
}

function attentionUser(userId,type) {
    if (type == 0) {
        $yykf.ajaxHttpReq.httpPost('/wblog/addAttUser.mvc',{attentionedUser:userId},function (data) {
            if (data.status == 0) {
                loadUnAttUserList();
            } else {
                console.error(data.errorMsg);
            }
        });
    } else {
        $yykf.ajaxHttpReq.httpPost('/wblog/delAttUser.mvc',{attentionedUser:userId},function (data) {
            if (data.status == 0) {
                loadAttUserList();
            } else {
                console.error(data.errorMsg);
            }
        });
    }
}

function isVedio(fileName) {
    var vedioFileType = "mp4,ogg,webm";
    var fileType = fileName.toLowerCase().substr(fileName.lastIndexOf(".")+1);
    if (vedioFileType.indexOf(fileType) == -1) {
        return false;
    }
    return true;
}

function checkPicOrVedioValid(file) {
    var allFileType = "jpg,jpeg,pjpeg,png,bmp,gif,mp4,ogg,webm";
    var fileType = file.name.toLowerCase().substr(file.name.lastIndexOf(".")+1);
    if (allFileType.indexOf(fileType) == -1) {
        console.error("只能上传"+allFileType+"格式的文件");
        file.value = "";
        return false;
    }
    if (isVedio(file.name)) {
        if (file.size > 20*1024*1024) {
            console.error("上传的视频文件不能超过10M,请重新选择");
            file.value = "";
            return false;
        }
    } else {
        if (file.size > 10*1024*1024) {
            console.error("上传的图片文件不能超过10M,请重新选择");
            file.value = "";
            return false;
        }
    }

    return true;
}

function checkPicValid(file) {
    var allFileType = "jpg,jpeg,pjpeg,png,bmp,gif";
    var fileType = file.name.toLowerCase().substr(file.name.lastIndexOf(".")+1);
    if (allFileType.indexOf(fileType) == -1) {
        console.error("只能上传jpg,jpeg,pjpeg,png,bmp,gif格式的图片");
        file.value = "";
        return false;
    }
    if (file.size > 10*1024*1024) {
        console.error("上传的图片不能超过10M,请重新选择");
        file.value = "";
        return false;
    }
    return true;
}

function uploadUserPic(node) {
    if (node.files.length == 0 || checkPicValid(node.files[0]) == false) {
        return;
    }
    var myform = new FormData();
    myform.append('files',node.files[0]);
    $.ajax({
        url: "/wblog/uploadUserPic.mvc",
        type: "POST",
        data: myform,
        dataType: 'json',
        contentType: false,
        processData: false,
        success: function (data) {
            $("#userImg").attr('src',data.data);
        },
        error:function(data){
        }
    });
}