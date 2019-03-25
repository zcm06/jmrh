package com.example.jmrh.controller;

import com.example.jmrh.entity.*;
import com.example.jmrh.entity.vo.PermissionVo;
import com.example.jmrh.entity.vo.RoleVo;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.PermissionService;
import com.example.jmrh.service.RolePermissionService;
import com.example.jmrh.service.RoleService;
import com.example.jmrh.service.UserRoleService;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-25 22:41
 **/
@Controller
@RequestMapping("/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;



    @RequestMapping("/saveRole")
    @ResponseBody
    public ResultObject saveRole(@RequestBody Role role, HttpServletRequest request){
        try {
            role = roleService.save(role);
            return ResultUtil.successfulResultMap(role);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("添加失败！");
        }
    }

    @RequestMapping("/saveRolePermissions")
    @ResponseBody
    public ResultObject saveRolePermissions(@RequestBody Map<String,Object> map,HttpServletRequest request){
        try {
            Integer roleId = Integer.parseInt(map.get("roleId")+"");
            List<Integer> list = (List<Integer>) map.get("permissionIds");
            List<RolePermission> rolePermissions = new ArrayList<>();
            RolePermission rolePermission = null;
            for (Integer id:list){
                rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId.longValue());
                rolePermission.setPermissionId(id.longValue());
                rolePermissions.add(rolePermission);
            }
            return ResultUtil.successfulResultMap("");

        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("添加失败！");
        }
    }

    @RequestMapping("/deleteRoleById")
    @ResponseBody
    public ResultObject deleteRoleById(HttpServletRequest request){
        try {
            String roleId = request.getParameter("roleId");
            Long id = Long.parseLong(roleId);
            roleService.deleteById(id);
            return ResultUtil.successfulResultMap("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("删除失败！");
        }
    }

    @RequestMapping("/queryRoles")
    @ResponseBody
    public ResultObject queryRoles(HttpServletRequest request){
        try {
            return ResultUtil.successfulResultMap(roleService.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！");
        }
    }


    @RequestMapping("/queryPermissionsByRoleId")
    @ResponseBody
    public ResultObject queryPermissionsByRoleId(HttpServletRequest request){
        try {
            String roleId = request.getParameter("roleId");
            Long id = Long.parseLong(roleId);
            List<RolePermission> rolePermissions = rolePermissionService.queryRolePermissionsByRoleId(id);
            List<Long> ids = new ArrayList<>();
            for (RolePermission rolePermission:rolePermissions){
                ids.add(rolePermission.getPermissionId());
            }
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            return ResultUtil.successfulResultMap(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！");
        }
    }


    @RequestMapping("/queryPermissions")
    @ResponseBody
    public ResultObject queryPermissions(HttpServletRequest request){
        try {
            List<Permission> list= permissionService.findAll();
            List<PermissionVo> voList = loadChild(list);
            return ResultUtil.successfulResultMap(voList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！");
        }
    }


    @RequestMapping("/savePermission")
    @ResponseBody
    public ResultObject savePermission(@RequestBody Permission permission,HttpServletRequest request){
        try {
            if (ObjectUtils.isEmpty(permission.getParentId())){
                permission.setPermissionLevel(1);
            }else{
                Long parentId = permission.getParentId();
                Permission parent = permissionService.queryPermissionById(parentId);
                permission.setPermissionLevel(parent.getPermissionLevel()+1);
            }
            permission = permissionService.save(permission);
            return ResultUtil.successfulResultMap(permission);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("保存失败！");
        }
    }

    @RequestMapping("/deletePermissionById")
    @ResponseBody
    public ResultObject deletePermissionById(HttpServletRequest request){
        try {
            String roleId = request.getParameter("permissionId");
            Long id = Long.parseLong(roleId);
            permissionService.deleteById(id);
            return ResultUtil.successfulResultMap("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("删除失败！");
        }
    }

    @RequestMapping("/queryUserRole")
    @ResponseBody
    public ResultObject queryUserRole(HttpServletRequest request){
        try {
            UserVo user = UserUtil.getUser(request);
            RoleVo roleVo = new RoleVo();
            List<Permission> permissions = null;
            List<PermissionVo> voList = null;
            if (user.getUserName().equals(Admin.getName())){
                permissions= permissionService.findAll();
                voList = loadChild(permissions);
                roleVo.setPermissionList(voList);
                roleVo.setName(Admin.getRoleName());
                return ResultUtil.successfulResultMap(roleVo);
            }
            List<UserRole> userRoles= userRoleService.queryUserRolesByUserId(user.getId());
            Set<Long> permissionIds = new HashSet<>();
            for (UserRole userRole:userRoles){
                List<RolePermission> rolePermissions= rolePermissionService.queryRolePermissionsByRoleId(userRole.getRoleId());
                for (RolePermission rolePermission:rolePermissions){
                    permissionIds.add(rolePermission.getPermissionId());
                }
            }

            permissions = permissionService.queryPermissionsByIds(new ArrayList<>(permissionIds));
            if (!ObjectUtils.isEmpty(permissions)){
                voList = loadChild(permissions);
                roleVo.setPermissionList(voList);
            }
            return ResultUtil.successfulResultMap(roleVo);

        }catch(Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("加载失败！");
        }
    }


    /**
     * @Description: 加载子节点
     * @Param: [itemList]
     * @return: java.util.List<com.example.jmrh.entity.vo.PermissionVo>
     * @Author: ZHANG CANMING
     * @Date: 2019/3/9
     */
    private List<PermissionVo> loadChild(List<Permission> itemList) {

        List<PermissionVo> itemVoList = new ArrayList<>();
        PermissionVo itemVo = null;
        for (Permission item : itemList) {
            itemVo = new PermissionVo();
            BeanUtils.copyProperties(item, itemVo);
            itemVoList.add(itemVo);
        }
        if (itemVoList.size() <= 1) {
            return itemVoList;
        }

        for (PermissionVo vo : itemVoList) {
            for (PermissionVo child : itemVoList) {
                if (vo.getId().equals(child.getParentId())) {
                    vo.getChildList().add(child);
                }
            }
        }

        Iterator<PermissionVo> iterator = itemVoList.iterator();
        while (iterator.hasNext()) {
            PermissionVo next = iterator.next();
            if (next.getParentId() != null) {
                iterator.remove();
            }
        }
        return itemVoList;
    }
}
