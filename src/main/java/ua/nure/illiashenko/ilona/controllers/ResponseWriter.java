package ua.nure.illiashenko.ilona.controllers;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.dao.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResponseWriter {

    public void writeUser(HttpServletResponse response, User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", new JSONObject(user));
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeUserList(HttpServletResponse response, List<User> users) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamMembers", users);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeValidationErrors(HttpServletResponse response, List<String> validationErrors) throws IOException {
        response.setStatus(400);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("validationErrors", validationErrors);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }
}
