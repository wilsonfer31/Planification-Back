package com.planification.wf.webhook;

import com.google.gson.Gson;
import com.planification.wf.models.enums.DiscordTypeMessage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DiscordWebhookSender {

    public DiscordWebhookSender(Environment env) {
        this.env = env;
    }

    private final Environment env;

    private String getWebHookUrl() {
        return this.env.getProperty("spring.discord.webhook");
    }

    /**
     * Cette fonction envoie un message à un webhook Discord avec un message et un type spécifiés.
     *
     * @param message Le message qui sera envoyé au webhook Discord.
     * @param type Le type de message Discord envoyé, qui peut être INFO, ERROR, OTHER ou DEFAULT.
     */
    public void sendWebhook(String message, DiscordTypeMessage type) throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(this.getWebHookUrl());

        DiscordWebhookPayload payload = new DiscordWebhookPayload();
        DiscordWebhookPayload.Embed discordEmbedValue = null;

        switch (type) {
            case INFO:
                discordEmbedValue = new DiscordWebhookPayload.Embed("[INFO] - " + message, 255);
                payload.getEmbeds().add(discordEmbedValue);
                break;
            case ERROR:
                discordEmbedValue = new DiscordWebhookPayload.Embed("[ERROR] - " + message, 16711680);
                payload.getEmbeds().add(discordEmbedValue);
                break;
            case OTHER:
                discordEmbedValue = new DiscordWebhookPayload.Embed("[OTHER] - " + message, 16776960);
                payload.getEmbeds().add(discordEmbedValue);
                break;
            default:
                discordEmbedValue = new DiscordWebhookPayload.Embed("[DEFAULT] - " + message, 9127187);
                payload.getEmbeds().add(discordEmbedValue);
                break;

        }


        Gson gson = new Gson();
        String jsonPayload = gson.toJson(payload);

        httpPost.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));
        client.execute(httpPost);

        client.close();
    }

}

