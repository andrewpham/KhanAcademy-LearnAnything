package com.andrewpham.android.khanacademy_learnanything.api;

import com.andrewpham.android.khanacademy_learnanything.exercise_model.ExerciseData;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.video_model.VideoData;

import java.util.List;

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

        @GET("/topic/{topic_slug}/videos")
        void getTopicVideos(@Path("topic_slug") String topic_slug, Callback<List<VideoData>> callback);

        @GET("/topic/{topic_slug}/exercises")
        void getTopicExercises(@Path("topic_slug") String topic_slug, Callback<List<ExerciseData>> callback);
    }

}
