package org.marceloleite.tutorials.dsl.propertiestonumbers

@Category(Integer)
class KitchenMeasuresCategory {

    Integer getCup() {
        this * 240
    }

    Integer getCups() {
        cup
    }

    Integer getTablespoon() {
        this * 18
    }

    Integer getTablespoons() {
        tablespoon
    }

    Integer getTeaspoon() {
        this * 8
    }

    Integer getTeaspoons() {
        teaspoon
    }
}
