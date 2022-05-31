package ua.nure.illiashenko.ilona.controllers.servlets.user;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.BIRTH_DATE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.GENDER;

@WebServlet("/pulse")
public class PulseServlet extends HttpServlet {

    private UserService userService;
    private DataValidator dataValidator;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String birthDate = Objects.requireNonNull(request.getParameter(BIRTH_DATE));
        String gender = Objects.requireNonNull(request.getParameter(GENDER));
        if (!dataValidator.isGender(gender) || !dataValidator.isDate(birthDate)) {
            response.setStatus(400);
            return;
        }
        Map<String, Double> pulseValues = userService.getPulseValues(gender, Date.valueOf(birthDate));
        responseWriter.writePulseValues(response, pulseValues);
    }
}
