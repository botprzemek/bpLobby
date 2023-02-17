package pl.botprzemek.bpLobby.utils;

public class Button {

    private final int slot;

    private final String itemId;

    private final String action;

    public Button(int slot, String itemId, String action) {

        this.slot = slot;

        this.itemId = itemId;

        this.action = action;

    }

    public int getSlot() {

        return slot;

    }

    public String getItemId() {

        return itemId;

    }

    public String getAction() {

        return action;

    }

}
