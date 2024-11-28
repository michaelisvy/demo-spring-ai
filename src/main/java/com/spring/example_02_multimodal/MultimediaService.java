package com.spring.example_02_multimodal;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
class MultimediaService {

    @Value("classpath:multimedia/singapore-weather.png")
    private Resource imageResourceWeather;

    @Value("classpath:multimedia/speech.mp3")
    private Resource audioResource;

    private final ChatClient chatClient;

    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public MultimediaService(ChatClient.Builder builder, OpenAiAudioTranscriptionModel transcriptionModel) {
        this.chatClient = builder.build();
        this.transcriptionModel = transcriptionModel;
    }

    public String analyseWeather() {
        return this.chatClient.prompt()
                .user(
                        userSpec -> userSpec.text("what will be the weather like on Tuesday")
                                            .media(MimeTypeUtils.IMAGE_PNG, this.imageResourceWeather)
                )
                .call()
                .content();
    }

    public String transcribeFromSpeechToText() {
        return transcriptionModel.call(new AudioTranscriptionPrompt(this.audioResource)).getResult().getOutput();
    }
}
