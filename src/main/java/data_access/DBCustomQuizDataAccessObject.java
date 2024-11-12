package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import app.Constants;
import entity.Quiz;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.access_quiz.AccessQuizUserDataAccessInterface;

/**
 * The DAO for accessing custom quiz data.
 */
public class DBCustomQuizDataAccessObject implements AccessQuizUserDataAccessInterface {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final int SUCCESS_CODE = 200;

    /**
     * Get the username of the user who created the quiz with the given key.
     * @param key the given key
     * @return the username of the creator
     */
    public String getKeyUser(String key) {
        final StringBuilder keyUser = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            if (i >= Constants.RANDOMKEYSIZE) {
                keyUser.append(key.charAt(i));
            }
        }
        return keyUser.toString();
    }

    @Override
    public boolean existsByKey(String key) {
        final String keyUser = this.getKeyUser(key);

        boolean keyExists = false;

        // checks if user keyUser exists
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", keyUser))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        // checks if data exists within key "key" in "info" part of database for keyUser
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                keyExists = data.has(key);
            }
            return keyExists;
        }
        catch (final IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public JSONObject getQuizFromKey(String key) {
        final String keyUser = this.getKeyUser(key);

        // checks if user keyUser exists
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", keyUser))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONObject userJSONObject = responseBody.getJSONObject("user");
            final JSONObject data = userJSONObject.getJSONObject("info");

            return data.getJSONObject(key);
        }
        catch (final IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

}