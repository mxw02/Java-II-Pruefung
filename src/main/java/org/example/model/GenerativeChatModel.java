package org.example.model;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import java.util.Arrays;
import java.util.List;

public class GenerativeChatModel {

    private GenerativeModel model;
    private final VertexAI vertexAI;
    private GenerationConfig generationConfig;
    private List<SafetySetting> safetySettings;

    GenerativeChatModel(String modelName, String projectID, String region) {
        this.vertexAI = new VertexAI(projectID, region);
        buildModel(modelName);
        generateGenerationConfig();
        setSafetySettings();
    }

    private void buildModel(String modelName) {
        this.model = new GenerativeModel.Builder()
            .setModelName(modelName)
            .setVertexAi(this.vertexAI)
            .setGenerationConfig(generationConfig)
            .setSafetySettings(safetySettings)
            .build();
    }

    private void generateGenerationConfig() {
        generationConfig = GenerationConfig.newBuilder()
            .setMaxOutputTokens(1246)
            .setTemperature(1F)
            .setTopP(0.95F)
            .build();
    }

    private void setSafetySettings() {
        safetySettings = Arrays.asList(
            SafetySetting.newBuilder()
                .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                .build(),
            SafetySetting.newBuilder()
                .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                .build(),
            SafetySetting.newBuilder()
                .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                .build(),
            SafetySetting.newBuilder()
                .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                .build()
        );
    }

    public GenerativeModel getModel() {
        return this.model;
    }

}
