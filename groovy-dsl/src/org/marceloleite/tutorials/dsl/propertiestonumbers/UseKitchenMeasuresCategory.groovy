package org.marceloleite.tutorials.dsl.propertiestonumbers

use(KitchenMeasuresCategory) {

    def use = { amount ->
        [ of: {
            ingredient ->
                println amount + " grams of " + ingredient
        }]
    }

    use 2.cups of "flour"
    use 1.teaspoons of "yeast"
    use 5.tablespoons of "sugar"
}