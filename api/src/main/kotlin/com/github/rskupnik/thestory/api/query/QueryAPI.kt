package com.github.rskupnik.thestory.api.query

import com.github.rskupnik.thestory.api.query.result.*
import com.github.rskupnik.thestory.gamestate.domain.GamePhase
import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.item.domain.InventorySlot

/**
 * The main interface for issuing queries to retrieve information. None of these methods modify the state of the game.
 */
interface QueryAPI {

    /**
     * Retrieve information about modules available to be launched.
     *
     * Should be used when displaying the main menu and showing the player options of modules he can start the game on.
     */
    fun getAvailableModules(): AvailableModules

    /**
     * Retrieve information about the current contents of the player's equipment.
     */
    fun getEquipmentContent(): EquipmentContent

    /**
     * Retrieve information about a single item in the specified equipment [slot].
     */
    fun getSingleItemFromEquipment(slot: EquipmentSlot): EquipmentSingleItem

    /**
     * Retrieve information about the current contents of the player's equipment.
     */
    fun getInventoryContent(): InventoryContent

    /**
     * Retrieve information about a single item in the specified inventory [slot].
     */
    fun getSingleItemFromInventory(slot: InventorySlot): InventorySingleItem

    /**
     * Retrieve information about the current phase the game is in.
     */
    fun getCurrentGamePhase(): GamePhase
}