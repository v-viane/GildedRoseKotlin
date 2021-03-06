package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    /**
     * Create an object GildedRose of a list of items add apply updateQuality
     * @param items : the list of items to update
     */
    private fun updateItems(items: Array<Item>)
    {
        val app = GildedRose(items)
        app.updateQuality()
    }

    /**
     * Execute a test on a "normal" list of items
     */
    @Test fun normalUpdate() {
        val items = arrayOf(Item("+5 Dexterity Vest",10,20))
        val expectedItems = arrayOf(Item("+5 Dexterity Vest",9,19))

        updateItems(items)
        assertEquals(expectedItems.contentToString(), items.contentToString())
    }

    /**
     * Execute a test on the legendary item Sulfuras
     */
    @Test fun sulfurasUpdate() {

        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80),
                            Item("Sulfuras, Hand of Ragnaros", -1, 80))

        val expectedItems = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80),
                            Item("Sulfuras, Hand of Ragnaros", -1, 80))

        updateItems(items)
        assertEquals(items.contentToString(), expectedItems.contentToString())
    }

    /**
     * Execute a test on the item "Aged Brie"
     */
    @Test fun agedBrieUpdate() {

        val items = arrayOf(Item("Aged Brie", 1, 2), Item("Aged Brie", 2, 0))
        val expectedItems = arrayOf(Item("Aged Brie",0,3), Item("Aged Brie", 1, 1))

        updateItems(items)
        assertEquals(items.contentToString(), expectedItems.contentToString())
    }

    /**
     * Execute a test on the items "Backstage passes"
     */
    @Test fun backstagePassesUpdate() {

        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
                Item("Backstage passes to a TAFKAL80ETC concert", 0, 0))

        val expectedItems = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                Item("Backstage passes to a TAFKAL80ETC concert", 9, 22),
                Item("Backstage passes to a TAFKAL80ETC concert", 4, 23),
                Item("Backstage passes to a TAFKAL80ETC concert", -1, 0))

        updateItems(items)
        assertEquals(expectedItems.contentToString(), items.contentToString())
    }

    /**
     * Execute a test to check if the quality of the items is never negative
     */
    @Test fun nonNegativeUpdate()
    {
        val items = arrayOf(Item("+5 Dexterity Vest",10,0),
                            Item("+5 Dexterity Vest",5,1),
                            Item("Conjured Mana Cake", 0, 2))
        val negativeItems = arrayOf(Item("+5 Dexterity Vest",9,0),
                                    Item("+5 Dexterity Vest",4,0),
                                    Item("Conjured Mana Cake", -1, 0))

        updateItems(items)
        assertEquals(negativeItems.contentToString(), items.contentToString())
    }

    /**
     * Execute a test to check if the quality of the items is never more than 50
     */
    @Test fun notOver50Update()
    {
        val items = arrayOf(Item("Aged Brie", 2, 50),
                            Item("Backstage passes to a TAFKAL80ETC concert", 10, 50))
        val over50Items = arrayOf(Item("Aged Brie", 1, 50),
                                  Item("Backstage passes to a TAFKAL80ETC concert", 9, 50))

        updateItems(items)
        assertEquals(over50Items.contentToString(), items.contentToString())
    }

    /**
     * Execute a test to check if, when the sell by date has passed, Quality degrades twice as fast
     * Only for "Normals" items & "Aged Brie" (this one increase instead)
     */
    @Test fun datePassedUpdate()
    {
        val items = arrayOf(Item("+5 Dexterity Vest",0,20),
                            Item("Aged Brie", 0, 2))
        val expectedItems = arrayOf(Item("+5 Dexterity Vest",-1,18),
                                    Item("Aged Brie", -1, 4))

        updateItems(items)
        assertEquals(expectedItems.contentToString(), items.contentToString())
    }

    /**
     * Execute a test on the item "Conjured"
     */
    @Test fun conjuredItemUpdate()
    {
        val items = arrayOf(Item("Conjured Mana Cake", 3, 6),
                Item("Conjured Mana Cake", 0, 6))
        val expectedItems = arrayOf(Item("Conjured Mana Cake", 2, 4),
                Item("Conjured Mana Cake", -1, 2))

        updateItems(items)
        assertEquals(expectedItems.contentToString(), items.contentToString())
    }
}


