package net.serveron.hane.siton;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class SitStickCommand implements CommandExecutor, TabCompleter {

    private final SitOn plugin;
    private String player_name = null;


    public SitStickCommand(SitOn plugin) {
        this.plugin = plugin;
        plugin.getCommand("sit").setExecutor(this);
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use commands with the console.");
            return true;
        }
        Player player = (Player) sender;

        if(args.length>0){
            if(player.hasPermission("sit")){
                if(args[0].equals("selector")){
                    if(player_name == null){
                        player_name = player.getName();
                        player.sendMessage(ChatColor.BLUE+"座ることができる場所の選択ができます。" +
                                "\nその場所を選択してクリックしてください。\n同じコマンドを打つと選択者モードが解除されます。");
                        plugin.getServer().getPluginManager().registerEvents(new SelectListener(plugin,player_name), plugin);
                    } else if(player_name.equals(player.getName())){
                        player_name = null;
                        HandlerList.unregisterAll();
                        player.sendMessage(ChatColor.BLUE+"選択者モードを解除しました。");
                    } else{
                        player.sendMessage(ChatColor.BLUE+player_name+"が選択者モードを使用しています。");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED+"権限がありません。");
            }
        } else{
            player.sendMessage(ChatColor.RED+"コマンドの引数が間違っています");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        List<String> autoComplete = new ArrayList<>();
        if(sender.hasPermission("sit")){
            if (args.length == 1){//一段目
                autoComplete.addAll(Collections.singletonList("selector"));
            }
        }
        //文字比較と削除-----------------------------------------------------
        //Collections.sort(autoComplete);
        autoComplete.removeIf(str -> !str.startsWith(args[args.length - 1]));
        //------------------------------------------------------
        return autoComplete;
    }

}
