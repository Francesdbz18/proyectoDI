package com.chesco.proyecto_di

data class Ciudad(
    val nombre: String,
    val emergencias: String,
    val policia: String,
    val bomberos: String,
    val oficinaInformacionTurismo: String,
    val ayuntamiento: String,
    val servicioTaxi: String,
    val oficina: String,
    val contacto: Contacto
)

data class Contacto(
    val nombre: String,
    val telefono: String,
    val email: String
)

val ciudades = listOf(
    Ciudad(
        "Madrid",
        "112",
        "091",
        "080",
        "+34 914 201 314",
        "+34 915 883 300",
        "+34 915 474 700",
        "+34 913 423 600",
        Contacto("Antonio Avellaneda", "+34 913 423 634", "aavellaneda@splatnot.com")
    ),
    Ciudad(
        "París",
        "112",
        "17",
        "18",
        "+33 1 49 52 42 63",
        "+33 1 42 76 60 00",
        "+33 1 45 30 30 30",
        "+33 1 45 26 20 30",
        Contacto("François Merlin", "+33 1 45 26 22 46", "fmerlin@splatnot.com")
    ),
    Ciudad(
        "Londres",
        "999",
        "101",
        "999",
        "+44 20 7344 1000",
        "+44 20 7983 4000",
        "+44 20 7272 0272",
        "+44 20 2536 0200",
        Contacto("Sarah Louise Taylor", "+44 20 2536 0232", "staylor@splatnot.com")
    ),
    Ciudad(
        "Porto Alegre",
        "190 (Policía), 193 (Bomberos)",
        "190",
        "193",
        "+55 51 3289 4285",
        "+55 51 3289 1027",
        "+55 51 3211 1188",
        "+55 51 5644 1000",
        Contacto("Maria Fernanda Oliveira Costa", "+55 51 5644 1688", "mfoliveira@splatnot.com")
    ),
    Ciudad(
        "Acapulco",
        "911",
        "911",
        "911",
        "+52 744 482 2855",
        "+52 744 482 1400",
        "+52 744 485 1073",
        "+52 744 779 1000",
        Contacto("Antonio Avellaneda", "+52 744 779 1948", "aavellaneda@splatnot.com")
    ),
    Ciudad(
        "Vancouver",
        "911",
        "911",
        "911",
        "+1 604 482 2888",
        "+1 604 873 7000",
        "+1 604 681 1111",
        "+34 913 423 600",
        Contacto("David Miller", "+34 913 423 634", "dmiller@splatnot.com")
    ),
    Ciudad(
        "Houston",
        "911",
        "713 884 3131",
        "911",
        "+1 713 437 5240",
        "+1 713 247 1000",
        "+1 713 236 1111",
        "+1 713 555 1222",
        Contacto("Robinson Hill", "+1 713 555 1291", "rhill@splatnot.com")
    ),
    Ciudad(
        "Casablanca",
        "19 (Policía), 15 (Bomberos)",
        "19",
        "15",
        "+212 522 225 410",
        "+212 522 235 157",
        "+212 522 252 014",
        "+212 522 449 000",
        Contacto("Ahmed Ben Youssef El Fassi", "+212 522 449 644", "abenyoussef@splatnot.com")
    ),
    Ciudad(
        "Osaka",
        "110 (Policía), 119 (Bomberos y Ambulancias)",
        "110",
        "119",
        "+81 6 6345 3301",
        "+81 6 6208 7171",
        "+81 6 6345 1234",
        "+81 6 4882 6600",
        Contacto("Takahashi Hiroshi", "+81 6 4882 6632", "thiroshi@splatnot.com")
    ),
    Ciudad(
        "Melbourne",
        "000",
        "000",
        "000",
        "+61 3 9658 9658",
        "+61 3 9658 9658",
        "+61 3 8413 7300",
        "+61 3 9974 9600",
        Contacto("Emily Johnson", "+61 3 9974 9677", "ejohnson@splatnot.com")
    ),
    Ciudad(
        "Ankara",
        "112",
        "155",
        "110",
        "+90 312 310 13 55",
        "+90 312 507 10 00",
        "+90 312 444 75 47",
        "+90 312 822 70 00",
        Contacto("Elif Demir", "+90 312 822 70 94", "edemir@splatnot.com")
    ),
    Ciudad(
        "Dubai",
        "999",
        "999",
        "997",
        "+971 4 201 5555",
        "+971 4 406 5555",
        "+971 4 208 0808",
        "+971 4 495 7000",
        Contacto("Khalid Al Maktoum", "+971 4 495 7556", "kalmaktoum@splatnot.com")
    )
)

//Mapa que relaciona las ciudades con su imagen.
val cityImages = mapOf(
    "Madrid" to R.drawable.madrid_image,
    "París" to R.drawable.paris_image,
    "Londres" to R.drawable.london,
    "Porto Alegre" to R.drawable.porto_alegre_image,
    "Acapulco" to R.drawable.acapulco_image,
    "Vancouver" to R.drawable.vancouver_image,
    "Houston" to R.drawable.houston_image,
    "Casablanca" to R.drawable.casablanca_image,
    "Osaka" to R.drawable.osaka_image,
    "Melbourne" to R.drawable.melbourne_image,
    "Ankara" to R.drawable.ankara_image,
    "Dubai" to R.drawable.dubai_image
)
