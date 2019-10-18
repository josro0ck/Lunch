package com.josro0ck.lunch.repository

object Catalog {

    private val _myCatalog = mutableListOf<String>()

    val myCatalog: List<String>
        get() = _myCatalog

    init {
        //Spices
        _myCatalog.add("Salt")
        _myCatalog.add("Pepper")
        _myCatalog.add("Garlic Salt")
        _myCatalog.add("Onion Powder")
        _myCatalog.add("Chicken Broth")
//        _myCatalog.add("")

        //Vegetables
        _myCatalog.add("Onion")
        _myCatalog.add("Tomato")
        _myCatalog.add("Garlic")
        _myCatalog.add("Jalapeño")
        _myCatalog.add("Serrano Pepper")
        _myCatalog.add("Cilantro")
//        _myCatalog.add("")

        //Seeds
        _myCatalog.add("Pinto Beans")
        _myCatalog.add("Short grain rice")
        _myCatalog.add("Long grain rice")
//        _myCatalog.add("")

        //Meat
        _myCatalog.add("Bacon")
        _myCatalog.add("Smoked Salmon")
        _myCatalog.add("Chicharrón")
        _myCatalog.add("Egg")
//        _myCatalog.add("")

        //Dairy
        _myCatalog.add("Evaporated Milk")
        _myCatalog.add("Condensed Milk")
        _myCatalog.add("Cream Cheese")
        _myCatalog.add("Milk")
        _myCatalog.add("Butter")
//        _myCatalog.add("")

        //Extracts
        _myCatalog.add("Vanilla Extract")
//        _myCatalog.add("")

        //Powdered
        _myCatalog.add("All purpose Flour")
        _myCatalog.add("Baking Powder")
        _myCatalog.add("Sugar")
//        _myCatalog.add("")
    }

}