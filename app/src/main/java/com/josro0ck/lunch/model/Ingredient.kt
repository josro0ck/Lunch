package com.josro0ck.lunch.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josro0ck.lunch.enum.RatioScale
import java.util.Date

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @PrimaryKey @ColumnInfo var name: String,
    @ColumnInfo var acquisitionDate: Date,
    @ColumnInfo var averageExpirationDays: Int,
    @ColumnInfo var scale: RatioScale
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readSerializable() as Date,
        parcel.readInt(),
        RatioScale.valueOf(parcel.readString()!!)
    )

    constructor() : this("", Date(), 0, RatioScale.QUANTITY)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeSerializable(acquisitionDate)
        parcel.writeInt(averageExpirationDays)
        parcel.writeString(scale.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}