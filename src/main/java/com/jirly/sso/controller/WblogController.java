package com.jirly.sso.controller;


import com.jirly.sso.domain.*;
import com.jirly.sso.service.WblogFriendsService;
import com.jirly.sso.service.WblogRemarkService;
import com.jirly.sso.service.WblogService;
import com.jirly.sso.service.WblogUpvoteService;
import com.jirly.sso.utils.Bs2Tool;
import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2019/4/29
 */
@RestController
@RequestMapping("wblog")
public class WblogController {
    private static Logger logger = LoggerFactory.getLogger(WblogController.class);

    @Autowired
    private WblogService wblogService;

    @Autowired
    private WblogRemarkService remarkService;

    @Autowired
    private WblogUpvoteService upvoteService;

    @Autowired
    private WblogFriendsService friendsService;

    @RequestMapping(value = "getBlogInfo.mvc")
    public JsonResp getBlogInfo(HttpSession session) {
        try {
            Map<String,Object> result = new HashMap<String,Object>();
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            PageRequest pageRequest = new PageRequest();
            pageRequest.addParam("createUser",curUser);
            int blogCount = wblogService.count(pageRequest);
            result.put("blogCount",blogCount);
            List<Integer> friends = friendsService.getAttCount(curUser);
            result.put("attCount",friends.get(0));
            result.put("attedCount",friends.get(1));
            String userPic = wblogService.getUserPic(curUser);
            if (userPic == null) {
                userPic = "/images/wblog/userPic.jpg";
            }
            result.put("userPic",userPic);
            Map<String,String> userOtherInfo = wblogService.getUserOtherInfo(curUser);
            result.put("curUserName",userOtherInfo.get("name"));
            result.put("curUserLoginName",userOtherInfo.get("loginName"));
            Map<String,Object> params = new HashMap<String, Object>();
            result.put("zoneList",wblogService.listConfig(params));
            result.put("curUser",curUser);
            return JsonResp.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "add.mvc")
    public JsonResp add(@RequestParam(value="files") MultipartFile[] files, @RequestParam("content") String content, @RequestParam("zoneType") Integer zoneType, HttpSession session) {
        try {
            Wblog wblog = new Wblog();
            if (files != null && files.length > 0){
                List<String> uploadPaths = Bs2Tool.upload(files);
                wblog.setPicAttaList(StringUtils.join(uploadPaths,";"));
            }
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            wblog.setContent(content);
            wblog.setCreateUser(curUser);
            wblog.setZoneType(zoneType);
            wblogService.addBlog(wblog);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "delBlog.mvc")
    public JsonResp delBlog(@RequestBody Integer blogId) {
        try {
            wblogService.delBlog(blogId);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "list.mvc")
    public JsonResp list(@RequestBody PageRequest request, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            request.addParam("curUser",curUser);
            int total  = wblogService.count(request);
            if (total == 0) {
                return PageResponse.emptyResult();
            }
            PageInfo page = new PageInfo(request.getPageID(), request.getPageCount(), total);
            request.setPage(page);
            List<Wblog> list = wblogService.list(request);
            if (list != null && list.size() > 0) {
                for (Wblog wblog: list) {
                    wblog.setCurUser(curUser);
                }
            }
            return new PageResponse(total,page.getSize(),page.getIndex(),page.getPages(),list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "remark.mvc")
    public JsonResp remark(@RequestBody WblogRemark remark, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            remark.setRemarkUser(curUser);
            remarkService.addRemark(remark);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listRemarkByBlogId.mvc")
    public JsonResp listRemarkByBlogId(@RequestBody Integer blogId, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            QueryParameters qp = new QueryParameters();
            qp.addParam("blogId",blogId);
            List<WblogRemark> list = remarkService.list(qp);
            if (list != null && list.size() > 0) {
                for (WblogRemark remark: list) {
                    remark.setCurUser(curUser);
                }
            }
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "delRemark.mvc")
    public JsonResp delRemark(@RequestBody Integer remarkId) {
        try {
            remarkService.delRemark(remarkId);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "upvote.mvc")
    public JsonResp upvote(@RequestBody WblogUpvote upvote, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            upvote.setUpvoteUser(curUser);
            upvoteService.addUpvote(upvote);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "delUpvote.mvc")
    public JsonResp delUpvote(@RequestBody WblogUpvote upvote, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            upvote.setUpvoteUser(curUser);
            upvoteService.delUpvote(upvote);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listUpvoteByBlogId.mvc")
    public JsonResp listUpvoteByBlogId(@RequestBody Integer blogId) {
        try {
            QueryParameters qp = new QueryParameters();
            qp.addParam("blogId",blogId);
            List<WblogUpvote> list = upvoteService.list(qp);
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listUnAttUserList.mvc")
    public JsonResp listUnAttUserList(@RequestBody PageRequest pageRequest, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            pageRequest.addParam("attentionUser",curUser);
            PageInfo page = new PageInfo(pageRequest.getPageID(), pageRequest.getPageCount());
            pageRequest.setPage(page);
            List<Map<String,Object>> list = friendsService.listUnAttUserList(pageRequest);
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listAttUserList.mvc")
    public JsonResp listAttUserList(@RequestBody PageRequest pageRequest, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            pageRequest.addParam("attentionUser",curUser);
            PageInfo page = new PageInfo(pageRequest.getPageID(), pageRequest.getPageCount());
            pageRequest.setPage(page);
            List<Map<String,Object>> list = friendsService.listAttUserList(pageRequest);
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "addAttUser.mvc")
    public JsonResp addAttUser(@RequestBody WblogFriends friends, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            friends.setAttentionUser(curUser);
            friendsService.addFriends(friends);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "delAttUser.mvc")
    public JsonResp delAttUser(@RequestBody WblogFriends friends, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            friends.setAttentionUser(curUser);
            friendsService.delFriends(friends);
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "uploadUserPic.mvc")
    public JsonResp uploadUserPic(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        try {
            UserInfo userInfo = (UserInfo)session.getAttribute(Constant.CURRENT_USER);
            Integer curUser = 26;//Integer.valueOf(userInfo.getId());
            List<String> uploadUrl = Bs2Tool.upload(files);
            wblogService.saveOrUpdateUserPic(curUser,uploadUrl.get(0));
            return JsonResp.success(uploadUrl.get(0));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listZone.mvc")
    public JsonResp listZone(@RequestBody Map<String,Object> params) {
        try {
            List<Map> list = wblogService.listConfig(params);
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "listConfig.mvc")
    public List<Map> listConfig(@RequestBody Map<String,Object> params) {
        try {
            List<Map> list = wblogService.listConfig(params);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @RequestMapping(value = "updateConfig.mvc")
    public JsonResp updateConfig(@RequestBody Map<String,Object> params) {
        try {
            String action = (String) params.get("action");
            if (action.equals("add")) {
                wblogService.addConfig(params);
            } else if(action.equals("update")) {
                wblogService.updateConfig(params);
            } else if (action.equals("valid")) {
                Integer id = (Integer) params.get("id");
                wblogService.validConfig(id);
            }
            return JsonResp.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "getEditUsers.mvc")
    public JsonResp getEditUsers(@RequestBody Map<String,Object> params) {
        try {
            String editUsers = (String) params.get("editUsers");
            List<Map> list = wblogService.getEditUsers(editUsers);
            return JsonResp.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResp.fail(e.getMessage());
        }
    }
}
