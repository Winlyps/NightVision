package winlyps.nightvision;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class VisionNight extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        getCommand("gamma").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Clean-up if necessary
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        giveNightVision(event.getPlayer());
    }

    private void giveNightVision(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("gamma")) {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    player.sendMessage(ChatColor.YELLOW + "Widok nocny wyłączony.");
                } else {
                    giveNightVision(player);
                    player.sendMessage(ChatColor.YELLOW + "Widok nocny włączony.");
                }
                return true;
            }
        }
        return false;
    }
}

