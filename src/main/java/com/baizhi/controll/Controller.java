package com.baizhi.controll;

import com.baizhi.entity.Part;
import com.baizhi.entity.User;
import com.baizhi.service.PartService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private PartService partService;
    @RequestMapping("/addEmp")
    public List<User> addEmp(User emp) {
        System.out.println(emp);
        userService.add(emp);
        List<User> all = userService.findAll();
        return all;

    }
    @RequestMapping("/showAllUser")
    public List<User> showAllUser(){

        List<User> all = userService.findAll();
        return all;
    }
    @RequestMapping("/findDept")
    public List<Part> findDept() {
        List<Part> list =partService.findAll();
        return list;
    }
    @RequestMapping("showOneUser")
    public User showOneUser(String id){
        User one = userService.findOne(id);
        return one;
    }
    @RequestMapping("updateUser")
    public List<User> updateUser(User user){

        userService.update(user);
        List<User> all = userService.findAll();
        return all;
    }
    @RequestMapping("deleteUser")
    public List<User> deleteUser(String id){
        userService.delete(id);
        List<User> all = userService.findAll();
        return all;
    }
    @RequestMapping("showAllpart")
    public List<Part> showAllpart(){

        List<Part> all = partService.findAll();


        return all;
    }
    @RequestMapping("addPart")
    public List<Part> addpart(String pname){
        System.out.println("~~~~~~~~~~~~~~~~~~~~"+pname);
        Part part = new Part();
        part.setName(pname);
        partService.add(part);
        List<Part> all = partService.findAll();
        return all;
    }
    @RequestMapping("updatePart")
    public List<Part> upart(Part part){
        partService.update(part);
        List<Part> all = partService.findAll();
        return all;

    }
    @RequestMapping("/findByPage")
    public Map<String, Object> findByPage(Integer rows, Integer page,
                                          String searchField,
                                          String searchString,
                                          String searchOper) {
        List<User> emps = userService.findByPage(rows, page,
                searchField,
                searchString,
                searchOper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", emps);
        map.put("page", page);
        Integer count = userService.findCount();
        // 总页数
        Integer total = null;
        if (count % rows == 0) {
            total = count / rows;
        } else {
            total = count / rows + 1;
        }
        map.put("total", total);
        map.put("records", count);

        return map;
    }
    @RequestMapping("findPart")
    public String findPart() {
        List<Part> all = partService.findAll();
        StringBuilder sbu = new StringBuilder("<select>");
        for (Part dept:all) {
            String option = "<option value='"+dept.getId()+"'>"+dept.getName()+"</option>";
            sbu.append(option);
        }
        sbu.append("</select>");
        return sbu.toString();
    }
    /**
     * 完成增 删 改
     * @return
     * oper  代表请求的操作是什么 |  add/del/edit
     */
    @RequestMapping("/operEmp")
    public Map<String,String> operEmp(String oper,User emp) {
        System.out.println(emp);
        HashMap hashMap = new HashMap();

        if ("add".equals(oper)) {
            String id = emp.getPart().getId();
            emp.setPid(id);
            userService.add(emp);
            hashMap.put("status", "addOk");
            return hashMap;
        } else if("edit".equals(oper)) {
            System.out.println(emp);
            String id = emp.getPart().getId();
            emp.setPid(id);
            userService.update(emp);
            hashMap.put("status", "error");
            return hashMap;
        }else{
            String id = emp.getId();
            String[] split = id.split(",");

            for (String s : split) {
                userService.delete(s);
            };
            hashMap.put("status", "error");
            return hashMap;
        }
    }
    //展示部门信息
    @RequestMapping("showPart")
    public Map<String, Object> showPart(Integer rows, Integer page,
                                          String searchField,
                                          String searchString,
                                          String searchOper) {
// {"rows":[当前页结果(list)],"page":"当前页","total":"总页数","records":"总条数"}
        List<Part> bypage = partService.findBypage(rows, page,
                searchField,
                searchString,
                searchOper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", bypage);
        map.put("page", page);
        Integer count = partService.findCount();

        // 总页数
        Integer total = null;
        if (count % rows == 0) {
            total = count / rows;
        } else {
            total = count / rows + 1;
        }
        map.put("total", total);
        map.put("records", count);

        return map;
    }
//修改部门信息
    @RequestMapping("updateP")
    public Map<String,String> UPDATEp(String oper,Part emp) {
        System.out.println(emp);
        HashMap hashMap = new HashMap();

        if ("add".equals(oper)) {
           partService.add(emp);
            hashMap.put("status", "addOk");
            return hashMap;
        } else if("edit".equals(oper)) {
            partService.update(emp);
            hashMap.put("status", "error");
            return hashMap;
        }else{
            String id = emp.getId();
            String[] split = id.split(",");

            for (String s : split) {
                //System.out.println(s);
                partService.delete(s);
            };
            hashMap.put("status", "error");
            return hashMap;
        }
    }

}
