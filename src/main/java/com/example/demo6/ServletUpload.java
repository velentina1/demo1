package com.example.demo6;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ServletUpload", value = "/ServletUpload")
@MultipartConfig
public class ServletUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>文件上传</title></head><body>");
        out.println("<h1>上传文件</h1>");
        out.println("<form method='post' action='ServletUpload' enctype='multipart/form-data'>");
        out.println("请选择上传文件: <input type='file' name='file' id='file'><br><br>");
        out.println("<input type='submit' value='Upload File'><br>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Part filePart = request.getPart("file");
        String fileName = getSubmittedFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String uploadDir = getServletContext().getRealPath("/img/");
            File uploadFile = new File(uploadDir, fileName);

            try (InputStream fileContent = filePart.getInputStream();
                 OutputStream os = new FileOutputStream(uploadFile)) {
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = fileContent.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
                out.println(fileName + "上传成功");
            } catch (IOException e) {
                out.println("出错了： " + e.getMessage());
            }
        } else {
            out.println("请选择上传文件");
        }
    }


    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}

