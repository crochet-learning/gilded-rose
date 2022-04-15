package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
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
        Item item = createAndUpdate(AGED_BRIE, 15, 25);
        assertEquals(26, item.quality);
    }

    @Test
    public void agedBrieNeverExpires() {
        Item item = createAndUpdate(AGED_BRIE, 0, 42);
        assertEquals(-1, item.sellIn);
        assertEquals(44, item.quality);

        item = createAndUpdate(AGED_BRIE, 0, MAXIMUM_QUALITY);
        assertEquals(MAXIMUM_QUALITY, item.quality);
    }

    @Test
    public void backstagePassMaximumQuality() {
        Item item = createAndUpdate(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 48);
        assertEquals(MAXIMUM_QUALITY, item.quality);

        item = createAndUpdate(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 49);
        assertEquals(MAXIMUM_QUALITY, item.quality);

        item = createAndUpdate(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 24);
        assertEquals(27, item.quality);
    }

    @Test
    public void whenSellInIsLessThanZero() {
        Item item = createAndUpdate("foo", 0, 2);
        assertEquals(0, item.quality);
    }

    @Test
    public void sulfurasHandOfRagnaros() {
        Item item = createAndUpdate(SULFURAS_HAND_OF_RAGNAROS, 0, 2);
        assertEquals(2, item.quality);
    }

    @Test
    public void backStagePassesSellInLessThanZero() {
        Item item = createAndUpdate(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 0, 24);
        assertEquals(0, item.quality);

        item = createAndUpdate(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 0, 0);
        assertEquals(0, item.quality);
    }

    @Test
    public void conjuredItemsDegradeTwiceAsFast() {
        Item item = createAndUpdate(GildedRose.CONJURED, 15, 25);
        assertEquals(23, item.quality);
    }

}
