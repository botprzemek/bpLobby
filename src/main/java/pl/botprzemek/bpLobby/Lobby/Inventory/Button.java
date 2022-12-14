package pl.botprzemek.bpLobby.Lobby.Inventory;

public class Button {

    private int slot;

    private String itemID;

    private String action;

    public Button(int slot, String itemID, String action) {

        this.slot = slot;

        this.itemID = itemID;

        this.action = action;

    }

    public int getSlot() {

        return slot;

    }

    public void setSlot(int slot) {

        this.slot = slot;

    }

    public String getItemID() {

        return itemID;

    }

    public void setItemID(String itemID) {

        this.itemID = itemID;

    }

    public String getAction() {

        return action;

    }

    public void setAction(String action) {

        this.action = action;

    }
}
