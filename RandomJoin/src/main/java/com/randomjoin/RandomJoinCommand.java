package com.randomjoin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomJoinCommand extends Command {

    private final RandomJoinPlugin plugin;
    private final Random random = new Random();

    public RandomJoinCommand(RandomJoinPlugin plugin) {
        super(
            plugin.getConfig().getString("command.name", "rjoin"),
            plugin.getConfig().getString("command.permission", "randomjoin.use"),
            plugin.getConfig().getStringList("command.aliases").toArray(new String[0])
        );
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("randomjoin.admin")) {
                sender.sendMessage(new TextComponent(color(plugin.getConfig().getString(
                    "messages.no-permission", "&cYou dont have permission to use that commands!"))));
                return;
            }
            plugin.reloadConfig();
            sender.sendMessage(new TextComponent(color(plugin.getConfig().getString(
                "messages.reloaded", "&aReload success!"))));
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(color("&cthis command only use by players!")));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        Configuration config = plugin.getConfig();

        List<String> servers = config.getStringList("servers");

        if (servers == null || servers.isEmpty()) {
            player.sendMessage(new TextComponent(color(config.getString(
                "messages.no-servers", "&chave no server in config file!"))));
            return;
        }

        boolean excludeCurrent = config.getBoolean("exclude-current-server", true);
        String currentServer = player.getServer() != null ? player.getServer().getInfo().getName() : "";

        List<String> availableServers = new ArrayList<>();
        for (String serverName : servers) {
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);
            if (serverInfo == null) continue;

            if (excludeCurrent && serverName.equalsIgnoreCase(currentServer)) continue;

            if (config.getBoolean("check-online", false)) {
                if (serverInfo.getPlayers().isEmpty()) continue;
            }

            availableServers.add(serverName);
        }

        if (availableServers.isEmpty()) {
            player.sendMessage(new TextComponent(color(config.getString(
                "messages.no-available-servers", "&chave no availble servers!"))));
            return;
        }

        String chosen = availableServers.get(random.nextInt(availableServers.size()));
        ServerInfo targetServer = ProxyServer.getInstance().getServerInfo(chosen);

        String connectingMsg = config.getString("messages.connecting", "&econnecting to server &b{server}&e...")
            .replace("{server}", chosen);
        player.sendMessage(new TextComponent(color(connectingMsg)));

        player.connect(targetServer, (success, ex) -> {
            if (success) {
                String connectedMsg = config.getString("messages.connected", "&aconnected to server &b{server}&a!")
                    .replace("{server}", chosen);
                player.sendMessage(new TextComponent(color(connectedMsg)));
            } else {
                String failMsg = config.getString("messages.failed", "&cconnect failed to server &b{server}!")
                    .replace("{server}", chosen);
                player.sendMessage(new TextComponent(color(failMsg)));
            }
        });
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
