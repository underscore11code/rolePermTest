package io.github.underscore11code.rolepermtest;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your bot's token:");
        String token = scanner.nextLine();
        System.out.println("Logging into Discord...");
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Logged in!");
        for (Server server : api.getServers()) {
            System.out.println("G: " + server.getName() + "(" + server.getIdAsString() + ")");
            server.getAllowedPermissions(api.getYourself()).forEach(permissionType -> System.out.println("  " + permissionType.name()));
            for (ServerTextChannel channel : server.getTextChannels()) {
                System.out.println("  TC: " + channel.getName() + "(" + channel.getIdAsString() + ")");
                channel.getEffectiveAllowedPermissions(api.getYourself()).forEach(permissionType -> System.out.println("    " + permissionType.name()));
            }
        }
        System.exit(0);
    }
}
