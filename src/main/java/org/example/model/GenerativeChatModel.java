package org.example.model;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenerativeChatModel {

    private GenerativeModel model;
    private final VertexAI vertexAI;
    private GenerationConfig generationConfig;
    private List<SafetySetting> safetySettings;

    /**
     * Public constructor to generate a new GenerativeChatModel
     * @param modelName The model name of the model you want to build
     * @param projectID Your Google Cloud project ID
     * @param region Your Google Cloud region
     */
    public GenerativeChatModel(String modelName, int maxTokens, float temperature, float topP, String projectID, String region) {
        this.vertexAI = new VertexAI(projectID, region);
        generateGenerationConfig(1246, 1F, 0.95F);
        setSafetySettings();
        buildModel(modelName);
    }

    /**
     * @param modelName The model name of the new model you want to build
     */
    private void buildModel(String modelName) {
        this.model = new GenerativeModel.Builder()
            .setModelName(modelName)
            .setVertexAi(this.vertexAI)
            .setGenerationConfig(generationConfig)
            .setSafetySettings(safetySettings)
            .build();
    }

    // TODO: Set max.Tokens, Temp. and TopP variable
    private void generateGenerationConfig(int maxTokens, float temperature, float topP) {
        generationConfig = GenerationConfig.newBuilder()
            .setMaxOutputTokens(maxTokens)
            .setTemperature(temperature)
            .setTopP(topP)
            .build();
    }


    /**
     * Generates new safety settings
     */
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

    /**
     * @return The GenerativeModel
     */
    public GenerativeModel getModel() {
        return this.model;
    }


    /**
     * @param prompt The prompt you want to count tokens on
     * @return CountTokensResponse
     * @throws IOException When counting the tokens fails
     */
    public CountTokensResponse countTokens(String prompt) throws IOException {
        return model.countTokens(prompt);
    }

    /**
     * @param prompt The prompt for the Generative Model
     * @return The Response as ResponseStream
     * @throws IOException when generating content stream fails
     */
    public ResponseStream<GenerateContentResponse> generateSingleResponse(String prompt) throws IOException {
        var content = ContentMaker.fromString(prompt);
        return model.generateContentStream(content);
    }

}
