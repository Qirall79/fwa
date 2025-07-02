
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.models.Avatar;
import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.AuthenticationsRepository;
import fr.fortytwo.cinema.repositories.AvatarsRepository;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/images/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1000, // 10 MB
        maxRequestSize = 1024 * 1024 * 10000 // 100 MB
)
public class AvatarServlet extends HttpServlet {

    private AvatarsRepository avatarsRepository;

    private final String UPLOAD_PATH = "/home/wbelfatm/Desktop/fwa/ex02/Cinema/src/main/webapp/images/";

    public AvatarServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");

        avatarsRepository = springContext.getBean(AvatarsRepository.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fileName = UPLOAD_PATH + req.getRequestURI().split("/cinema/images/")[1];


        String extension = fileName.split("\\.")[1];

        InputStream in;
        in = new FileInputStream(new File(fileName));
        resp.setContentType(req.getServletContext().getMimeType(extension));
        
        ServletOutputStream out = resp.getOutputStream();
        
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("avatar");
        String fileName = UUID.randomUUID().toString()+ "_" + filePart.getSubmittedFileName();

        File uploadFolder = new File(UPLOAD_PATH);
        File file = new File(UPLOAD_PATH + fileName);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
        long size = 0;
        for (Part part : req.getParts()) {
            size += part.getSize();
            part.write(UPLOAD_PATH + fileName);
        }

        String extension = fileName.split("\\.")[1];
        User user = (User) req.getSession().getAttribute("user");
        avatarsRepository.save(new Avatar(0, size, fileName, req.getServletContext().getMimeType(extension), user.getId()));
        resp.sendRedirect("/cinema/profile");
    }
}
