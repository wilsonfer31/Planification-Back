package com.planification.wf.webhook;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * La classe représente une charge utile de webhook Discord avec un contenu de message et une liste d'intégrations, chacune
 * contenant une description et une couleur.
 */
@Data
public class DiscordWebhookPayload {
    private String content;
    private List<Embed> embeds = new ArrayList<>();
    public static class Embed {
        private String description;
        private int color;

        public Embed(String description , int color) {
            this.description = description;
            this.color = color; // Blue color, RGB: 0, 0, 255
        }
    }
}
