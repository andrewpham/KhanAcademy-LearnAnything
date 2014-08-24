package com.andrewpham.android.khanacademy_learnanything.api;

import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

public class ApiClient {
    private static KhanAcademyService sKhanAcademyService;

    public static KhanAcademyService get() {
        if (sKhanAcademyService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://www.khanacademy.org/api/v1")
                    .build();

            sKhanAcademyService = restAdapter.create(KhanAcademyService.class);
        }

        return sKhanAcademyService;
    }

    public interface KhanAcademyService {
        @GET("/topic/{topic_slug}")
        void getTopicData(@Path("topic_slug") String topic_slug, Callback<TopicData> callback);
    }
}
