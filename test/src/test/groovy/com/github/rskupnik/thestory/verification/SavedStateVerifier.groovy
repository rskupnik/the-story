package com.github.rskupnik.thestory.verification

import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.implementations.inmemory.InMemoryFileSaver

class SavedStateVerifier {
    private final Map<String, ?> state

    SavedStateVerifier(FileSaver fileSaver, String filename) {
        this.state = ((InMemoryFileSaver)fileSaver).get(filename) as Map<String, ?>
    }

    boolean verifyExists() {
        return state != null
    }

    boolean verifyItemAmount(int amount) {
        List<Map<String, ?>> items = state.get("items")
        return items != null && items.size() == amount
    }

    boolean verifyItem(int index, String blueprint, Map<String, ?> externalState) {
        List<Map<String, ?>> items = state.get("items")
        Map<String, ?> state = items.get(index)
        return ((String)state.get("id")).length() > 10 && ((String)state.get("blueprint")).equals(blueprint) &&
                verifyState((Map<String, ?>)state.get("externalState"), externalState)
    }

    boolean verifyNormalMappedBackground(String image, String normalImage) {
        Map<String, String> bs = state.get("background")
        return bs != null && bs.get("type").equals("NORMAL_MAPPED") && bs.get("image").equals(image) &&
                bs.get("normalImage").equals(normalImage)
    }

    boolean verifyPlayerLocation(String zone, int x, int y) {
        Map<String, ?> playerData = state.get("player")
        if (playerData == null) return false
        Map<String, ?> l = playerData.get("location")
        return l != null && l.get("zone").equals(zone) && l.get("x").equals(x) && l.get("y").equals(y)
    }

    private static boolean verifyState(Map<String, ?> expected, Map<String, ?> given) {
        for (Map.Entry<String, ?> entry : given) {
            if (expected.get(entry.key) == null || !entry.value.equals(expected.get(entry.key))) {
                return false
            }
        }
        return true
    }
}
