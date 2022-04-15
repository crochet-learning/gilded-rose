package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item createAndUpdate(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    void testFrameworkWorks() {
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals("foo", item.name);
    }

    @Test
    public void systemLowersValues() {
        Item item = createAndUpdate("foo", 15, 25);
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    public void qualityDegradesTwiceAsFast() {
        Item item = createAndUpdate("foo", 0, 17);
        assertEquals(15, item.quality);
    }

    @Test
    public void qualityIsNeverNegative() {
        Item item = createAndUpdate("foo", 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesInQuality() {
        Item item = createAndUpdate("Aged Brie", 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void agedBrieNeverExpires() {
        Item item = createAndUpdate("Aged Brie", 0, 42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);
    }

    @Test
    public void backstagePassMaximumQuality() {
        Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 48);
        assertEquals(50, item.quality);

        item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        assertEquals(50, item.quality);
    }

}
