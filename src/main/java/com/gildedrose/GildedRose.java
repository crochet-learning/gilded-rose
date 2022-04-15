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
            if (!(isAgedBrie(item)
                || isBackstagePass(item))) {
                if (item.quality > 0) {
                    if (!isHandOfRagnaros(item)) {
                        item.quality--;
                    }
                }
            } else {
                if (item.quality < MAXIMUM_QUALITY) {
                    item.quality++;

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < MAXIMUM_QUALITY) {
                                item.quality++;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < MAXIMUM_QUALITY) {
                                item.quality++;
                            }
                        }
                    }
                }
            }

            if (!isHandOfRagnaros(item)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < MAXIMUM_QUALITY) {
                        item.quality++;
                    }
                } else {
                    if (isBackstagePass(item)) {
                        item.quality = 0;
                    } else {
                        if (item.quality > 0) {
                            if (!isHandOfRagnaros(item)) {
                                item.quality--;
                            }
                        }
                    }
                }
            }
        }
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
