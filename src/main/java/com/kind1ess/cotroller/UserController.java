package com.kind1ess.cotroller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController implements Serializable {

    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("fileUpload1...");
        //使用fileupload组件
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        //判断文件是否存在
        if(!file.exists()){
            file.mkdirs();
        }
        //解析request域对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = fileUpload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            // 进行判断，当前item对象是否是上传文件项
            if (fileItem.isFormField()){
                //普通表单项
            }else {
                //上传文件项
                String fileName = fileItem.getName();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid+"_"+fileName;
                //完成文件上传
                fileItem.write(new File(path,fileName));
                //删除临时文件
                fileItem.delete();
            }
        }

        return "success";
    }

    /**
     * SpringMVC方式上传
     * @param upload
     * @return
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request,MultipartFile upload) throws IOException {
        System.out.println("fileUpload2...");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        //判断文件是否存在
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid+"_"+fileName;
        //完成上传
        upload.transferTo(new File(path,fileName));
        return "success";
    }

}
