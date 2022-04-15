package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int MAXIMUM_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (isNormalItem(item)) {
                handleNormalItem(item);
            } else if (isAgedBrie(item)) {
                handleAgedBrie(item);
            } else if (isBackstagePass(item)) {
                handleBackStagePass(item);
            } else {
                if (!(isAgedBrie(item)
                    || isBackstagePass(item))) {
                    if (item.quality > 0) {
                        if (!isHandOfRagnaros(item)) {
                            item.quality--;
                        }
                    }
                }
            }
        }
    }

    private void handleAgedBrie(Item item) {
        if (item.quality == MAXIMUM_QUALITY) {
        } else if (item.sellIn <= 0) {
            item.quality = item.quality + 2;
        } else {
            item.quality++;
        }
        item.sellIn--;
    }

    private void handleBackStagePass(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn < 6) {
            item.quality = item.quality + 3;
        } else if (item.sellIn < 11) {
            item.quality = item.quality + 2;
        } else if (item.sellIn >= 11) {
            item.quality++;
        }

        if (item.quality > MAXIMUM_QUALITY) {
            item.quality = MAXIMUM_QUALITY;
        }
    }

    private void handleNormalItem(Item item) {
        item.sellIn--;
        if (item.sellIn <= 0) {
            item.quality = item.quality - 2;
        } else {
            item.quality--;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private boolean isNormalItem(Item item) {
        return !(isAgedBrie(item) || isBackstagePass(item) || isHandOfRagnaros(item));
    }

    private boolean isHandOfRagnaros(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}
