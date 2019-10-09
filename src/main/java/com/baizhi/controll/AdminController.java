package com.baizhi.controll;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Supplier;
import com.baizhi.service.AdminService;
import com.baizhi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SupplierService supplierService;
    @RequestMapping("login")
    public String login(String name, String password, Model model){

        Admin bynp = adminService.findBynp(name, password);

        if (bynp==null){
            String s="出错了";
            model.addAttribute("a",s);
            return "view/login";
        }else {
            model.addAttribute("admin",bynp);
            return "view/showAll";
        }
    }
    @RequestMapping("/findByPage")
    @ResponseBody
    public Map<String, Object> findByPage(Integer rows, Integer page,
                                          String searchField,
                                          String searchString,
                                          String searchOper) {
        List<Supplier> emps = supplierService.findByPage(rows, page,
                searchField,
                searchString,
                searchOper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", emps);
        map.put("page", page);
        Integer count = supplierService.findCount();
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
    @RequestMapping("/operEmp")
    @ResponseBody
    public Map<String,String> operEmp(String oper,Supplier emp) {
        HashMap hashMap = new HashMap();

        if ("add".equals(oper)) {
            emp.setCreate_date(new Date());
            supplierService.add(emp);
            hashMap.put("status", "addOk");
            return hashMap;
        } else if("edit".equals(oper)) {
            System.out.println(emp);
            emp.setCreate_date(new Date());
            supplierService.update(emp);
            hashMap.put("status", "error");
            return hashMap;
        }else{
            String id = emp.getId();
            String[] split = id.split(",");

            for (String s : split) {
                supplierService.delete(s);
            };
            hashMap.put("status", "error");
            return hashMap;
        }
    }
    @RequestMapping("/delete")
    public String delete(String id){
        supplierService.delete(id);
        return "delete success";

    }

    @RequestMapping("/update")
    public String update(Supplier supplier){
        supplier.setCreate_date(new Date());
        supplierService.update(supplier);
        return "success";

    }
    @RequestMapping("/add")
    public String add(Supplier supplier){
        supplier.setCreate_date(new Date());
        supplierService.add(supplier);
        return "addsuccess";
    }
}
