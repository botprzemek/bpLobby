package pl.botprzemek.bpLobby.Lobby.Inventory;

public class Button {

    private final int slot;

    private final String itemID;

    private final String action;

    public Button(int slot, String itemID, String action) {

        this.slot = slot;

        this.itemID = itemID;

        this.action = action;

    }

    public int getSlot() {

        return slot;

    }

    public String getItemID() {

        return itemID;

    }

    public String getAction() {

        return action;

    }

}
