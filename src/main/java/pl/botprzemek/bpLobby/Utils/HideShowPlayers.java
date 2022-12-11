//package pl.botprzemek.bpLobby.Utils;
//
//import org.bukkit.Bukkit;
//import org.bukkit.entity.Player;
//import pl.botprzemek.bpLobby.BpLobby;
//
//public class HideShowPlayers {
//
//    private final BpLobby instance = BpLobby.getInstance();
//
//    public void showPlayers(Player player) {
//
//        for(Player show : Bukkit.getOnlinePlayers()) {
//
//            player.showPlayer(instance, show);
//
//        }
//
//    }
//
//    public void hidePlayers(Player player) {
//
//        for(Player hide : Bukkit.getOnlinePlayers()) {
//
//            player.hidePlayer(instance, hide);
//
//        }
//
//    }
//
//    public void hidePlayersEveryone(Player player) {
//
//        for(Player hide : Bukkit.getOnlinePlayers()) {
//
//            player.hidePlayer(instance, hide);
//            hide.hidePlayer(instance, player);
//
//        }
//
//    }
//
//}