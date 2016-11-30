package ua.com.vertex.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.vertex.logic.interfaces.CertDetailsPageLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CertificateDetailsPageController {
    private final Logger logger = Logger.getLogger(CertificateDetailsPageController.class);
    private final CertDetailsPageLogic logic;

    @Autowired
    public CertificateDetailsPageController(CertDetailsPageLogic logic) {
        this.logic = logic;
    }

    // TODO: testing with 'verify'
    @RequestMapping(value = "/certificateDetails")
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String certificationId = req.getParameter("certificationId");

        try {
            logic.getCertificateDetails(session, certificationId);
        } catch (IOException e) {
            logger.error(e);
        }

        try {
            resp.sendRedirect("/certificateDetails.jsp");
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
