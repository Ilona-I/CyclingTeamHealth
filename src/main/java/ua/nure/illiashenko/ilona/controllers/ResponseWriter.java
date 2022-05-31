package ua.nure.illiashenko.ilona.controllers;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;
import ua.nure.illiashenko.ilona.dao.entities.Message;
import ua.nure.illiashenko.ilona.dao.entities.Team;
import ua.nure.illiashenko.ilona.dao.entities.TrainingGoals;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

    public void writeUserChats(HttpServletResponse response, List<UserChat> chats) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chats", chats);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeMessages(HttpServletResponse response, List<Message> messages) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messages", messages);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeTrainingResults(HttpServletResponse response, List<TrainingResults> trainingResults) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("results", trainingResults);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeUserTrainingResults(HttpServletResponse response, TrainingResults trainingResults) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userTrainingResults", trainingResults);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeFeedbacks(HttpServletResponse response, List<Feedback> feedbacks) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("feedbacks", feedbacks);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeTeamsRatings(HttpServletResponse response, Map<Team, Integer> teams) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamsRatings", teams);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeAllTeamTrainingGoals(HttpServletResponse response, List<TrainingGoals> trainingGoals) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trainingGoals", trainingGoals);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writeTrainingGoals(HttpServletResponse response, TrainingGoals trainingGoals) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trainingGoals", trainingGoals);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
    }

    public void writePulseValues(HttpServletResponse response, Map<String, Double> pulseValues) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pulseValues", pulseValues);
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
