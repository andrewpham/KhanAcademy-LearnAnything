package com.andrewpham.android.khanacademy_learnanything.api;

import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class ApiClient {
    private static KhanAcademyMathService sKhanAcademyMathService;
    private static KhanAcademyScienceService sKhanAcademyScienceService;

    public static KhanAcademyMathService getMathApiClient() {
        if (sKhanAcademyMathService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://www.khanacademy.org/api/v1")
                    .build();

            sKhanAcademyMathService = restAdapter.create(KhanAcademyMathService.class);
        }

        return sKhanAcademyMathService;
    }

    public static KhanAcademyScienceService getScienceApiClient() {
        if (sKhanAcademyScienceService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://www.khanacademy.org/api/v1")
                    .build();

            sKhanAcademyScienceService = restAdapter.create(KhanAcademyScienceService.class);
        }

        return sKhanAcademyScienceService;
    }

    public interface KhanAcademyMathService {
        @GET("/topic/math")
        void getTopicData(Callback<TopicData> callback);
    }

    public interface KhanAcademyScienceService {
        @GET("/topic/science")
        void getTopicData(Callback<TopicData> callback);
    }
}
