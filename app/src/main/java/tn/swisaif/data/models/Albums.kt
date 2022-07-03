package tn.swisaif.data.models

class Albums : ArrayList<AlbumsItem>()

data class AlbumsItem(
    val id: Int,
    val title: String,
    val userId: Int
)