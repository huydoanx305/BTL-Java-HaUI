package com.hit.admission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.admission.dto.StudentDTO;
import com.hit.admission.model.Admission;
import com.hit.admission.model.MajorDetail;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Huy Doan
 */
public class ThreadHandleAdmission extends Thread {

    private Thread thread;

    private final String threadName;

    private final int year;

    private final StudentDTO student;

    private final AdmissionController admissionController;

    private final MajorDetailController majorDetailController;

    public ThreadHandleAdmission(String name, int year, StudentDTO student) {
        this.threadName = name;
        this.year = year;
        this.student = student;
        this.admissionController = new AdmissionController();
        this.majorDetailController = new MajorDetailController();
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            List<Admission> admissions = admissionController.getAdmissionsByStudentId(student.getId());
            for (Admission admission : admissions) {
                Float totalScore = randomTotalScore();
                admission.setTotalScore(totalScore);
                if (checkStatusAdmission(admissions)) {
                    admission.setStatus(4);
                } else {
                    MajorDetail majorDetail = majorDetailController.getMajorDetailByMajorIdAndYear(admission.getMajor().getId(), year);
                    if (totalScore < majorDetail.getBenchMark()) {
                        admission.setStatus(3);
                    } else {
                        admission.setStatus(2);
                    }
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " bị gián đoạn.");
        }
        System.out.println("Thread " + threadName + " hoàn thành.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    private boolean checkStatusAdmission(List<Admission> admissions) {
        return admissions.stream().anyMatch(admission -> (admission.getStatus().equals(2)));
    }

    private Float randomTotalScore() {
        Random random = new Random();
        float minValue = 15.0f;
        float maxValue = 30.0f;
        float[] validFractions = {0.2f, 0.4f, 0.6f, 0.8f, 0.25f, 0.5f, 0.75f};
        float randomFloat = minValue + random.nextFloat() * (maxValue - minValue);
        int randomIndex = random.nextInt(validFractions.length);
        randomFloat = (float) (Math.floor(randomFloat) + validFractions[randomIndex]);
        return randomFloat;
    }

    private String callApiGetTotalScore() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String apiUrl = "http://example.com/api/endpoint";
        HttpGet httpGet = new HttpGet(apiUrl);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            // Xử lý dữ liệu phản hồi
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
